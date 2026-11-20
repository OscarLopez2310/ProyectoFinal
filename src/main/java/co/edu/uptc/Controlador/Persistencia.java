package co.edu.uptc.Controlador;
import co.edu.uptc.Modelo.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
public class Persistencia {//Terminada

    private static final String SEP= ";";

    private Path carpeta;
    private Path archivoClientes;
    private Path archivoMesas;
    private Path archivoReservas;
    private Path archivoCaterings;

    public Persistencia(String ruta){
        this.carpeta = Path.of(ruta);
        this.archivoClientes = carpeta.resolve("Clientes.txt");
        this.archivoMesas = carpeta.resolve("Mesas.txt");
        this.archivoReservas = carpeta.resolve("Reservas.txt");
        this.archivoCaterings = carpeta.resolve("Caterings.txt");
    }

    public boolean existeClientes(){
        return Files.exists(archivoClientes);
    }
    public boolean existeMesas(){
        return Files.exists(archivoMesas);
    }
    public boolean existeReservas(){
        return Files.exists(archivoReservas);
    }
    public boolean existeCaterings(){
        return Files.exists(archivoCaterings);
    }

    public void guardarClientes(ArrayList<Cliente>clientes)throws IOException {
        ArrayList<String>lineas = new ArrayList<>();

        for (Cliente c: clientes){
            String linea = c.getNombre() + SEP +
                    c.getDocumento()+ SEP +
                    c.getTelefono()+ SEP +
                    c.getDesdeCuandoViene();
            lineas.add(linea);
        }
        Files.createDirectories(carpeta);
        Files.write(archivoClientes ,lineas);
    }

    public void guardarMesas(ArrayList<Mesa>mesas)throws IOException {
        ArrayList<String>lineas = new ArrayList<>();

        for (Mesa m: mesas){
            String linea = m.getIdmesa() + SEP +
                    m.getCapacidad();
            lineas.add(linea);
        }
        Files.createDirectories(carpeta);
        Files.write(archivoMesas ,lineas);
    }

    public void guardarReservas(ArrayList<Reserva>reservas)throws IOException {
        ArrayList<String>lineas = new ArrayList<>();

        for (Reserva r: reservas){
            String linea = r.getIdReserva() + SEP +
                    r.getCliente().getDocumento()+ SEP +
                    r.getMesa().getIdmesa()+ SEP +
                    r.getServicio().tipo()+ SEP +
                    r.getFecha_Y_Hora_Reserva()+ SEP +
                    r.getEstadoReserva()+SEP+
                    r.getPenalidad();
            lineas.add(linea);
        }
        Files.createDirectories(carpeta);
        Files.write(archivoReservas ,lineas);
    }

    public void guardarCaterings(ArrayList<Catering>caterings)throws IOException{
        List<String>lineas = new ArrayList<>();

        for (Catering ca: caterings){
            String linea = ca.getPersonas() +SEP+
                    ca.getDireccion();
            lineas.add(linea);
        }
        Files.createDirectories(carpeta);
        Files.write(archivoCaterings,lineas);
    }

    public ArrayList<Cliente> cargarClientes()throws IOException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        if (!existeClientes()) {
            return clientes;
        }
        List<String> lineas = Files.readAllLines(archivoClientes);

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] data = linea.split(SEP);

            if (data.length != 4) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " esta dañada: " + linea);
            }
            try {
                String nombre = data[0].trim();
                String documento = data[1].trim();
                String telefono = data[2].trim();
                LocalDate desdeCuandoViene = LocalDate.parse(data[3].trim());
                clientes.add(new Cliente(nombre, documento, telefono, desdeCuandoViene));
            } catch (DateTimeParseException e) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " requiere un formato de fecha correcto.");
            }
        }
        return clientes;
    }

    public ArrayList<Mesa> cargarMesas()throws IOException {
        ArrayList<Mesa> mesas = new ArrayList<>();
        if (!existeMesas()) {
            return mesas;
        }
        List<String> lineas = Files.readAllLines(archivoMesas);

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] data = linea.split(SEP);

            if (data.length != 2) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " esta dañada: " + linea);
            }
            try {
                String idmesa = data[0].trim();
                int capacidad = Integer.parseInt(data[1].trim());
                mesas.add(new Mesa(idmesa, capacidad));
            } catch (NumberFormatException e) {
                throw new DatoInvalidoException("La linea: " +(i+1)+ " debe ser un numero.");
            }
        }
        return mesas;
    }

    public ArrayList<Reserva> cargarReservas(ArrayList<Cliente> clientes,  ArrayList<Mesa>mesas)throws IOException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        if (!existeReservas()) {
            return reservas;
        }
        List<String> lineas = Files.readAllLines(archivoReservas);

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] data = linea.split(SEP);

            if (data.length != 7) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " esta dañada: " + linea);
            }
            try {
                 String idReserva= data[0].trim();
                 Cliente cliente= buscarClientePorDocumento(data[1].trim(),clientes);
                 Mesa mesa= buscarMesa(data[2].trim(), mesas);
                 Servicio servicio= crearServicio(data[3].trim());
                 LocalDateTime fecha_Y_Hora_Reserva= LocalDateTime.parse(data[4].trim());
                 EstadoReserva estadoReserva= EstadoReserva.valueOf(data[5].trim());
                 long penalidad= Long.parseLong(data[6].trim());
                 reservas.add(new Reserva( idReserva,  cliente,  mesa,servicio,  fecha_Y_Hora_Reserva,estadoReserva, penalidad));
            } catch (DateTimeParseException | IllegalArgumentException e) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " tiene un tipo de dato invalido.");
            }
        }
        return reservas;
    }

    public ArrayList<Catering> cargarCaterings()throws IOException{
        ArrayList<Catering>caterings = new ArrayList<>();

        if (!existeCaterings()){
            return caterings;
        }

        List<String>lineas = Files.readAllLines(archivoCaterings);

        for (int i = 0; i< lineas.size(); i++){
            String linea = lineas.get(i);
            if (linea.isBlank()){
                continue;
            }
            String[]data = linea.split(SEP);

            if (data.length != 2) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " esta dañada: " + linea);
            }
            try {
                int personas= Integer.parseInt(data[0].trim());
                String direccion= data[1].trim();
                caterings.add(new Catering( personas,  direccion));
            } catch (NumberFormatException e) {
                throw new DatoInvalidoException("La linea " + (i + 1) + " debe ser un numero.");
            }
        }
        return caterings;
    }

    private Cliente buscarClientePorDocumento(String documento, ArrayList<Cliente> clientes) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        throw new DatoInvalidoException("No existe un cliente con el documento: " + documento);
    }

    private Mesa buscarMesa(String idmesa, ArrayList<Mesa>mesas){
        for (Mesa m: mesas){
            if (m.getIdmesa().equalsIgnoreCase(idmesa)){
                return m;
            }
        }
        throw new DatoInvalidoException("No existe una mesa con el id: " + idmesa);
    }

    private Servicio crearServicio(String texto) {
        String[] partes = texto.split(",");
        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
        }
        String codigo = partes[0];
        try {
            switch (codigo) {
                case "ALMUERZO":
                    return new AlmuerzoEjecutivo(Integer.parseInt(partes[1]));
                case "CENA":
                    return new CenaCarta(Integer.parseInt(partes[1]));
                case "EVENTO":
                    return new EventoPrivado(Integer.parseInt(partes[2]), TipoEventoPri.valueOf(partes[1]));
                default:
                    throw new DatoInvalidoException("Tipo de servicio desconocido: " + codigo);
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new DatoInvalidoException("Servicio mal guardado: " + texto);
        }
    }
}

