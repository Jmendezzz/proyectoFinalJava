package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.exceptions.EditUserException;
import com.example.finaljavaproyect.exceptions.InputException;
import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.threads.MyProfileThread;
import com.example.finaljavaproyect.validations.EditUserValidation;
import com.example.finaljavaproyect.validations.InputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class MyProfileController implements Initializable , Runnable {

    ModelFactoryController mfc = ModelFactoryController.getInstance();

    User userActive = mfc.marketcol.getLoginService().userLoginActive();
    InputValidation inputValidation = new InputValidation();
    EditUserValidation editUserValidation = new EditUserValidation();
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
    private Button myUserButton;
    File fileUser = new File("src/main/resources/com/example/finaljavaproyect/images/userImg.png");
    Image imgUser = new Image(fileUser.toURI().toString());
    ImageView imageViewUser = new ImageView(imgUser);

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField idField;


    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private TextField cellphoneNumberField;

    @FXML
    private TextField emailField;
    @Override
    public void run() {
        initalizeMyInfoFields();
        disableInputs(); // Cuando se carguen las publicaciones sera más útil.

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        run();
        applyButtonImages();


    }

    //Cambiar imagenes de botones del nav

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

    public void initalizeMyInfoFields() {
        fullNameField.setText(userActive.getName());
        idField.setText(userActive.getId());
        emailField.setText(userActive.getEmail());
        cellphoneNumberField.setText(userActive.getCellphoneNumber());
    }

    public void disableInputs() { // El correo electronico no se podra editar, tampoco el ID
        emailField.setEditable(false);
        emailField.setMouseTransparent(true);
        emailField.setFocusTraversable(false);
        emailField.setCursor(Cursor.DISAPPEAR);
        idField.setEditable(false);
        idField.setMouseTransparent(true);
        idField.setFocusTraversable(false);
        idField.setCursor(Cursor.DISAPPEAR);

    }

    @FXML
    void logoutHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToLoginScene(e);
        userActive.setLoginStatus(false);
    }

    @FXML
    void myProfileHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToMyProfileScene(e);
    }

    @FXML
    void cartHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToCartScene(e);
    }

    //My Profile functions
    @FXML
    void editMyProfileHandler(ActionEvent e) {
        try {
            verifyNoEmptyEditableInputs();
            if (passwordField.getText().equals("") && newPasswordField.getText().equals("")) { //Si se desean cambiar los otros campos no sera necesario el ingreso de la contraseña.
                mfc.marketcol.getUserService()
                        .editUser(userActive,
                                fullNameField.getText(),
                                cellphoneNumberField.getText(),
                                userActive.getUserCredentials().getPassword()
                        );
            } else {
                editUserValidation.verifyPasswordToEdit(userActive,passwordField.getText()); // Verificar que la contraseña actual ingresada sea correcta y asi permitir el cambio a una nueva contraseña.
                mfc.marketcol.getUserService()
                        .editUser(userActive,
                                fullNameField.getText(),
                                cellphoneNumberField.getText(),
                                newPasswordField.getText()
                        );

            }
        AlertMessage.informationMessage("Se han actualizado los datos");

        } catch (InputException | RegisterException  | EditUserException err) {
            AlertMessage.errMessage(err.getMessage());
        }
    }

    void verifyNoEmptyEditableInputs() throws InputException {
        inputValidation.verifyEmptyInput(fullNameField.getText());
        inputValidation.verifyEmptyInput(cellphoneNumberField.getText());
    }
    @FXML
    private TextField publicationNameField;

    @FXML
    private Button saveChangesButton;

    @FXML
    private TextField urlImageField;
    @FXML
    private TextField articlePriceField;

    @FXML
    private TextField avaibleAmountField;


    @FXML
    private Button createPublicationButton;

    @FXML
    private TextArea descriptionArticleField;
    //Create a publication functions





    void verifyNoEmptyPublicationInputs() throws InputException {
        inputValidation.verifyEmptyInput(publicationNameField.getText());
        inputValidation.verifyEmptyInput(urlImageField.getText());
        inputValidation.verifyEmptyInput(avaibleAmountField.getText());
        inputValidation.verifyEmptyInput(articlePriceField.getText());
        inputValidation.verifyEmptyInput(descriptionArticleField.getText());

    }
    void verifyNumericalPublicationInputs() throws InputException{
        inputValidation.verifyNumericalInput(articlePriceField.getText());
        inputValidation.verifyNumericalInput(avaibleAmountField.getText());
    }

    @FXML
    void createPublicationHandler(){
        try{
            verifyNoEmptyPublicationInputs();
            verifyNumericalPublicationInputs();
            mfc.marketcol.getUserService()
                    .addPublicationToUser(
                            userActive,
                            new Publication(
                                    userActive,
                                    Integer.parseInt(articlePriceField.getText()) ,
                                    Integer.parseInt(avaibleAmountField.getText()),
                                    publicationNameField.getText(),
                                    descriptionArticleField.getText(),
                                    urlImageField.getText()
                                    )
                    );

        }catch (InputException err){
            AlertMessage.errMessage(err.getMessage());
        }
    }



}
