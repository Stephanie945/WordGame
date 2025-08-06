// Guessable.java
// This is my interface implemented by multiple classes
public interface Guessable {
    boolean makeGuess(char guess) throws TooManyWrongGuessesException;
    String getHint();
    String getWord();
    String getCurrentProgress();
}
