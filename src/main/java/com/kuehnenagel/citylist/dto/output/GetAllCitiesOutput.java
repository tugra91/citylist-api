package com.kuehnenagel.citylist.dto.output;

import com.kuehnenagel.citylist.dto.model.City;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record GetAllCitiesOutput(List<City> cityList) implements Serializable {
    @Serial
    private static final long serialVersionUID = 5847819421318389765L;
}
