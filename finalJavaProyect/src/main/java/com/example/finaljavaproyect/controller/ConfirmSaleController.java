package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.exceptions.InputException;
import com.example.finaljavaproyect.exceptions.SaleException;
import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.validations.InputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmSaleController implements Initializable {
    ModelFactoryController mfc = ModelFactoryController.getInstance();
    User userActive = mfc.marketcol.getLoginService().userLoginActive();
    @FXML
    private Button cartButton;
    File fileCart = new File("src/main/resources/com/example/finaljavaproyect/images/cartImg.png");
    Image imgCart = new Image(fileCart.toURI().toString());
    ImageView imageViewCart = new ImageView(imgCart);

    @FXML
    private Button logoutButton;
    File fileLogOut = new File("src/main/resources/com/example/finaljavaproyect/images/logoutImg.png");
    Image imgLogOut = new Image(fileLogOut.toURI().toString());
    ImageView imageViewLogOut = new ImageView(imgLogOut);
    @FXML
    private TextField creditCartField;
    @FXML
    private TextField addressField;

    @FXML
    private Button myUserButton;
    File fileUser = new File("src/main/resources/com/example/finaljavaproyect/images/userImg.png");
    Image imgUser = new Image(fileUser.toURI().toString());
    ImageView imageViewUser = new ImageView(imgUser);
    @FXML
    void logoutHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToLoginScene(e);
        userActive.setLoginStatus(false);
    }
    void applyButtonImages() {
        imageViewLogOut.setFitWidth(64);
        imageViewLogOut.setFitHeight(64);
        logoutButton.setGraphic(imageViewLogOut);

        imageViewUser.setFitHeight(64);
        imageViewUser.setFitHeight(64);
        myUserButton.setGraphic(imageViewUser);

        imageViewCart.setFitHeight(64);
        imageViewCart.setFitWidth(64);
        cartButton.setGraphic(imageViewCart);

    }

    @FXML
    void myProfileHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToMyProfileScene(e);
    }
    @FXML
    void switchToMainMenuHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToMainMenu(e);
    }


    @FXML
    void cartHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToCartScene(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        applyButtonImages();
    }
    InputValidation inputValidation = new InputValidation();

    public void onConfirmSaleHandler(ActionEvent e) throws IOException {
        try{
            inputValidation.verifyEmptyInput(addressField.getText());
            inputValidation.verifyEmptyInput(creditCartField.getText());

            mfc.marketcol.getSaleService().realizeSale(mfc.marketcol.getCartService().getUserCart(userActive));
            SwitchScene.switchToMainMenu(e);
            AlertMessage.informationMessage("Se ha realizado la compra exitosamente, esperamos que pronto pueda disfrutar de sus productos!");

        }catch (SaleException | InputException err){
            AlertMessage.errMessage(err.getMessage());
        }

    }
}
