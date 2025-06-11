import java.util.Scanner;

public class Turn {
    Scanner scanner = new Scanner(System.in);

    public void takeTurn(String playerName) {
        System.out.println(playerName + ", enter a single letter to guess:");
        String input = scanner.nextLine();

        try {
            Phrases.findLetters(input);
        } catch (MultipleLettersException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid letter.");
        }

        Phrases.displayPlayingPhrase();
    }
}
