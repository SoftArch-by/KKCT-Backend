package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class Credit {
    @Field
    private float creditScore ;
    @Field
    private String recommend;

    public Credit(float creditScore, String recommend){
        this.creditScore = creditScore;
        this.recommend = recommend;
    }

    public float getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(float creditScore) {
        this.creditScore = creditScore;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
    
}
