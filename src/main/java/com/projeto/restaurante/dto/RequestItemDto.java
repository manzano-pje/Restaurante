package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.RequestItem;
import lombok.*;

import java.util.Currency;

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
    private Currency subtotal1;

    public RequestItemDto(RequestItem item, int itemNumber){
        this.itemNumber = itemNumber;
        this.productName = item.getProduct().getNameProduct();
        this.quantity = item.getQuantity();
        this.subtotal = item.getSubtotal();
        this.section = item.getProduct().getProductGroup().getGroupSection().getName();
    }
}
