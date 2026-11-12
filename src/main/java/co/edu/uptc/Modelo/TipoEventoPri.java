package co.edu.uptc.Modelo;
public enum TipoEventoPri {
    BASICO("Basico", 400000, 8),
    SUPERIOR("Superior", 650000, 12);

    private final String descripcion;
    private final int costo;
    private final int cantPersonas;

    TipoEventoPri(String descripcion, int costo, int cantPersonas) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.cantPersonas = cantPersonas;
    }

    public int getCosto() {
        return costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }
}
