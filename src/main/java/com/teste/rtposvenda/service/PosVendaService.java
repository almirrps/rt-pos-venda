package com.teste.rtposvenda.service;

import com.teste.rtposvenda.configuration.exception.usecase.*;
import com.teste.rtposvenda.util.CalculaJuros;
import com.teste.rtposvenda.dto.FinanceiroDto;
import com.teste.rtposvenda.dto.PosVendaDto;
import com.teste.rtposvenda.dto.RetornoPosVendaDto;
import com.teste.rtposvenda.util.model.CalculoJurosModel;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PosVendaService {

    private void validaContratoAtivo(PosVendaDto posVenda) {

        if (!posVenda.getContrato().getAtivo()) {
            throw new ContratoInativoException();
        }

    }

    private void validaQuantidadeParcelas(PosVendaDto posVenda) {

        for (FinanceiroDto financeiroDto : posVenda.getFinanceiro()) {

            if (financeiroDto.getQuantidadeParcelas() >= posVenda.getAditamento().getNovaQuantidadeParcelas()) {
                throw new QuantidadeParcelasInferiorException();
            }

        }

    }

    private void validaDiaPagamentoExcedido(PosVendaDto posVenda) {

        Integer novoDiaPagamento = posVenda.getAditamento().getNovaDataPagamento();

        for (FinanceiroDto financeiroDto : posVenda.getFinanceiro()) {

            if ((financeiroDto.getDiaPagamento()+10) < novoDiaPagamento) {
                throw new QuantidadeParcelasInferiorException();
            }

        }

    }

    private void validaParcelaAtrasada(PosVendaDto posVenda) {

        if (posVenda.getContrato().getParcelasEmAtraso()) {
            throw new ContratoParcelaAtrasadaException();
        }

    }

    public RetornoPosVendaDto alteraQuantidadeParcelas(PosVendaDto posVenda) {

        validaContratoAtivo(posVenda);
        validaQuantidadeParcelas(posVenda);

        RetornoPosVendaDto novoPosVenda = new RetornoPosVendaDto();
        novoPosVenda.setContrato(posVenda.getContrato());
        novoPosVenda.getContrato().setUltimoDigitoContrato(9);

        novoPosVenda.setFinanceiro(posVenda.getFinanceiro());

        Date data = new Date();
        String dataAtual = new SimpleDateFormat("yyyy/MM/dd").format(data);

        DecimalFormat df = new DecimalFormat("#.00");

        String dtContratacao = posVenda.getContrato().getDataContratacao();
        Integer novaQtdeParcelas = posVenda.getAditamento().getNovaQuantidadeParcelas();

        try {
            for (FinanceiroDto financeiroDto : posVenda.getFinanceiro()) {
                CalculoJurosModel calculoJurosModel = CalculaJuros.buscaCalculoJuros(dtContratacao, novaQtdeParcelas,
                        financeiroDto.getValorTotal());

                String valorParcela = df.format(calculoJurosModel.getValor_total()/novaQtdeParcelas);
                Double novoValorParcela = Double.parseDouble(valorParcela.replaceAll(",", "."));

                FinanceiroDto financeiroCalculado = new FinanceiroDto();
                financeiroCalculado.setDataCalculo(dataAtual);
                financeiroCalculado.setTipoCalculo("ADITAMENTO");
                financeiroCalculado.setValorTotal(calculoJurosModel.getValor_total());
                financeiroCalculado.setQuantidadeParcelas(novaQtdeParcelas);
                financeiroCalculado.setValorParcelas(novoValorParcela);
                financeiroCalculado.setDiaPagamento(financeiroDto.getDiaPagamento());
                financeiroCalculado.setPercentualTaxaJuro(calculoJurosModel.getPercentual_juros());

                novoPosVenda.getFinanceiro().add(financeiroCalculado);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return novoPosVenda;
    }

    public RetornoPosVendaDto alteraDiaPagamento(PosVendaDto posVenda) {

        validaContratoAtivo(posVenda);
        validaDiaPagamentoExcedido(posVenda);
        validaParcelaAtrasada(posVenda);

        RetornoPosVendaDto novoPosVenda = new RetornoPosVendaDto();
        novoPosVenda.setContrato(posVenda.getContrato());
        novoPosVenda.getContrato().setUltimoDigitoContrato(9);

        novoPosVenda.setFinanceiro(posVenda.getFinanceiro());

        Date data = new Date();
        String dataAtual = new SimpleDateFormat("yyyy/MM/dd").format(data);
        Integer novoDiaPagamento = posVenda.getAditamento().getNovaDataPagamento();

        for (FinanceiroDto financeiroDto : posVenda.getFinanceiro()) {

            FinanceiroDto financeiroCalculado = new FinanceiroDto();
            financeiroCalculado.setDataCalculo(dataAtual);
            financeiroCalculado.setTipoCalculo("ADITAMENTO");
            financeiroCalculado.setValorTotal(financeiroDto.getValorTotal());
            financeiroCalculado.setQuantidadeParcelas(financeiroDto.getQuantidadeParcelas());
            financeiroCalculado.setValorParcelas(financeiroDto.getValorParcelas());
            financeiroCalculado.setDiaPagamento(novoDiaPagamento);
            financeiroCalculado.setPercentualTaxaJuro(financeiroDto.getPercentualTaxaJuro());

            novoPosVenda.getFinanceiro().add(financeiroCalculado);
        }

        return novoPosVenda;
    }

}
