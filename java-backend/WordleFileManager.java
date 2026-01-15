import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordleFileManager {
    private String archivoPalabras;
    private String archivoHistorial;

    /**
     * Constructor de la Clase WordleFileManager
     * @param archivoPalabras Ruta del archivo que contiene las palabras para el juego.
     * @param archivoHistorial Ruta del archivo donde se guardará el historial de las partidas
     */
    public WordleFileManager(String archivoPalabras, String archivoHistorial) {
        this.archivoPalabras = archivoPalabras;
        this.archivoHistorial = archivoHistorial;
    }
    //Método para leer las palabras del archivo "words.txt"
    public List<String> leerPalabras(){
        List<String> palabras = new ArrayList<>();
        Path ruta = Paths.get(archivoPalabras); {
        }
        try{
            return Files.readAllLines(ruta);
        } catch (IOException e) {
             System.out.println("Error al leer el archivo: " + e.getMessage());
             return null;
        }

    }

    /** Constructor de la Clase WordleFileManager
     * @param out Text que será guardado en el historial de la partida.
     */
    public void saveHistorial(String out){
        try(PrintWriter pw = new PrintWriter(new FileWriter(archivoHistorial,true))) {
            pw.println(out);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el historial: " + e.getMessage(), e);
        }

    }
}


