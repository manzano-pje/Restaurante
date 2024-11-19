package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Company;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    public CompanyDto(Company company) {
        this.name = company.getName();
        this.adress = company.getAdress();
        this.number = company.getNumber();
        this.complement = company.getComplement();
        this.neighborhood = company.getNeighborhood();
        this.city = company.getCity();
        this.uf = company.getUf();
        this.zipcode = company.getZipcode();
        this.cnpj = company.getCnpj();
        this.email = company.getEmail();
        this.phone = company.getPhone();
    }
}
