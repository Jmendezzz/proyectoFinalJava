package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.model.CartDetail;
import com.example.finaljavaproyect.model.User;

public interface CartDetailService {
    void increaseAmount(CartDetail cartDetail);
    void decreaseAmount(User user, CartDetail cartDetail);

    void deleteCartArticle(User user, CartDetail cartDetail);
}
