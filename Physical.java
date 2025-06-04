import java.util.Random;

public class Physical implements Award {
    private String[] prizes = {"Smart TV", "Laptop", "Vacation Package", "Bicycle", "Gaming Console"};

    private int getRandomPrize() {
        Random rand = new Random();
        return rand.nextInt(prizes.length);
    }

    @Override
    public int displayWinnings(Players player, boolean isCorrect) {
        int prizeIndex = getRandomPrize();
        String prize = prizes[prizeIndex];

        if (isCorrect) {
            System.out.println(player.getName() + " won a " + prize + "!");
        } else {
            System.out.println(player.getName() + " lost! You could have won a " + prize + ".");
        }
        return 0;
    }
}

