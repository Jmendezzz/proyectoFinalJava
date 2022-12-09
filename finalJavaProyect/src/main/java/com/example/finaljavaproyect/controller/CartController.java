package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.CartItemViewHelper;
import com.example.finaljavaproyect.EmptyCartViewHelper;
import com.example.finaljavaproyect.PublicationViewHelper;
import com.example.finaljavaproyect.SwitchScene;
import com.example.finaljavaproyect.helpers.alerts.AlertMessage;
import com.example.finaljavaproyect.model.CartDetail;
import com.example.finaljavaproyect.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

public class CartController implements Initializable {

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
    private Text totalPriceText;


    @FXML
    private Button endSaleButton;
    @FXML
    private HBox containerCartContent;


     ModelFactoryController mfc = ModelFactoryController.getInstance();

     User userActive = mfc.marketcol.getLoginService().userLoginActive(); // Se obtiene el usuario activo.


    @FXML
    void switchToMainMenuHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToMainMenu(e);
    }

    @FXML
    void logoutHandler(ActionEvent e) throws IOException {
        SwitchScene.switchToLoginScene(e);
        userActive.setLoginStatus(false); // Se cambia el estado de sesión una vez hace LogOut.
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
    private GridPane cartItemsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        applyButtonImages();
        printCartContainer();

    }

    final int[] columns = {0};
    final int[] rows = {1};

    void printCartList(CartDetail cartDetail) {

        try {
            CartItemViewHelper cartItemViewHelper = new CartItemViewHelper();
            cartItemViewHelper.setLocation();

            HBox hBox = cartItemViewHelper.fxmlLoader.load();

            CartItemController cartItemController = cartItemViewHelper.fxmlLoader.getController();
            cartItemController.setData(cartDetail);
            if (columns[0] == 1) {
                columns[0] = 0;
                rows[0]++;
            }
            cartItemsLayout.add(hBox, columns[0]++, rows[0]);
            GridPane.setMargin(hBox, new Insets(0, 0, 50, 0));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void printUserCartList() {

        mfc.marketcol.getCartService().getUserCartDetail(userActive) //Se obtiene la lista de carrito del usuario.
                .stream()
                .forEach(cartDetail -> printCartList(cartDetail)); // Por cada carrito detalle se crea una nueva vista.


    }

    void printTotalPrice() {
            totalPriceText.setText("$" + mfc.marketcol.getCartService().calculateTotalPrice(userActive));
    }

    void printCartContainer()  {
        if (mfc.marketcol.getCartService().getUserCartDetail(userActive) != null) {

            printUserCartList();
            printTotalPrice();
        }else{

            EmptyCartViewHelper emptyCartViewHelper = new EmptyCartViewHelper();

            try{
                containerCartContent.getChildren().clear();
                emptyCartViewHelper.setLocation();
                VBox vBox = emptyCartViewHelper.fxmlLoader.load();
                containerCartContent.getChildren().add(vBox);


            }catch ( IOException e){
                System.out.println(e.getMessage());
            }

        }


    }
    public   void verifyEmptyCart(ActionEvent e) throws IOException {
        if(mfc.marketcol.getCartService().getUserCartDetail(userActive)==null){

            SwitchScene.switchToCartScene(e);

        }
    }
    public void confirmSaleScene(ActionEvent e) throws IOException{
        SwitchScene.switchToConfirmSaleScene(e);
    }
}
