package com.example.finaljavaproyect.validations;

import com.example.finaljavaproyect.exceptions.SaleException;
import com.example.finaljavaproyect.model.CartDetail;

import java.util.ArrayList;

public class SaleValidation {

    public static void validateAvaibleAmount(ArrayList<CartDetail> cartDetailArrayList) throws SaleException {

            for (CartDetail cartDetail : cartDetailArrayList){
                if(cartDetail.getWishPublication().getAmount()-cartDetail.getAmount() < 0 ){
                    throw  new SaleException("Error en la compra. No hay la cantidad disponible para su compra del articulo: " +
                             "'" + cartDetail.getWishPublication().getTitle() + "'" + " cantidad disponible: "+ cartDetail.getWishPublication().getAmount());
                }
            }

    }
}
