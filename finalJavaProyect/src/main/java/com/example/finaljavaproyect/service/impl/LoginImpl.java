package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.exceptions.LoginException;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.service.LoginService;

import java.util.ArrayList;

public class LoginImpl implements LoginService {

    private final Marketcol marketcol;

    public LoginImpl(Marketcol marketcol) {
        this.marketcol = marketcol;
    }


    @Override
    public void verifyLoginCredential(String email, String password, ArrayList<User> userList ) throws LoginException{
        System.out.println(userList.size());

        for (User user: userList){
            System.out.println("work in loop");
            System.out.println(user.getEmail());
        }

        marketcol.getUserService().getUsers().stream()
                .filter(user -> user.getUserCredentials().getEmail().equals(email)
                                &&
                                user.getUserCredentials().getPassword().equals(password))
                .findFirst()
                .orElseThrow(()-> new LoginException("Credenciales invalidas."))
                .setLoginStatus(true);
    }

    @Override
    public User userLoginActive() {
        return marketcol.getUserService().getUsers()
                .stream()
                .filter(user -> user.getLoginStatus())
                .findFirst()
                .orElse(null);
    }


}
