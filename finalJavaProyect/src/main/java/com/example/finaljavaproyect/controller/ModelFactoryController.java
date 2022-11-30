package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.service.impl.Marketcol;
import com.example.finaljavaproyect.service.ModelFactoryContollerService;

public class ModelFactoryController implements ModelFactoryContollerService {
    public Marketcol marketcol;
    private static class SingletonHolder{

        private final static ModelFactoryController eINSTANCE = new  ModelFactoryController();

    }
    public static ModelFactoryController getInstance(){
        return SingletonHolder.eINSTANCE;
    }
    public ModelFactoryController(){
        marketcol = new Marketcol();
    }



}
