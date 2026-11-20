package co.edu.uptc.Modelo;
public class Mesa {//Terminada
    private String idmesa;
    private int capacidad;

    public Mesa(String idmesa, int capacidad) {
        if (idmesa==null|| idmesa.isBlank()){
            throw new DatoInvalidoException("Faltan datos de la mesa");
        }
        if (capacidad<1){
            throw new DatoInvalidoException("La capacidad minima debe ser mayor de cero");
        }
        this.idmesa = idmesa;
        this.capacidad = capacidad;
    }

    public String getIdmesa() {
        return idmesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String toString(){
        return "Mesa: " + idmesa + " | "+" capacidad: " + capacidad;
    }
}
