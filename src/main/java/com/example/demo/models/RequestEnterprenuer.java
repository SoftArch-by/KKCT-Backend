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
    private String Request_customerId;
    @Field
    private LocalDate requestDate;

    public RequestEnterprenuer(String Request_customerId,String EntreprenuerName,LocalDate requestDate){
        this.EntreprenuerName = EntreprenuerName;
        this.Request_customerId = Request_customerId;
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

    public String getRequest_customerId() {
        return Request_customerId;
    }

    public void setRequest_customerId(String request_customerId) {
        Request_customerId = request_customerId;
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
        "EtreprenuerName = " + getEntreprenuerName() + "Request_customerId" + getRequest_customerId() + 
        "RequestDate = "  + getRequestDate()+"}";
    }

    
    
}
