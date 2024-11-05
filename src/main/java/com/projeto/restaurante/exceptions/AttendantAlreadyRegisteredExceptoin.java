package com.projeto.restaurante.exceptions;

public class AttendantAlreadyRegisteredExceptoin extends ErrorResponse {
    public AttendantAlreadyRegisteredExceptoin(){
        super("Atendente já cadastrado!", 409);
    }
}
