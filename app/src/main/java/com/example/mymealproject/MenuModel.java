package com.example.mymealproject;


import android.os.Parcel;
import android.os.Parcelable;

public class MenuModel implements Parcelable {
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

    protected MenuModel(Parcel in) {
        rID = in.readString();
        name = in.readString();
        price = in.readString();
        catagory = in.readString();
        Rating = in.readString();
        ratingCount = in.readString();
        totalRating = in.readString();
        person = in.readString();
        imageUrl = in.readString();
        restName = in.readString();
        mID = in.readString();
        tag = in.readString();
        restCity = in.readString();
    }

    public static final Creator<MenuModel> CREATOR = new Creator<MenuModel>() {
        @Override
        public MenuModel createFromParcel(Parcel in) {
            return new MenuModel(in);
        }

        @Override
        public MenuModel[] newArray(int size) {
            return new MenuModel[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rID);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(catagory);
        dest.writeString(Rating);
        dest.writeString(ratingCount);
        dest.writeString(totalRating);
        dest.writeString(person);
        dest.writeString(imageUrl);
        dest.writeString(restName);
        dest.writeString(mID);
        dest.writeString(tag);
        dest.writeString(restCity);
    }
}
