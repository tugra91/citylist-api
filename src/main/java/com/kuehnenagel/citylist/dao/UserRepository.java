package com.kuehnenagel.citylist.dao;

import com.kuehnenagel.citylist.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, ObjectId> {

    Optional<UserEntity> findByUsername(String username);
}
