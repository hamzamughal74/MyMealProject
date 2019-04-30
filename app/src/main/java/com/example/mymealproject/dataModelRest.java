package com.example.mymealproject;

import android.support.v7.app.AppCompatActivity;

public class dataModelRest{

    public dataModelRest(String name, String adress, String contact) {
        this.name = name;
        this.adress = adress;
        this.contact = contact;
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

    String name;
  String adress;
  String contact;
}
