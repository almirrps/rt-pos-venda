package com.teste.rtposvenda.configuration.exception.usecase;

import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class QuantidadeParcelasInferiorException extends BusinessException {

    public QuantidadeParcelasInferiorException(Object... args) {
        super(ErroEnum.QUANTIDADE_PARCELA_INFERIOR, args);
    }

}
