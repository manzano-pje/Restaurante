package com.projeto.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestMessageDto {

    private int tableNumber; // Número da mesa
    private long requestNumber; // Número do pedido
    private String productName; // Nome do produto
    private int quantity; // Quantidade
    private String section; // Seção (kitchen, bar, blank)
}
