package com.projeto.restaurante.identities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String adress;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String uf;
    private String zipcode;
    private String cnpj;
    private String email;
    private String phone;
}
