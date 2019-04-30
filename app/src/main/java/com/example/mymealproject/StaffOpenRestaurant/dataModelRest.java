package com.example.mymealproject.StaffOpenRestaurant;

import android.support.v7.app.AppCompatActivity;

public class dataModelRest{

    public dataModelRest(String restName, String adress, String contact) {
        mRestName = restName;
        mAdress = adress;
        mContact = contact;
    }

    public String getRestName() {
        return mRestName;
    }

    public void setRestName(String restName) {
        mRestName = restName;
    }

    public String getAdress() {
        return mAdress;
    }

    public void setAdress(String adress) {
        mAdress = adress;
    }

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

    public String mRestName;
    public String mAdress;
    public  String mContact;




}
