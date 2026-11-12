package co.edu.uptc.Modelo;
import java.time.LocalTime;
public class AlmuerzoEjecutivo extends Servicio{

    private static final int DURACION = 90;
    private static final long tarifaBase = 22000;
    public AlmuerzoEjecutivo(int personas, LocalTime duracion) {
        super(personas, DURACION);
    }

    @Override
    public long tarifa(){
        long t = 0;
        t= tarifaBase*getPersonas();
        return t;
    }
}
