package com.projeto.restaurante.exceptions;

public class UnregisteredAttendantExceptoin extends ErrorResponse {
    public UnregisteredAttendantExceptoin(){
        super("Atendente não cadastrado!", 404);
    }
}
