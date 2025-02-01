package com.example.demo.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class userDetails {
    @Id
    private ObjectId id;
    @Indexed(unique = true,sparse = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<postDetails> PostDetails = new ArrayList<>();
    private List<String> roles;
}
