package com.kuehnenagel.citylist.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;

@Document(collection = "city-list")
public record CityEntity(@MongoId(FieldType.OBJECT_ID) String cityId,
                         Integer cityNumber,
                         String cityName,
                         String photoUrl ) implements Serializable {
    @Serial
    private static final long serialVersionUID = 278972749694878563L;
}
