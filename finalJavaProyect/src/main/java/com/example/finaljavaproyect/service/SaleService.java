package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.exceptions.SaleException;
import com.example.finaljavaproyect.model.*;

import java.util.ArrayList;

public interface SaleService {


    void initializeSaleList();

    void saveSales();

    void realizeSale(Cart userCart) throws SaleException;

    ArrayList<Sale> getUserSales(User user);

    void deletedPublication(Publication publication);
}
