package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.model.Cart;
import com.example.finaljavaproyect.model.CartDetail;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;

import java.util.ArrayList;

public interface CartService {

    void addCart(User user, Publication wishArticle);

    void initializeCartList();

    void saveCarts();

    Cart getUserCart(User user);

    void updateTotalPrice(User user);

    ArrayList<CartDetail> getUserCartDetail(User user);
    int calculateTotalPrice(User user);

    void verifyUserCartIsEmpty(User user);

    void deleteCart(User user);

    void deletedPublication(Publication publication);
}
