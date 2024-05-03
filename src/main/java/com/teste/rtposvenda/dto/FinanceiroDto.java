package com.teste.rtposvenda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceiroDto {

    @JsonProperty("data_calculo")
    private String dataCalculo;

    @JsonProperty("tipo_calculo")
    private String tipoCalculo;

    @JsonProperty("valor_total")
    private Double valorTotal;

    @JsonProperty("quantidade_parcelas")
    private Integer quantidadeParcelas;

    @JsonProperty("valor_parcelas")
    private Double valorParcelas;

    @JsonProperty("dia_pagamento")
    private Integer diaPagamento;

    @JsonProperty("percentual_taxa_juro")
    private Double percentualTaxaJuro;

}
