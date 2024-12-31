package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.RequestItem;
import lombok.*;
import java.text.NumberFormat;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class RequestItemDto {
    private int itemNumber;
    private String productName;
    private int quantity;
    private String subtotal;
    private String section;

    public RequestItemDto(RequestItem item, int itemNumber){
        this.itemNumber = itemNumber;
        this.productName = item.getProduct().getNameProduct();
        this.quantity = item.getQuantity();
        this.subtotal = formatCurrency(item.getSubtotal());
        this.section = item.getProduct().getProductGroup().getGroupSection().getName();
    }

    private String formatCurrency(double value){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(value);
    }
}
