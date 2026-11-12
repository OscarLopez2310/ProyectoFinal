package co.edu.uptc.Modelo;
import java.time.LocalTime;
public class CenaCarta extends Servicio{

    private static final int DURACION = 90;
    private static final long tarifaBase = 55000;
    public CenaCarta(int personas, LocalTime duracion) {
        super(personas, DURACION);
    }

    @Override
    public long tarifa(){
        long t = 0;
        t= tarifaBase*getPersonas();
        return t;
    }
}
