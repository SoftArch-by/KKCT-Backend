package com.example.demo.Update;

import java.sql.Date;

public class createTransactionCid {
    private String citizen_id;
    private String email;
    private String OrganizationName ;
    private Double Money;
    private Date DueDate;
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

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
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
