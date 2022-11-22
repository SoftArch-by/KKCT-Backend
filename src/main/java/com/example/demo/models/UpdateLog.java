package com.example.demo.models;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.data.annotation.Id;
import  org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class UpdateLog {
    @Id
    private String id;
    @Field
    private String T_id;
    @Field
    private Double paid;
    @Field
    private Date Update_Date;

    
    public UpdateLog(String T_id, Double paid) {
        this.T_id = T_id;
        this.paid = paid;
        this.Update_Date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()); 
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getT_id() {
        return this.T_id;
    }

    public void setT_id(String T_id) {
        this.T_id = T_id;
    }

    public Double getPaid() {
        return this.paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Date getUpdate_Date() {
        return this.Update_Date;
    }

    public void setUpdate_Date(Date Update_Date) {
        this.Update_Date = Update_Date;
    }

}
