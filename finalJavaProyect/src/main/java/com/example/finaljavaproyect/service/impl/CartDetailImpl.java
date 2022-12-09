package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.controller.ModelFactoryController;
import com.example.finaljavaproyect.model.CartDetail;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.service.CartDetailService;

public class CartDetailImpl implements CartDetailService {
    private Marketcol marketcol;

    public CartDetailImpl(Marketcol marketcol) {
        this.marketcol=marketcol;
    }

    @Override
    public void increaseAmount(CartDetail cartDetail) {
        cartDetail.setAmount(cartDetail.getAmount()+1);

        marketcol.getCartService().saveCarts();
    }

    @Override
    public void decreaseAmount(User user, CartDetail cartDetail) {

        if(cartDetail.getAmount()<=1){

            deleteCartArticle(user,cartDetail);
            marketcol.getCartService().verifyUserCartIsEmpty(user);

        }else{

            cartDetail.setAmount(cartDetail.getAmount()-1);
            marketcol.getCartService().saveCarts();

        }
    }

    @Override
    public void deleteCartArticle(User user, CartDetail cartDetail) {

        marketcol.getCartService().getUserCartDetail(user).remove(cartDetail);

        marketcol.getCartService().saveCarts();

        marketcol.getCartService().verifyUserCartIsEmpty(user);

    }


}
