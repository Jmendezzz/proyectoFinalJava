package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.controller.ModelFactoryController;
import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.model.User;

public interface RegisterService {
    void registerUser(String name, String email, String id, String cellphoneNumber, String password, String passwordConfirmed) throws  RegisterException;
}
