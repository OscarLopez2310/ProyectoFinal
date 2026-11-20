package co.edu.uptc.Modelo;
public abstract class Servicio {//Terminada
    private int personas;
    private long duracion;

    public Servicio(int personas, long duracion) {
        if (personas<1 || duracion<0){
            throw new DatoInvalidoException("La duracion y la cantidad de personas debe ser mayor de cero.");
        }
        this.personas = personas;
        this.duracion = duracion;
    }

    public int getPersonas() {
        return personas;
    }

    public long getDuracion() {
        return duracion;
    }

    public abstract long tarifa();

    public abstract String tipo();
}
