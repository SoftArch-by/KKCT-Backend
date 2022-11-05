package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Entrepreneur {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private String organizationName;

    public Entrepreneur(String id, String email, String organizationName) {
        this.id = id;
        this.email = email;
        this.organizationName = organizationName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return String.format("Entrepreneur[id='%s', email='%s', organizationName='%s']",id,email,organizationName);
    }
}
