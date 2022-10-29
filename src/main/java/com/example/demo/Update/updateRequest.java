package com.example.demo.Update;

public class updateRequest {
    private String ID;
    private Double paid;
    updateRequest(String ID,Double paid){
        this.ID =ID;
        this.paid = paid;
    }
    
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getPaid() {
        return this.paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }
    

}
