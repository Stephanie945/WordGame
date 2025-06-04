import java.util.Random;

public class Turn {
    public boolean takeTurn(Players player, int guess) {
        int target = 7; // or however you get the correct number
        boolean isCorrect = (guess == target);

        Award award;
        Random rand = new Random();
        int prizeType = rand.nextInt(2); // 0 = Money, 1 = Physical

        if (prizeType == 0) {
            award = new Money();
        } else {
            award = new Physical();
        }

        int winnings = award.displayWinnings(player, isCorrect);
        player.setMoney(player.getMoney() + winnings);

        System.out.println(player.toString());

        return isCorrect;
    }
}

