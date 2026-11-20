package co.edu.uptc.Modelo;
public class AlmuerzoEjecutivo extends Servicio {//Terminada

    private static final int DURACION = 90;
    private static final long TARIFA_BASE = 22000;

    public AlmuerzoEjecutivo(int personas) {
        super(personas, DURACION);
    }

    @Override
    public long tarifa() {
        long t = 0;
        t = TARIFA_BASE * getPersonas();
        return t;
    }

    @Override
    public String tipo(){
        return "ALMUERZO, "+getPersonas();
    }
}
