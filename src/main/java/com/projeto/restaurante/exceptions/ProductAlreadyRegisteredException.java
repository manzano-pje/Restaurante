package com.projeto.restaurante.exceptions;

public class ProductAlreadyRegisteredException extends ErrorResponse {
    public ProductAlreadyRegisteredException(){
        super("Produto já cadastrado!", 409);
    }
}
