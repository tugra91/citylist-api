package com.kuehnenagel.citylist.common.constant;

public enum BusinessErrorEnum {

    NOT_EXITS_CITY_RECORD_ERROR ("-2001", "It is not found a city entity. Please insert a valid ID."),

    EXIST_USER_RECORD_ERROR("-3001", "Can not register with that username due to it is used by someone else.");

    private final String code;
    private final String message;

    BusinessErrorEnum(String code, String message) {
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
