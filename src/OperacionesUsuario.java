import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OperacionesUsuario implements  IUsuario {

    //Método para leer los usuarios del archivo de texto//
    @Override
    public  ArrayList <Usuario> leerUsuarios(){
        String nFile = "Usuarios.txt";
        File file = new File(nFile);
        ArrayList<Usuario> usrs = new ArrayList<>();
        Scanner scanner;
        String nombre, contraseña,direccion;
        int saldo;
        if (file.exists()) {
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    Usuario e = new Usuario();
                    delimitar.useDelimiter("\\s*,\\s*");
                    nombre = delimitar.next();
                    contraseña = delimitar.next();
                    direccion = delimitar.next();
                    //saldo = Integer.parseInt(delimitar.next());
                    e = new Usuario(nombre, direccion,contraseña);
                    usrs.add(e);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return usrs;
    }

    //Método para guardar la informacion de un usuario en un archivo de texto//
    @Override
    public  void IngresarUsuario(Usuario usr) {
        FileWriter flwriter = null;
        try {
            String nFile = "Usuarios.txt";
            File file = new File(nFile);
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(usr.getnombre() + "," + usr.getcontraseña() +"," +usr.getDireccion()+  "\n");
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(usr.getnombre() + "," + usr.getcontraseña() +","+ usr.getDireccion()+ "\n");
                bfwriter.close();
            }
            System.out.println("Usuario Ingresado Satisfactoriamente..");
            System.out.println("--------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Método para obtener un usuario por su nombre//
    @Override
    public  Usuario GetUsuarioByName(ArrayList<Usuario> ArrayUsuarios, String nombre){
        Usuario usr = null;
        for(int i =0;i<ArrayUsuarios.size();i++){
            usr = ArrayUsuarios.get(i);
            if(usr.getnombre().equals(nombre)){
                return usr;
            }
        }
        return usr;
    }


}
