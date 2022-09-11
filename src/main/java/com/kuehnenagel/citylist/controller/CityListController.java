package com.kuehnenagel.citylist.controller;

import com.kuehnenagel.citylist.dto.input.EditCityInput;
import com.kuehnenagel.citylist.dto.input.SearchCityInput;
import com.kuehnenagel.citylist.dto.output.EditCityOutput;
import com.kuehnenagel.citylist.dto.output.GetAllCitiesOutput;
import com.kuehnenagel.citylist.dto.output.SearchCityOutput;

public interface CityListController {

    SearchCityOutput searchCity(SearchCityInput input);

    EditCityOutput editCity(EditCityInput input);

    EditCityOutput editCity2(EditCityInput input);

    GetAllCitiesOutput getAllCities();
}
