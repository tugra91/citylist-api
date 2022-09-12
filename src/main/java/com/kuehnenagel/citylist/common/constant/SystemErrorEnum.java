package com.kuehnenagel.citylist.common.constant;

public enum SystemErrorEnum {

    GENERAL_SYSTEM_EXCEPTION ("-30000", "We are facing some unexpected issues. Please try again later"),
    ACCESS_TOKEN_SYSTEM_EXCEPTION("-30001", "We have faced a issue for authorization. Please try again later"),
    UNAUTHORIZATION_SYSTEM_EXCEPTION("-30002", "Your username or password is incorrect. Please try again.")
    ;

    private final String code;
    private final String message;

    SystemErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
