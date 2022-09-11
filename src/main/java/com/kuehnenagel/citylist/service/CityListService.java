package com.kuehnenagel.citylist.service;

import com.kuehnenagel.citylist.dto.input.EditCityInput;
import com.kuehnenagel.citylist.dto.input.SearchCityInput;
import com.kuehnenagel.citylist.dto.output.EditCityOutput;
import com.kuehnenagel.citylist.dto.output.GetAllCitiesOutput;
import com.kuehnenagel.citylist.dto.output.SearchCityOutput;

import java.io.IOException;

public interface CityListService {

    void init() throws IOException;

    SearchCityOutput searchCity(SearchCityInput input);

    EditCityOutput editCity (EditCityInput input);

    GetAllCitiesOutput getAllCities();
}
