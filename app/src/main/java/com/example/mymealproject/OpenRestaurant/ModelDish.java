package com.example.mymealproject.OpenRestaurant;

public class ModelDish {
    private String rID;
    private String name;
    private String price;
    private String catagory;

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public ModelDish( String name, String price, String catagory, String rID) {

        this.name = name;
        this.price = price;
        this.catagory = catagory;
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

}
