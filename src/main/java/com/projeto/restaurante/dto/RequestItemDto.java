package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.RequestItem;
import lombok.*;

import java.util.List;

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

    public RequestItemDto(int itemNumber, String key,int totalQuantity,double totalSubtotal,String section, String productName ){
        this.itemNumber = itemNumber;
        this.productName = productName;
        this.quantity = totalQuantity;
        this.subtotal = totalSubtotal;
        this.section = section;
    }
    public RequestItemDto(RequestItem item, int itemNumber){
        this.itemNumber = itemNumber;
        this.productName = item.getProduct().getNameProduct();
        this.quantity = item.getQuantity();
        this.subtotal = item.getSubtotal();
        this.section = item.getProduct().getProductGroup().getGroupSection().getName();
    }
}
