package com.projeto.restaurante.identities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private double salePrice;
    private double costPrice;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDeMedida;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date registrationDate;
    private int stock;
    private int minimumStock;

    /******** RELATIONS ********/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private Group productGroup;

    @OneToMany (mappedBy = "product",cascade = CascadeType.ALL)
    private List<RequestItem> products = new ArrayList<>();
}