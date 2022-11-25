import java.util.Random;

public class Estampa {

    //Propiedades//
        private int numEstampa;
        private String nombre;
        private String club;
        private String pais;

        private String posicion;

        private Random precio;

        private Usuario usuario = null;

        private Album album = null;

        private int tipo;

        //Constructores de la clase Estampa//
        public Estampa(){
            numEstampa = 0;
            nombre = "";
        }




    public Estampa(int numEstampa, String nombre, String posicion, String club, String pais, int tipo ) {
            this.numEstampa = numEstampa;
            this.nombre = nombre;
            this.club= club;
            this.pais = pais;
        }

    //getters//
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
        public int getnumEstampa() {

            return numEstampa;
        }

        public String getnombre() {

            return nombre;
        }

        public int getTipo(){
            return tipo;
        }

        public Usuario getUsuario(){
            return usuario;
        }

        public Random getPrecio(){
            return precio;
        }

        public Album getAlbum(){
            return album;
        }

    //setters//

        public void setnombre(String nombre) {

            this.nombre = nombre;
        }

        public void setnumEstampa(int numEstampa){

            this.numEstampa = numEstampa;
        }

        public void setPrecio(Random precio){
            this.precio = precio;
        }

        public void setUsuario(Usuario usuario){
            this.usuario = usuario;
        }

        public void setAlbum(Album album){
            this.album = album;
        }
        public void setTipo(int tipo)
        {
            this.tipo = tipo;
        }
    }

