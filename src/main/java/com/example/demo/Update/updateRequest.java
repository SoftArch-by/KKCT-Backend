package com.example.demo.Update;

public class updateRequest {
    private String ID;
    private int paid;
    updateRequest(String ID,int paid){
        this.ID =ID;
        this.paid = paid;
    }
    
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPaid() {
        return this.paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
    

}
