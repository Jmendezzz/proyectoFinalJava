package com.example.finaljavaproyect;

import javafx.fxml.FXMLLoader;

public class PublicationViewHelper {

    public  FXMLLoader fxmlLoader = new FXMLLoader();

    public  void setLocation(){
        fxmlLoader.setLocation(getClass().getResource("fxml/publication-view.fxml"));

    }

}
