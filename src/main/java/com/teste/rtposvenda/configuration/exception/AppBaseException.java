package com.teste.rtposvenda.configuration.exception;

import com.teste.rtposvenda.configuration.exception.enums.ErroEnum;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class AppBaseException extends RuntimeException {

    String origin = null;
    String type = null;
    String code = null;
    String scope = null;
    List<Object> args = new ArrayList<>();

    public static final String TYPE_BUSINESS_ERROR = "Erro de negócio";

    public AppBaseException(String type, String origin, ErroEnum error, Object... args) {
        super(error.format(args));
        this.type = type;
        this.origin = origin;
        this.code = String.valueOf(error.getCode());
        this.scope = error.getDescription();
        this.args = List.of(args);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Object> getArgs() {
        return args;
    }

    public Object getFirstArgs() {
        if (args != null) {
            return args.stream().findFirst().orElse(null);
        }

        return null;
    }

    public String getResponseBodyFromArgs() {
        try {
            Object obj = getFirstArgs();

            if (obj instanceof Response) {
                Response res = (Response) obj;

                return res.getEntity().toString();
            }

        } catch (Exception ignored) {
        }

        return "Corpo vazio";
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
