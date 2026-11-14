package co.edu.uptc.Modelo;
public enum TipoEventoPri {//terminada
    BASICO("Basico", 400000, 8),
    SUPERIOR("Superior", 650000, 12);

    private final String descripcion;
    private final long costo;
    private final int cantPersonas;

    TipoEventoPri(String descripcion, int costo, int cantPersonas) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.cantPersonas = cantPersonas;
    }

    public long getCosto() {
        return costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }
}
