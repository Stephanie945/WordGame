import java.util.Scanner;

public class GamePlay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Hosts host = new Hosts("Alex");
        Players player;

        System.out.println("Enter player name:");
        String name = scanner.nextLine();
        player = new Players(name);

        Turn turn = new Turn();

        boolean keepPlaying = true;

        while (keepPlaying) {
            host.randomizeNum();

            boolean guessedCorrectly = false;
            while (!guessedCorrectly) {
                guessedCorrectly = turn.takeTurn(player, host);
            }

            System.out.println("Would you like to play again? (yes/no)");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("yes")) {
                keepPlaying = false;
                System.out.println("Thanks for playing!");
            }
        }
    }
}
