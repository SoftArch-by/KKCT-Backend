package com.example.demo.Update;

import java.sql.Date;

public class createTransactionEmail {
    private String c_email;
    private String email;
    private String OrganizationName ;
    private Double Money;
    private Date DueDate;
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

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationName() {
        return this.OrganizationName;
    }

    public void setOrganizationName(String OrganizationName) {
        this.OrganizationName = OrganizationName;
    }

    public Double getMoney() {
        return this.Money;
    }

    public void setMoney(Double Money) {
        this.Money = Money;
    }
    public Date getDueDate() {
        return this.DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

}
