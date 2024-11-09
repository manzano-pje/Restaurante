package com.projeto.restaurante.identities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int productGroup;
    private double salePrice;
    private double costPrice;
    private UnidadeDeMedida unidadeDeMedida;

    /********** RELATIONS**********/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private Group group;



}
