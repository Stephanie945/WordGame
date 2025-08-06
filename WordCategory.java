import java.util.ArrayList;

// WordCategory.java
// This is my parent class with child classes
// This is my overloaded constructor
public abstract class WordCategory implements Guessable {
    protected String word;
    protected char[] progress;
    protected int wrongGuesses;
    protected final int MAX_WRONG = 6;

    public WordCategory() {
        this.word = "DEFAULT";
        progress = new char[word.length()];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '_';
        }
        wrongGuesses = 0;
    }

    // Overloaded constructor that accepts list of words
    public WordCategory(ArrayList<String> words) {
        this.word = words.get((int) (Math.random() * words.size())).toUpperCase();
        progress = new char[word.length()];
        for (int i = 0; i < progress.length; i++) {
            progress[i] = '_';
        }
        wrongGuesses = 0;
    }

    // This method throws and handles custom exception in child classes when guess limit exceeded
    public boolean makeGuess(char guess) throws TooManyWrongGuessesException {
        guess = Character.toUpperCase(guess);
        boolean correct = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                progress[i] = guess;
                correct = true;
            }
        }
        if (!correct) {
            wrongGuesses++;
            if (wrongGuesses >= MAX_WRONG) {
                throw new TooManyWrongGuessesException("Too many wrong guesses! You lose.");
            }
        }
        return correct;
    }

    public String getCurrentProgress() {
        return new String(progress);
    }

    public String getWord() {
        return word;
    }

    public abstract String getHint();
}
