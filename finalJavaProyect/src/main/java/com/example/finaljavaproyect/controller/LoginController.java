package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.exceptions.LoginException;
import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class LoginController{
    ModelFactoryController mfc = ModelFactoryController.getInstance();

    @FXML
    private VBox cont;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginEmailField;


    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Button registerButtonLoginScreen;



    @FXML
    void loginHandler(ActionEvent e) {
        try {
            mfc.marketcol.getLoginService().verifyLoginCredential(
                    loginEmailField.getText(),
                    loginPasswordField.getText(),
                    mfc.marketcol.getUserService().getUsers() // Lista usuarios
            );
            AlertMessage.informationMessage("Bienvenido");
            SwitchScene.switchToMainMenuScene(e);


        } catch (LoginException err) {
            AlertMessage.errMessage(err.getMessage());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void switchToRegisterSceneHandler(ActionEvent event) throws IOException {

        SwitchScene.switchToRegister(event);


    }

}