package com.teste.rtposvenda.configuration.exception.usecase;

import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class ContratoParcelaAtrasadaException extends BusinessException {

    public ContratoParcelaAtrasadaException(Object... args) {
        super(ErroEnum.CONTRATO_PARCELA_ATRASADA, args);
    }

}
