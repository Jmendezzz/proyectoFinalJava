package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.PublicationViewHelper;
import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.threads.MyProfileThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class MainMenuController implements Initializable {

    ModelFactoryController mfc = ModelFactoryController.getInstance();

    User userActive = mfc.marketcol.getLoginService().userLoginActive();
    @FXML
    private Button cartButton;
    File fileCart= new File("src/main/resources/com/example/finaljavaproyect/images/cartImg.png");
    Image imgCart = new Image(fileCart.toURI().toString());
    ImageView imageViewCart = new ImageView(imgCart);

    @FXML
    private Button logoutButton;
    File fileLogOut = new File("src/main/resources/com/example/finaljavaproyect/images/logoutImg.png");
    Image imgLogOut = new Image(fileLogOut.toURI().toString());
    ImageView imageViewLogOut = new ImageView(imgLogOut);


    @FXML
    private Button myUserButton;
    File fileUser= new File("src/main/resources/com/example/finaljavaproyect/images/userImg.png");
    Image imgUser = new Image(fileUser.toURI().toString());
    ImageView imageViewUser = new ImageView(imgUser);


    @FXML
    private Text welcomeText;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        applyButtonImages();
        welcomeMainMenuMessage();
        Platform.runLater(()->loadPublications());

    }

    void applyButtonImages (){
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


    void welcomeMainMenuMessage(){
        welcomeText.setText("Hola, " + userActive.getName().split(" ")[0] + " ¿Qué deseas comprar hoy?");
    }

    @FXML
    void logoutHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToLoginScene(e);
        userActive.setLoginStatus(false);
    }
    @FXML
    void myProfileHandler(ActionEvent e)throws IOException{
        SwitchScene.switchToMyProfileScene(e);
    }
    @FXML
    void cartHandler (ActionEvent e) throws  IOException{
        SwitchScene.switchToCartScene(e);
    }


    @FXML
    private HBox publicationsLayout;

    void loadPublications (){

        mfc.marketcol.getUserService().getUsers().stream().forEach((user)->{
            System.out.println("+++*****"+user.getName());

            user.getPublications().stream().forEach((publication)->{
                        System.out.println("*****"+ publication.getTitle());


                        try {
                            PublicationViewHelper publicationViewHelper = new PublicationViewHelper();
                            publicationViewHelper.setLocation();


                            VBox vBox = publicationViewHelper.fxmlLoader.load();

                            PublicationItemController publicationItemController = publicationViewHelper.fxmlLoader.getController();
                            publicationItemController.setData(publication);
                            publicationsLayout.getChildren().add(vBox);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                    );



        });
    }


}
