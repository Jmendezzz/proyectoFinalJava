package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.controller.ModelFactoryController;
import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.model.UserCredentials;
import com.example.finaljavaproyect.service.RegisterService;
import com.example.finaljavaproyect.validations.RegisterValidation;

public class RegisterImpl implements RegisterService {
    RegisterValidation registerValidation = new RegisterValidation();
    private final Marketcol marketcol;

    public RegisterImpl(Marketcol marketcol) {
        this.marketcol = marketcol;
    }

    public void registerUser (String name, String email, String id, String cellphoneNumber, String password, String passwordConfirmed)throws RegisterException {
        registerValidation.verifyPasswords(password,passwordConfirmed);
        registerValidation.verifyIdInput(id);
        registerValidation.verifyCellphoneNumberInput(cellphoneNumber);
        registerValidation.verifyEmailRepeated(email, marketcol.getUserService().getUsers());
        registerValidation.verifyIdRepeated(id,marketcol.getUserService().getUsers());


        marketcol.getUserService().addUser(
                 new User(name,email,id,cellphoneNumber,new UserCredentials(email,password))
         );

    }
}
