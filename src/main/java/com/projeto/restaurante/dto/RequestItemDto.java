package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.RequestItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestItemDto {
    private int productId;
    private String productName;
    private int quantity;

    public RequestItemDto(RequestItem requestItem){
        this.productId = requestItem.getId();
        this.productName = requestItem.getProduct().getNameProduct();
        this.quantity = requestItem.getQuantity();
    }
}
