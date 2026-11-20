package co.edu.uptc.Modelo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
public class Cliente {//terminado
    private String nombre;
    private String documento;
    private String telefono;
    private LocalDate desdeCuandoViene;

    public Cliente(String nombre, String documento, String telefono, LocalDate desdeCuandoViene) {
        if (nombre==null || nombre.isBlank() || documento==null || documento.isBlank()
                || telefono==null  || telefono.isBlank() || desdeCuandoViene==null){
            throw new DatoInvalidoException("Faltan datos del cliente.");
        }
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.desdeCuandoViene = desdeCuandoViene;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getDesdeCuandoViene() {
        return desdeCuandoViene;
    }

    public long aniosAntiguedad(LocalDateTime fechaReserva){
        long  anios = ChronoUnit.YEARS.between(desdeCuandoViene, fechaReserva);
        return anios;
    }

    public long calcularPorcentajeDescuento(LocalDateTime fechaReserva){
        LocalDate fecha = fechaReserva.toLocalDate();
        long porcentaje = 0;
        if (!fecha.isBefore(desdeCuandoViene)) {
             porcentaje = aniosAntiguedad(fechaReserva)*2;
            if (porcentaje>15) {
                porcentaje = 15;
            }
        }
        return porcentaje;
    }
}
