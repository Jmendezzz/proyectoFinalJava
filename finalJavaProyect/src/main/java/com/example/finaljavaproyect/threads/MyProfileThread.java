package com.example.finaljavaproyect.threads;

import com.example.finaljavaproyect.controller.MyProfileController;

public class MyProfileThread implements Runnable{
    MyProfileController myProfileController = new MyProfileController();

    @Override
    public void run() {

        myProfileController.initalizeMyInfoFields();
        myProfileController.disableInputs();
        System.out.println("Hilo ejecutado");
    }
}
