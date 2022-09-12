package com.kuehnenagel.citylist.service;

import com.kuehnenagel.citylist.common.exception.BusinessException;
import com.kuehnenagel.citylist.dao.CityRepository;
import com.kuehnenagel.citylist.dto.input.EditCityInput;
import com.kuehnenagel.citylist.dto.output.EditCityOutput;
import com.kuehnenagel.citylist.dto.output.GetAllCitiesOutput;
import com.kuehnenagel.citylist.entity.CityEntity;
import com.kuehnenagel.citylist.service.impl.ICityListService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith({MockitoExtension.class})
public class CityListServiceTest {

    @Mock
    private CityRepository cityRepository;

    private CityListService cityListService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cityListService = new ICityListService(cityRepository);
    }


    @Test
    public void testEditCity_validRequestChangedTwoValue_returnSuccess() {
        EditCityInput input = new EditCityInput("631b91d612f511489dff1aca", "Ankara", "http://newimgurl.com");
        CityEntity mockEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Istanbul", "http://imgurl.com");
        CityEntity mockNewEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Ankara", "http://newimgurl.com");

        Mockito.when(cityRepository.findById(Mockito.refEq(new ObjectId(input.cityId())))).thenReturn(Optional.of(mockEntity));
        Mockito.when(cityRepository.save(Mockito.refEq(mockNewEntity))).thenReturn(mockNewEntity);

        EditCityOutput actualOutput = cityListService.editCity(input);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.newCity());
        Assertions.assertEquals(input.newCityName(), actualOutput.newCity().cityName());
        Assertions.assertEquals(input.newPhotoUrl(), actualOutput.newCity().photoUrl());
    }

    @Test
    public void testEditCity_validRequestChangedCityName_returnSuccess() {
        EditCityInput input = new EditCityInput("631b91d612f511489dff1aca", "Ankara", "http://imgurl.com");
        CityEntity mockEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Istanbul", "http://imgurl.com");
        CityEntity mockNewEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Ankara", "http://imgurl.com");

        Mockito.when(cityRepository.findById(Mockito.refEq(new ObjectId(input.cityId())))).thenReturn(Optional.of(mockEntity));
        Mockito.when(cityRepository.save(Mockito.refEq(mockNewEntity))).thenReturn(mockNewEntity);

        EditCityOutput actualOutput = cityListService.editCity(input);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.newCity());
        Assertions.assertEquals(input.newCityName(), actualOutput.newCity().cityName());
    }

    @Test
    public void testEditCity_validRequestChangedPhotoUrl_returnSuccess() {
        EditCityInput input = new EditCityInput("631b91d612f511489dff1aca", "Istanbul", "http://newimgurl.com");
        CityEntity mockEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Istanbul", "http://imgurl.com");
        CityEntity mockNewEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Istanbul", "http://newimgurl.com");

        Mockito.when(cityRepository.findById(Mockito.refEq(new ObjectId(input.cityId())))).thenReturn(Optional.of(mockEntity));
        Mockito.when(cityRepository.save(Mockito.refEq(mockNewEntity))).thenReturn(mockNewEntity);

        EditCityOutput actualOutput = cityListService.editCity(input);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.newCity());
        Assertions.assertEquals(input.newPhotoUrl(), actualOutput.newCity().photoUrl());
    }

    @Test
    public void testEditCity_nonExistCity_throwException() {
        EditCityInput input = new EditCityInput("631b91d612f511489dff1aca", "Istanbul", "http://newimgurl.com");
        Mockito.when(cityRepository.findById(Mockito.refEq(new ObjectId(input.cityId())))).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> cityListService.editCity(input));
    }

    @Test
    public void testGetAllCities_validRequest_returnSuccess() {
        CityEntity existEntity = new CityEntity("631b91d612f511489dff1aca", 2, "Istanbul", "http://newimgurl.com");
        Iterable<CityEntity> iterable = List.of(existEntity);
        Mockito.when(cityRepository.findAll()).thenReturn(iterable);

        GetAllCitiesOutput actualOutput = cityListService.getAllCities();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(1, actualOutput.cityList().size());
        Assertions.assertEquals(existEntity.cityId(), actualOutput.cityList().get(0).cityId());
    }


}
