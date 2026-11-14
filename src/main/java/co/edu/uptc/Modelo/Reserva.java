package co.edu.uptc.Modelo;
import java.time.LocalDateTime;
public class Reserva {

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
        if (fecha_Y_Hora_Reserva.getHour()>22 || fecha_Y_Hora_Reserva.getHour()<11){
            throw new DatoInvalidoException("El horario de atencion es de 11:20 AM a 11:00 PM.");
        }
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.mesa = mesa;
        this.servicio = servicio;
        this.fecha_Y_Hora_Reserva = fecha_Y_Hora_Reserva;
        this.estadoReserva = EstadoReserva.CREADA;
        this.penalidad = 0;
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
}
