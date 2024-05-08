package com.teste.rtposvenda.shared;

import com.teste.rtposvenda.dto.AditamentoDto;
import com.teste.rtposvenda.dto.ContratoDto;
import com.teste.rtposvenda.dto.FinanceiroDto;
import com.teste.rtposvenda.dto.PosVendaDto;

import java.util.ArrayList;
import java.util.List;

public class MockFactory {

    public static final PosVendaDto NOVA_QUANTIDADE_PARCELAS = getPosVendaDtoNovaQtdeParcelas();
    public static final PosVendaDto NOVO_DIA_PAGAMENTO = getPosVendaDtoNovoDiaPagamento();

    private static PosVendaDto getPosVendaDtoNovaQtdeParcelas() {

        ContratoDto contratoDto = new ContratoDto();
        contratoDto.setIdContrato("37959");
        contratoDto.setNumeroCpfCnpjCliente("66273815089");
        contratoDto.setDataContratacao("2023-03-10");
        contratoDto.setAtivo(true);
        contratoDto.setParcelasEmAtraso(false);

        List<FinanceiroDto> financeiroDto = new ArrayList<>();
        financeiroDto.add(new FinanceiroDto());
        financeiroDto.get(0).setDataCalculo("2023-03-10");
        financeiroDto.get(0).setTipoCalculo("CONTRATACAO");
        financeiroDto.get(0).setValorTotal(50000.00);
        financeiroDto.get(0).setQuantidadeParcelas(50);
        financeiroDto.get(0).setValorParcelas(1000.00);
        financeiroDto.get(0).setDiaPagamento(23);
        financeiroDto.get(0).setPercentualTaxaJuro(1.99);

        AditamentoDto aditamentoDto = new AditamentoDto();
        aditamentoDto.setNovaQuantidadeParcelas(56);

        return PosVendaDto.builder()
                .contrato(contratoDto)
                .financeiro(financeiroDto)
                .aditamento(aditamentoDto)
                .build();

    }

    private static PosVendaDto getPosVendaDtoNovoDiaPagamento() {

        ContratoDto contratoDto = new ContratoDto();
        contratoDto.setIdContrato("37959");
        contratoDto.setNumeroCpfCnpjCliente("66273815089");
        contratoDto.setDataContratacao("2023-03-10");
        contratoDto.setAtivo(true);
        contratoDto.setParcelasEmAtraso(false);

        List<FinanceiroDto> financeiroDto = new ArrayList<>();
        financeiroDto.add(new FinanceiroDto());
        financeiroDto.get(0).setDataCalculo("2023-03-10");
        financeiroDto.get(0).setTipoCalculo("CONTRATACAO");
        financeiroDto.get(0).setValorTotal(50000.00);
        financeiroDto.get(0).setQuantidadeParcelas(50);
        financeiroDto.get(0).setValorParcelas(1000.00);
        financeiroDto.get(0).setDiaPagamento(23);
        financeiroDto.get(0).setPercentualTaxaJuro(1.99);

        AditamentoDto aditamentoDto = new AditamentoDto();
        aditamentoDto.setNovaDataPagamento(15);

        return PosVendaDto.builder()
                .contrato(contratoDto)
                .financeiro(financeiroDto)
                .aditamento(aditamentoDto)
                .build();

    }

}
