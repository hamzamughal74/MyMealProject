package com.example.mymealproject.StaffOpenRestaurant;

public class AdminModelCatagory {
    private  int image;
    private String name;

    public AdminModelCatagory(int image, String name) {
        this.image = image;
        this.name = name;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}