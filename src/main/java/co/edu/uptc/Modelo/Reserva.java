package co.edu.uptc.Modelo;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
public class Reserva { //INCOMPLETA
    private String idReserva;
    private Cliente cliente;
    private Mesa mesa;
    private Servicio servicio;
    private LocalDateTime fecha_Y_Hora_Reserva;
    private EstadoReserva estadoReserva;
    private long penalidad;

    public Reserva(String idReserva, Cliente cliente, Mesa mesa,
                   Servicio servicio, LocalDateTime fecha_Y_Hora_Reserva) {

        if (idReserva==null|| idReserva.isBlank() || cliente==null
                || mesa==null || servicio==null || fecha_Y_Hora_Reserva==null){
            throw new DatoInvalidoException("Faltan datos de la reserva");
        }
        if (servicio.getPersonas()>mesa.getCapacidad()) {
            throw new DatoInvalidoException("La mesa no tiene capacidad suficiente.");
        }
        LocalTime horaReserva = fecha_Y_Hora_Reserva.toLocalTime();
        if (horaReserva.isBefore(LocalTime.of(11, 30)) || horaReserva.isAfter(LocalTime.of(23, 0))) {
            throw new DatoInvalidoException("El horario de atencion es de 11:30 AM a 11:00 PM.");
        }
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.mesa = mesa;
        this.servicio = servicio;
        this.fecha_Y_Hora_Reserva = fecha_Y_Hora_Reserva;
        this.estadoReserva = EstadoReserva.CREADA;
        this.penalidad = 0;
    }

    public Reserva(String idReserva, Cliente cliente, Mesa mesa, Servicio servicio,
                   LocalDateTime fecha_Y_Hora_Reserva, EstadoReserva estadoReserva, long penalidad) {
        this(idReserva, cliente, mesa, servicio, fecha_Y_Hora_Reserva);
        if (estadoReserva == null || penalidad < 0) {
            throw new DatoInvalidoException("Estado o penalidad inválidos.");
        }

        this.estadoReserva = estadoReserva;
        this.penalidad = penalidad;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public LocalDateTime getFecha_Y_Hora_Reserva() {
        return fecha_Y_Hora_Reserva;
    }

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public long getPenalidad() {
        return penalidad;
    }

    /*public LocalTime getFin(){
        LocalTime horaInicio = fecha_Y_Hora_Reserva.toLocalTime();
        LocalTime duracion = servicio.getDuracion();
        LocalTime fin = horaInicio+duracion;
    }

    public boolean seCruzan(Reserva reservaNueva){

        if (idReserva.equalsIgnoreCase(reservaNueva.getIdReserva())
                || !estadoReserva.equals(EstadoReserva.CANCELADA)
                || !reservaNueva.getEstadoReserva().equals(EstadoReserva.CANCELADA)
                ||mesa.getIdmesa().equalsIgnoreCase(reservaNueva.getIdReserva())){

            LocalTime horaIniReservaActual = fecha_Y_Hora_Reserva.toLocalTime();
            LocalTime horaIniReservaNueva = reservaNueva.getFecha_Y_Hora_Reserva().toLocalTime();
            if (horaIniReservaActual.isBefore(reservaNueva.getFin()) && horaIniReservaNueva.isBefore(getFin())){
                return true;}
        }

        return false;
    }

     */
    public long calcularValor(){
        long total = 0;
        long base = 0;
        long descuento = 0;
        base =servicio.tarifa();
        long recargo =0;
        if (fecha_Y_Hora_Reserva.get(ChronoField.DAY_OF_WEEK)==6||fecha_Y_Hora_Reserva.get(ChronoField.DAY_OF_WEEK)==7 ||fecha_Y_Hora_Reserva.getHour()>=18){
            recargo = (servicio.tarifa()*10)/100;
        }
        base += recargo;
        descuento = base*cliente.calcularPorcentajeDescuento(fecha_Y_Hora_Reserva)/100;
        total += base - descuento;
        return total;
    }

    public void iniciar()throws TransicionInvalidaException{
         if (estadoReserva.equals(EstadoReserva.CONFIRMADA) && LocalDateTime.now().toLocalDate().equals(fecha_Y_Hora_Reserva.toLocalDate())){
             estadoReserva= EstadoReserva.EN_CURSO;
         }
         else {
             throw new TransicionInvalidaException("Solo se puede iniciar una reserva confirmada");
         }
    }

    public void confirmar()throws TransicionInvalidaException{//YA
        if (estadoReserva.equals(EstadoReserva.CREADA)){
            estadoReserva= EstadoReserva.CONFIRMADA;
        }
        else{
        throw new TransicionInvalidaException("No se puede pasar de: "+ estadoReserva + " a Confirmada.");}
    }

    public void cerrar()throws TransicionInvalidaException{//YA
        if (estadoReserva.equals(EstadoReserva.EN_CURSO)){
            estadoReserva= EstadoReserva.CERRADA;
        }
        else{
        throw new TransicionInvalidaException("No se puede pasar de: "+ estadoReserva + " a Confirmada.");}
    }

    public void cancelar()throws TransicionInvalidaException{
        long horas = ChronoUnit.HOURS.between(LocalDateTime.now(),fecha_Y_Hora_Reserva);
        if (estadoReserva.equals(EstadoReserva.CONFIRMADA) || estadoReserva.equals(EstadoReserva.CREADA)){
            if (estadoReserva.equals(EstadoReserva.CONFIRMADA) && horas<24){
                penalidad = (calcularValor()*25)/100;
            }
            estadoReserva= EstadoReserva.CANCELADA;
        }
        else{
        throw new TransicionInvalidaException("Solo se puede cancelar una reserva confirmada o creada.");}
    }

    public void reprogramar(LocalDateTime fechaNueva, Mesa nuevaMesa)throws TransicionInvalidaException{
       LocalTime horaEntrada = fechaNueva.toLocalTime();
       if(fechaNueva.isBefore(LocalDateTime.now()) || fecha_Y_Hora_Reserva.equals(fechaNueva) || horaEntrada.isBefore(LocalTime.of(11, 30)) || horaEntrada.isAfter(LocalTime.of(23,0))){
           throw new DatoInvalidoException("Error la hora de atencion es de 11:30 AM a 11 PM y la fecha nueva debe ser diferente a la fecha actual a futuro.");
       }
       if (estadoReserva.equals(EstadoReserva.CERRADA) || estadoReserva.equals(EstadoReserva.CANCELADA)|| estadoReserva.equals(EstadoReserva.EN_CURSO)){
           throw new TransicionInvalidaException("La reserva tiene que estar confirmada o creada.");
       }
       if (servicio.getPersonas()>nuevaMesa.getCapacidad()){
           throw new DatoInvalidoException("La mesa no tiene capacidad suficiente.");
       }
           this.fecha_Y_Hora_Reserva = fechaNueva;
           this.mesa=nuevaMesa;
    }
}
