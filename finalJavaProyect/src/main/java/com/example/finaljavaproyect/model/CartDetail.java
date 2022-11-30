package com.example.finaljavaproyect.model;

public class CartDetail {

    private Publication wishPublication;
    private int amount;

    public CartDetail(Publication whishPublication, int amount) {
        this.wishPublication = whishPublication;
        this.amount = amount;
    }

    public Publication getWishPublication() {
        return wishPublication;
    }

    public void setWishPublication(Publication wishPublication) {
        this.wishPublication = wishPublication;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
