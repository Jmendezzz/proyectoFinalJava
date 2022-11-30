package com.example.finaljavaproyect.validations;
import com.example.finaljavaproyect.exceptions.InputException;

import java.math.BigInteger;

public class InputValidation {

    public void verifyEmptyInput (String input) throws InputException {
        if(input.trim().equals("")) throw new InputException("Se deben completar todos los campos de entrada.");
    }

    public void verifyEmailInput (String email) throws InputException{
        if(!email.contains("@") && !email.contains(".")) throw  new InputException("El correo electronico ingresado no es válido.");
    }
    public void verifyNumericalInput(String numericalInput) throws InputException{
        try {
            new BigInteger(numericalInput);

        }catch (NumberFormatException err){
            throw new InputException("Un campo númerico no es válido, estos solo debe contener caracteres numericos");

        }
    }


}
