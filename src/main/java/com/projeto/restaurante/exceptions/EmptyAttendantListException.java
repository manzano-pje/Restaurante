package com.projeto.restaurante.exceptions;

public class EmptyAttendantListException extends ErrorResponse{
    public EmptyAttendantListException(){
        super("Não existem atendentes cadastrados!",404);
    }
}
