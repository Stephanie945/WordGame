public class Numbers {
    private static int randomNum;

    public void generateNumber() {
        randomNum = (int)(Math.random() * 101); // 0 to 100
    }

    public String checkGuess(int guess) {
        if (guess == randomNum) return "correct";
        return (guess < randomNum) ? "low" : "high";
    }

    public static int getRandomNum() {
        return randomNum;
    }
}
