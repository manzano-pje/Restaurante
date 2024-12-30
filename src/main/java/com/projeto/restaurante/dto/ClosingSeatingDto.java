package com.projeto.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClosingSeatingDto {

    private String seatingName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm")
    private Date openingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm")
    private Date closingDate;
    private double total;
    private List<RequestItemDto> itens = new ArrayList<>();

    public ClosingSeatingDto(double total, String seatingName, List<RequestItemDto> itens){
        this.openingDate = getOpeningDate();
        this.closingDate = new Date();
        this.total = total;
        this.seatingName = seatingName;
        AtomicInteger numItem = new AtomicInteger(1);
        this.itens = itens.stream()
                .collect(Collectors.groupingBy(RequestItemDto::getProductName))
                .entrySet()
                .stream()
                .map(entry -> {
                    List<RequestItemDto> groupedItems = entry.getValue();
                    String productName = groupedItems.stream().map(RequestItemDto::getProductName).findFirst().get();
                    int totalQuantity = groupedItems.stream().mapToInt(RequestItemDto::getQuantity).sum();
                    double totalSubtotal = groupedItems.stream().mapToDouble(RequestItemDto::getSubtotal).sum();
                    String section = groupedItems.get(0).getSection();// Assume que a seção é a mesma para itens com o mesmo nome.
                    return new RequestItemDto(numItem.getAndIncrement(), entry.getKey(), totalQuantity, totalSubtotal, section, productName);
                })
                .collect(Collectors.toList());
    }
}