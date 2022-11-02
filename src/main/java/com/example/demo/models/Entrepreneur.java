package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Entrepreneur {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private String oranganizationName;

    public Entrepreneur(String id, String email, String oranganizationName) {
        this.id = id;
        this.email = email;
        this.oranganizationName = oranganizationName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getOrangnizationName() {
        return oranganizationName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrangnizationName(String orangnizationName) {
        this.oranganizationName = orangnizationName;
    }

    @Override
    public String toString() {
        return String.format("Entrepreneur[id='%s', email='%s', oranganizationName='%s']",id,email,oranganizationName);
    }
}
