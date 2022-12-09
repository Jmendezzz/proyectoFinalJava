package com.example.finaljavaproyect;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene  {

    private static Stage stage;
    private  static Scene scene;
    private static Parent root;

    public static  void switchToRegister(ActionEvent e) throws IOException {
        switchScene(e,"fxml/register-view.fxml");
    }
    public static void switchToLoginScene(ActionEvent e) throws IOException{
        switchScene(e,"fxml/login-view.fxml");
    }
    public static void switchToMainMenuScene(ActionEvent e) throws IOException{
        switchScene(e,"fxml/main-menu-view.fxml");
    }
    public static void switchToMyProfileScene(ActionEvent e) throws IOException{
        switchScene(e,"fxml/my-profile-view.fxml");
    }
    public static void switchToCartScene(ActionEvent e) throws IOException{
        switchScene(e,"fxml/cart-view.fxml");

    }
    public static void switchToMainMenu(ActionEvent e ) throws  IOException{
        switchScene(e,"fxml/main-menu-view.fxml");
    }
    public static void switchToConfirmSaleScene(ActionEvent e) throws IOException{
        switchScene(e,"fxml/confirm-sale-view.fxml");
    }

    public static void switchScene(ActionEvent e, String resource) throws IOException {
        root = FXMLLoader.load(SwitchScene.class.getResource(resource));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1400, 710);
        stage.setScene(scene);
        stage.show();
    }


}
