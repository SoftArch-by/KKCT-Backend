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
    private String enterpreneurId;
    @Field
    private String Request_customerId;
    @Field
    private Date requestDate;

    public RequestLog(String enterpreneurId,String Request_customerId){
        this.enterpreneurId = enterpreneurId;
        this.Request_customerId = Request_customerId;
        this.requestDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpreneurId() {
        return enterpreneurId;
    }

    public void setEnterpreneurId(String enterpreneurId) {
        this.enterpreneurId = enterpreneurId;
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
