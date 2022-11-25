import java.util.*;
public class Album {

    //Propiedades//
        private int IDAlbum;
        private String nombre;
        private int año;
        private int numEstampas=0;
        private Usuario usuario = null;

        ArrayList<Estampa> Estampas = new ArrayList<>();
        ArrayList<Estampa> Repetidas = new ArrayList<>();

        //Constructores de la clase Album//
        public Album(){

        }

        public Album(int año, String nombre, int numEstampas, int IDAlbum, Usuario usuario){
            this.año = año;
            this.nombre = nombre;
            this.numEstampas = numEstampas;
            this.IDAlbum = IDAlbum;
            this.usuario = usuario;
            Estampas =new ArrayList<>(numEstampas);
        }

    //getters//
        public int getnumEstampas() {

            return numEstampas;
        }

        public String getnombre() {

            return nombre;
        }

        public int getaño() {

            return año;
        }

        public int getIDAlbum(){

            return IDAlbum;
        }

        public Usuario getUsuario(){
            return usuario;
        }


    //setters//
        public void setnumEstampas(int numEstampas) {

            this.numEstampas = numEstampas;
        }

        public void setnombre(String nombre) {

            this.nombre = nombre;
        }

        public void setaño(int año) {

            this.año = año;
        }

        public void setUsuario(Usuario usuario){
            this.usuario = usuario;
        }

        public void setIDAlbum(int IDAlbum){

            this.IDAlbum = IDAlbum;
        }

    }

