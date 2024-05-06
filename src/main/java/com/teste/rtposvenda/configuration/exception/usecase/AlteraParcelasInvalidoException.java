package com.teste.rtposvenda.configuration.exception.usecase;

import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class AlteraParcelasInvalidoException extends BusinessException {

    public AlteraParcelasInvalidoException(Object... args) {
        super(ErroEnum.ADITAMENTO_ALTERA_PARCELAS_INVALIDO, args);
    }

}
