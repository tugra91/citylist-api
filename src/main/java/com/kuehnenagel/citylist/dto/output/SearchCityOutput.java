package com.kuehnenagel.citylist.dto.output;

import com.kuehnenagel.citylist.dto.model.City;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record SearchCityOutput(List<City> resultList) implements Serializable {
    @Serial
    private static final long serialVersionUID = 2126708743162350723L;
}
