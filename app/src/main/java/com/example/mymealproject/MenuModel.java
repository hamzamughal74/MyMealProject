package com.example.mymealproject;


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

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    private String mID;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    private  String restName;

    public MenuModel(String name, String price, String catagory,String rID,String restName,String imageUrl, String mID) {
        this.name = name;
        this.price = price;
        this.catagory = catagory;
        this.rID = rID;
        this.restName = restName;
        this.imageUrl = imageUrl;
        this.mID = mID;

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
