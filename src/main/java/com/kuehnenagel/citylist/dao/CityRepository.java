package com.kuehnenagel.citylist.dao;

import com.kuehnenagel.citylist.entity.CityEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository("cityRepository")
public interface CityRepository extends CrudRepository<CityEntity, ObjectId> {


    @Async
    CompletableFuture<List<CityEntity>> findByCityNumber(Integer cityNumber);

    @Async
    @Query("{'cityName': {$regex: ?0, $options: 'i'}}")
    CompletableFuture<List<CityEntity>> findByCityNameQuery(String cityName);
}
