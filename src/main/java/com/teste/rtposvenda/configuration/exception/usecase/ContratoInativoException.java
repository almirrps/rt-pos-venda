package com.teste.rtposvenda.configuration.exception.usecase;

import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class ContratoInativoException extends BusinessException {

    public ContratoInativoException(Object... args) {
        super(ErroEnum.CONTRATO_INATIVO, args);
    }

}
