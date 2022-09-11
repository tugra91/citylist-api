package com.kuehnenagel.citylist.common.exception;

import java.io.Serial;

public class RequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7416462225229091645L;

    private final String code;

    public RequestException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RequestException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
