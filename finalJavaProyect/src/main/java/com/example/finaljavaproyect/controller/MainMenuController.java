package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.NoPublicationsFoundViewHelper;
import com.example.finaljavaproyect.NoSalesViewHelper;
import com.example.finaljavaproyect.PublicationViewHelper;
import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.service.PublicationService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

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
    private Button myUserButton;
    File fileUser = new File("src/main/resources/com/example/finaljavaproyect/images/userImg.png");
    Image imgUser = new Image(fileUser.toURI().toString());
    ImageView imageViewUser = new ImageView(imgUser);


    @FXML
    private Text welcomeText;
    @FXML
    private Button searchArticleButton;

    @FXML
    private TextField searchArticleField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        applyButtonImages();
        welcomeMainMenuMessage();
        initializeChoiceBoxOptions();
        Platform.runLater(() -> loadPublications());

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
    private ScrollPane scrollPaneLayout;

    @FXML
    void welcomeMainMenuMessage() {
        welcomeText.setText("Hola, " + userActive.getName().split(" ")[0] + " ¿Qué deseas comprar hoy?");
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

    @FXML
    void onChangeSearchField() {

        publicationsLayout.getChildren().clear();
        mfc.marketcol.getPublicationService().getPublications().stream()
                .filter(publication -> publication.getTitle().toLowerCase().contains(searchArticleField.getText().toLowerCase()))
                .forEach(publication -> printPublications(publication));
    }

    @FXML
    private GridPane publicationsLayout;

    void loadPublications() {

        publicationsContainer.getChildren().clear();
        publicationsLayout.getChildren().clear();
        publicationsContainer.getChildren().add(scrollPaneLayout);
        columns[0]=0;
        rows[0]=1;

        mfc.marketcol.getPublicationService().getPublications().stream().forEach((publication) -> {
                    printPublications(publication);
                }
        );
    }


    final int[] columns = {0};
    final int[] rows = {1};

    void printPublications(Publication publication) {

        try {
            PublicationViewHelper publicationViewHelper = new PublicationViewHelper();
            publicationViewHelper.setLocation();

            VBox vBox = publicationViewHelper.fxmlLoader.load();

            PublicationItemController publicationItemController = publicationViewHelper.fxmlLoader.getController();
            publicationItemController.setData(publication);
            if (columns[0] == 3) {
                columns[0] = 0;
                rows[0]++;
            }
            publicationsLayout.add(vBox, columns[0]++, rows[0]);
            GridPane.setMargin(vBox, new Insets(0, 30, 100, 30));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //filter by category
    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    private String[] categoryOptions = {"Todas", "Tecnologia", "Ropa", "Hogar", "Deportes", "Juegos"};

    void initializeChoiceBoxOptions() {
        categoryChoiceBox.getItems().addAll(categoryOptions);
    }

    @FXML
    private HBox publicationsContainer;

    @FXML
    void onFilterButtonHandler() {
        if (!categoryChoiceBox.getValue().equals("Todas")) {

            if (mfc.marketcol.getPublicationService().filterPublicationsByCategory(categoryChoiceBox.getValue()).size() > 0) {
                columns[0]=0;
                rows[0]=1;
                publicationsContainer.getChildren().clear();
                publicationsLayout.getChildren().clear();
                publicationsContainer.getChildren().add(scrollPaneLayout);


                mfc.marketcol.getPublicationService()
                        .filterPublicationsByCategory(categoryChoiceBox.getValue())
                        .forEach(publication -> printPublications(publication));


            } else {
                NoPublicationsFoundViewHelper noPublicationsFoundViewHelper = new NoPublicationsFoundViewHelper();

                try {
                    publicationsContainer.getChildren().clear();
                    noPublicationsFoundViewHelper.setLocation();
                    VBox vBox = noPublicationsFoundViewHelper.fxmlLoader.load();
                    publicationsContainer.getChildren().add(vBox);


                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }


            }


        } else loadPublications();
    }

}
