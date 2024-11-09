package com.projeto.restaurante.exceptions;

public class SeatingAlreadyRegisteredException extends ErrorResponse {
    public SeatingAlreadyRegisteredException(){
        super("Mesa já cadastrada", 409);
    }
}
