import java.util.Scanner;

public class Hosts {
    Scanner scanner = new Scanner(System.in);

    public void setPhrase() {
        System.out.println("Host, enter a phrase for the game:");
        String phrase = scanner.nextLine();
        Phrases.setGamePhrase(phrase);
    }
}
