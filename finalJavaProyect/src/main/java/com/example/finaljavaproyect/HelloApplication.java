package com.example.finaljavaproyect;

import com.example.finaljavaproyect.controller.ModelFactoryController;
import com.example.finaljavaproyect.controller.MyProfileController;
import com.example.finaljavaproyect.threads.MyProfileThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ModelFactoryController mfc  = ModelFactoryController.getInstance();
        mfc.marketcol.getUserService().initializeUsersList();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 710);
        stage.setTitle("MARKETCOL");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}