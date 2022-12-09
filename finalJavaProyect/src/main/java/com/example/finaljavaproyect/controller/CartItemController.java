package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.CartViewHelper;
import com.example.finaljavaproyect.model.CartDetail;
import com.example.finaljavaproyect.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CartItemController {

    ModelFactoryController mfc = ModelFactoryController.getInstance();

    private User userActive = mfc.marketcol.getLoginService().userLoginActive();

    private CartDetail cartDetail;
    @FXML
    private ImageView addButton;

    @FXML
    private Text amountWishPublicationText;

    @FXML
    private ImageView imagePublication;

    @FXML
    private ImageView minusAmountButton;

    @FXML
    private Text publicationTitlteText;

    @FXML
    private ImageView removeItemCartButton;
    @FXML
    private HBox cartItemLayout;

    @FXML
    Stage stage;

    @FXML
    private Text subtotalText;

    public void setData(CartDetail cartDetail){
        this.cartDetail = cartDetail;
        Image image  = new Image(cartDetail.getWishPublication().getUrlImage());
        imagePublication.setImage(image);
        publicationTitlteText.setText(cartDetail.getWishPublication().getTitle());
        amountWishPublicationText.setText( cartDetail.getAmount()+"");
        subtotalText.setText("$"+ cartDetail.getAmount() * cartDetail.getWishPublication().getPrice());
    }
    @FXML
    void addButtonHandler(ActionEvent e){

        mfc.marketcol.getCartDetailService().increaseAmount(this.cartDetail);
        mfc.marketcol.getCartService().updateTotalPrice(userActive);
        amountWishPublicationText.setText( cartDetail.getAmount()+"");
        subtotalText.setText("$"+ cartDetail.getAmount() * cartDetail.getWishPublication().getPrice());

        updateScene(e);



    }

    @FXML
    void decreaseButtonHandler(ActionEvent event){
        if(cartDetail.getAmount()==1){
            cartItemLayout.setVisible(false);
            cartItemLayout.setPrefHeight(0);
            mfc.marketcol.getCartDetailService().decreaseAmount(userActive, this.cartDetail);
        }else{
            mfc.marketcol.getCartDetailService().decreaseAmount(userActive, this.cartDetail);

            mfc.marketcol.getCartService().updateTotalPrice(userActive);
            amountWishPublicationText.setText( cartDetail.getAmount()+"");
            subtotalText.setText("$"+ cartDetail.getAmount() * cartDetail.getWishPublication().getPrice());

        }
        updateScene(event);

    }
    @FXML
    void deleteArticleButtonHandler(ActionEvent event){
        cartItemLayout.setVisible(false);
        cartItemLayout.setPrefHeight(0);
        mfc.marketcol.getCartDetailService().deleteCartArticle(userActive,cartDetail);
        updateScene(event);



    }
    void updateScene(ActionEvent event){
        CartViewHelper cartViewHelper = new CartViewHelper();
        cartViewHelper.setLocation();
        try{
            Parent reloadedScene = cartViewHelper.fxmlLoader.load();
            Scene scene = new Scene(reloadedScene);
            CartController cartController =cartViewHelper.fxmlLoader.getController();
            stage = ((Stage)((Button) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();


        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
