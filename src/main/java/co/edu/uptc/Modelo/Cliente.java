package co.edu.uptc.Modelo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Cliente {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getdesdeCuandoViene() {
        return desdeCuandoViene;
    }

    public long aniosAntiguedad(LocalDate fechaReserva){
        long anios =0;
        anios = ChronoUnit.YEARS.between(desdeCuandoViene, fechaReserva);
        return anios;
    }

    public double calcularDescuento(LocalDate fechaReserva){
        double descuento = 0;
        long porcentaje = aniosAntiguedad(fechaReserva)*2;
        if (porcentaje>15) {
            descuento =  15;
        }else {
            descuento = (aniosAntiguedad(fechaReserva) * 2);
        }
        return descuento/100;
    }
}
