package com.projeto.restaurante.exceptions;

public class UnregisteredCompanyException extends ErrorResponse{
    public UnregisteredCompanyException(){
        super("Empresa não cadastrada!", 404);
    }
}
