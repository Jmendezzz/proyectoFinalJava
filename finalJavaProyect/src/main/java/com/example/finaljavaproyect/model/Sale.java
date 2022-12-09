package com.example.finaljavaproyect.model;

public class Sale {

    private User seller;
    private User buyer;
    private Publication soldArticle;
    private int amountSold;
    private int totalPrice;

    public Sale(User seller, User buyer, Publication soldArticle, int amountSold, int totalPrice) {
        this.seller = seller;
        this.buyer = buyer;
        this.soldArticle = soldArticle;
        this.amountSold = amountSold;
        this.totalPrice = totalPrice;
    }

    public Sale() {

    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Publication getSoldArticle() {
        return soldArticle;
    }

    public void setSoldArticle(Publication soldArticle) {
        this.soldArticle = soldArticle;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
