package com.example.finaljavaproyect.validations;

import com.example.finaljavaproyect.exceptions.EditUserException;
import com.example.finaljavaproyect.model.User;

public class EditUserValidation {

    public void verifyPasswordToEdit(User user, String password) throws EditUserException {

        if(!user.getUserCredentials().getPassword().equals(password)) throw  new EditUserException("La contraseña ingresada no coincide con la contraseña actual");

    }
}
