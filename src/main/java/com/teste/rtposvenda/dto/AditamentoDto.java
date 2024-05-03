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
public class AditamentoDto {

    @JsonProperty("nova_quantidade_parcelas")
    private Integer novaQuantidadeParcelas;

    @JsonProperty("nova_data_pagamento")
    private Integer novaDataPagamento;

}
