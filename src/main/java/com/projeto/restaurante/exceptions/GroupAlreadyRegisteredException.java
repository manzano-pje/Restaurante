package com.projeto.restaurante.exceptions;

public class GroupAlreadyRegisteredException extends ErrorResponse {
    public GroupAlreadyRegisteredException(){
        super("Grupo já cadastrado!",409);
    }
}
