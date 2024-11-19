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
    private int productGroup;
    private double salePrice;
    private double costPrice;
    private UnidadeDeMedida unidadeDeMedida;
    private int stock;
    private int minimumStock;

    public ProductDto(Product product) {
        this.nameProduct = product.getNameProduct();
        this.productGroup = product.getGroup().getId();
        this.salePrice = product.getSalePrice();
        this.costPrice = product.getCostPrice();
        this.unidadeDeMedida = product.getUnidadeDeMedida();
        this.stock = product.getStock();
        this.minimumStock = product.getMinimumStock();
    }
}
