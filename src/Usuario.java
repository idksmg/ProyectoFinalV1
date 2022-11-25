import java.util.*;
public class Usuario {
    private String nombre;
    private String contraseña;
    private String direccion;

    private int saldo;

    public Usuario(){

    }

    public Usuario( String nombre, String direccion,String contraseña) {
        this.saldo = saldo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.direccion = direccion;

    }

    public int getSaldo() {

        return saldo;
    }

    public String getnombre() {

        return nombre;
    }

    public String getcontraseña() {

        return contraseña;
    }

    public String getDireccion() {

        return direccion;
    }

    public void setnombre(String nombre) {

        this.nombre = nombre;
    }

    public void setsaldo(int saldo) {

        this.saldo = saldo;
    }

    public void setdireccion(String direccion) {

        this.direccion = direccion;
    }

    public void setContraseña(String contraseña) {

        this.contraseña = contraseña;
    }
}
