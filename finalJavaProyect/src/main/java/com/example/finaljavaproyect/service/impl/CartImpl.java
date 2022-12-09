package com.example.finaljavaproyect.service.impl;

import com.example.finaljavaproyect.model.Cart;
import com.example.finaljavaproyect.model.CartDetail;
import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.persistence.Persistence;
import com.example.finaljavaproyect.service.CartService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

//mejorar el carrito crear mas funciones
public class CartImpl implements CartService {

    Marketcol marketcol;
    ArrayList<Cart> cartArrayList;

    public CartImpl(Marketcol marketcol) {
        this.marketcol = marketcol;
    }

    @Override
    public void saveCarts(){
        try{
            Persistence.saveCarts(cartArrayList);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addCart(User user, Publication wishArticle) {

        if(verifyUserHasACart(user)){

            if(!verifyEqualArticle(user,wishArticle).get()){ // en caso de que haya un objeto repetido se sumara uno a la cantidad agregada al carrito
                System.out.println(" no repetido");
                addWishItem(user,wishArticle); // En caso de que no se agregara un nuevo articulo al carrito.
                updateTotalPrice(user); // se calcula el precio final para eso primero se busca el carrito del usuario y luego se le calculo el precio total de su carrito.
            }

        }else{
            Cart newCart = new Cart(); // en caso de que el usuario no tenga un carro, se le  crea y se le agrega el articulo que desea.

            newCart.setUser(user);

            newCart.setWishItems(new ArrayList<CartDetail>());

            newCart.getWishItems().add(new CartDetail(wishArticle,1));

            cartArrayList.add(newCart);

            newCart.setTotalPrice(calculateTotalPrice(user));

        }

             saveCarts(); // Se actualiza la persistencia

    }

    @Override
    public void initializeCartList(){
        try{
            this.cartArrayList = Persistence.loadCarts();

        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }

    boolean verifyUserHasACart(User user){

        for (Cart cart : cartArrayList){
            if(cart.getUser().getEmail().equals(user.getEmail())) return true;
        }
        return false;
    }

    AtomicBoolean verifyEqualArticle(User user , Publication wishItem){

         AtomicBoolean band = new AtomicBoolean(false);
                getUserCartDetail(user)
                .stream()
                .forEach(cartDetail -> {
                    if(cartDetail.getWishPublication().getTitle().equals(wishItem.getTitle())){
                        cartDetail.setAmount(cartDetail.getAmount()+1);
                        band.set(true);

                    }

                });
        updateTotalPrice(user);
        return band;
    }
    void addWishItem(User user , Publication publication ){
                getUserCartDetail(user)
                .add(new CartDetail(publication,1));
    }
    @Override
    public int calculateTotalPrice(User user){

        return getUserCartDetail(user)
                .stream()
                .reduce(0,(acum,publication)->acum +publication.getWishPublication().getPrice() * publication.getAmount(), Integer::sum
                );


    }

    @Override
    public ArrayList<CartDetail> getUserCartDetail(User user){

        return  getUserCart(user) != null ? getUserCart(user).getWishItems() : null;


    }

    @Override
    public Cart getUserCart(User user){
        return cartArrayList.stream()
                .filter(cart -> cart.getUser().getEmail().equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }
    public void updateTotalPrice(User user){
        getUserCart(user).setTotalPrice(calculateTotalPrice(user));
    }
    @Override
    public void verifyUserCartIsEmpty(User user){

        if(getUserCartDetail(user).size()==0){
            cartArrayList.remove(getUserCart(user)); // En caso de que la lista de articulos deseados este vacia se eliminara tambien el carro.
            saveCarts();
        }
    }
    @Override
    public void deleteCart(User user){

        cartArrayList.remove(getUserCart(user));
        saveCarts();
    }
    @Override
    public void deletedPublication(Publication publication){ //Eliminar del carrito de las personas aquellas publicaciones que fueron eliminadas por el dueño
            for(int i= 0; i<cartArrayList.size(); i++){
                ArrayList<CartDetail> cartDetailArrayList = cartArrayList.get(i).getWishItems();
                for(int j =0; j<cartDetailArrayList.size();j++){ //Se utilza un for normal debido a que si se utilizaba un forEach habia una excepcion ConcurrentModificationException
                    if(cartDetailArrayList.get(j).getWishPublication().getTitle().equals(publication.getTitle())){
                        cartDetailArrayList.remove(cartDetailArrayList.get(j));
                    }
                }
                verifyUserCartIsEmpty(cartArrayList.get(i).getUser()); // Siempre verificar que ningun carrito quede vacío ya que podrá generar errores.
            }
            saveCarts(); // Actualizar la persistencia de los carritos.
    }


}
