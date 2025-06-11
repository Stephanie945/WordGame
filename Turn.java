import java.util.Scanner;

public class Turn {
    private final int rewardAmount = 500;
    private final int penaltyAmount = 100;

    public boolean takeTurn(Players player, Hosts host) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(host.getName() + " says: " + player.getName() + ", guess a number between 0 and 100:");
        int guess = scanner.nextInt();

        Numbers numbers = new Numbers();
        String result = numbers.checkGuess(guess);

        if (result.equals("correct")) {
            player.setMoney(player.getMoney() + rewardAmount);
            System.out.println("Correct! " + player.toString());
            return true;
        } else {
            player.setMoney(player.getMoney() - penaltyAmount);
            System.out.println("Incorrect! " + player.toString());
            System.out.println("Your guess was too " + result + ".");
            return false;
        }
    }
}
