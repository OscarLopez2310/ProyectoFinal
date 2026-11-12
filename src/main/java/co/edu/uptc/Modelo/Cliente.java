package co.edu.uptc.Modelo;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Cliente {
    private String nombre;
    private String documento;
    private String telefono;
    private LocalDate fechaDeCliente;
    private LocalDateTime fechaReserva;

    public Cliente(String nombre, String documento, String telefono, LocalDate fechaDeCliente, LocalDateTime fechaReserva) {
        if (nombre==null || documento==null || telefono==null || fechaDeCliente==null){
            throw new DatoInvalidoException("Faltan datos del cliente.");
        }
        if (fechaReserva.getHour()>23 || fechaReserva.getHour()<11.30){
            throw new DatoInvalidoException("El horario de atencion es de 11:30 AM a 11:00 pm");
        }
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.fechaDeCliente = fechaDeCliente;
        this.fechaReserva = fechaReserva;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaDeCliente() {
        return fechaDeCliente;
    }

    public void setFechaDeCliente(LocalDate fechaDeCliente) {
        this.fechaDeCliente = fechaDeCliente;
    }
}
