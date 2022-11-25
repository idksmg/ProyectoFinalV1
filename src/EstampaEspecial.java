public class EstampaEspecial extends Estampa {

    //Propiedades//
    private String paisLeyenda;
    private String paisEscudo;

    private int mundialesJugados;

    private int precio;

    //Constructores de la clase EstampaEspecial//
    public EstampaEspecial(){
        super();
        paisLeyenda = "";
        paisEscudo = "";
    }

    public EstampaEspecial(int numEstampa, String nombre,String posicion, String club, String pais, int tipo, int mundialesJugados, String paisLeyenda, String piasEscudo){
        super(numEstampa,nombre,posicion,club,pais,tipo);
        this.paisLeyenda = paisLeyenda;
        this.paisEscudo = paisEscudo;
    }

    //getters//

    public String getPaisLeyenda(){
        return  paisLeyenda;
    }

    public String getPaisEscudo(){
        return paisEscudo;
    }

    //setters//

    public void setPaisLeyenda(String paisLeyenda){
        this.paisLeyenda = paisLeyenda;
    }

    public void setPaisEscudo(String paisEscudo){
        this.paisEscudo = paisEscudo;
    }

    public void tipo(){
        super.setTipo(1);
    }
}
