package com.example.finaljavaproyect.model;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String id;
    private String cellphoneNumber;
    private UserCredentials userCredentials;
    private Boolean loginStatus = false;

    //private ArrayList<Publication> publications = new ArrayList<>();

    private Cart cart;

    public User(String name,  String email, String id, String cellphoneNumber, UserCredentials userCredentials) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.cellphoneNumber = cellphoneNumber;
        this.userCredentials = userCredentials;
    }
    public User(){

    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
