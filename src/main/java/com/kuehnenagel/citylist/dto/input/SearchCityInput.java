package com.kuehnenagel.citylist.dto.input;

import com.kuehnenagel.citylist.common.constant.RequestErrorEnum;
import com.kuehnenagel.citylist.common.exception.RequestException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;


public record SearchCityInput(String searchText) implements Serializable {
    @Serial
    private static final long serialVersionUID = 4200146906762884612L;

    public SearchCityInput {
        if ( StringUtils.isEmpty(searchText)) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_SEARCH_TEXT_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_SEARCH_TEXT_ERROR.getMessage());
        }
    }
}
