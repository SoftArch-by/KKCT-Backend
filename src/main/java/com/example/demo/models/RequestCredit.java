package com.example.demo.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class RequestCredit {
    @Field
    List<Transaction> historyTransaction;
    @Field
    Credit creditCalculation;

    public RequestCredit(Credit creditCalcuCredit ,List<Transaction> historyTransaction){
        this.historyTransaction = historyTransaction;
        this.creditCalculation = creditCalcuCredit;
    }

    public List<Transaction> getHistoryTransaction() {
        return historyTransaction;
    }

    public void setHistoryTransaction(List<Transaction> historyTransaction) {
        this.historyTransaction = historyTransaction;
    }

    public Credit getCreditCalculation() {
        return creditCalculation;
    }

    public void setCreditCalculation(Credit creditCalculation) {
        this.creditCalculation = creditCalculation;
    }

    

    
}
