package com.projeto.restaurante.exceptions;

public class UnregisteredAttendantException extends ErrorResponse {
    public UnregisteredAttendantException(){
        super("Atendente não cadastrado!", 404);
    }
}
