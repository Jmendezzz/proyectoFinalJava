module com.example.finaljavaproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.finaljavaproyect to javafx.fxml;
    exports com.example.finaljavaproyect;
    exports com.example.finaljavaproyect.controller;
    opens com.example.finaljavaproyect.controller to javafx.fxml;
}