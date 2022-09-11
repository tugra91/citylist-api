package com.kuehnenagel.citylist.dto.output;

import com.kuehnenagel.citylist.dto.model.User;

import java.io.Serial;
import java.io.Serializable;

public record RegisterUserOutput(User user) implements Serializable {


    @Serial
    private static final long serialVersionUID = -5109182141570362789L;
}
