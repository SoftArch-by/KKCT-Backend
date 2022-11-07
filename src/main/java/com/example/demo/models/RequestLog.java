package com.example.demo.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class RequestLog {
    @Id
    private String id;
    @Field
    private String emailEntrepreuer;
    @Field
    private String organizationName;
    @Field
    private String Request_customerId;
    @Field
    private Date requestDate;

    public RequestLog(String emailEntrepreuer,String organizationName,String Request_customerId){
        this.emailEntrepreuer = emailEntrepreuer;
        this.organizationName = organizationName;
        this.Request_customerId = Request_customerId;
        this.requestDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    
    public String getRequest_customerId() {
        return Request_customerId;
    }

    public void setRequest_customerId(String Request_customerId) {
        this.Request_customerId = Request_customerId;
    } 

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
}
