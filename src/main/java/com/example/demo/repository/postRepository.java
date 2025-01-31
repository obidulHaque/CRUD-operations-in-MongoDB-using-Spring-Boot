package com.example.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.postDetails;

public interface postRepository extends MongoRepository<postDetails, ObjectId> {
}
