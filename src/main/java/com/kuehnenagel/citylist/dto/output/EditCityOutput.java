package com.kuehnenagel.citylist.dto.output;

import com.kuehnenagel.citylist.dto.model.City;

import java.io.Serial;
import java.io.Serializable;


public record EditCityOutput(City newCity) implements Serializable {
    @Serial
    private static final long serialVersionUID = 5173154432400999004L;
}
