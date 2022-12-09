package com.example.finaljavaproyect;

import javafx.fxml.FXMLLoader;

public class NoSalesViewHelper {

    // Lo puse fuera de algún package debido a que no me reconoce la ruta de la vista, esta clase está diseñada para obtener el fxmloader y mostrar las publicaciones dinamicamente

    public  FXMLLoader fxmlLoader = new FXMLLoader();

    public  void setLocation(){
        fxmlLoader.setLocation(getClass().getResource("fxml/no-sales-view.fxml"));
    }

}
