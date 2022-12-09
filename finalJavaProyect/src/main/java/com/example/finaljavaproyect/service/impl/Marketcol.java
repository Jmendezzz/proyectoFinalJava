package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.model.Cart;
import com.example.finaljavaproyect.service.*;

public class Marketcol {
    private final LoginService loginService;
    private final RegisterService registerService;
    private final UserService userService;
    private final CartService cartService;

    private  final PublicationService publicationService;

    private  final CartDetailService cartDetailService;

    private final SaleService saleService;

    public Marketcol (){
        loginService = new LoginImpl(this);
        registerService = new RegisterImpl(this);
        userService = new UserImpl(this);
        publicationService = new PublicationImpl(this);
        cartService = new CartImpl(this);
        cartDetailService =new CartDetailImpl(this);
        saleService = new SaleImpl(this);
    }

    public PublicationService getPublicationService() {
        return publicationService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public UserService getUserService() {
        return userService;
    }
    public CartService getCartService(){
        return cartService;
    }

    public CartDetailService getCartDetailService() {
        return cartDetailService;
    }

    public SaleService getSaleService() {
        return saleService;
    }
}
