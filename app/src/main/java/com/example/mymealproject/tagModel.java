package com.example.mymealproject;

import java.util.List;

public class tagModel {
    private String rID;
    private String name;
    private String price;
    private String catagory;
    private  String Rating;
    private String ratingCount;
    private String totalRating;
    private String person;
    private String mID;
    private  String restName;
    private String imageUrl;
    private List taglist;

    public tagModel(String name, String price, String catagory,String rID,String restName,
                    String imageUrl, String mID,String Rating,String ratingCount,String totalRating,String person,
                    List taglist) {
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
        this.taglist = taglist;
    }

    public List getTaglist() {
        return taglist;
    }

    public void setTaglist(List taglist) {
        this.taglist = taglist;
    }

    public tagModel() {
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
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

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(String totalRating) {
        this.totalRating = totalRating;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
