package com.projeto.restaurante.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse extends RuntimeException {
    private final String mensagem;
    private final Integer codigo;

    public ErrorResponse(String mensagem, Integer codigo) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public Map<String, Object> paraJson() {
        Map<String, Object> json = new HashMap();
        json.put("Código:", this.codigo);
        json.put("Erro: ", this.mensagem);
        return json;
    }
}
