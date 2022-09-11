package com.kuehnenagel.citylist.dto.input;

import com.kuehnenagel.citylist.common.constant.RequestErrorEnum;
import com.kuehnenagel.citylist.common.exception.RequestException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;

public record EditCityInput(String cityId,
                            String newCityName,
                            String newPhotoUrl) implements Serializable {
    @Serial
    private static final long serialVersionUID = -2629011419279302318L;

    public EditCityInput {
        if (StringUtils.isEmpty(cityId)) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_CITY_ID_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_CITY_ID_ERROR.getMessage());
        }

        if(StringUtils.isEmpty(newCityName) && StringUtils.isEmpty(newPhotoUrl)) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_CITY_AND_PHOTO_URL_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_CITY_AND_PHOTO_URL_ERROR.getMessage());
        }

    }
}
