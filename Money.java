public class Money implements Award {
    private int winAmount = 100;
    private int loseAmount = -50;

    @Override
    public int displayWinnings(Players player, boolean isCorrect) {
        if (isCorrect) {
            System.out.println(player.getName() + " won $" + winAmount + "!");
            return winAmount;
        } else {
            System.out.println(player.getName() + " lost $" + (-loseAmount) + "!");
            return loseAmount;
        }
    }
}
