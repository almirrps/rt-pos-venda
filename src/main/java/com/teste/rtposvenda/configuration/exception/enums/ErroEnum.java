package com.teste.rtposvenda.configuration.exception.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ErroEnum {

    ADITAMENTO_ALTERA_PARCELAS_INVALIDO("Aditamento invalido", "40", "Este é um aditamento de alteração de quantidade de parcelas. Informe somente o campo nova_quantidade_parcelas."),
    QUANTIDADE_PARCELA_INFERIOR("Quantidade Inferior", "41", "A quantidade de parcelas é inferior à atual."),
    CONTRATO_INATIVO("Contrato Inativo", "42", "O contrato informado está inativo."),
    ADITAMENTO_ALTERA_DIA_INVALIDO("Aditamento invalido", "50", "Este é um aditamento de alteração de data de pagamento. Informe somente o campo nova_data_pagamento."),
    DIA_PAGAMENTO_EXCEDIDO("Dias de Pagamento Excedidos", "51", "O dia de pagamento informado é maior que 10 dias após a data corrente"),
    CONTRATO_PARCELA_ATRASADA("Parcelas atrasadas", "52", "O contrato informado possui parcelas com atraso.");

    String scope;
    String code;
    String description;

    ErroEnum(String scope, String code, String description) {
        this.scope = scope;
        this.code = code;
        this.description = description;
    }

    public String getScope() {
        return scope;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String format(Object... args) {
        StringBuilder strParse = new StringBuilder(getDescription());
        if (args != null) {
            for (Object arg : args) {
                if (strParse.toString().contains("{}")) {
                    String cleanMessageStr = arg.toString().replace("$", "").replace("^", "");
                    strParse = new StringBuilder(strParse.toString().replaceFirst(Pattern.quote("\\{\\}"), Matcher.quoteReplacement(cleanMessageStr)));
                }
            }
        }

        return strParse.toString();
    }

}
