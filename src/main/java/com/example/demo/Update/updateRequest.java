package com.example.demo.Update;

import com.fasterxml.jackson.annotation.JsonProperty;


public class updateRequest {
    
    private String ID;
    private Double paid;
    updateRequest(@JsonProperty("ID") String ID,@JsonProperty("paid") Double paid){
        this.ID =ID;
        this.paid = paid;
    }

    public String getID() {
        return this.ID;
    }

    public Double getPaid() {
        return this.paid;
    }

}