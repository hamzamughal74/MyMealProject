package com.example.mymealproject.StaffOpenRestaurant;

import android.widget.Spinner;



public class MenuModel  {
    public String getrID() {
        return rID;
    }

    public MenuModel() {
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    private String rID;
    public String name;
   public String price;
    private String catagory;

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    private  String restName;

    public MenuModel(String name, String price, String catagory,String rID,String restName) {
        this.name = name;
        this.price = price;
        this.catagory = catagory;
        this.rID = rID;
        this.restName = restName;
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

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }




}
