package com.example.demo.models;

import org.springframework.data.annotation.Id;
import  org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document
public class User {
    @Id
    private String id;
    @Field
    private  String firstName;
    @Field
    private String lastName;

    public  User(){
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id='%s', firstName='%s', lastName='%s']",id,firstName,lastName);
    }
}
