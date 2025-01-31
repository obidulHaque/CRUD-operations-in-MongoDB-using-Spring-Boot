package com.example.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.userDetails;
public interface userRepository extends MongoRepository<userDetails, ObjectId> {
    userDetails findByUserName(String userName);
}
