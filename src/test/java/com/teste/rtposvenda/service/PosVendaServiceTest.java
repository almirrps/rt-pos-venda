package com.teste.rtposvenda.service;

import static com.teste.rtposvenda.shared.MockFactory.NOVA_QUANTIDADE_PARCELAS;
import static com.teste.rtposvenda.shared.MockFactory.NOVO_DIA_PAGAMENTO;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.teste.rtposvenda.configuration.exception.FalhaGenericaAplicacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;


public class PosVendaServiceTest {

    @InjectMocks
    PosVendaService posVendaService;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void alterarQuantidadeParcelas() throws FalhaGenericaAplicacao {

        //Mockito.doNothing().when(posVendaService).alteraQuantidadeParcelas(NOVA_QUANTIDADE_PARCELAS);
        Assertions.assertDoesNotThrow(() -> posVendaService.alteraQuantidadeParcelas(NOVA_QUANTIDADE_PARCELAS));

    }

    @Test
    void alterarQuantidadeParcelasComErro() throws Exception {

        Assertions.assertThrows(RuntimeException.class, () -> posVendaService.alteraQuantidadeParcelas(NOVA_QUANTIDADE_PARCELAS));

    }

    @Test
    void alterarDiaPagamento() throws FalhaGenericaAplicacao {

        //Mockito.doNothing().when(posVendaService).alteraDiaPagamento(NOVO_DIA_PAGAMENTO);
        Assertions.assertDoesNotThrow(() -> posVendaService.alteraDiaPagamento(NOVO_DIA_PAGAMENTO));

    }

    @Test
    void alterarDiaPagamentoComErro() throws Exception {

        Assertions.assertThrows(RuntimeException.class, () -> posVendaService.alteraDiaPagamento(NOVO_DIA_PAGAMENTO));

    }

}
