import java.util.ArrayList;

public interface IEstampa {

    //Métodos abstractos de la clase de interface IEstampa//
    public void IngresarEstampaIntercambio(Estampa estampa);
    public void IngresarEstampa(Estampa estampa);
    public ArrayList<Estampa> leerEstampas();
    public ArrayList<EstampaEspecial> leerEstampaespecial();
    public void MostrarEstampasIntercambio(ArrayList <Estampa> ArrayEstampasIntercambio, Album alb,OperacionesEstampa operacionesestampa);
}
