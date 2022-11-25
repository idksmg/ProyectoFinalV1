import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OperacionesAlbum implements IAlbum{

    //Método que nos permite el ingreso y guardado de un Album en un archivo de texto//
    public  int IngresarAlbum(Album alb,Usuario usr) {
        FileWriter flwriter = null;
        Random random = new Random();
        int idAlbum = random.nextInt(100000);
        try {
            String nFile = "Albums.txt";
            File file = new File(nFile);

            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(idAlbum +","+alb.getaño() + "," + alb.getnombre() +"," +alb.getnumEstampas()+ ","+usr.getnombre()+ "\n");
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(idAlbum+","+alb.getaño() + "," + alb.getnombre() +"," +alb.getnumEstampas()+ ","+usr.getnombre()+ "\n");
                bfwriter.close();
            }
            System.out.println("Album Ingresado Satisfactoriamente..");
            System.out.println("----------------------------------------------------------------------------------------------");
            return idAlbum;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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

    //Método que nos permite guardar el Detalle de los albums por usuario creado//
    public  void InicializarDetalleAlbum(Album alb,Usuario usr,ArrayList<Estampa>ArrayEstampas,OperacionesEstampa operacionesestampa) {
        FileWriter flwriter = null;
        try {
            String nFile = "EstampasAlbum.txt";
            File file = new File(nFile);
            Random random = new Random();
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                for(int i =0;i<=25;i++) {
                    int idestampa = random.nextInt(1,778);
                    alb.Estampas.add(operacionesestampa.GetEstampaByID(ArrayEstampas,idestampa));
                    bfwriter.write( idestampa+ "," +alb.getIDAlbum() + "," + "\n");
                }
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                for(int i =0;i<=25;i++) {
                    int idestampa = random.nextInt(1,778);
                    alb.Estampas.add(operacionesestampa.GetEstampaByID(ArrayEstampas,idestampa));
                    bfwriter.write( idestampa+ "," +alb.getIDAlbum() + "," + "\n");
                }bfwriter.close();
            }

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

   //Método que permite realizar la lectura del archivo de texto de los albums por usuario y cargarlos//
    public Album leerAlbumByUsuario(Usuario usr, ArrayList<Usuario> ArrayUsuarios, OperacionesUsuario operacionesUsuario){
        String nFile = "Albums.txt";
        String nEstampasFile = "EstampasAlbum.txt";
        File file = new File(nFile);
        File estampasFile = new File(nEstampasFile);
        ArrayList <Album> ArrayAlbum = new ArrayList<>();
        Scanner scanner;
        int numero, año, numEstampas;
        String nombre, usuario;
        Album album;

        if (file.exists()) {
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    Album e = new Album();
                    delimitar.useDelimiter("\\s*,\\s*");
                    numero = Integer.valueOf(delimitar.next());
                    año = Integer.valueOf(delimitar.next());
                    nombre = delimitar.next();
                    numEstampas = Integer.valueOf(delimitar.next());
                    usuario = delimitar.next();
                    Usuario tmpUsuario = operacionesUsuario.GetUsuarioByName(ArrayUsuarios, usuario);
                    e = new Album(año, nombre, numEstampas,numero, tmpUsuario);
                    ArrayAlbum .add(e);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (ArrayAlbum.size() > 0){
            for(Album album1: ArrayAlbum){
                if (album1.getUsuario().getnombre() == usr.getnombre()){
                    return album1;
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
