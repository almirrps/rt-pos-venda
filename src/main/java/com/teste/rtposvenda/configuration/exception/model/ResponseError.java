package com.teste.rtposvenda.configuration.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseError {

    @JsonProperty("tipo")
    private String type;

    @JsonProperty("origem")
    private String origin;

    @JsonProperty("escopo")
    private String scope;

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("mensagem")
    private String messages;

    @JsonProperty("errors")
    private List<SubErro> errors;

    public ResponseError(String type, String origin, String scope, String code, String messages, List<SubErro> errors) {
        this.type = type;
        this.origin = origin;
        this.scope = scope;
        this.code = code;
        this.messages = messages;
        this.errors = errors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<SubErro> getErrors() {
        return errors;
    }

    public void setErrors(List<SubErro> errors) {
        this.errors = errors;
    }
}
