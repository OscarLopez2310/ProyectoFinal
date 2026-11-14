package co.edu.uptc.Controlador;
import java.nio.file.Files;
import java.nio.file.Path;
public class Persistencia {

    private static final String SEP= ";";

    private Path carpeta;
    private Path archivoClientes;

    public Persistencia(String ruta){
        this.carpeta = Path.of(ruta);
        this.archivoClientes = carpeta.resolve("Clientes .txt");
    }

    public boolean existeClientes(){
        return Files.exists(archivoClientes);
    }
}
