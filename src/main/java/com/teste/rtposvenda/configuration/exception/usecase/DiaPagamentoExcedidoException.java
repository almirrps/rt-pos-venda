package com.teste.rtposvenda.configuration.exception.usecase;

import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class DiaPagamentoExcedidoException extends BusinessException {

    public DiaPagamentoExcedidoException(Object... args) {
        super(ErroEnum.DIA_PAGAMENTO_EXCEDIDO, args);
    }

}
