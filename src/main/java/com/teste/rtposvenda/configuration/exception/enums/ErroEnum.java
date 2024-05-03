package com.teste.rtposvenda.configuration.exception.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ErroEnum {

    QUANTIDADE_PARCELA_INFERIOR("Quantidade Inferior", "40", "A quantidade de parcelas é inferior à atual."),
    CONTRATO_INATIVO("Contrato Inativo", "41", "O contrato informado está inativo."),
    DIA_PAGAMENTO_EXCEDIDO("Dias de Pagamento Excedidos", "50", "O dia de pagamento informado é maior que 10 dias após a data corrente"),
    CONTRATO_PARCELA_ATRASADA("Parcelas atrasadas", "51", "O contrato informado possui parcelas com atraso.");

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
