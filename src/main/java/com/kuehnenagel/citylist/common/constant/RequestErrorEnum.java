package com.kuehnenagel.citylist.common.constant;

public enum RequestErrorEnum {


    CITYLIST_EMPTY_CITY_NUMBER_ERROR("-1001", "Can not be empty cityNumber field"),
    CITYLIST_EMPTY_CITY_NAME_ERROR("-1002", "Can not be empty cityName field"),
    CITYLIST_EMPTY_CITY_PHOTO_URL_ERROR("-1003", "Can not be empty photoUrl field"),
    CITYLIST_EMPTY_CITY_ID_ERROR("-1004", "Can not be empty cityId field"),
    CITYLIST_EMPTY_CITY_AND_PHOTO_URL_ERROR("-1005", "Can not be empty both cityName and photoUrl fields together"),
    CITYLIST_EMPTY_SEARCH_TEXT_ERROR("-1006", "Can not be empty searchText field."),

    USER_EMPTY_USER_ERROR("-1101", "Can not be empty newUser field"),
    USER_EMPTY_USERNAME_ERROR("-1102", "Can not be empty username field"),
    USER_EMPTY_PASSWORD_ERROR("-1103", "Can not be empty password field"),
    USER_EMPTY_NAME_ERROR("-1104", "Can not be empty name field");


    private final String code;
    private final String message;

    RequestErrorEnum(String code, String message) {
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
