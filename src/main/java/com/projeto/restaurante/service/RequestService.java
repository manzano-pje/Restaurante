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
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * request service
 */
@Api(tags = "  request service ")
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
    public ReturnRequestDto create(RequestInputDto requestInputDto) {
        Optional<Seating> seatingOptional = seatingRepository.findById(requestInputDto.getSeatingId());
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }

        Optional<Attendant> attendant = attendantRepositpry.findById(requestInputDto.getAttendantId());
        if (attendant.isEmpty()) {
            throw new UnregisteredAttendantException();
        }
        long requestNumber = requestRepository.count() + 1;

        Request request = new Request();
        request.setRequestAttendant(attendant.get());
        request.setRequestSeating(seatingOptional.get()); // mesa
        request.setTotal(0.0);
        request.setRequestNumber(requestNumber); // pedido
        request.setOpeningDate(new Date());
        request.setStatus(true);
        request.getRequestSeating().setStatus(true);
        requestRepository.save(request);

        seatingOptional.get().setStatus(true);
        seatingRepository.save(seatingOptional.get());

        for (RequestItemInputDto itemDto : requestInputDto.getItens()) {
            Optional<Product> productOptional = productRepository.findById(itemDto.getProductId());
            if (productOptional.isEmpty()) {
                throw new UnregisteredProductException();
            }
            Product product = productOptional.get();
            RequestItem item = new RequestItem();
            item.setProduct(product); // produto
            item.setQuantity(itemDto.getQuantity()); // quantidade
            item.setSubtotal(product.getSalePrice() * itemDto.getQuantity());
            item.setRequest(request); // associa o item a lista de itens do pedido
            request.getItens().add(item);
        }

        double total = request.getItens().stream().mapToDouble(RequestItem::getSubtotal).sum();
        request.setTotal(total);
        request = requestRepository.save(request);
        requestProducer.sendToSection(request);
        return mapper.map(request, ReturnRequestDto.class);
    }

    @Transactional
    public ClosingSeatingDto ClosingRequestSeating(String seatingName) {
        Seating seating = seatingRepository.findByName(seatingName)
                .orElseThrow(UnregisteredSeatingException::new);

        // Validar se mesa está aberta
        if (!seating.isStatus()) {
            throw new UnregisteredRequestException();
        }

        // Buscar pedidos ativos associados à mesa
        List<Request> requestList = requestRepository.findRequestsBySeatingIdAndStatusTrue(seating.getId());
        if (requestList.isEmpty()) {
            throw new UnregisteredRequestException();
        }

        Map<String, RequestItemDto> groupedItems = new HashMap<>();
        // calcular o total do pedido e gerar lista de itens dos pedidos da mesa
        double total = 0.0;
        int numeroItem = 1;

        List<RequestItemDto> requestItems;
        for (Request request : requestList) {
            for (RequestItem item : request.getItens()) {
                total += item.getSubtotal();

                // Verifica se item já foi adicionado
                String productName = item.getProduct().getNameProduct();

                if (groupedItems.containsKey(productName)) {
                    // Atualiza quantidade e subtotal do item agrupado
                    RequestItemDto existingItem = groupedItems.get(productName);
                    existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
//                    existingItem.setSubtotal(existingItem.getSubtotal() + item.getSubtotal());
                    double updatedSubtotal = parseCurrency(existingItem.getFormattedSubtotal()) + item.getSubtotal();
                    existingItem.setFormattedSubtotal(existingItem.formatCurrency(updatedSubtotal));
                } else {
                    // Adiciona novo item ao mapa
                    groupedItems.put(productName, new RequestItemDto(item,numeroItem));
                }
            }
            request.setStatus(false);
        }
        // Atualização do status da mesa para fechada
        requestItems = new ArrayList<>(groupedItems.values());
        // montar o DTO do fechamento
        return new ClosingSeatingDto(seatingName,
                total,
                requestList.get(0).getOpeningDate(),
                new Date(),
                requestItems
        );
    }

    private double parseCurrency(String formattedValue) {
        try {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return formatter.parse(formattedValue).doubleValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao converter moeda para número: " + formattedValue, e);
        }
    }
}

