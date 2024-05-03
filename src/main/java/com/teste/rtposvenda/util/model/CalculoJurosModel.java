package com.teste.rtposvenda.util.model;

public class CalculoJurosModel {

    private String data;
    private Double percentual_juros;
    private Double valor_total;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getPercentual_juros() {
        return percentual_juros;
    }

    public void setPercentual_juros(Double percentual_juros) {
        this.percentual_juros = percentual_juros;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

}
