package com.example.demo.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class RequestLogCustomerEmail extends RequestLogCustomer{

    private String Request_Customer_Email;

    public RequestLogCustomerEmail(String Entrepreneur_Email,String Entrepreneur_Name,String Request_Customer_Email){
        this.Entrepreneur_Email = Entrepreneur_Email;
        this.Entrepreneur_Name = Entrepreneur_Name;
        this.Request_Customer_Email = Request_Customer_Email;
        this.requestDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getRequest_Customer_Email() {
        return Request_Customer_Email;
    }

    public void setRequest_Customer_Email(String request_Customer_Email) {
        Request_Customer_Email = request_Customer_Email;
    }
    
    
}
