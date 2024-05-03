package com.teste.rtposvenda.configuration.exception;

import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

public class BusinessException extends AppBaseException {

    public BusinessException(ErroEnum error, Object... args) {
        super(TYPE_BUSINESS_ERROR, null, error, args);
        this.code = error.getCode();
    }

}
