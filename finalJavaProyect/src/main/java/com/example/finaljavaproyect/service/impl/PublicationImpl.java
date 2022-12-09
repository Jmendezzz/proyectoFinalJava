package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.persistence.Persistence;
import com.example.finaljavaproyect.service.PublicationService;

import java.io.IOException;
import java.util.ArrayList;

public class PublicationImpl implements PublicationService {
    private final Marketcol marketcol;
    private ArrayList<Publication> publications;

    @Override
    public void initializePublicationsList(){
        try{
            this.publications = Persistence.loadPublications();

        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    @Override
    public void savePublications(){
        try{
            Persistence.savePublications(publications);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public PublicationImpl(Marketcol marketcol) {
        this.marketcol = marketcol;
    }

    @Override
    public void addPublication(Publication publication) {
        publications.add(publication);
        savePublications();
    }
    @Override
    public ArrayList<Publication> getPublications(){
        return this.publications;
    }

    @Override
    public Publication getPublicationByName(String name) {
        return publications.stream()
                .filter(publication -> publication.getTitle().equals(name))
                .findFirst().
                orElse(null);
    }

    @Override
    public ArrayList<Publication> userPublications(User user) {

        ArrayList<Publication> userPublications = new ArrayList<>();

        publications.stream()
                .filter(publication -> publication.getUser().getEmail().equals(user.getEmail()))
                .forEach(publication -> userPublications.add(publication));

        return userPublications;

    }

    @Override
    public void editPublication(Publication publication, int price, int amount, String title, String description, String urlImage) {

        publication.setTitle(title);
        publication.setAmount(amount);
        publication.setDescription(description);
        publication.setPrice(price);
        publication.setUrlImage(urlImage);
        marketcol.getCartService().saveCarts(); //Cuando un usuario agregaba algo al carrito y el propietario de la publicación editaba el nombre del articulo,
                                                // provocaba un error a la hora de cerrar el programa y cuando se cargaba la persistencia del carro habia un error porque este no encontraba la publicacion mediante el nombre.
        savePublications();
    }

    @Override
    public void deletePublication(Publication publication) {
        publications.remove(publication);
        marketcol.getCartService().deletedPublication(publication);
        marketcol.getSaleService().deletedPublication(publication);
        savePublications();

    }

    @Override
    public ArrayList<Publication> filterPublicationsByCategory(String categoryFiltered){
        ArrayList<Publication> publicationArrayList = new ArrayList<>();

        publications.stream().forEach(publication -> {
            if (publication.getCategory().equals(categoryFiltered)){
                publicationArrayList.add(publication);
            }
        });
        return publicationArrayList;
    }

}
