package com.example.finaljavaproyect.controller;

import com.example.finaljavaproyect.model.Sale;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MySaleItemController {


    @FXML
    private Text amountBougthText;

    @FXML
    private Text buyerText;

    @FXML
    private Text productTitleText;

    @FXML
    private Text salePriceText;

    public void setData(Sale sale){

        buyerText.setText(sale.getBuyer().getName());

        productTitleText.setText(sale.getSoldArticle().getTitle());

        amountBougthText.setText(""+sale.getAmountSold());

        salePriceText.setText("$"+sale.getTotalPrice());
    }
}
