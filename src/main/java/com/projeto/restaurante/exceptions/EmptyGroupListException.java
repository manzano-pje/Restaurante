package com.projeto.restaurante.exceptions;

public class EmptyGroupListException extends ErrorResponse {
    public EmptyGroupListException(){
        super("Não existem grupos cadastrados",404);
    }
}
