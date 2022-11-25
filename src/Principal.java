//
// Sara Mylena Guzmán Salvatierra
// 22097
// Programación Orientada a Objetos
// Proyecto Final
//

import java.util.*;
import java.util.Arrays;
public class Principal {
    public static void main(String[] args){
        //Se inicializan todos los Arrays y objetos a utilizar//
        int respuesta = 0;
        Pagina pagina = new Pagina();
        Usuario usr = new Usuario();
        Estampa estampa = new Estampa();
        Album album = new Album();
        ArrayList <Usuario> Arrayusuarios = new ArrayList<>();
        ArrayList <Estampa> ArrayEstampas = new ArrayList<>();
        ArrayList <EstampaEspecial> ArrayEstampasEspeciales = new ArrayList<>();
        ArrayList <Album> ArrayAlbum = new ArrayList<>();
        ArrayList<Estampa> Repetidas = new ArrayList<>();
        OperacionesUsuario operacionesUsuario = new OperacionesUsuario();
        OperacionesEstampa operacionesEstampa = new OperacionesEstampa();
        OperacionesAlbum operacionesAlbum = new OperacionesAlbum();
        try {
            Arrayusuarios = operacionesUsuario.leerUsuarios();
            ArrayEstampas = operacionesEstampa.leerEstampas();
            ArrayEstampasEspeciales = operacionesEstampa.leerEstampaespecial();

            Scanner in = new Scanner(System.in);
            System.out.println("---------BIENVENIDO A STAMPTRADE----------");
            try {
                while(respuesta !=3) {
                    System.out.println("Selecciona tu opción:\n1. Log in\n2. Crear cuenta");
                    System.out.println("------------------------------------------------------------------");
                    int seleccion = Integer.parseInt(in.nextLine());
                    if (seleccion == 1) {
                        //Si el usuario posee ya no una cuenta ingresa a la opcion 1 donde se le solicita su informacion personas//
                        System.out.println("Ingresa tu nombre de usuario: ");
                        System.out.println("---------------------------------------------------------------");
                        String usuario = in.nextLine();
                        System.out.println("Ingresa tu contraseña: ");
                        System.out.println("----------------------------------------------------------------");
                        String contraseña = in.nextLine();
                        usr = pagina.Validacion(Arrayusuarios, usuario, contraseña);
                        if (usr != null) {
                            System.out.println("Bienvenido " + usuario + " que gusto verte de nuevo");
                            System.out.println("----------------------------------------------------------------");

                            break;
                        } else {
                            //Si el usuario ingresa los datos incorrectos se le regresa al menú//
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("Usuario Invalido, Intente de nuevo");
                            continue;
                        }

                    }
                    if (seleccion == 2) {
                        //Si el usuario no posee una cuenta se le solicita toda la informacion para la creacion de una//
                        System.out.println("Primero ingresa tu nombre de usuario: ");
                        System.out.println("----------------------------------------------------------------");
                        String nombre = in.nextLine();
                        System.out.println("Ahora ingresa tu contraseña: ");
                        System.out.println("-----------------------------------------------------------------");
                        String contraseña = in.nextLine();
                        System.out.println("Ahora ingresa tu correo: ");
                        System.out.println("------------------------------------------------------------------");
                        String direccion = in.nextLine();
                        int saldo = 1000;
                        usr = new Usuario(nombre,direccion,contraseña);
                        System.out.println("Gracias! Ya puedes comenzar: ");
                        System.out.println("------------------------------------------------------------------");
                        operacionesUsuario.IngresarUsuario(usr);
                        break;
                    }
                }
            }// Excepción por si el usuario ingresa cualquier cosa no dentro del programa
                catch (Exception ex) {
                System.out.println("Opcion invalida " + ex.getMessage());
                }
            try {
                while (respuesta != 7) {
                    try {
                        //Menú de opciones del programa//
                        System.out.println("¿Qué haremos hoy?\n1. Registrar mi Album\n2. Comprar estampas\n3. Buscar estampas\n4. Consultar precio en el mercado\n5. Intercambio\n6. Obtener Estampas Especiales\n7. Salir");
                        System.out.println("-----------------------------------------------------------");
                        respuesta = Integer.parseInt(in.nextLine());
                        if (respuesta == 1) {
                            //Dentro de la opcion 1 llamamos al método de Registrar Album para comenzar con el programa//
                            pagina.RegistrarAlbum(ArrayAlbum,usr,operacionesAlbum,ArrayEstampas,operacionesEstampa);
                        }
                        if (respuesta == 2) {
                            //Dentro de la opcion 2 llamamos al método que nos permitira comprar sobres//
                            pagina.RegistrarEstampas(ArrayEstampas,usr,operacionesAlbum, operacionesEstampa, Arrayusuarios, operacionesUsuario);

                        }
                        if (respuesta == 3) {
                            if (ArrayEstampas.size() > 0) {
                                //Dentro de la opcion 3 mandamos a llamar al método que nos permita buscar las estampas deseadas y confirmar si las tenemos o no//
                                pagina.buscarEstampa(ArrayEstampas, operacionesEstampa, operacionesAlbum, usr, Arrayusuarios, operacionesUsuario);
                            } else {
                                System.out.println("No hay ninguna estampa para realizar esta acción");
                            }
                        }
                        if (respuesta == 4) {
                            //Dentro de la opcion 4 mandamos a llamar al método que nos permitira consultar el precio de la estampa que solicitemos//
                            if (ArrayEstampas.size() > 0) {
                                pagina.consultarPrecio(ArrayEstampas);
                            } else {
                                System.out.println("No hay ninguna estampa para realizar esta acción");
                            }
                        }
                        if(respuesta == 5){
                            //Dentro de la opcion 5 mandamos a llamar al método que nos permitira realizar el Intercambio//
                            pagina.Intercambio(ArrayEstampas,Repetidas,Arrayusuarios,operacionesEstampa,operacionesUsuario,operacionesAlbum,usr);
                        }
                        if(respuesta ==6){
                            //Dentro de la opcion 6 mandamos a llamar al método que nos permitirá comprar estampas Especiales//
                            pagina.EstampasEspeciales(ArrayEstampasEspeciales,ArrayEstampas,album,operacionesEstampa, operacionesAlbum,usr,Arrayusuarios,operacionesUsuario);
                        }
                    } catch (Exception ex) {
                        // Excepción por si el usuario ingresa cualquier cosa no dentro del programa
                        System.out.println("Opción Invalida " + ex.getMessage());
                    }
                }
            }
            catch (Exception ex) {
                // Excepción por si el usuario ingresa cualquier cosa no dentro del programa
                System.out.println("Opcion invalida " + ex.getMessage());
            }
        }

        catch(Exception ex){
            System.out.println("Ocurrio un error en la Aplicación " + ex.getMessage()+ " Contacte a Soporte");
        }
    }
}
