package com.kuehnenagel.citylist.common.dto;

import java.io.Serial;
import java.io.Serializable;

public record ExceptionOutput(String exceptionType, String message, String code) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8082863068647649787L;
}
