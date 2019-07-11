package com.example.mymealproject;


public class MenuModel  {
    private String rID;
    public String name;
    public String price;
    private String catagory;
    private  String Rating;
    private String ratingCount;
    private String totalRating;
    private String person;
    private String imageUrl;
    private  String restName;
    private String mID;
    private String tag;
    private String restCity;

    public MenuModel() {
    }

    public String getRestCity() {
        return restCity;
    }

    public void setRestCity(String restCity) {
        this.restCity = restCity;
    }

    public String getrID() {
        return rID;
    }


    public void setrID(String rID) {
        this.rID = rID;
    }


    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }



    public MenuModel(String name, String price, String catagory,String rID,String restName,
                     String imageUrl, String mID,String Rating,
                     String ratingCount,String totalRating,String person,String tag , String restCity) {
        this.name = name;
        this.price = price;
        this.catagory = catagory;
        this.rID = rID;
        this.restName = restName;
        this.imageUrl = imageUrl;
        this.mID = mID;
        this.Rating = Rating;
        this.ratingCount = ratingCount;
        this.totalRating = totalRating;
        this.person = person;
        this.tag = tag;
        this.restCity = restCity;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(String totalRating) {
        this.totalRating = totalRating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
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
