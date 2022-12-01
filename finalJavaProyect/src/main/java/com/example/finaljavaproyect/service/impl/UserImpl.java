package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.persistence.Persistence;
import com.example.finaljavaproyect.service.UserService;
import com.example.finaljavaproyect.validations.RegisterValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

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
  /*  public void ejemplo() throws RegisterException {
        CompletableFuture.supplyAsync(()->getUsers()).thenApply(userList->{
            userList.stream().forEach(user -> System.out.println(user.getName()));


            return null;
        });
    }
*/

    @Override
    public void addUser(User user) {

        users.add(user);
        try{
            Persistence.saveUsers(users);

        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }

    @Override
    public void editUser(User user, String name, String cellphoneNumber, String password) throws RegisterException {
        System.out.println(cellphoneNumber);
        registerValidation.verifyCellphoneNumberInput(cellphoneNumber);
        user.setName(name);
        user.setCellphoneNumber(cellphoneNumber);
        user.getUserCredentials().setPassword(password);
        try{
            Persistence.saveUsers(users);

        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }

    @Override
    public void addPublicationToUser(User user, Publication publication) {
        user.getPublications().add(publication);
        try{
            Persistence.saveUsers(users);

        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }
}
