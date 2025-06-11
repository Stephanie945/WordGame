import java.util.Scanner;

public class GamePlay {
    Scanner scanner = new Scanner(System.in);
    Hosts host = new Hosts();
    Turn turn = new Turn();

    public void startGame() {
        boolean playAgain = true;

        while (playAgain) {
            host.setPhrase();
            Phrases.displayPlayingPhrase();

            // Three players take turns
            for (int i = 0; i < 3; i++) {
                System.out.println("\nTurn for Player " + (i + 1));
                turn.takeTurn("Player " + (i + 1));
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing the Word Game!");
    }
}
