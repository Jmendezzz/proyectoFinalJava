package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.model.Publication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PublicationItemController implements Initializable {
    @FXML
    private Button addToCartButton;

    @FXML
    private Text publicationDescription;

    @FXML
    private ImageView publicationImage;

    @FXML
    private Text publicationTitle;

    public void setData (Publication publication){
        Image imagePublication = new Image(getClass().getResourceAsStream(publication.getUrlImage()));
        publicationImage.setImage(imagePublication);
        publicationTitle.setText(publication.getTitle());
        publicationDescription.setText(publication.getDescription());


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
