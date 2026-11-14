package co.edu.uptc.Modelo;
public class EventoPrivado extends Servicio implements Contratable { //Terminada

    private TipoEventoPri tipo;
    private static final int DURACION = 240;

    public EventoPrivado(int personas, TipoEventoPri tipo) {
        super(personas, DURACION);
        if (tipo == null){
            throw new DatoInvalidoException("Faltan datos de servicio.");
        }
        if (personas>tipo.getCantPersonas() || personas<1){
            throw new DatoInvalidoException("La cantidad maxima de personas del evento privado con paquete: "
                    +tipo.getDescripcion()+" es de: " +tipo.getCantPersonas()+ " y el minimo es de 1.");
        }
        this.tipo = tipo;
    }

    @Override
    public long tarifa(){
        long t = tipo.getCosto();
            return t;
    }

    @Override
    public long calcularAnticipo(){
      return tarifa()/2;
    }

    @Override
    public String obtenerDescripcionContrato(){
      return "Paquete contratado: "+ tipo.getDescripcion();
    }
}
