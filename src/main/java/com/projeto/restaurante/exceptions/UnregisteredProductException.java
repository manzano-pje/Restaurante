package com.projeto.restaurante.exceptions;

public class UnregisteredProductException extends ErrorResponse{
    public UnregisteredProductException(){
        super("Produto não cadastrado!", 404);
    }
}
