package co.edu.uptc.Modelo;
public class CenaCarta extends Servicio{//Terminada

    private static final int DURACION = 120;
    private static final long TARIFA_BASE = 55000;
    public CenaCarta(int personas) {
        super(personas, DURACION);
    }

    @Override
    public long tarifa(){
        long t = 0;
        t= TARIFA_BASE*getPersonas();
        return t;
    }

    @Override
    public String tipo(){
        return "CENA, "+getPersonas();
    }
}
