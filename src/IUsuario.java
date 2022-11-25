import java.util.ArrayList;

public interface IUsuario {

    //Métodos abstractos de la clase de interface IEstampa//
    public ArrayList<Usuario> leerUsuarios();
    public void IngresarUsuario(Usuario usr);
    public Usuario GetUsuarioByName(ArrayList<Usuario> ArrayUsuarios, String nombre);
}
