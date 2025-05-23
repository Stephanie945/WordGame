// src/lesson2/Numbers.java
package lesson2;

import java.util.Random;

public class Numbers {
    private int randomNum;

    // Getter
    public int getRandomNum() {
        return randomNum;
    }

    // Setter
    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    // Generate random number from 0 to 100
    public void generateNumber() {
        Random rand = new Random();
        this.randomNum = rand.nextInt(101); // 0 to 100 inclusive
    }

    // Compare guess
    public boolean compareNumber(int guess) {
        if (guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        } else if (guess > randomNum) {
            System.out.println("I'm sorry. That guess was too high.");
        } else {
            System.out.println("I'm sorry. That guess was too low.");
        }
        return false;
    }
}
