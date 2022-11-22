package com.example.demo.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RequestLogCustomerCid extends RequestLogCustomer {

    private String Request_Customer_Cid;

    public RequestLogCustomerCid(String Entrepreneur_Email,String Entrepreneur_Name,String Request_Customer_Cid){
        this.Entrepreneur_Email = Entrepreneur_Email;
        this.Entrepreneur_Name = Entrepreneur_Name;
        this.Request_Customer_Cid = Request_Customer_Cid;
        this.requestDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getRequest_Customer_Cid() {
        return Request_Customer_Cid;
    }

    public void setRequest_Customer_Cid(String request_Customer_Cid) {
        Request_Customer_Cid = request_Customer_Cid;
    }

}
