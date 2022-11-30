package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.service.LoginService;
import com.example.finaljavaproyect.service.RegisterService;
import com.example.finaljavaproyect.service.UserService;

public class Marketcol {
    private final LoginService loginService;
    private final RegisterService registerService;
    private final UserService userService;

    public Marketcol (){
        loginService = new LoginImpl(this);
        registerService = new RegisterImpl(this);
        userService = new UserImpl(this);
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

}
