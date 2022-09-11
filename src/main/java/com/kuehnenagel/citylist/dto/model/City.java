package com.kuehnenagel.citylist.dto.model;

import com.kuehnenagel.citylist.common.constant.RequestErrorEnum;
import com.kuehnenagel.citylist.common.exception.RequestException;
import com.kuehnenagel.citylist.entity.CityEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public record City(String cityId,
                   Integer cityNumber,
                   String cityName,
                   String photoUrl) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8510185356929052018L;

    public City {
        if(StringUtils.isEmpty(cityId)) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_CITY_ID_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_CITY_ID_ERROR.getMessage());
        }
        if ( cityNumber == null ) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_CITY_NUMBER_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_CITY_NUMBER_ERROR.getMessage());
        }
        if(StringUtils.isEmpty(cityName)) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_CITY_NAME_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_CITY_NAME_ERROR.getMessage());
        }
        if(StringUtils.isEmpty(photoUrl)) {
            throw new RequestException(RequestErrorEnum.CITYLIST_EMPTY_CITY_PHOTO_URL_ERROR.getCode(), RequestErrorEnum.CITYLIST_EMPTY_CITY_PHOTO_URL_ERROR.getMessage());
        }
    }

    public static List<City> convertCityEntityListToCityList(List<CityEntity> cityEntityList) {
        return cityEntityList.stream().map(s -> new City(s.cityId(), s.cityNumber(), s.cityName(), s.photoUrl()))
                .collect(Collectors.toList());
    }

    public static City convertCityEntityToCity(CityEntity cityEntity) {
        return new City(cityEntity.cityId(), cityEntity.cityNumber(), cityEntity.cityName(), cityEntity.photoUrl());
    }
}
