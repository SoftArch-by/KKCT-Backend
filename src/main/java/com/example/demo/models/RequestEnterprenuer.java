package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class RequestEnterprenuer {
    @Id
    private String id;
    @Field
    private String emailEntrepreuer;
    @Field
    private String EntreprenuerName;
    @Field
    private String Request_C_id;
    @Field
    private LocalDate requestDate;

    public RequestEnterprenuer(String Request_C_id,String EntreprenuerName,LocalDate requestDate){
        this.EntreprenuerName = EntreprenuerName;
        this.Request_C_id = Request_C_id;
        this.requestDate = requestDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailEntrepreuer() {
        return emailEntrepreuer;
    }

    public void setEmailEntrepreuer(String emailEntrepreuer) {
        this.emailEntrepreuer = emailEntrepreuer;
    }

    public String getEntreprenuerName() {
        return EntreprenuerName;
    }

    public void setEntreprenuerName(String entreprenuerName) {
        EntreprenuerName = entreprenuerName;
    }

    public String getRequest_C_id() {
        return Request_C_id;
    }

    public void setRequest_C_id(String request_C_id) {
        Request_C_id = request_C_id;
    } 

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "{"+ "id = " + getId() + "EmailEntreprenuer" + getEmailEntrepreuer() + 
        "EtreprenuerName = " + getEntreprenuerName() + "Request_C_id" + getRequest_C_id() + 
        "RequestDate = "  + getRequestDate()+"}";
    }

    
    
}
