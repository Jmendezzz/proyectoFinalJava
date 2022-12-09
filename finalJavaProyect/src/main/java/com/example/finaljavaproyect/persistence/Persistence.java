package com.example.finaljavaproyect.persistence;

import com.example.finaljavaproyect.controller.ModelFactoryController;
import com.example.finaljavaproyect.model.*;
import javafx.scene.Parent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistence {
    static ModelFactoryController mfc = ModelFactoryController.getInstance();
    //RUTA DONDE QUEREMOS GUARDAR LA INFORMACION DE NUESTROS CLIENTES
    public static final String RUTA_ARCHIVO_CLIENTES = "src/main/java/com/example/finaljavaproyect/persistence/resources/users.txt";

    public static final String RUTA_ARCHIVO_PUBLICACIONES = "src/main/java/com/example/finaljavaproyect/persistence/resources/publications.txt";

    public static final String RUTA_ARCHIVO_CART = "src/main/java/com/example/finaljavaproyect/persistence/resources/cart.txt";


    public  static  void savePublications(ArrayList<Publication> publications)throws IOException{

        String content = "";

        for(Publication publication : publications){
            content+= publication.getUser().getEmail() + "~" +  publication.getPrice() +
                    "~" + publication.getAmount() + "~" + publication.getTitle() +
                    "~" + publication.getDescription().replace("\n" ,"}{") + "~" + publication.getCategory() +
                    "~" + publication.getUrlImage() +
                    "\n";
        }

        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUBLICACIONES, content, false);

    }
    public static ArrayList<Publication> loadPublications () throws IOException {
        ArrayList<Publication> publications = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUBLICACIONES);
        String linea = "";
        for (int i = 0; i<contenido.size();i++){
            Publication publication  = new Publication();
            linea = contenido.get(i);

            publication.setUser(mfc.marketcol.getUserService().getUserByEmail(linea.split("~")[0])); // Se obtiene el usuario por medio del email para evitar que se cargue tanta informacion en la persistencia de las publicaciones.
            publication.setPrice(Integer.parseInt(linea.split("~")[1]));
            publication.setAmount(Integer.parseInt(linea.split("~")[2]));
            publication.setTitle(linea.split("~")[3]);
            publication.setDescription(linea.split("~")[4].replace("}{","\n"));
            publication.setCategory(linea.split("~")[5]);
            publication.setUrlImage(linea.split("~")[6]);

            publications.add(publication);
        }

        return publications;
    }

    //METODO PARA GUARDAR LA LISTA DE CLIENTES EN EL ARCHIVO .TXT
    public static void saveUsers(ArrayList<User> usersList) throws IOException {

        String content = "";

        for(User user: usersList) {

            content+= user.getId()+"~"+user.getName()+"~"+user.getEmail()+
                    "~"+user.getCellphoneNumber()+"~"+user.getLoginStatus()+
                    "~"+user.getUserCredentials().getEmail()+
                    "~"+ user.getUserCredentials().getPassword()+
                    //savePublications(user) +
                    "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, content, false);
    }

    //METODO PARA CARGAR LOS CLIENTES A PARTIR DEL ARCHIVO
    public static ArrayList<User> loadUsers() throws FileNotFoundException, IOException {

        ArrayList<User> userList = new ArrayList<>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
        String linea = "";

        for (int i = 0;i<contenido.size(); i++) {
            User myUser = new User();
            linea = contenido.get(i);
            myUser.setId(linea.split("~")[0]);
            myUser.setName(linea.split("~")[1]);
            myUser.setEmail(linea.split("~")[2]);
            myUser.setCellphoneNumber(linea.split("~")[3]);
            myUser.setLoginStatus(Boolean.parseBoolean(linea.split("~")[4]));
            myUser.setUserCredentials(new UserCredentials(linea.split("~")[5], linea.split("~")[6]));

            userList.add(myUser);

        }

        return userList;
    }



    //CARTS PERSISTENCE
    static String savePublicationsToCart(ArrayList<CartDetail> cartDetailArrayList){
        String content = "";
        if(cartDetailArrayList.size()>0){
            content+="~";
            for(CartDetail cartDetail: cartDetailArrayList){
                content += cartDetail.getWishPublication().getTitle()  + "<>" + cartDetail.getAmount()+  "^";
            }
            return content;
        }else return content.trim();
    }
    static ArrayList<CartDetail> loadCartDetail(String linea){
        ArrayList<CartDetail> cartDetailArrayList = new ArrayList<>();
        System.out.println(linea);
        String[] cartDetailContent = linea.split("\\^");
        if(cartDetailContent.length>0){
            for(int i = 0; i<cartDetailContent.length;i++){
                CartDetail cartDetail = new CartDetail();
                cartDetail.setWishPublication( mfc.marketcol.getPublicationService().getPublicationByName(cartDetailContent[i].split("<>")[0]));
                cartDetail.setAmount(Integer.parseInt(cartDetailContent[i].split("<>")[1]));

                cartDetailArrayList.add(cartDetail);
            }
        }
        return cartDetailArrayList;

    }

    public static void saveCarts(ArrayList<Cart> cartArrayList) throws IOException{
        String content = "";

        for(Cart cart: cartArrayList){
            content += cart.getUser().getEmail() + "~" +cart.getTotalPrice() + savePublicationsToCart(cart.getWishItems()) + "\n";

        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CART,content,false);

    }
    public  static ArrayList<Cart> loadCarts() throws IOException{
        ArrayList<Cart> carts= new ArrayList<>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CART);

        String linea ="";

        for(int i=0; i<contenido.size();i++){
            Cart myCart = new Cart();
            linea = contenido.get(i);
            System.out.println(linea);
            myCart.setUser(mfc.marketcol.getUserService().getUserByEmail(linea.split("~")[0]));

            myCart.setTotalPrice(Integer.parseInt(linea.split("~")[1]) );

            if(linea.split("~").length>2){
                myCart.setWishItems(loadCartDetail(linea.split("~")[2]));

            }
            carts.add(myCart);
        }
        return carts;
    }


    // Ventas
    public static final String RUTA_ARCHIVO_VENTAS =  "src/main/java/com/example/finaljavaproyect/persistence/resources/sale.txt";
    public static void saveSales(ArrayList<Sale> saleArrayList) throws IOException {

        String content = "";

        for (Sale sale : saleArrayList){
            content+= sale.getSeller().getEmail() + "~" +
                    sale.getBuyer().getEmail() + "~" +
                    sale.getSoldArticle().getTitle() + "~" +
                    sale.getAmountSold() + "~" +
                    sale.getTotalPrice() +  "~" +
                    "\n";


        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENTAS,content,false);

    }

    public static ArrayList<Sale> loadSales() throws IOException {
        ArrayList<Sale> sales = new ArrayList<>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENTAS);

        String linea = "";

        for (int i = 0 ; i<contenido.size(); i++){

            Sale mySale = new Sale();

            linea = contenido.get(i);

            mySale.setSeller(mfc.marketcol.getUserService().getUserByEmail(linea.split("~")[0]));

            mySale.setBuyer(mfc.marketcol.getUserService().getUserByEmail(linea.split("~")[1]));

            mySale.setSoldArticle(mfc.marketcol.getPublicationService().getPublicationByName(linea.split("~")[2]));

            mySale.setAmountSold(Integer.parseInt(linea.split("~")[3]));

            mySale.setTotalPrice(Integer.parseInt(linea.split("~")[4]));

            sales.add(mySale);

        }

        return sales;

    }


}
