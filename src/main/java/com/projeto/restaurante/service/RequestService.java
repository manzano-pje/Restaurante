package com.projeto.restaurante.service;

import com.projeto.restaurante.dto.*;
import com.projeto.restaurante.exceptions.UnregisteredAttendantException;
import com.projeto.restaurante.exceptions.UnregisteredProductException;
import com.projeto.restaurante.exceptions.UnregisteredRequestException;
import com.projeto.restaurante.exceptions.UnregisteredSeatingException;
import com.projeto.restaurante.identities.*;
import com.projeto.restaurante.rabbitMQ.RequestProducer;
import com.projeto.restaurante.repository.AttendantRepositpry;
import com.projeto.restaurante.repository.ProductRepository;
import com.projeto.restaurante.repository.RequestRepository;
import com.projeto.restaurante.repository.SeatingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RequestService {

    @Autowired
    private RequestProducer requestProducer; // Injeta o producer
    private final RequestRepository requestRepository;
    private final AttendantRepositpry attendantRepositpry;
    private final ProductRepository productRepository;
    private final SeatingRepository seatingRepository;
    private final ModelMapper mapper;

    @Transactional
    public ReturnRequestDto create(RequestInputDto requestInputDto){
        Optional<Seating> seatingOptional = seatingRepository.findById(requestInputDto.getSeatingId());
        if(seatingOptional.isEmpty()){
            throw new UnregisteredSeatingException();
        }

        Optional<Attendant> attendant = attendantRepositpry.findById(requestInputDto.getAttendantId());
        if(attendant.isEmpty()){
            throw new UnregisteredAttendantException();
        }
        long numeroPedido = requestRepository.count()+1;

        Request pedido = new Request();
        pedido.setRequestAttendant(attendant.get());
        pedido.setRequestSeating(seatingOptional.get()); // mesa
        pedido.setTotal(0.0);
        pedido.setRequestNumber(numeroPedido); // pedido
        if(!seatingOptional.get().isStatus()){
            pedido.setOpeningDate(new Date());
        }
        seatingOptional.get().setStatus(true);
        seatingRepository.save(seatingOptional.get());

        for(RequestItemInputDto itemDto : requestInputDto.getItens()){
            Optional<Product> productOptional = productRepository.findById(itemDto.getProductId());
            if(productOptional.isEmpty()){
                throw new UnregisteredProductException();
            }
            Product product = productOptional.get();
            RequestItem item = new RequestItem();
            item.setProduct(product); // produto
            item.setQuantity(itemDto.getQuantity()); // quantidade
            item.setSubtotal(product.getSalePrice() * itemDto.getQuantity());
            item.setRequest(pedido); // associa o item a lista de itens do pedido

            pedido.getItens().add(item);
        }

        double total = pedido.getItens().stream().mapToDouble(RequestItem::getSubtotal).sum();
        pedido.setTotal(total);
        pedido = requestRepository.save(pedido);
        requestProducer.sendToSection(pedido);
        return mapper.map(pedido, ReturnRequestDto.class);
    }


    /*
    gerar retorno em branco dos dados agrupados por item:
    number do item
    Nome do produto
    quantidade
    subtotal
    total geral

     */

    @Transactional
    public ClosingSeatingDto ClosingRequestSeating(String seatingName){
        Optional<Seating> seatingOptional = seatingRepository.findByName(seatingName);
        if(seatingOptional.isEmpty()){
            throw new UnregisteredSeatingException();
        }

        Seating seating = seatingOptional.get();

        // Validar se mesa está aberta
        if(!seating.isStatus()){
          throw new UnregisteredRequestException();
        }

        // Buscar pedidos ativos associados à mesa
        List<Request> requestList = requestRepository.findRequestsBySeatingIdAndStatusTrue(seating.getId());
        if(requestList.isEmpty()){
            throw new UnregisteredRequestException();
        }
        System.out.println(requestList);

        // calcular o total do pedido e gerar lista de itens dos pedidos da mesa
        double total = 0.0;
        int numeroItem = 1;
        List<RequestItemDto> requestItem = new ArrayList<>();

        for(Request request : requestList ){
            for (RequestItem item : request.getItens()){
                total += item.getSubtotal();
                requestItem.add(new RequestItemDto(item, numeroItem++));
            }
        }

        // Obter data de abertura da mesa
        Date openingDate = requestList.get(0).getOpeningDate();

        // Atualização do status da mesa
        seating.setStatus(false);
        seatingRepository.save(seating);

        // criar o DTO do fechamento


        ClosingSeatingDto closingSeatingDto = new ClosingSeatingDto();
        closingSeatingDto.setSeatingName(seating.getName());
        closingSeatingDto.setOpeningDate(openingDate);
        closingSeatingDto.setTotal(total);
        closingSeatingDto.setItens(requestItem);
        return closingSeatingDto;

    }
}

