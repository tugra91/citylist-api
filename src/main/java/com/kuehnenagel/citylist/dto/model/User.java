package com.kuehnenagel.citylist.dto.model;

import com.kuehnenagel.citylist.common.constant.RequestErrorEnum;
import com.kuehnenagel.citylist.common.exception.RequestException;
import com.kuehnenagel.citylist.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;


public record User(String username,
                   String password,
                   String name) implements Serializable {
    @Serial
    private static final long serialVersionUID = 7376428869376693995L;

    public User {
        if(StringUtils.isEmpty(username)) {
            throw new RequestException(RequestErrorEnum.USER_EMPTY_USERNAME_ERROR.getCode(), RequestErrorEnum.USER_EMPTY_USERNAME_ERROR.getMessage());
        }
        if(StringUtils.isEmpty(password)) {
            throw new RequestException(RequestErrorEnum.USER_EMPTY_PASSWORD_ERROR.getCode(), RequestErrorEnum.USER_EMPTY_PASSWORD_ERROR.getMessage());
        }
        if(StringUtils.isEmpty(name)) {
            throw new RequestException(RequestErrorEnum.USER_EMPTY_NAME_ERROR.getCode(), RequestErrorEnum.USER_EMPTY_NAME_ERROR.getMessage());
        }
    }

    public static User convertUserEntityToUser(UserEntity userEntity) {
        return new User(userEntity.username(), "protected", userEntity.name());
    }
}
