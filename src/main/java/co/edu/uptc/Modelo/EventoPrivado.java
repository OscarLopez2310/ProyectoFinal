package co.edu.uptc.Modelo;
public class EventoPrivado extends Servicio {

    private TipoEventoPri tipo;
    private static final int DURACION = 240;

    public EventoPrivado(Integer personas, TipoEventoPri tipo) {
        super(personas, DURACION);
        if (tipo == null){
            throw new DatoInvalidoException("Faltan datos de servivio.");
        }
        if (personas>tipo.getCantPersonas() || personas<1){
            throw new DatoInvalidoException("La cantidad maxima de personas del evento privado con paquete: "
                    +tipo.getDescripcion()+" es de: " +tipo.getCantPersonas()+ " y el minimo es de 1.");
        }
        this.tipo = tipo;
    }

    @Override
    public long tarifa(){
        long t = 0;
        if (tipo.equals(TipoEventoPri.BASICO)){
            t = tipo.getCosto();
        }else if (tipo.equals(TipoEventoPri.SUPERIOR)){
            t = tipo.getCosto();
        }
        return t;
    }
}
