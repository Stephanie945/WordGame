// src/lesson2/GamePlay.java
package lesson2;

import java.util.Scanner;

public class GamePlay {
    private static Person player;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for first name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        // Ask if user wants to enter last name
        System.out.print("Would you like to enter a last name? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            player = new Person(firstName, lastName);
        } else {
            player = new Person(firstName);
        }

        Numbers game = new Numbers();
        game.generateNumber();

        boolean guessedCorrectly = false;
        while (!guessedCorrectly) {
            System.out.print(player.getFirstName() + ", enter your guess (0-100): ");
            int guess = scanner.nextInt();
            guessedCorrectly = game.compareNumber(guess);
        }

        scanner.close();
    }
}
