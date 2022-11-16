package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import  org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Document(collection = "customers") @Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = AUTO)
    private String id;
    private String email;
    private String password;
    private String citizenID;
    
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();
    public Customer(String id, String email, String password, String citizenID) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.citizenID = citizenID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public boolean isEmpty() {
        return false;
    }
    public Customer orElseGet(Object object) {
        return null;
    }
}
