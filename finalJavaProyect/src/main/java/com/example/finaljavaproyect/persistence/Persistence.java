package com.example.finaljavaproyect.persistence;

import com.example.finaljavaproyect.model.Publication;
import com.example.finaljavaproyect.model.User;
import com.example.finaljavaproyect.model.UserCredentials;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistence {
    //RUTA DONDE QUEREMOS GUARDAR LA INFORMACION DE NUESTROS CLIENTES
    public static final String RUTA_ARCHIVO_CLIENTES = "src/main/java/com/example/finaljavaproyect/persistence/resources/users.txt";

    static String savePublications(User user){
        String content = "";
        if(user.getPublications().size()>0){
            content+="~";
            for(Publication publication: user.getPublications()){
                content += publication.getPrice() + "<>" + publication.getAmount() + "<>"+ publication.getTitle() + "<>" + publication.getDescription().replace("\n" ,"") +  "<>" + publication.getUrlImage()  + "^";
            }
            return content;
        }else return content.trim();
    }
    static ArrayList<Publication> loadPublications(User user, String linea){
        ArrayList<Publication> publications = new ArrayList<>();
        System.out.println(linea);
        String[] publicationContent = linea.split("\\^");
        if(publicationContent.length>0){
            for(int i = 0; i<publicationContent.length;i++){
                Publication publication = new Publication();
                publication.setUser(user);
                publication.setPrice(Integer.parseInt(publicationContent[i].split("<>")[0]));
                publication.setAmount(Integer.parseInt(publicationContent[i].split("<>")[1]));
                publication.setTitle(publicationContent[i].split("<>")[2]);

                publication.setDescription(publicationContent[i].split("<>")[3]);
                publication.setUrlImage(publicationContent[i].split("<>")[4]);
                publications.add(publication);
            }
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
                    savePublications(user) +
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
            if(linea.split("~").length >7){
                myUser.setPublications(loadPublications(myUser,linea.split("~")[7]));
            }

            userList.add(myUser);

        }

        return userList;
    }
    public static  ArrayList<User> test(){
        return  new ArrayList<>();
    }
}
