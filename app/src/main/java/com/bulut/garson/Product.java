package com.bulut.garson;

import java.util.ArrayList;
import java.util.Locale;

public class Product {

    private static int id = 0;
    private float price;
    private String name;
    private String description;
    private String type;
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Product> basket = new ArrayList<>();

    public Product(float price, String name, String description, String type) {
        this.id = id++;
        this.price = price;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getPriceString() {
        return (int) price + " ₺";
    }

    public void changePrice(){
        price = price + 2;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Id: %d Price: %f, Name: %s, Description: %s, Type: %s", id, price, name, description ,type);
    }

}
