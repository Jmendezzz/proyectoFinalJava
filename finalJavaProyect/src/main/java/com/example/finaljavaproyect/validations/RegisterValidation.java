package com.example.finaljavaproyect.validations;

import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.model.User;

import java.util.ArrayList;

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
    public void verifyEmailRepeated(String email, ArrayList<User> userArrayList) throws RegisterException {
        for(User user : userArrayList){
            if(user.getEmail().equalsIgnoreCase(email)) throw new RegisterException("El correo electronico ingresado ya está en uso.");
        }
    }
    public void verifyIdRepeated(String id, ArrayList<User> userArrayList) throws RegisterException {

        for ( User user:userArrayList){
            if(user.getId().equals(id)) throw new RegisterException("La identificación ingresada ya está en uso.");
        }

    }
}
