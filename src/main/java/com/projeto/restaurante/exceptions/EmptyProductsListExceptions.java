package com.projeto.restaurante.exceptions;

public class EmptyProductsListExceptions extends ErrorResponse {
    public EmptyProductsListExceptions(){
        super("Não existem produtos cadastrados!",404);
    }
}
