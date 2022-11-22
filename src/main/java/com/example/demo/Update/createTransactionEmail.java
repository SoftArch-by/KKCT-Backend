package com.example.demo.Update;

import java.sql.Date;

public class createTransactionEmail extends createTransaction{
    private String c_email;
    createTransactionEmail (
        String c_email,
        String email,
        String OrganizationName ,
        Double Money,
        Date DueDate)
     {
        this.c_email =c_email;
        this.email = email;
        this.OrganizationName = OrganizationName;
        this.Money = Money;
        this.DueDate = DueDate;
     }

    public String getC_email() {
        return this.c_email;
    }
}
