package com.teste.rtposvenda.util;

import com.teste.rtposvenda.util.model.CalculoJurosModel;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class CalculaJuros {

    public static CalculoJurosModel buscaCalculoJuros(String dtContratacao, Integer qtdeParcelas, Double valorContratacao) throws Exception {

        try {
            JSONObject definicoes = new JSONObject();
            definicoes.put("definir_data_contratacao", dtContratacao);
            definicoes.put("definir_criterio_calculo", "JUROS_SIMPLES");
            definicoes.put("definir_quantidade_parcelas", qtdeParcelas);
            definicoes.put("definir_valor_contratacao", valorContratacao);

            JSONObject contrato = new JSONObject();
            contrato.put("contrato", definicoes);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://juros-api.itau.br/calculo-juros"))
                    .header("content-type", "application/json")
                    .header("itau-pos-venda-teste", UUID.randomUUID().toString())
                    .POST(HttpRequest.BodyPublishers.ofString(contrato.toString()))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            CalculoJurosModel calculoJurosModel = gson.fromJson(response.body(), CalculoJurosModel.class);

            return calculoJurosModel;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

}
