public class WordleFeedback {
    private static final int WORD_LENGTH = 5;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /** Método que colorea una letra con el color proporcionado.
     * @param letter La letra que se va a colorear.
     * @param color El código ANSI que representa el color deseado.
     * @return La letra coloreada como un string con el formato adecuado.*/

    public static String coloredLetter(char letter, String color){
        return color + letter + ANSI_RESET;
    }
    /** Método que compara las letras de la palabra del jugador con la palabra secreta y devuelve
     * la palabra coloreada con los colores correspondientes.
     * - Verde si la letra está en la posición correcta.
     * - Amarillo si la letra está en la palabra pero en la posición incorrecta.
     * - Rojo si la letra no está en la palabra.
     * @param userWord La palabra introducida por el jugador.
     * @param secretWord La palabra secreta que el jugador debe adivinar.
     * @return La palabra coloreada que indica la retroalimentación del jugador.*/

    public static String coloredWord (String userWord, String secretWord) {
        StringBuilder coloredWord = new StringBuilder();
            for (int i = 0; i < WORD_LENGTH; i++) {
                char userChar = userWord.charAt(i);
                if (userChar == secretWord.charAt(i)){
                    coloredWord.append(coloredLetter(userChar, ANSI_GREEN));
                } else if (secretWord.indexOf(userChar) != -1){
                    coloredWord.append(coloredLetter(userChar, ANSI_YELLOW));
                } else {
                    coloredWord.append(coloredLetter(userChar, ANSI_RED));
                }
            }
            return coloredWord.toString();
    }
}
