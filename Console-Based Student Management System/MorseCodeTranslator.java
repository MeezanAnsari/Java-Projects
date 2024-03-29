import java.util.*;

public class MorseCodeTranslator {

    private static final Map<Character, String> morseCodeMap = new HashMap<>();
    private static final Map<String, Character> charMap = new HashMap<>();

    static {
        // Initialize Morse code mappings
        morseCodeMap.put('A', ".-");
        morseCodeMap.put('B', "-...");
        morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-..");
        morseCodeMap.put('E', ".");
        morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--.");
        morseCodeMap.put('H', "....");
        morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---");
        morseCodeMap.put('K', "-.-");
        morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--");
        morseCodeMap.put('N', "-.");
        morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--.");
        morseCodeMap.put('Q', "--.-");
        morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "...");
        morseCodeMap.put('T', "-");
        morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-");
        morseCodeMap.put('W', ".--");
        morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--");
        morseCodeMap.put('Z', "--..");
        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");
        morseCodeMap.put('.', ".-.-.-");
        morseCodeMap.put(',', "--..--");
        morseCodeMap.put('?', "..--..");
        morseCodeMap.put('\'', ".----.");
        morseCodeMap.put('!', "-.-.--");
        morseCodeMap.put('/', "-..-.");
        morseCodeMap.put('(', "-.--.");
        morseCodeMap.put(')', "-.--.-");
        morseCodeMap.put('&', ".-...");
        morseCodeMap.put(':', "---...");
        morseCodeMap.put(';', "-.-.-.");
        morseCodeMap.put('=', "-...-");
        morseCodeMap.put('+', ".-.-.");
        morseCodeMap.put('-', "-....-");
        morseCodeMap.put('_', "..--.-");
        morseCodeMap.put('"', ".-..-.");
        morseCodeMap.put('$', "...-..-");
        morseCodeMap.put('@', ".--.-.");
        
        // Initialize reverse mapping for morseCodeMap
        for (Map.Entry<Character, String> entry : morseCodeMap.entrySet()) {
            charMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static String textToMorseCode(String text) {
        StringBuilder morseCode = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morseCode.append(" / ");
            } else if (morseCodeMap.containsKey(c)) {
                morseCode.append(morseCodeMap.get(c)).append(" ");
            }
        }
        return morseCode.toString();
    }

    public static String morseCodeToText(String morseCode) {
        StringBuilder text = new StringBuilder();
        String[] words = morseCode.split(" / ");
        for (String word : words) {
            String[] letters = word.trim().split(" ");
            for (String letter : letters) {
                if (charMap.containsKey(letter)) {
                    text.append(charMap.get(letter));
                }
            }
            text.append(" ");
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Text to Morse Code");
        System.out.println("2. Morse Code to Text");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (option) {
            case 1:
                System.out.println("Enter text to convert to Morse Code:");
                String text = scanner.nextLine();
                System.out.println("Morse Code: " + textToMorseCode(text));
                break;
            case 2:
                System.out.println("Enter Morse Code to convert to text:");
                String morseCode = scanner.nextLine();
                System.out.println("Text: " + morseCodeToText(morseCode));
                break;
            default:
                System.out.println("Invalid option.");
        }
        scanner.close();
    }
}