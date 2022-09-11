package com.kuehnenagel.citylist.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;

@Document(collection = "users")
public record UserEntity (@MongoId(FieldType.OBJECT_ID) String userId,
                          String username,
                          String password,
                          String name,
                          String role) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1678487897555261461L;
}
