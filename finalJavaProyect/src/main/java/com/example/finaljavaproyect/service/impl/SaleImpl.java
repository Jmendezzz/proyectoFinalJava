package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.exceptions.SaleException;
import com.example.finaljavaproyect.model.*;
import com.example.finaljavaproyect.persistence.Persistence;
import com.example.finaljavaproyect.service.SaleService;
import com.example.finaljavaproyect.validations.SaleValidation;

import java.io.IOException;
import java.util.ArrayList;

public class SaleImpl implements SaleService {

    Marketcol marketcol;

    public SaleImpl(Marketcol marketcol) {
        this.marketcol = marketcol;
    }

    ArrayList<Sale> saleArrayList;

    @Override
    public void initializeSaleList() {
        try {

            saleArrayList = Persistence.loadSales();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void saveSales(){
        try {
            Persistence.saveSales(saleArrayList);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void realizeSale(Cart userCart) throws SaleException { // Cuando se ejecute la acción de comprar.
        SaleValidation.validateAvaibleAmount(userCart.getWishItems());

        decreaseAmountCartArticles(userCart);

        createSale(userCart);

        clearUserCart(userCart.getUser());

        saveSales();
    };

    void decreaseAmountCartArticles(Cart userCart){

        userCart.getWishItems().stream().forEach(cartDetail -> {
            cartDetail.getWishPublication().setAmount(
                    cartDetail.getWishPublication().getAmount() - cartDetail.getAmount() // Se resta la cantidad del articulo vendido.
            );
        });


        marketcol.getPublicationService().savePublications(); // Se actualiza el estado de la persistencia de las publicaciones.
    }
    void createSale(Cart userCart){
        User userBuyer = userCart.getUser(); // Se obtiene el comprador
        userCart.getWishItems().stream().forEach(cartDetail -> {
            User userSeller = cartDetail.getWishPublication().getUser(); // Se obtiene el vendedor de la publicación.
            Sale sale = new Sale(userSeller,userBuyer,cartDetail.getWishPublication(),cartDetail.getAmount(), cartDetail.getAmount() * cartDetail.getWishPublication().getPrice());// Se crea la venta, el ultimo parametro es el valor de la venta total.
            saleArrayList.add(sale);
        });
    }
    void clearUserCart(User user){
        marketcol.getCartService().deleteCart(user); // Se vacia el carrito del usuario
        marketcol.getCartService().saveCarts();

    }

    @Override
    public ArrayList<Sale> getUserSales(User user){
        ArrayList<Sale> sales = new ArrayList<>();
         saleArrayList.stream()
                .forEach(sale -> {
                    if (sale.getSeller().getEmail().equals(user.getEmail())){
                        sales.add(sale);
                    }
                });
         return sales;
    }
    @Override
    public void deletedPublication(Publication publication){

        for(int i = 0;i<saleArrayList.size();i++){
            if(saleArrayList.get(i).getSoldArticle().getTitle().equals(publication.getTitle())){
                saleArrayList.remove(saleArrayList.get(i));
            }
        }
        saveSales();

    }

}
