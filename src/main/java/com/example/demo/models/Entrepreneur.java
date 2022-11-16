package com.example.demo.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter @Setter
public class Entrepreneur {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private String organizationName;
    @Field
    private String type;

    public Entrepreneur(String id, String email, String organizationName, String type) {
        this.id = id;
        this.email = email;
        this.organizationName = organizationName;
        this.type = type;
    }

//    @Override
//    public String toString() {
//        return String.format("Entrepreneur[id='%s', email='%s', organizationName='%s', type='%s' ]",id,email,organizationName,type);
//    }
}
