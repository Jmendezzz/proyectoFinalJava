package com.example.finaljavaproyect.service;

import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;

import java.util.ArrayList;

public interface PublicationService {

    void initializePublicationsList();

    void savePublications();

    void addPublication(Publication publication);

    ArrayList<Publication> getPublications();

    Publication getPublicationByName (String name);

    ArrayList<Publication> userPublications(User user);


    void editPublication(Publication publication, int price, int amount, String title, String description, String urlImage);

    void deletePublication(Publication publication);

    ArrayList<Publication> filterPublicationsByCategory(String categoryFiltered);
}
