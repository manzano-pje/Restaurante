package com.projeto.restaurante.identities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameProduct;
    private int productGroup;
    private double salePrice;
    private double costPrice;
    private UnidadeDeMedida unidadeDeMedida;

    /******** RELATIONS ********/

    @ManyToOne
    @JoinColumn(name = "name")
    private Group group;

}
