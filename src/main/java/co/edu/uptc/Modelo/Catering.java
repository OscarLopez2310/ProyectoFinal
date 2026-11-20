package co.edu.uptc.Modelo;
public class Catering implements Contratable{//Terminada
    private String direccion;
    private int personas;
    public Catering(int personas, String direccion) {
        if (direccion==null || direccion.isBlank()){
            throw new DatoInvalidoException("Faltan datos del catering.");
        }
        if (personas<1){
            throw new DatoInvalidoException("La cantidad de personas debe ser mayor de cero.");
        }
        this.personas=personas;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getPersonas() {
        return personas;
    }

    public long tarifa(){
        long t = 0;
        long tarifaPersona = personas*35000;
        long transporte = 80000;
        t = tarifaPersona+transporte;
        return t;
    }

    @Override
    public long calcularAnticipo(){
       long anticipo =0;
       anticipo = (tarifa()*40)/100;
       return anticipo;
    }

    @Override
    public String obtenerDescripcionContrato(){
        return "Servicio de catering para: "+ personas + " personas desde: "+ direccion;
    }
}
