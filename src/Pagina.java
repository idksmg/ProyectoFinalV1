import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Pagina {

    //Propiedades//
    private int seleccion;

    //Constructores de la clase Pagina//
    public Pagina(){
        seleccion = 0;
    }

    public Pagina(int seleccion){

        this.seleccion = seleccion;
    }

    //Método que permite la validacion del usuario si este hace Log in//
    public Usuario Validacion (ArrayList <Usuario> ArrayUsuario,String usuario, String contraseña){
        for(int i=0; i<ArrayUsuario.size(); i++){
            Usuario validacion = ArrayUsuario.get(i);
            if(validacion.getnombre().equals(usuario) && validacion.getcontraseña().equals(contraseña)){
                return  validacion;
            }
        }
        return null;
    }

    //Método para realizar el registro de albums//
    public void RegistrarAlbum(ArrayList <Album> ArrayAlbum, Usuario usr,OperacionesAlbum operacionesAlbum, ArrayList<Estampa> ArrayEstampas,OperacionesEstampa operacionesestampa){
        Scanner in = new Scanner(System.in);
        Album album = new Album();
        System.out.println("Ingresa el año de tu album: ");
        System.out.println("--------------------------------------------------------------------");
        int año = Integer.parseInt(in.nextLine());
        System.out.println("Ingresa el nombre del album: ");
        System.out.println("-------------------------------------------------------------------");
        String nombre = in.nextLine();
        System.out.println("Ingresa el numero de estampas del album: ");
        System.out.println("-------------------------------------------------------------------");
        int numEstampas = Integer.parseInt(in.nextLine());
        album.setUsuario(usr);
        album.setaño(año);
        album.setnombre(nombre);
        album.setnumEstampas(numEstampas);
        ArrayAlbum.add(album);
        int idAlbum = operacionesAlbum.IngresarAlbum(album,usr);
        album.setIDAlbum(idAlbum);
        operacionesAlbum.InicializarDetalleAlbum(album,usr,ArrayEstampas,operacionesestampa);
    }

    //Método para realizar la compra de sobres//
    public void RegistrarEstampas(ArrayList <Estampa> ArrayEstampas, Usuario usr, OperacionesAlbum operacionesAlbum, OperacionesEstampa operacionesEstampa, ArrayList<Usuario> ArrayUsuarios, OperacionesUsuario operacionesUsuario){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingresa el numero de sobres que deseas comprar: ");
        System.out.println("-------------------------------------------------------------");
        int cantidadSobres = Integer.parseInt(in.nextLine());
        Album album = operacionesAlbum.leerAlbumByUsuario(usr, ArrayUsuarios, operacionesUsuario);
        ArrayList<Estampa> estampas = operacionesEstampa.leerEstampasAlbum(album.getIDAlbum(), operacionesEstampa, ArrayEstampas);
        album.Estampas.addAll(estampas);
        operacionesEstampa.ComprarEstampas(album, usr, ArrayEstampas, operacionesEstampa, cantidadSobres);
    }

    //Método para realizar la busqueda de estampas//
    public void buscarEstampa(ArrayList <Estampa> ArrayEstampas, OperacionesEstampa operacionesEstampa, OperacionesAlbum operacionesAlbum, Usuario usr, ArrayList<Usuario> ArrayUsuarios, OperacionesUsuario operacionesUsuario){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el numero de la estampa:");
        int numero = Integer.parseInt(in.nextLine());
        Estampa estampa = operacionesEstampa.GetEstampaByID(ArrayEstampas, numero);
        if (estampa == null){
            System.out.println("No se encontro la estampa: " + numero);
            return;
        }
        Album alb = operacionesAlbum.leerAlbumByUsuario(usr, ArrayUsuarios, operacionesUsuario);
        if (alb == null){
            System.out.println("No tienes ningun album asignado, por favor registra uno.");
        }
        else{
            ArrayList<Estampa> estampasAlbum = operacionesEstampa.leerEstampasAlbum(alb.getIDAlbum(), operacionesEstampa, ArrayEstampas);
            boolean existeEnAlbum = operacionesEstampa.VerificarIDEstampa(estampa.getnumEstampa(), estampasAlbum);
            //for(int i=0; i<ArrayEstampas.size();i++){
            if (existeEnAlbum){
                System.out.println("La estampa " + numero +" se encontró en tu album");
                System.out.println("Nombre : " + estampa.getnombre());
                System.out.println("Pais : " + estampa.getPais());
                System.out.println("Club : " + estampa.getClub());
            }else{
                System.out.println("La estampa " + numero +" no se encontró en tu album");
            }
        }
    }

    //Método para consultar el precio de una estampa//
    public void consultarPrecio(ArrayList <Estampa> ArrayEstampas){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingresa el numero de estampa de la que te gustaria saber su precio: ");
        System.out.println("------------------------------------------------------------");
        int numero = Integer.parseInt(in.nextLine());
        for(int i=0; i<ArrayEstampas.size();i++) {
            Estampa estampa = ArrayEstampas.get(i);
            if (estampa.getnumEstampa() == numero) {
                Random random = new Random();
                System.out.println("----------------------------------------------------");
                System.out.println("Jugador-Estampa-Precio");
                System.out.println("---------------------------------------------------");
                System.out.println(numero +" " +estampa.getnombre()+" Q." + random.nextInt(200));
                estampa.setPrecio(random);
            }
        }
    }

    //Método para realizar intercambios//
    public void Intercambio(ArrayList<Estampa> ArrayEstampas,ArrayList <Estampa> Repetidas, ArrayList<Usuario> ArrayUsuarios,OperacionesEstampa operacionesEstampa,OperacionesUsuario operacionesUsuario, OperacionesAlbum operacionesAlbum, Usuario usr){
        Album alb = operacionesAlbum.leerAlbumByUsuario(usr, ArrayUsuarios, operacionesUsuario);
        operacionesEstampa.MostrarEstampasIntercambio(ArrayEstampas,alb,operacionesEstampa);
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("Ingresa el numero de estampa que deseas: ");
        System.out.println("----------------------------------------------");
        int num = Integer.parseInt(in.nextLine());
        ArrayList<Estampa> estampas = operacionesEstampa.leerEstampasAlbum(alb.getIDAlbum(), operacionesEstampa, ArrayEstampas);
        alb.Estampas.addAll(estampas);
        operacionesEstampa.IntercambioEstampas(ArrayEstampas,Repetidas,ArrayUsuarios,operacionesEstampa,alb,num);


    }

   //Método para comprar estampas Especiales//
    public void EstampasEspeciales(ArrayList<EstampaEspecial> ArrayEstampasEspeciales, ArrayList<Estampa> ArrayEstampas, Album alb,OperacionesEstampa operacionesestampa, OperacionesAlbum operacionesAlbum, Usuario usr,ArrayList<Usuario> ArrayUsuarios, OperacionesUsuario operacionesUsuario ){
        operacionesestampa.MostrarEstampaEspecial(ArrayEstampasEspeciales,ArrayEstampas,alb,operacionesestampa);
        Scanner in = new Scanner(System.in);
        System.out.println("Ingresa el numero de Estampa Especial que deseas: ");
        System.out.println("-----------------------------------------------");
        int idEstampaE = Integer.parseInt(in.nextLine());
        Album album = operacionesAlbum.leerAlbumByUsuario(usr, ArrayUsuarios, operacionesUsuario);
        ArrayList<Estampa> estampas = operacionesestampa.leerEstampasAlbum(album.getIDAlbum(), operacionesestampa, ArrayEstampas);
        album.Estampas.addAll(estampas);
        operacionesestampa.ComprarEstampasEspecial(album, usr, ArrayEstampasEspeciales, operacionesestampa, idEstampaE);
    }

}
