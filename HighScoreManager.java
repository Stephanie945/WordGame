import java.io.*;
import java.util.ArrayList;

// HighScoreManager.java
// This is my file I/O for high scores
public class HighScoreManager {
    private static final String FILE_NAME = "highscores.txt";

    public static void saveScore(String playerName, String category, int wrongGuesses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(playerName + "," + category + "," + wrongGuesses);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving high score: " + e.getMessage());
        }
    }

    public static ArrayList<String> loadScores() {
        ArrayList<String> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading high scores: " + e.getMessage());
        }
        return scores;
    }
}
