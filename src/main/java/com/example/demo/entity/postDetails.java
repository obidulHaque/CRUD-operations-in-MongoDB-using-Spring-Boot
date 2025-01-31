package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document
@Getter
@Setter
public class postDetails {
    @Id
    private ObjectId id;
    private String title;
    private String Description;
    private LocalDate time;


}
