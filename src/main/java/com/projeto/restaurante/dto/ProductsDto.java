package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Product;
import com.projeto.restaurante.identities.UnidadeDeMedida;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsDto {
    private int id;
    private String name;
    private int group;
    private double salePrice;
    private double costPrice;
    private UnidadeDeMedida unidadeDeMedida;

    public ProductsDto(Product product) {
        this.name = product.getName();
        this.group = product.getProductGroup();
        this.salePrice = product.getSalePrice();
        this.costPrice = product.getCostPrice();
        this.unidadeDeMedida = product.getUnidadeDeMedida();
    }
}
