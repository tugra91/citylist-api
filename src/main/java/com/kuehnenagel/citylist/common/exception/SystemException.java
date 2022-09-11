package com.kuehnenagel.citylist.common.exception;

import java.io.Serial;

public class SystemException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3347770410572878241L;

    private final String code;

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}
