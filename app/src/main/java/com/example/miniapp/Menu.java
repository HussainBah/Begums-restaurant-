package com.example.miniapp;


public class Menu{
    private String name;
    private String image;
    private String description;
    private float price;

    public Menu(){

    }

    public Menu(String name, String  image, String description, float price){
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
