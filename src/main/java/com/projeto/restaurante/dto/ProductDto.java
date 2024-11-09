package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Product;
import com.projeto.restaurante.identities.UnidadeDeMedida;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private int id;
    private String nameProduct;
    private int group;
    private double salePrice;
    private double costPrice;
    private UnidadeDeMedida unidadeDeMedida;

    public ProductDto(Product product) {
        this.nameProduct = product.getNameProduct();
        this.group = product.getProductGroup();
        this.salePrice = product.getSalePrice();
        this.costPrice = product.getCostPrice();
        this.unidadeDeMedida = product.getUnidadeDeMedida();
    }
}
