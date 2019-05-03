package com.example.mymealproject.DiscoverRestaurant;

public class ModelFood {
    private String name,price,restName;
    public ModelFood() {
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public ModelFood(String name, String price, String restName) {
        this.restName = restName;
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }





}
