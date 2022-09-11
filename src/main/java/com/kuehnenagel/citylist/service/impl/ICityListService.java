package com.kuehnenagel.citylist.service.impl;

import com.kuehnenagel.citylist.common.constant.BusinessErrorEnum;
import com.kuehnenagel.citylist.common.exception.BusinessException;
import com.kuehnenagel.citylist.common.exception.SystemException;
import com.kuehnenagel.citylist.dao.CityRepository;
import com.kuehnenagel.citylist.dto.input.EditCityInput;
import com.kuehnenagel.citylist.dto.input.SearchCityInput;
import com.kuehnenagel.citylist.dto.model.City;
import com.kuehnenagel.citylist.dto.output.EditCityOutput;
import com.kuehnenagel.citylist.dto.output.GetAllCitiesOutput;
import com.kuehnenagel.citylist.dto.output.SearchCityOutput;
import com.kuehnenagel.citylist.entity.CityEntity;
import com.kuehnenagel.citylist.service.CityListService;
import com.kuehnenagel.citylist.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ICityListService implements CityListService {

    @Value("classpath:data/cities.csv")
    private Resource initDataFile;
    
    private final CityRepository cityRepository;

    @PostConstruct
    @DependsOn(value = {"mongoTemplate", "cityRepository"})
    @Override
    public void init() throws IOException {
        long numberOfExistData = cityRepository.count();
        if ( numberOfExistData == 0) {
            List<CityEntity> initEntityData = new ArrayList<>();
            List<List<String>> initCityData = DataUtil.retrieveDataFromCsvToList(initDataFile.getFile());
            initCityData.remove(0);
            initCityData.forEach(initCityRow -> {
                Integer cityId = Integer.valueOf(initCityRow.get(0));
                String cityName = initCityRow.get(1);
                String cityPhotoUrl = initCityRow.get(2);
                CityEntity cityEntity = new CityEntity(null, cityId, cityName, cityPhotoUrl);
                initEntityData.add(cityEntity);
            });
            cityRepository.saveAll(initEntityData);
        }
    }

    @Override
    public SearchCityOutput searchCity(SearchCityInput input) {
        String searchText = input.searchText();
        List<City> resultList;
        try {
            if (NumberUtils.isDigits(searchText)) {
                CompletableFuture<List<CityEntity>> allResults = cityRepository.findByCityNumber(Integer.valueOf(searchText))
                        .thenCompose(s -> cityRepository.findByCityNameQuery(searchText).thenCompose(s1 -> CompletableFuture.supplyAsync(() -> {
                            s.addAll(s1);
                            return s;
                        })));
                resultList = City.convertCityEntityListToCityList(allResults.get());
            } else {
                CompletableFuture<List<CityEntity>> nameResults = cityRepository.findByCityNameQuery(searchText);
                resultList = City.convertCityEntityListToCityList(nameResults.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new SystemException("", "", e);
        }
        return new SearchCityOutput(resultList);
    }

    @Override
    public EditCityOutput editCity(EditCityInput input) {
        CityEntity existEntity = cityRepository.findById(new ObjectId(input.cityId()))
                .orElseThrow(() -> new BusinessException(BusinessErrorEnum.NOT_EXITS_CITY_RECORD_ERROR.getCode(), BusinessErrorEnum.NOT_EXITS_CITY_RECORD_ERROR.getMessage()));
        String newCityName = StringUtils.isEmpty(input.newCityName()) || StringUtils.equalsAnyIgnoreCase(input.newCityName(), existEntity.cityName()) ? existEntity.cityName() : input.newCityName();
        String newPhotoUrl = StringUtils.isEmpty(input.newPhotoUrl()) || StringUtils.equalsAnyIgnoreCase(input.newPhotoUrl(), existEntity.photoUrl()) ? existEntity.photoUrl() : input.newPhotoUrl();
        CityEntity newEntity = new CityEntity(existEntity.cityId(), existEntity.cityNumber(), newCityName, newPhotoUrl);
        CityEntity savedEntity = cityRepository.save(newEntity);
        return new EditCityOutput(City.convertCityEntityToCity(savedEntity));
    }

    @Override
    public GetAllCitiesOutput getAllCities() {
        List<CityEntity> allCityEntityList = new ArrayList<>();
        Iterable<CityEntity> allCityEntityResults = cityRepository.findAll();
        allCityEntityResults.forEach(allCityEntityList::add);
        List<City> allCityList = City.convertCityEntityListToCityList(allCityEntityList);
        return new GetAllCitiesOutput(allCityList);
    }


}
