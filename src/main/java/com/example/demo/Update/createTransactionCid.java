package com.example.demo.Update;

import java.sql.Date;

public class createTransactionCid extends createTransaction{
    private String citizen_id;
    createTransactionCid (
        String citizen_id,
        String email,
        String OrganizationName ,
        Double Money,
        Date DueDate)
     {
        this.citizen_id = citizen_id;
        this.email = email;
        this.OrganizationName = OrganizationName;
        this.Money = Money;
        this.DueDate = DueDate;
     }

    public String getCitizen_id() {
        return this.citizen_id;
    }
}
