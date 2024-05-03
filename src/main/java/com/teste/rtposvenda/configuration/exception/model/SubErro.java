package com.teste.rtposvenda.configuration.exception.model;

import com.teste.rtposvenda.configuration.exception.AppBaseException;

public class SubErro {

    String codigo;

    String mensagem;

    public SubErro(AppBaseException baseExcept) {
        this.codigo = baseExcept.getCode();
        this.mensagem = baseExcept.getMessage();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }
    
}
