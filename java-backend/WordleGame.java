import java.util.*;
/*La clase WordleGame es la principal para el funcionamiento del juego, contiene la lógica, el manejo
de las palabras secretas, los intentos del usuario, y guarda las partidas*/
public class WordleGame {

    private final int MAX_TRIES = 6;
    private final int WORD_LENGTH = 5;
    private List<String> fileWords;
    private String secretWord;
    private int remainingAttempts;
    private List<String> triesHistory;
    private final WordleFileManager fileManager;

    /** Constructor de la clase WordleGame. Inicializa los valores del juego.
     * @param fileManager Objeto encargado de la gestión de ficheros externos */
    public WordleGame(WordleFileManager fileManager) {
        this.fileWords = fileManager.leerPalabras();
        this.secretWord = secretRandomWord(fileWords);
        this.remainingAttempts = MAX_TRIES;
        this.triesHistory = new ArrayList<>();
        this.fileManager = fileManager;
    }

    /** Elige una palabra aleatoria, en este caso de la lista "words.txt"
     * @param words Lista de palabras desde la cual se elige la palabra secreta
     * @return Una palabra aleatoria */
    private String secretRandomWord(List<String> words) {
        Random randomWord = new Random();
        int random = randomWord.nextInt(words.size());
        return words.get(random);
    }

    /** Añade una palabra al historial de intentos y reduce los intentos restantes.
     * @param word La palabra ingresada por el jugador */
    private void addWord(String word){
        triesHistory.add(word);
        remainingAttempts --;
    }

    //Muestra en consola todas las palabras que el jugador ha ingresado.
    public void showWordsHistorial(){
        for(String word : triesHistory) {
            System.out.println(word);
        }
    }
    /**
     * Imprime los mensajes en consola y lo guarda a su vez en el archivo de "historial.txt".
     * @param text El texto que se desea mostrar y guardar
     */
    public void printAndSave(String text){
        System.out.println(text);
        // Elimina los códigos de color ANSI para que el texto guardado sea limpio
        String cleanText = text.replaceAll("\u001B\\[[;\\d]*m", "");
        // Guarda el texto limpio en el archivo de historial
        fileManager.saveHistorial(cleanText);
    }
    //Inicia el juego, controla el flujo, gestiona los intentos y da retroalimentación al jugador
    public void start(){
        Scanner snWord = new Scanner(System.in);
        printAndSave("¡Bienvenido a Wordle!");
        printAndSave("Debes adivinar una palabra de " + WORD_LENGTH + " letras.");
        printAndSave("Tienes " + MAX_TRIES + " intentos. ¡Buena suerte!");
        boolean correctWord = false;

        // Bucle principal del juego: sigue mientras haya intentos disponibles
        while(remainingAttempts > 0){
            String userWord = getUserInput(snWord).toLowerCase();
            addWord(userWord);
            // Si la palabra del jugador coincide con la palabra secreta, termina el juego
            if (userWord.equals(secretWord)){
                printAndSave("Felicidades!! Has adivinado la palabra secreta");
                correctWord = true;
                break;
            }
            // Obtiene la retroalimentación de la palabra ingresada (coloreada según la coincidencia)
            String feedbackWord = WordleFeedback.coloredWord(userWord, secretWord);
            printAndSave(feedbackWord);
            printAndSave("Intentos restantes: " + remainingAttempts);
            printAndSave("Palabras ingresadas: "); showWordsHistorial();
        }
        // Si el jugador no adivina la palabra, muestra un mensaje de derrota
        if (!correctWord){
            printAndSave("Lo siento, te has quedado sin intentos. La palabra correcta era " + secretWord);
        }
    }

    /**
     * Este método obtiene la entrada del usuario y asegura que la palabra tenga exactamente 5 letras.
     * Si no es así, vuelve a solicitarla.
     * @param snWord Objeto Scanner que lee la entrada del usuario.
     * @return La palabra ingresada por el usuario (de 5 letras).
     */
    private String getUserInput(Scanner snWord){
        String inputWord;
        while(true) {
            inputWord = snWord.nextLine();
            if (inputWord.length() == 5) {
                break;
            } else {
                System.out.println("La palabra debe tener exactamente 5 letras. Inténtalo de nuevo.");
            }
        }
        return inputWord;
    }
    public List<String> getFileWords() {
        return fileWords;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getWORD_LENGTH() {
        return WORD_LENGTH;
    }

    public List<String> getTriesHistory() {
        return triesHistory;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public int getMAX_TRIES() {
        return MAX_TRIES;
    }
}
