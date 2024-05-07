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

    private Integer aditamentoAlteraQuant = 1;
    private Integer aditamentoAlteraDia = 2;

    private void validaTipoAditamento(Integer tipoAditamento, PosVendaDto posVenda) {

        if (tipoAditamento == aditamentoAlteraQuant) { //Aditamento de alteração de quantidade de parcelas

            if (posVenda.getAditamento().getNovaDataPagamento() != null) {
                throw new AlteraDiaPagamentoInvalidoException();
            }

        }

        if (tipoAditamento == aditamentoAlteraDia) { //Aditamento de alteração de dia de pagamento

            if (posVenda.getAditamento().getNovaQuantidadeParcelas() != null) {
                throw new AlteraParcelasInvalidoException();
            }

        }

    }

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

        validaTipoAditamento(aditamentoAlteraQuant, posVenda);
        validaContratoAtivo(posVenda);
        validaQuantidadeParcelas(posVenda);

        RetornoPosVendaDto novoPosVenda = new RetornoPosVendaDto();
        novoPosVenda.setContrato(posVenda.getContrato());
        novoPosVenda.getContrato().setUltimoDigitoContrato(9);

        Date data = new Date();
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd").format(data);

        DecimalFormat df = new DecimalFormat("#.00");

        try {
            for (int idx=0; idx<posVenda.getFinanceiro().size(); idx++) {

                novoPosVenda.getFinanceiro().add(new FinanceiroDto());
                novoPosVenda.getFinanceiro().get(idx).setDataCalculo(posVenda.getFinanceiro().get(idx).getDataCalculo());
                novoPosVenda.getFinanceiro().get(idx).setTipoCalculo(posVenda.getFinanceiro().get(idx).getTipoCalculo());
                novoPosVenda.getFinanceiro().get(idx).setValorTotal(posVenda.getFinanceiro().get(idx).getValorTotal());
                novoPosVenda.getFinanceiro().get(idx).setQuantidadeParcelas(posVenda.getFinanceiro().get(idx).getQuantidadeParcelas());
                novoPosVenda.getFinanceiro().get(idx).setValorParcelas(posVenda.getFinanceiro().get(idx).getValorParcelas());
                novoPosVenda.getFinanceiro().get(idx).setDiaPagamento(posVenda.getFinanceiro().get(idx).getDiaPagamento());
                novoPosVenda.getFinanceiro().get(idx).setPercentualTaxaJuro(posVenda.getFinanceiro().get(idx).getPercentualTaxaJuro());

                String dtContratacao = posVenda.getContrato().getDataContratacao();
                Integer novaQtdeParcelas = posVenda.getAditamento().getNovaQuantidadeParcelas();
                CalculoJurosModel calculoJurosModel = CalculaJuros.buscaCalculoJuros(dtContratacao, novaQtdeParcelas,
                        posVenda.getFinanceiro().get(idx).getValorTotal());

                String valorParcela = df.format(calculoJurosModel.getValor_total()/novaQtdeParcelas);
                Double novoValorParcela = Double.parseDouble(valorParcela.replaceAll(",", "."));

                novoPosVenda.getFinanceiro().add(new FinanceiroDto());
                novoPosVenda.getFinanceiro().get(idx+1).setDataCalculo(dataAtual);
                novoPosVenda.getFinanceiro().get(idx+1).setTipoCalculo("ADITAMENTO");
                novoPosVenda.getFinanceiro().get(idx+1).setValorTotal(calculoJurosModel.getValor_total());
                novoPosVenda.getFinanceiro().get(idx+1).setQuantidadeParcelas(novaQtdeParcelas);
                novoPosVenda.getFinanceiro().get(idx+1).setValorParcelas(novoValorParcela);
                novoPosVenda.getFinanceiro().get(idx+1).setDiaPagamento(posVenda.getFinanceiro().get(idx).getDiaPagamento());
                novoPosVenda.getFinanceiro().get(idx+1).setPercentualTaxaJuro(calculoJurosModel.getPercentual_juros());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return novoPosVenda;
    }

    public RetornoPosVendaDto alteraDiaPagamento(PosVendaDto posVenda) {

        validaTipoAditamento(aditamentoAlteraDia, posVenda);
        validaContratoAtivo(posVenda);
        validaDiaPagamentoExcedido(posVenda);
        validaParcelaAtrasada(posVenda);

        RetornoPosVendaDto novoPosVenda = new RetornoPosVendaDto();
        novoPosVenda.setContrato(posVenda.getContrato());
        novoPosVenda.getContrato().setUltimoDigitoContrato(9);

        for (int idx=0; idx<posVenda.getFinanceiro().size(); idx++) {

            novoPosVenda.getFinanceiro().add(new FinanceiroDto());
            novoPosVenda.getFinanceiro().get(idx).setDataCalculo(posVenda.getFinanceiro().get(idx).getDataCalculo());
            novoPosVenda.getFinanceiro().get(idx).setTipoCalculo(posVenda.getFinanceiro().get(idx).getTipoCalculo());
            novoPosVenda.getFinanceiro().get(idx).setValorTotal(posVenda.getFinanceiro().get(idx).getValorTotal());
            novoPosVenda.getFinanceiro().get(idx).setQuantidadeParcelas(posVenda.getFinanceiro().get(idx).getQuantidadeParcelas());
            novoPosVenda.getFinanceiro().get(idx).setValorParcelas(posVenda.getFinanceiro().get(idx).getValorParcelas());
            novoPosVenda.getFinanceiro().get(idx).setDiaPagamento(posVenda.getFinanceiro().get(idx).getDiaPagamento());
            novoPosVenda.getFinanceiro().get(idx).setPercentualTaxaJuro(posVenda.getFinanceiro().get(idx).getPercentualTaxaJuro());

            Date data = new Date();
            String dataAtual = new SimpleDateFormat("yyyy-MM-dd").format(data);
            Integer novoDiaPagamento = posVenda.getAditamento().getNovaDataPagamento();

            novoPosVenda.getFinanceiro().add(new FinanceiroDto());
            novoPosVenda.getFinanceiro().get(idx+1).setDataCalculo(dataAtual);
            novoPosVenda.getFinanceiro().get(idx+1).setTipoCalculo("ADITAMENTO");
            novoPosVenda.getFinanceiro().get(idx+1).setValorTotal(posVenda.getFinanceiro().get(idx).getValorTotal());
            novoPosVenda.getFinanceiro().get(idx+1).setQuantidadeParcelas(posVenda.getFinanceiro().get(idx).getQuantidadeParcelas());
            novoPosVenda.getFinanceiro().get(idx+1).setValorParcelas(posVenda.getFinanceiro().get(idx).getValorParcelas());
            novoPosVenda.getFinanceiro().get(idx+1).setDiaPagamento(novoDiaPagamento);
            novoPosVenda.getFinanceiro().get(idx+1).setPercentualTaxaJuro(posVenda.getFinanceiro().get(idx).getPercentualTaxaJuro());
        }

        return novoPosVenda;
    }

}
