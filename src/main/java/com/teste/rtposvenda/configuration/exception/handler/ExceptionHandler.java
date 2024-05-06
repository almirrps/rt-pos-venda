package com.teste.rtposvenda.configuration.exception.handler;

import com.teste.rtposvenda.configuration.exception.AppBaseException;
import com.teste.rtposvenda.configuration.exception.BusinessException;
import com.teste.rtposvenda.configuration.exception.model.ResponseError;
import com.teste.rtposvenda.configuration.exception.model.SubErro;
import com.teste.rtposvenda.configuration.exception.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    private ResponseEntity<ResponseError> createBodyResponse(HttpStatus httpStatus, AppBaseException exception) {
        SubErro subErro = new SubErro(exception);

        return ResponseEntity.status(httpStatus).body(ResponseError.builder().errors(Collections.singletonList(subErro)).build());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AlteraDiaPagamentoInvalidoException.class)
    public ResponseEntity<ResponseError> alteraDiaPagamentoInvalidoExceptionHandler(final BusinessException e) {
        return createBodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AlteraParcelasInvalidoException.class)
    public ResponseEntity<ResponseError> alteraParcelasInvalidoExceptionHandler(final BusinessException e) {
        return createBodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(QuantidadeParcelasInferiorException.class)
    public ResponseEntity<ResponseError> quantidadeParcelasInferiorExceptionHandler(final BusinessException e) {
        return createBodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ContratoInativoException.class)
    public ResponseEntity<ResponseError> contratoInativoExceptionHandler(final BusinessException e) {
        return createBodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DiaPagamentoExcedidoException.class)
    public ResponseEntity<ResponseError> diaPagamentoExcedidoExceptionHandler(final BusinessException e) {
        return createBodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ContratoParcelaAtrasadaException.class)
    public ResponseEntity<ResponseError> contratoParcelaAtrasadaExceptionHandler(final BusinessException e) {
        return createBodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
    }

}
