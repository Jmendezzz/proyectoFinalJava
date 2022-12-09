package com.example.finaljavaproyect.model;

public class Publication {

    private User user;

    private int price;

    private int amount;

    private String title;

    private String description;

    private String category;
    private String urlImage;

    public Publication(User user, int price, int amount, String title, String description,String category, String urlImage) {
        this.user = user;
        this.price = price;
        this.amount = amount;
        this.title = title;
        this.description = description;
        this.category = category;
        this.urlImage = urlImage;
    }
    public Publication(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
