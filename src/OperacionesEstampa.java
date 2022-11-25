import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OperacionesEstampa implements IEstampa{

    //Método para guardar la informacion de una estamopa para intercambio
    @Override
    public void IngresarEstampaIntercambio(Estampa estampa){
        FileWriter flwriter = null;
        try {
            String nFile = "EstampasIntercambio.txt";
            File file = new File(nFile);
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(estampa.getnombre() + "," + estampa.getnumEstampa() + "\n");
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(estampa.getnumEstampa() + "," + estampa.getnombre() + "\n");
                bfwriter.close();
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

    //Método para guardar la informacion de las estampas//
    @Override
    public void IngresarEstampa(Estampa estampa){
        FileWriter flwriter = null;
        try {
            String nFile = "Estampas.txt";
            File file = new File(nFile);
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(estampa.getnumEstampa()+", " +estampa.getnombre() + ", "+estampa.getAlbum()+", "+estampa.getUsuario()+ "\n");
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(estampa.getnumEstampa() + "," + estampa.getnombre() + ", "+estampa.getAlbum()+", "+estampa.getUsuario()+"\n");
                bfwriter.close();
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

    //Método para leer las estampas del archivo de texto//
    @Override
    public ArrayList<Estampa> leerEstampas(){
        String nFile = "Estampas.txt";
        File file = new File(nFile);
        ArrayList <Estampa> ArrayIntercambio = new ArrayList<>();
        Scanner scanner;
        int numero;
        String nombre,posicion, club,pais;
        Album album;
        Usuario usr;

        if (file.exists()) {
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    Estampa e = new Estampa();
                    delimitar.useDelimiter("\\s*,\\s*");
                    String tmpNumero = delimitar.next();
                    tmpNumero = tmpNumero.replaceAll("[^0-9]+", "");
                    numero = Integer.valueOf(tmpNumero);
                    nombre = delimitar.next();
                    posicion = delimitar.next();
                    club= delimitar.next();
                    pais = delimitar.next();
                    e = new Estampa(numero,nombre,posicion,club,pais,0);
                    ArrayIntercambio.add(e);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ArrayIntercambio;
    }

    //Método para leer el archivo de estampas especiales//
    @Override
    public ArrayList<EstampaEspecial> leerEstampaespecial(){
        String nFile = "EstampaEspecial.txt";
        File file = new File(nFile);
        ArrayList <EstampaEspecial> ArrayEstampasEspacial = new ArrayList<>();
        Scanner scanner;
        int numero,mundiales;
        String nombre,pais,posicion;
        if (file.exists()) {
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    EstampaEspecial e = new EstampaEspecial();
                    delimitar.useDelimiter("\\s*,\\s*");
                    numero = Integer.parseInt(delimitar.next());
                    nombre = delimitar.next();
                    pais= delimitar.next();
                    posicion = delimitar.next();
                    mundiales = Integer.parseInt(delimitar.next());
                    e = new EstampaEspecial(numero,nombre,posicion,"",pais,mundiales,0,"","");
                    ArrayEstampasEspacial.add(e);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ArrayEstampasEspacial;
    }

    //Método para imprimir el archivo de estampas especiales//
    @Override
    public void MostrarEstampasIntercambio(ArrayList<Estampa> ArrayEstampas, Album alb,OperacionesEstampa operacionesestampa){
        FileWriter flwriter = null;
        try {
            String nFile = "EstampasIntercambio.txt";
            File file = new File(nFile);
            Random random = new Random();
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                System.out.println("-----------------------------------------");
                System.out.println("Estampas disponibles para Intercambio: ");
                System.out.println("-------------------------------------------");
                for (int i = 0; i < 3; i++) {
                    int idestampa = random.nextInt(1, 805);
                    //valida si no es estampa repetida
                    boolean existeEstampa = operacionesestampa.VerificarIDEstampa(idestampa, alb);
                    if (!existeEstampa) {
                        for(int u=0; u<ArrayEstampas.size();u++){
                            Estampa estampa = ArrayEstampas.get(u);
                            if(estampa.getnumEstampa()==idestampa) {
                                bfwriter.write(idestampa + "," + alb.getIDAlbum() + "," + "\n");
                                System.out.println(idestampa + ", " + estampa.getnombre() + ", " + estampa.getPais());
                            }
                        }
                    }
                }bfwriter.close();
            }else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                System.out.println("-------------------------------------------");
                System.out.println("Estampas disponibles para Intercambio: ");
                System.out.println("--------------------------------------------");
                for(int i = 0; i < 3; i++) {
                    int idestampa = random.nextInt(1, 805);
                    //valida si no es estampa repetida
                    boolean existeEstampa = operacionesestampa.VerificarIDEstampa(idestampa, alb);
                    if (!existeEstampa) {
                        for(int u=0; u<ArrayEstampas.size();u++){
                            Estampa estampa = ArrayEstampas.get(u);
                            if(estampa.getnumEstampa()==idestampa) {
                                bfwriter.write(idestampa + "," + alb.getIDAlbum() + "," + "\n");
                                System.out.println(idestampa + ", " + estampa.getnombre() + ", " + estampa.getPais());
                            }
                        }
                    }
                }bfwriter.close();
            }

        }catch (IOException e) {
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

    //Método para obtener una estampa por su Id (numero)//
    public Estampa GetEstampaByID(ArrayList<Estampa> Arrayestampa,int idEstampa)
    {
        for(Estampa estampa: Arrayestampa){
            if (estampa.getnumEstampa() == idEstampa)
                return estampa;
        }
       return null;
    }

    //Método para comprar sobres//
    public  void ComprarEstampas(Album alb,Usuario usr,ArrayList<Estampa>ArrayEstampas,OperacionesEstampa operacionesestampa, int numeroSobres) {
        FileWriter flwriter = null;
        try {
            String nFile = "EstampasAlbum.txt";
            File file = new File(nFile);
            Random random = new Random();
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                for(int i =0;i<=3 * numeroSobres;i++){
                    int idestampa = random.nextInt(1,805);
                    //valida si no es estampa repetida
                    boolean existeEstampa = operacionesestampa.VerificarIDEstampa(idestampa, alb);
                    if (!existeEstampa){
                        alb.Estampas.add(operacionesestampa.GetEstampaByID(ArrayEstampas,idestampa));
                        bfwriter.write( idestampa+ "," +alb.getIDAlbum() + "," + "\n");
                        System.out.println("Se agregó la estampa " + idestampa + " a tu album");
                        System.out.println("---------------------------------------------------");
                    }else{
                        alb.Repetidas.add(operacionesestampa.GetEstampaByID(ArrayEstampas,idestampa));
                        bfwriter.write( idestampa+ "," +alb.getIDAlbum() + ",R" + "\n");
                        System.out.println("La estampa " + idestampa + " se añadió a tus repetidas");
                        System.out.println("--------------------------------------------------");
                    }
                }
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                for(int i =0;i<=3 * numeroSobres;i++) {
                    int idestampa = random.nextInt(1,805);
                    //valida si no es estampa repetida
                    boolean existeEstampa = operacionesestampa.VerificarIDEstampa(idestampa, alb);
                    if (!existeEstampa){
                        alb.Estampas.add(operacionesestampa.GetEstampaByID(ArrayEstampas,idestampa));
                        bfwriter.write( idestampa+ "," +alb.getIDAlbum() + "," + "\n");
                        System.out.println("Se agregó la estampa " + idestampa + " a tu album");
                        System.out.println("----------------------------------------------");
                    }else{
                        alb.Repetidas.add(operacionesestampa.GetEstampaByID(ArrayEstampas,idestampa));
                        bfwriter.write( idestampa+ "," +alb.getIDAlbum() + ",R" + "\n");
                        System.out.println("La estampa " + idestampa + " se añadió a tus repetidas");
                        System.out.println("-----------------------------------------------");
                    }
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

    //Método para comprar estampas especiales//
    public  void ComprarEstampasEspecial(Album alb,Usuario usr,ArrayList<EstampaEspecial>ArrayEstampasE,OperacionesEstampa operacionesestampa,int idEstampae) {
        FileWriter flwriter = null;
        try {
            String nFile = "EstampasAlbum.txt";
            File file = new File(nFile);
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                int idestampa = idEstampae;
                //valida si no es estampa repetida
                bfwriter.write( idestampa+ "," +alb.getIDAlbum() + "," + "\n");
                System.out.println("Se agregó la estampa " + idestampa + " a tu album");
                System.out.println("---------------------------------------------");
                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                int idestampa = idEstampae;
                //valida si no es estampa repetida
                bfwriter.write( idestampa+ "," +alb.getIDAlbum() + "," + "\n");
                System.out.println("Se agregó la estampa " + idestampa + " a tu album");
                System.out.println("---------------------------------------------");
                bfwriter.close();
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

    //Se valida que pueda comprar solo si tiene album//
    public ArrayList<Estampa> ComprarSobreEstampas(Usuario usr, OperacionesAlbum operacionesAlbum, ArrayList<Usuario> ArrayUsuarios, OperacionesUsuario operacionesUsuario){
        //Validar si usuario tiene album
        Album alb = operacionesAlbum.leerAlbumByUsuario(usr, ArrayUsuarios,operacionesUsuario);
        if (alb == null)
        {
            System.out.println("No tienes ningun album asignado, por favor registra uno.");
            System.out.println("-----------------------------------------------------");
            return null;
        }
        //
        return null;
    }

    //Método para verificar el id de una estampa y si esta, esta dentro del array de Estampas del Album//
    public boolean VerificarIDEstampa(int idestampa, Album alb){
       for (Estampa estampa: alb.Estampas){
           if (estampa.getnumEstampa() == idestampa){
               return true;
           }
       }
       return false;
    }

    //Método para verificar el id de una estampa y si esta, está dentro del array de Estampas general//
    public boolean VerificarIDEstampa(int idestampa, ArrayList<Estampa> estampas){
        for (Estampa estampa: estampas){
            if (estampa.getnumEstampa() == idestampa){
                return true;
            }
        }
        return false;
    }

    //Método para cargar la informacion de las estampas dentro de un album//
    public ArrayList<Estampa> leerEstampasAlbum(int idAlbum, OperacionesEstampa operacionesEstampa, ArrayList<Estampa> ArrayEstampas){
        String nFile = "EstampasAlbum.txt";
        File file = new File(nFile);
        ArrayList <Estampa> ArrayIntercambio = new ArrayList<>();
        Scanner scanner;
        int numeroEstampa, idUsuario;
        String tipo;

        if (file.exists()) {
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);
                    Estampa e = new Estampa();
                    delimitar.useDelimiter("\\s*,\\s*");
                    numeroEstampa = Integer.valueOf(delimitar.next());
                    ArrayIntercambio.add(GetEstampaByID(ArrayEstampas, numeroEstampa));
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ArrayIntercambio;
    }

    //Método para hacer el intercambio de estampas//
    public void IntercambioEstampas(ArrayList<Estampa> ArrayEstampas,ArrayList <Estampa> Repetidas, ArrayList<Usuario> ArrayUsuarios,OperacionesEstampa operacionesEstampa,Album alb, int num){
        FileWriter flwriter = null;
        try {
            String nFile = "EstampasAlbum.txt";
            File file = new File(nFile);
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                    //valida si no es estampa repetida
                    boolean existeEstampa = operacionesEstampa.VerificarIDEstampa(num, alb);
                    if (!existeEstampa){
                        alb.Estampas.add(operacionesEstampa.GetEstampaByID(ArrayEstampas,num));
                        bfwriter.write( num+ "," +alb.getIDAlbum() + "," + "\n");
                        System.out.println("Se agregó la estampa " + num + " a tu album");
                        System.out.println("--------------------------------------------");
                    }else{
                        alb.Repetidas.add(operacionesEstampa.GetEstampaByID(ArrayEstampas,num));
                        bfwriter.write( num+ "," +alb.getIDAlbum() + ",R" + "\n");
                        System.out.println("La estampa " + num + " se añadió a tus repetidas");
                        System.out.println("--------------------------------------------");
                    }

                bfwriter.close();

            } else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                    //valida si no es estampa repetida
                    boolean existeEstampa = operacionesEstampa.VerificarIDEstampa(num, alb);
                    if (!existeEstampa){
                        alb.Estampas.add(operacionesEstampa.GetEstampaByID(ArrayEstampas,num));
                        bfwriter.write( num+ "," +alb.getIDAlbum() + "," + "\n");
                        System.out.println("Se agregó la estampa " + num + " a tu album");
                        System.out.println("------------------------------------------");
                    }else{
                        alb.Repetidas.add(operacionesEstampa.GetEstampaByID(ArrayEstampas,num));
                        bfwriter.write( num+ "," +alb.getIDAlbum() + ",R" + "\n");
                        System.out.println("La estampa " + num + " se añadió a tus repetidas");
                        System.out.println("------------------------------------------");
                    }
                bfwriter.close();
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

    //Método para imprimir la lista de estampas especiales//
    public void MostrarEstampaEspecial(ArrayList<EstampaEspecial> ArrayEstampasEspeciales, ArrayList<Estampa> ArrayEstampas, Album alb,OperacionesEstampa operacionesestampa){
        FileWriter flwriter = null;

        try {
            String nFile = "EstampaEspecial.txt";
            File file = new File(nFile);
            if (!file.exists()) {
                flwriter = new FileWriter(nFile);
                //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                System.out.println("-----------------------------------");
                System.out.println("Estampas Especiales disponibles: ");
                System.out.println("-----------------------------------");
                    //valida si no es estampa repetida
                for(int u=0; u<ArrayEstampasEspeciales.size();u++){
                    Estampa estampa = ArrayEstampas.get(u);
                    if(estampa instanceof EstampaEspecial){
                        EstampaEspecial estampaEspecial = (EstampaEspecial)estampa;
                        System.out.println(estampa.getnumEstampa() + ", " + estampa.getnombre() + ", " + estampa.getPais());
                    }


                }

                bfwriter.close();
            }else {
                flwriter = new FileWriter(nFile, true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                System.out.println("--------------------------------------");
                System.out.println("Estampas disponibles para Intercambio: ");
                System.out.println("-------------------------------------");
                    //valida si no es estampa repetida
                        for(int u=0; u<ArrayEstampasEspeciales.size();u++){
                            Estampa estampa = ArrayEstampasEspeciales.get(u);
                            if(estampa instanceof EstampaEspecial){
                                EstampaEspecial estampaEspecial = (EstampaEspecial)estampa;
                                System.out.println(estampa.getnumEstampa() + ", " + estampa.getnombre() + ", " + estampa.getPais());
                            }
                        }
                bfwriter.close();
            }

        }catch (IOException e) {
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
}
