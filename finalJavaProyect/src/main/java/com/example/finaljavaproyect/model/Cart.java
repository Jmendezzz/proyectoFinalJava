package com.example.finaljavaproyect.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartDetail> wishItems;
    private int totalPrice;

    public Cart(ArrayList<CartDetail> wishItems, int totalPrice) {
        this.wishItems = wishItems;
        this.totalPrice = totalPrice;
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
}
