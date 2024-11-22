package com.projeto.restaurante.exceptions;

public class AlreadySectionRegisteredException extends ErrorResponse {
    public AlreadySectionRegisteredException(){
        super("Seção já cadastrada!", 409);
    }
}
