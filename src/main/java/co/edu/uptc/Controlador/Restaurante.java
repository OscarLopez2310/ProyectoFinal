package co.edu.uptc.Controlador;
import co.edu.uptc.Modelo.Catering;
import co.edu.uptc.Modelo.Cliente;
import co.edu.uptc.Modelo.Mesa;
import co.edu.uptc.Modelo.Reserva;
import java.util.ArrayList;
public class Restaurante {
  private ArrayList<Cliente>clientes;
  private ArrayList<Reserva>reservas;
  private ArrayList<Mesa>mesas;
  private ArrayList<Catering>caterings;

    public Restaurante() {
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.mesas = new ArrayList<>();
        this.caterings = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public ArrayList<Catering> getCaterings() {
        return caterings;
    }

    public boolean registrarCliente(Cliente clienteNuevo){
        for (Cliente c: clientes) {
            if (c.getDocumento().equalsIgnoreCase(clienteNuevo.getDocumento())){
                return false;
            }
        }
        clientes.add(clienteNuevo);
        return true;
    }

    public boolean registrarCatering(Catering cateringNuevo){
        caterings.add(cateringNuevo);
        return true;
    }

    public boolean registrarMesa(Mesa mesaNueva){
        for (Mesa m: mesas) {
            if (m.getIdmesa().equalsIgnoreCase(mesaNueva.getIdmesa())){
                return false;
            }
        }
        mesas.add(mesaNueva);
        return true;
    }

    public boolean CrearReserva(Reserva reservaNueva){
        for (Reserva r: reservas) {
            if (r.getIdReserva().equalsIgnoreCase(reservaNueva.getIdReserva())){
                return false;
            }
        }
        reservas.add(reservaNueva);
        return true;
    }

    public Cliente buscarCliente(String id){
        Cliente cliente = null;
        for (Cliente c: clientes){
            if (c.getDocumento().equalsIgnoreCase(id)){
                cliente = c;
            }
        }
        return cliente;
    }

    public Mesa buscarMesa(String idMesa){
        Mesa mesa = null;
        for (Mesa m: mesas){
            if (m.getIdmesa().equalsIgnoreCase(idMesa)){
                mesa = m;
            }
        }
        return mesa;
    }

    public Reserva buscarReservas(String idReserva){
        Reserva reserva = null;
        for(Reserva r : reservas){
            if (r.getIdReserva().equalsIgnoreCase(idReserva)){
                reserva = r;
            }
        }
        return reserva;
    }
}
