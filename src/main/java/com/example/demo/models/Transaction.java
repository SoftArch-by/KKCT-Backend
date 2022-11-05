package com.example.demo.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import  org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.repositories.TransactionRepository;
@Document
public class Transaction {
    @Id
    private String id;
    @Field
    private String customerID;
    @Field
    private  String Entreprenuer_ID;
    @Field
    private Double TransactionInfo;
    @Field
    private Double Unpaid;
    @Field
    private LocalDate TransactionDate;
    @Field
    private LocalDate DueDate;
    @Field
    private String Status;

    // public  Transaction(){
    // }
        
    public Transaction(String customerID, String Entreprenuer_ID,Double TransactionInfo,LocalDate TransactionDate,LocalDate DueDate) {
        this.customerID = customerID;
        this.Entreprenuer_ID = Entreprenuer_ID;
        this.TransactionInfo = TransactionInfo;
        this.Unpaid = TransactionInfo;
        this.TransactionDate = TransactionDate;
        this.DueDate = DueDate;
        this.Status = "In debt";
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

    public String getEntreprenuer_ID() {
        return this.Entreprenuer_ID;
    }

    public void setEntreprenuer_ID(String Entreprenuer_ID) {
        this.Entreprenuer_ID = Entreprenuer_ID;
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

    public LocalDate getTransactionDate() {
        return this.TransactionDate;
    }

    public void setTransactionDate(LocalDate TransactionDate) {
        this.TransactionDate = TransactionDate;
    }

    public LocalDate getDueDate() {
        return this.DueDate;
    }

    public void setDueDate(LocalDate DueDate) {
        this.DueDate = DueDate;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customerID='" + getcustomerID() + "'" +
            ", EntreprenuerName='" + getEntreprenuer_ID() + "'" +
            ", TransactionInfo='" + getTransactionInfo() + "'" +
            ", Unpaid='" + getUnpaid() + "'" +
            ", TransactionDate='" + getTransactionDate() + "'" +
            ", DueDate='" + getDueDate() + "'" +
            ", Status='" + getStatus() + "'" +
            "}";
    }

    public Transaction orElseGet(Object object) {
        return null;
    }

}
