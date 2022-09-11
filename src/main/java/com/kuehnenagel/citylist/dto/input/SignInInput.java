package com.kuehnenagel.citylist.dto.input;

import java.io.Serial;
import java.io.Serializable;

public record SignInInput(String username, String password) implements Serializable {
    @Serial
    private static final long serialVersionUID = 979621223290116157L;
}
