package com.projeto.restaurante.exceptions;

public class UnregisteredRequestException extends ErrorResponse {
    public UnregisteredRequestException(){
        super("Não existem pedidos abertos para esta mesa", 404);
    }
}
