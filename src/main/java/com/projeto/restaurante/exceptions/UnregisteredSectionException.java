package com.projeto.restaurante.exceptions;

public class UnregisteredSectionException extends ErrorResponse {
    public UnregisteredSectionException(){
        super("Categoria inexistente!", 404);
    }
}
