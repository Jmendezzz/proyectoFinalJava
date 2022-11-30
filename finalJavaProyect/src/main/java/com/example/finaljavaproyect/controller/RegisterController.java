package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.exceptions.InputException;
import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import com.example.finaljavaproyect.validations.InputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RegisterController {

    ModelFactoryController mfc = ModelFactoryController.getInstance();
    InputValidation inputValidation = new InputValidation();

    @FXML
    private TextField cellphoneNumberField;
    @FXML
    private VBox cont;

    @FXML
    private TextField emailField;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;


    @FXML
    private PasswordField passwordConfirmedField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;


    @FXML
    public void registerUserHandler (ActionEvent e){

        try{
            verifyNotEmptyInputs();
            verifyNumericalInputs();
            inputValidation.verifyEmailInput(emailField.getText());

            mfc.marketcol.getRegisterService().registerUser(
                    nameField.getText(),
                    emailField.getText(),
                    idField.getText(),
                    cellphoneNumberField.getText(),
                    passwordField.getText(),
                    passwordConfirmedField.getText()
            );

            AlertMessage.informationMessage("Cuenta creada correctamente!");
            SwitchScene.switchToLoginScene(e);
        }catch (InputException | RegisterException | IOException err){
            AlertMessage.errMessage(err.getMessage());
        }


    }

    public void verifyNotEmptyInputs () throws InputException {
        inputValidation.verifyEmptyInput(nameField.getText());
        inputValidation.verifyEmptyInput(emailField.getText());
        inputValidation.verifyEmptyInput(idField.getText());
        inputValidation.verifyEmptyInput(cellphoneNumberField.getText());
        inputValidation.verifyEmptyInput(passwordField.getText());
        inputValidation.verifyEmptyInput(passwordConfirmedField.getText()); // Revisar si puedo utilizar spread operator para recibir una lista de parametros indefinida


    }
    public void verifyNumericalInputs() throws InputException{
        inputValidation.verifyNumericalInput(cellphoneNumberField.getText());
        inputValidation.verifyNumericalInput(idField.getText());
    }

}