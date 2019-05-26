package com.example.mymealproject;

import android.support.v7.app.AppCompatActivity;

public class dataModelRest{

    public dataModelRest(String name, String adress, String contact,String imageUrl) {
        this.name = name;
        this.adress = adress;
        this.contact = contact;
        this.imageUrl = imageUrl;
    }

    public dataModelRest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String name;
  String adress;
  String contact;
  String imageUrl;
}
