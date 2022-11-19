package com.example.demo.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class RequestLogCustomerEmail {
    @Id
    private String id;
    @Field
    private String Entrepreneur_Email;
    @Field
    private String Entrepreneur_Name;
    @Field
    private String Request_Customer_Email;
    @Field
    private Date requestDate;

    public RequestLogCustomerEmail(String Entrepreneur_Email,String Entrepreneur_Name,String Request_Customer_Email){
        this.Entrepreneur_Email = Entrepreneur_Email;
        this.Entrepreneur_Name = Entrepreneur_Name;
        this.Request_Customer_Email = Request_Customer_Email;
        this.requestDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntrepreneur_Email() {
        return Entrepreneur_Email;
    }

    public void setEntrepreneur_Email(String Entrepreneur_Email) {
        this.Entrepreneur_Email = Entrepreneur_Email;
    }
     
    public String getRequest_Customer_Email() {
        return Request_Customer_Email;
    }

    public void setRequest_Customer_Email(String Request_Customer_Email) {
        this.Request_Customer_Email = Request_Customer_Email;
    } 

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getEntrepreneur_Name() {
        return Entrepreneur_Name;
    }

    public void setEntrepreneur_Name(String Entrepreneur_Name) {
        this.Entrepreneur_Name = Entrepreneur_Name;
    }
    
}
