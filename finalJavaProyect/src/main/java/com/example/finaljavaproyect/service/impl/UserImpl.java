package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.persistence.Persistence;
import com.example.finaljavaproyect.service.UserService;
import com.example.finaljavaproyect.validations.RegisterValidation;

import java.io.IOException;
import java.util.ArrayList;


public class UserImpl implements UserService{
    ArrayList<User> users;
    private  final Marketcol marketcol;
    RegisterValidation registerValidation = new RegisterValidation();

    public UserImpl(Marketcol marketcol) {
        this.marketcol = marketcol;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    public void initializeUsersList(){
        try{
            this.users = Persistence.loadUsers();

        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }
    void saveUsers(){
        try{
            Persistence.saveUsers(users);

        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }

    @Override
    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }


    @Override
    public void editUser(User user, String name, String cellphoneNumber, String password) throws RegisterException {
        registerValidation.verifyCellphoneNumberInput(cellphoneNumber);
        user.setName(name);
        user.setCellphoneNumber(cellphoneNumber);
        user.getUserCredentials().setPassword(password);

        saveUsers();
    }

 /*   @Override
    public void addPublicationToUser(User user, Publication publication) {
        user.getPublications().add(publication);

        saveUsers();

    }*/

    @Override
    public User getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }


}

