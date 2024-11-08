package com.projeto.restaurante.exceptions;

public class UnregisteredGroupException extends ErrorResponse {
    public UnregisteredGroupException(){
        super("Grupo não cadastrado!",404);
    }
}
