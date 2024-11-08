package com.projeto.restaurante.exceptions;

public class AttendantAlreadyRegisteredException extends ErrorResponse {
    public AttendantAlreadyRegisteredException(){

        super("Atendente já cadastrado!", 409);
    }
}
