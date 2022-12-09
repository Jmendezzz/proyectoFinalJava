package com.example.finaljavaproyect.model;

import java.util.ArrayList;

public class Cart {

    User user;
    private ArrayList<CartDetail> wishItems;
    private int totalPrice;

    public Cart(User user, ArrayList<CartDetail> wishItems, int totalPrice) {
        this.user = user;
        this.wishItems = wishItems;
        this.totalPrice = totalPrice;
    }

    public Cart() {

    }

    public ArrayList<CartDetail> getWishItems() {
        return wishItems;
    }

    public void setWishItems(ArrayList<CartDetail> wishItems) {
        this.wishItems = wishItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
