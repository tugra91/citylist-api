package com.kuehnenagel.citylist.dto.input;

import com.kuehnenagel.citylist.common.constant.RequestErrorEnum;
import com.kuehnenagel.citylist.common.exception.RequestException;
import com.kuehnenagel.citylist.dto.model.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


public record RegisterUserInput(User newUser) implements Serializable {
    @Serial
    private static final long serialVersionUID = 6857084305861974106L;

    public RegisterUserInput {
        if (Objects.isNull(newUser)) {
            throw new RequestException(RequestErrorEnum.USER_EMPTY_USER_ERROR.getCode(), RequestErrorEnum.USER_EMPTY_USER_ERROR.getMessage());
        }
    }

}
