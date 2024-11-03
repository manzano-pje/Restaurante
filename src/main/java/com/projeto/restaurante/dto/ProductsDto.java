package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.UnidadeDeMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductsDto {
    private int id;
    private String name;
    private int group;
    private double salePrice;
    private double costPrice;
    private UnidadeDeMedida unidadeDeMedida;
}
