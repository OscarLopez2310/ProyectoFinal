package co.edu.uptc.Modelo;
public abstract class Servicio {//Terminada
    private int personas;
    private long duracion;

    public Servicio(int personas, long duracion) {
        if (personas<1 || duracion<0){
            throw new DatoInvalidoException("Datos invalidos.");
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
}
