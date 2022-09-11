package com.kuehnenagel.citylist.dto.model;

import java.io.Serial;
import java.io.Serializable;

public record Token(String access_token, String refresh_token, String scope, String token_type, long expires_in) implements Serializable {
    @Serial
    private static final long serialVersionUID = 7974599964875457590L;
}
