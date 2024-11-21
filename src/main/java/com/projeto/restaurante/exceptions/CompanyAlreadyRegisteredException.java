package com.projeto.restaurante.exceptions;

public class CompanyAlreadyRegisteredException extends ErrorResponse {
    public CompanyAlreadyRegisteredException(){
        super("Já existe uma empresa cadastrada!", 409);
    }
}
