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
    private String Enterpreneur_Email;
    @Field
    private String Enterpreneur_Name;
    @Field
    private String Request_Customer_Cid;
    @Field
    private Date requestDate;

    public RequestLog(String Enterpreneur_Email,String Enterpreneur_Name,String Request_Customer_Cid){
        this.Enterpreneur_Email = Enterpreneur_Email;
        this.Enterpreneur_Name = Enterpreneur_Name;
        this.Request_Customer_Cid = Request_Customer_Cid;
        this.requestDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpreneur_Email() {
        return Enterpreneur_Email;
    }

    public void setEnterpreneur_Email(String Enterpreneur_Email) {
        this.Enterpreneur_Email = Enterpreneur_Email;
    }
     
    public String getRequest_Customer_Cid() {
        return Request_Customer_Cid;
    }

    public void setRequest_Customer_Cid(String Request_Customer_Cid) {
        this.Request_Customer_Cid = Request_Customer_Cid;
    } 

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getEnterpreneur_Name() {
        return Enterpreneur_Name;
    }

    public void setEnterpreneur_Name(String enterpreneur_Name) {
        Enterpreneur_Name = enterpreneur_Name;
    }
    
}
