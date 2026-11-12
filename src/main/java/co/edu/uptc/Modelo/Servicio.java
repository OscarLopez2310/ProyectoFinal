package co.edu.uptc.Modelo;
import java.time.LocalTime;
public abstract class Servicio {
    private Integer personas;
    private long duracion;

    public Servicio(int personas, long duracion) {
        this.personas = personas;
        this.duracion = duracion;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(Integer personas) {
        this.personas = personas;
    }

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public abstract long tarifa();
}
