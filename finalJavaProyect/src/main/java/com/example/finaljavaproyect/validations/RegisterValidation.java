package com.example.finaljavaproyect.validations;

import com.example.finaljavaproyect.exceptions.RegisterException;

public class RegisterValidation {

    public void verifyPasswords(String password, String confirmedPassword) throws RegisterException {
        if(!password.equals(confirmedPassword)) throw new RegisterException("Las contraseñas ingresadas no son iguales, intentelo de nuevo.");
    }
    public void verifyCellphoneNumberInput(String cellphoneNumber ) throws RegisterException {
        if(cellphoneNumber.trim().length()!=10) throw new RegisterException("El número telefonico debe tener 10 cifras.");
    }
    public void verifyIdInput (String id) throws RegisterException{
        if(id.trim().length()!=10) throw new RegisterException("La identificación ingresada no es válida, debe contener 10 cifras");
    }
}
