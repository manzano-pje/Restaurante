package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.RequestItem;
import lombok.*;

@Getter
@Setter
public class RequestItemDto {
    private int itemNumber;
    private String productName;
    private int quantity;
    private double subtotal;

    public RequestItemDto(RequestItem item, int itemNumber){
        this.itemNumber = itemNumber;
        this.productName = item.getProduct().getNameProduct();
        this.quantity = item.getQuantity();
        this.subtotal = item.getSubtotal();
    }
}
