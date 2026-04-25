import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

enum State {
    S0, S1, S2, S3, S4, S5, S6, ERROR
}

public class FiniteStateMachine {

    private static final Map<State, Function<Character, State>> transitionTable = new HashMap<>();

    static {
        transitionTable.put(State.S0, c -> c == '%' ? State.S1 : State.ERROR);
        transitionTable.put(State.S1, c -> Character.isDigit(c) ? State.S2 : State.ERROR);
        transitionTable.put(State.S2, c -> Character.isDigit(c) ? State.S2 : (c == '~' ? State.S3 : State.ERROR));
        transitionTable.put(State.S3, c -> (c == '~' || c == '%') ? State.S4 : State.ERROR);
        transitionTable.put(State.S4, c -> (c >= 'A' && c <= 'Z') ? State.S5 : State.ERROR);
        transitionTable.put(State.S5, c -> {
            if (c >= 'A' && c <= 'Z') return State.S5;
            if (c == '%') return State.S6;
            return State.ERROR;
        });
        transitionTable.put(State.S6, c -> State.ERROR);
        transitionTable.put(State.ERROR, c -> State.ERROR);
    }

    public static boolean isValidWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        State currentState = State.S0;
        for (char c : word.toCharArray()) {
            currentState = transitionTable.get(currentState).apply(c);
            if (currentState == State.ERROR) {
                return false;
            }
        }
        return currentState == State.S5 || currentState == State.S6;
    }

    public static void main(String[] args) {
        String filePath = "src/input.txt"; 
        
        System.out.println("=== РЕЗУЛЬТАТИ АНАЛIЗУ ФАЙЛУ ===");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                
                for (String word : words) {
                    if (word.isEmpty()) continue;
                    
                    if (isValidWord(word)) {
                        System.out.println("Знайдено вiдповiднiсть: " + word);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
            System.out.println("Переконайтеся, що файл '" + filePath + "' iснує.");
        }
    }
}