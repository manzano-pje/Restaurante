package com.projeto.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {

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
