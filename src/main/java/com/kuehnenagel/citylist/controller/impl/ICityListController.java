package com.kuehnenagel.citylist.controller.impl;

import com.kuehnenagel.citylist.controller.CityListController;
import com.kuehnenagel.citylist.dto.input.EditCityInput;
import com.kuehnenagel.citylist.dto.input.SearchCityInput;
import com.kuehnenagel.citylist.dto.output.EditCityOutput;
import com.kuehnenagel.citylist.dto.output.GetAllCitiesOutput;
import com.kuehnenagel.citylist.dto.output.SearchCityOutput;
import com.kuehnenagel.citylist.service.CityListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ICityListController implements CityListController {

    private final CityListService cityListService;

    @PostMapping(value = "/search")
    @Override
    public SearchCityOutput searchCity(@RequestBody  SearchCityInput input) {
        return cityListService.searchCity(input);
    }

    @PostMapping(value = "/user/editCity")
    @Override
    public EditCityOutput editCity(@RequestBody EditCityInput input) {
        return cityListService.editCity(input);
    }

    @PostMapping(value = "/editCity")
    @Override
    public EditCityOutput editCity2(@RequestBody EditCityInput input) {
        return cityListService.editCity(input);
    }

    @GetMapping(value = "/getAllCities")
    @Override
    public GetAllCitiesOutput getAllCities() {
        return cityListService.getAllCities();
    }
}
