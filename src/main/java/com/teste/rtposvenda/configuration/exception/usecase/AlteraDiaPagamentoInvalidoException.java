package com.teste.rtposvenda.configuration.exception.usecase;

import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class AlteraDiaPagamentoInvalidoException extends BusinessException {

    public AlteraDiaPagamentoInvalidoException(Object... args) {
        super(ErroEnum.ADITAMENTO_ALTERA_DIA_INVALIDO, args);
    }

}
