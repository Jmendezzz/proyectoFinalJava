package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PublicationItemController implements Initializable {
    ModelFactoryController mfc = ModelFactoryController.getInstance();
    User user = mfc.marketcol.getLoginService().userLoginActive();

    Publication publication;
    @FXML
    private Button addToCartButton;

    @FXML
    private Text publicationDescription;

    @FXML
    private ImageView publicationImage;

    @FXML
    private Text publicationTitle;
    @FXML
    private Text publicationPrice;

    public void setData (Publication publication){
        this.publication = publication;
        try {
            Image image = new Image(publication.getUrlImage());
            publicationImage.setImage(image);

        }catch(IllegalArgumentException err){
            File fileImageNotFound= new File("src/main/resources/com/example/finaljavaproyect/images/imageNotFound.png");
            Image img = new Image(fileImageNotFound.toURI().toString());
            publicationImage.setImage(img);

        }
        publicationTitle.setText(publication.getTitle());
        publicationDescription.setText(publication.getDescription());
        publicationPrice.setText("$"+ publication.getPrice());


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void addToCartHandler(){
        mfc.marketcol.getCartService().addCart(user,this.publication);
        AlertMessage.informationMessage(publication.getTitle() + " Se ha añadido al carrito!");
    }
}
