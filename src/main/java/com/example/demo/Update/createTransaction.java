package com.example.demo.Update;

import java.sql.Date;

public abstract class createTransaction {
    protected String email;
    protected String OrganizationName ;
    protected Double Money;
    protected Date DueDate;

    public String getEmail() {
        return this.email;
    }

    public String getOrganizationName() {
        return this.OrganizationName;
    }

    public Double getMoney() {
        return this.Money;
    }

    public Date getDueDate() {
        return this.DueDate;
    }
}
