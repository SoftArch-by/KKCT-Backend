package com.example.demo.models;


import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class TransactionToReturn {
    @Id
    private String id;
    @Field
    private String customerID;
    @Field
    private  String Entrepreneur_Name;
    @Field
    private Double TransactionInfo;
    @Field
    private Double Unpaid;
    @Field
    private Date TransactionDate;
    @Field
    private Date DueDate;

    // public  Transaction(){
    // }
        
    public TransactionToReturn(String id,String customerID, String Entrepreneur_Name,Double TransactionInfo,Date DueDate) {
        this.id = id;
        this.customerID = customerID;
        this.Entrepreneur_Name = Entrepreneur_Name;
        this.TransactionInfo = TransactionInfo;
        this.Unpaid = TransactionInfo;
        this.TransactionDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()); 
        this.DueDate = DueDate;
    }

    public String getId() {
        return id;
    }
    

    public String getcustomerID() {
        return this.customerID;
    }

    public void setcustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getEntrepreneur_Name() {
        return this.Entrepreneur_Name;
    }

    public void setEntrepreneur_Name(String Entrepreneur_Name) {
        this.Entrepreneur_Name = Entrepreneur_Name;
    }

    public Double getTransactionInfo() {
        return this.TransactionInfo;
    }

    public void setTransactionInfo(Double TransactionInfo) {
        this.TransactionInfo = TransactionInfo;
    }

    public Double getUnpaid() {
        return this.Unpaid;
    }

    public void setUnpaid(Double Unpaid) {
        this.Unpaid = Unpaid;
    }

    public Date getTransactionDate() {
        return this.TransactionDate;
    }

    public void setTransactionDate(Date TransactionDate) {
        this.TransactionDate = TransactionDate;
    }

    public Date getDueDate() {
        return this.DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customerID='" + getcustomerID() + "'" +
            ", EntrepreneurName='" + getEntrepreneur_Name() + "'" +
            ", TransactionInfo='" + getTransactionInfo() + "'" +
            ", Unpaid='" + getUnpaid() + "'" +
            ", TransactionDate='" + getTransactionDate() + "'" +
            ", DueDate='" + getDueDate() + "'" +
            "}";
    }

    public Transaction orElseGet(Object object) {
        return null;
    }

}

