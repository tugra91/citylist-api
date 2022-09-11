package com.kuehnenagel.citylist.common.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5656161153259816020L;

    private final String code;

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}
