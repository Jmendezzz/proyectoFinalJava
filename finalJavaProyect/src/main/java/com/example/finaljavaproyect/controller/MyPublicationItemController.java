package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.exceptions.InputException;
import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.validations.InputValidation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MyPublicationItemController {

    ModelFactoryController mfc =ModelFactoryController.getInstance();


    @FXML
    private TextField articlePriceField;

    @FXML
    private TextField avaibleAmountField;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea descriptionPublication;

    @FXML
    private Button editButton;

    @FXML
    private ImageView publicationImage;

    @FXML
    private TextField publicationNameField;

    Publication publication;
    @FXML
    private TextField urlImageField;

    public void setData(Publication publication){
        this.publication = publication;
        try {
            Image image = new Image(publication.getUrlImage());
            publicationImage.setImage(image);

        }catch(IllegalArgumentException err){
            File fileImageNotFound= new File("src/main/resources/com/example/finaljavaproyect/images/imageNotFound.png");
            Image img = new Image(fileImageNotFound.toURI().toString());
            publicationImage.setImage(img);

        }
        articlePriceField.setText(String.valueOf(publication.getPrice()));
        avaibleAmountField.setText(String.valueOf(publication.getAmount()));
        descriptionPublication.setText(publication.getDescription());

        publicationNameField.setText(publication.getTitle());
        urlImageField.setText(publication.getUrlImage());
    }

    InputValidation inputValidation = new InputValidation();
    @FXML
    public void editPublication(){

        try{
            inputValidation.verifyEmptyInput(articlePriceField.getText());
            inputValidation.verifyEmptyInput(publicationNameField.getText());
            inputValidation.verifyEmptyInput(descriptionPublication.getText());
            inputValidation.verifyEmptyInput(urlImageField.getText());
            inputValidation.verifyEmptyInput(avaibleAmountField.getText());
            inputValidation.verifyNumericalInput(articlePriceField.getText());
            inputValidation.verifyNumericalInput(avaibleAmountField.getText());

            mfc.marketcol.getPublicationService().editPublication
                    (
                            publication,
                            Integer.parseInt(articlePriceField.getText()),
                            Integer.parseInt(avaibleAmountField.getText()),
                            publicationNameField.getText(),
                            descriptionPublication.getText(),
                            urlImageField.getText()
                    );
            AlertMessage.informationMessage("Se ha editado la publicación correctamente");

        }catch (InputException exception){
            AlertMessage.errMessage(exception.getMessage());
        }


    }
    @FXML
    void deletePublication(){
        mfc.marketcol.getPublicationService().deletePublication(publication);
        AlertMessage.informationMessage("Se ha eliminado la publicación "+publication.getTitle() + " Todos los registros de venta de esta publicacion también seran eliminados. ");
    }

}
