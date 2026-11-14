package co.edu.uptc.Modelo;
public class Catering extends Servicio implements Contratable{//Terminada
    private String direccion;

    public Catering(int personas, String direccion) {
        super(personas, 0);
        if (direccion==null || direccion.isBlank()){
            throw new DatoInvalidoException("Faltan datos del catering.");
        }
        this.direccion = direccion;
    }

    @Override
    public long tarifa(){
        long t = 0;
        long personas = getPersonas()*35000;
        long transporte = 80000;
        t = personas+transporte;
        return t;
    }

    @Override
    public long calcularAnticipo(){
       long anticipo = (tarifa()*40)/100;
       return anticipo;
    }

    @Override
    public String obtenerDescripcionContrato(){
        return "Servicio de catering para: "+ getPersonas() + " personas desde: "+ direccion;
    }
}
