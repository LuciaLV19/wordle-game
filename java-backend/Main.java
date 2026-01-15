import java.util.List;

public class Main {

    public static void main(String[] args) {
        WordleFileManager archivoWordle = new WordleFileManager("TrabajoEnfoqueWordle/resources/words.txt", "TrabajoEnfoqueWordle/resources/historial.txt");
        WordleGame juegoWordle = new WordleGame(archivoWordle);
        juegoWordle.start();
    }
}

