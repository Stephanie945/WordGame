public class Phrases {
    public static String gamePhrase;
    public static String playingPhrase;

    // Set the phrase and convert letters to underscores
    public static void setGamePhrase(String phrase) {
        gamePhrase = phrase;
        playingPhrase = "";

        for (char c : gamePhrase.toCharArray()) {
            if (Character.isLetter(c)) {
                playingPhrase += "_";
            } else {
                playingPhrase += c;
            }
        }
    }

    // Show current progress
    public static void displayPlayingPhrase() {
        System.out.println("Current phrase: " + playingPhrase);
    }

    // Find and reveal matching letters
    public static void findLetters(String input) throws MultipleLettersException {
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            throw new MultipleLettersException();
        }

        char guess = Character.toLowerCase(input.charAt(0));
        StringBuilder updated = new StringBuilder(playingPhrase);
        boolean found = false;

        for (int i = 0; i < gamePhrase.length(); i++) {
            if (Character.toLowerCase(gamePhrase.charAt(i)) == guess && playingPhrase.charAt(i) == '_') {
                updated.setCharAt(i, gamePhrase.charAt(i));
                found = true;
            }
        }

        playingPhrase = updated.toString();

        if (!playingPhrase.contains("_")) {
            System.out.println("🎉 You guessed the full phrase: " + gamePhrase);
            System.out.println("🏆 You win a prize!");
        } else if (!found) {
            System.out.println("❌ Letter not found.");
        }
    }
}
