package com.projeto.restaurante.exceptions;

public class UnregisteredSeatingException extends ErrorResponse{
    public UnregisteredSeatingException(){
        super("Mesa não cadastrada", 404);
    }
}
