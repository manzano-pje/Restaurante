package com.projeto.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.RequestItem;
import com.projeto.restaurante.identities.Seating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClosingSeatingDto {

    private String seatingName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - hh:mm")
    private Date openingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - hh:mm")
    private Date closingDate;
    private double total;
    
    private List<RequestItemDto> itens = new ArrayList<>();

    public ClosingSeatingDto(double total, String seatingName, List<RequestItemDto> itens){
        this.openingDate = getOpeningDate();
        this.closingDate = new Date();
        this.total = total;
        this.seatingName = seatingName;
        this.itens = (List<RequestItemDto>) itens.stream().collect(Collectors.groupingBy(RequestItemDto::getProductName));

    }
}

/*
retornar os dados ( nome da mesa, hora de abertura, hora de fechamento,
    itens do pedido, quantidade dos itens, valor total dos itens, valor total da mesa).
 */