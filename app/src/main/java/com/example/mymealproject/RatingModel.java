package com.example.mymealproject;

public class RatingModel {
    private String userID;
    private String dishID;
    private String rateValue;

    public RatingModel() {
    }

    public RatingModel(String userID, String dishID, String rateValue) {
        this.userID = userID;
        this.dishID = dishID;
        this.rateValue = rateValue;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDishID() {
        return dishID;
    }

    public void setDishID(String dishID) {
        this.dishID = dishID;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }
}

