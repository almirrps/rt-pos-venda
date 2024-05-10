package com.teste.rtposvenda.service;

import static com.teste.rtposvenda.shared.MockFactory.NOVO_DIA_PAGAMENTO;
import static org.mockito.MockitoAnnotations.openMocks;

import com.teste.rtposvenda.configuration.exception.FalhaGenericaAplicacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


public class PosVendaServiceTest {

    @InjectMocks
    PosVendaService posVendaService;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void alterarDiaPagamento() throws FalhaGenericaAplicacao {

        //Mockito.doNothing().when(posVendaService).alteraDiaPagamento(NOVO_DIA_PAGAMENTO);
        Assertions.assertDoesNotThrow(() -> posVendaService.alteraDiaPagamento(NOVO_DIA_PAGAMENTO));

    }

}
