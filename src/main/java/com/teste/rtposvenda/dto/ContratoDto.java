package com.teste.rtposvenda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContratoDto {

    @JsonProperty("id_contrato")
    private String idContrato;

    @JsonProperty("ultimo_digito_contrato")
    private Integer ultimoDigitoContrato;

    @JsonProperty("numero_cpf_cnpj_cliente")
    private String numeroCpfCnpjCliente;

    @JsonProperty("data_contratacao")
    private String dataContratacao;

    @JsonProperty("ativo")
    private Boolean ativo;

    @JsonProperty("parcelas_em_atraso")
    private Boolean parcelasEmAtraso;

}
