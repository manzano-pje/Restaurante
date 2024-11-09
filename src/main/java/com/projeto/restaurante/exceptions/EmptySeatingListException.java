package com.projeto.restaurante.exceptions;

public class EmptySeatingListException extends ErrorResponse{
    public EmptySeatingListException(){
        super("Não existem mesas cadastradas",404);
    }
}
