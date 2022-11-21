package com.example.demo.models;

import java.util.Date;

public class RequestLogCustomer {
    protected String id;
    protected String Entrepreneur_Email;
    protected String Entrepreneur_Name;
    protected Date requestDate;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEntrepreneur_Email() {
        return Entrepreneur_Email;
    }
    public void setEntrepreneur_Email(String entrepreneur_Email) {
        Entrepreneur_Email = entrepreneur_Email;
    }
    public String getEntrepreneur_Name() {
        return Entrepreneur_Name;
    }
    public void setEntrepreneur_Name(String entrepreneur_Name) {
        Entrepreneur_Name = entrepreneur_Name;
    }
    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    

    
}
