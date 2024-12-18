package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.RequestItem;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class RequestItemDto {
    private int itemNumber;
    private String productName;
    private int quantity;
    private double subtotal;
    private String section;

    public RequestItemDto(RequestItem item, int itemNumber){
        System.out.println("Criando DTO para o item: " + item);
        this.itemNumber = itemNumber;
        this.productName = item.getProduct().getNameProduct();
        this.quantity = item.getQuantity();
        this.subtotal = item.getSubtotal();
        this.section = item.getProduct().getProductGroup().getGroupSection().getName();
    }
    public RequestItemDto(RequestItem requestItem) {
//        System.out.println("Criando DTO para o item - requestItem: " + requestItem);
    }
}
