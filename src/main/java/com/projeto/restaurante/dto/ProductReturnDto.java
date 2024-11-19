package com.projeto.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.restaurante.identities.Group;
import com.projeto.restaurante.identities.Product;
import com.projeto.restaurante.identities.UnidadeDeMedida;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductReturnDto {
    private int id;
    private String nameProduct;
    private Group productGroup;
    private double salePrice;
    private double costPrice;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDeMedida;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date registrationDate;
    private int stock;
    private int minimumStock;


    public ProductReturnDto(Product product){
        this.nameProduct = product.getNameProduct();
        this.productGroup = product.getGroup();
        this.salePrice = product.getSalePrice();
        this.costPrice = product.getCostPrice();
        this.unidadeDeMedida = product.getUnidadeDeMedida();
        this.registrationDate = product.getRegistrationDate();
        this.stock = product.getStock();
        this.minimumStock = product.getMinimumStock();
    }
}



