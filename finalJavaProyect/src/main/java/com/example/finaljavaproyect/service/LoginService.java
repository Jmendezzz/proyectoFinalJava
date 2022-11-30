package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.exceptions.LoginException;
import com.example.finaljavaproyect.model.User;

import java.util.ArrayList;

public interface LoginService {

    void verifyLoginCredential(String email, String password, ArrayList<User> userList) throws LoginException;
    User userLoginActive();
}
