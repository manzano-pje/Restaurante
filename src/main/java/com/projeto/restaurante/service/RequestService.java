package com.projeto.restaurante.service;

import com.projeto.restaurante.dto.RequestInputDto;
import com.projeto.restaurante.dto.RequestItemInputDto;
import com.projeto.restaurante.dto.ReturnRequestDto;
import com.projeto.restaurante.exceptions.UnregisteredAttendantException;
import com.projeto.restaurante.exceptions.UnregisteredProductException;
import com.projeto.restaurante.exceptions.UnregisteredSeatingException;
import com.projeto.restaurante.identities.*;
import com.projeto.restaurante.repository.AttendantRepositpry;
import com.projeto.restaurante.repository.ProductRepository;
import com.projeto.restaurante.repository.RequestRepository;
import com.projeto.restaurante.repository.SeatingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestService {

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
        pedido.setRequestSeating(seatingOptional.get());
        pedido.setTotal(0.0);
        pedido.setRequestNumber(numeroPedido);
        if(!seatingOptional.get().isStatus()){
            pedido.setOpeningDate(new Date());
            seatingOptional.get().setStatus(true);
            seatingRepository.save(seatingOptional.get());
        }

        requestRepository.save(pedido);

        for(RequestItemInputDto itemDto : requestInputDto.getItens()){
            Optional<Product> productOptional = productRepository.findById(itemDto.getProductId());
            if(productOptional.isEmpty()){
                throw new UnregisteredProductException();
            }
            Product product = productOptional.get();
            RequestItem item = new RequestItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setSubtotal(product.getSalePrice() * itemDto.getQuantity());
            item.setRequest(pedido); // associa o item a lista de itens do pedido

            pedido.getItens().add(item);
        }

        double total = pedido.getItens().stream().mapToDouble(RequestItem::getSubtotal).sum();
        pedido.setTotal(total);
        pedido = requestRepository.save(pedido);

        return mapper.map(pedido, ReturnRequestDto.class);
    }

    public List<ReturnRequestDto> listRequestBySeating(int seating){
        List<Request> requestList = requestRepository.findRequestsBySeatingIdAndStatusTrue(seating);
        if(requestList.isEmpty()){
            throw new UnregisteredProductException();
        }
        return requestList.stream().
                map(ReturnRequestDto::new).
                collect(Collectors.toList());
    }
}
