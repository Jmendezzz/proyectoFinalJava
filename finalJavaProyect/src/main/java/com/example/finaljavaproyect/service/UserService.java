package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.exceptions.RegisterException;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;

import java.util.ArrayList;

public interface UserService {
    ArrayList<User> getUsers();
    void initializeUsersList();

    void addUser(User user);
    void editUser(User user, String name, String cellphoneNumber,String password) throws RegisterException;

    //void addPublicationToUser(User user, Publication publication);

    User getUserByEmail(String email);



}
