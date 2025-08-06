import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.Stack;

// GameGUI.java
public class GameGUI extends JFrame {
    private WordCategory currentCategory;
    private JLabel wordLabel, hintLabel, timerLabel, wrongGuessesLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JComboBox<String> categoryComboBox;
    private ScheduledExecutorService scheduler;
    private int timeLeft;
    private Stack<Character> guessHistory = new Stack<>(); // This is my Stack usage

    private int wrongGuesses;

    public GameGUI() {
        super("Mystery Word Quest");

        // This is my Layout Manager usage
        setLayout(new BorderLayout(10,10));

        // Top panel for category selection
        JPanel topPanel = new JPanel();
        categoryComboBox = new JComboBox<>(new String[]{"Animals", "Countries", "Food"});
        categoryComboBox.setToolTipText("Select word category"); // Tool tip usage
        topPanel.add(new JLabel("Select Category:"));
        topPanel.add(categoryComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for word display and hint
        JPanel centerPanel = new JPanel(new GridLayout(3,1));
        wordLabel = new JLabel("Word: ", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        centerPanel.add(wordLabel);

        hintLabel = new JLabel("Hint: ", SwingConstants.CENTER);
        centerPanel.add(hintLabel);

        timerLabel = new JLabel("Time Left: 10", SwingConstants.CENTER);
        centerPanel.add(timerLabel);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for guesses
        JPanel bottomPanel = new JPanel();
        guessField = new JTextField(5);
        guessField.setToolTipText("Enter one letter"); // Tool tip usage
        guessButton = new JButton("Guess");
        guessButton.setToolTipText("Click to submit your guess"); // Tool tip usage

        wrongGuessesLabel = new JLabel("Wrong guesses: 0");

        bottomPanel.add(new JLabel("Your Guess: "));
        bottomPanel.add(guessField);
        bottomPanel.add(guessButton);
        bottomPanel.add(wrongGuessesLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Image icon (optional) - This is my image usage
        ImageIcon icon = new ImageIcon("icon.png"); // Provide your image file or skip if none
        setIconImage(icon.getImage());

        // Setup initial category and word
        setupNewGame();

        // Event Listeners
        guessButton.addActionListener(e -> processGuess());
        categoryComboBox.addActionListener(e -> setupNewGame());

        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupNewGame() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }

        String category = (String) categoryComboBox.getSelectedItem();
        switch (category) {
            case "Animals":
                currentCategory = new AnimalCategory();
                break;
            case "Countries":
                currentCategory = new CountryCategory();
                break;
            case "Food":
                currentCategory = new FoodCategory();
                break;
        }
        wrongGuesses = 0;
        guessHistory.clear();
        updateDisplay();

        startTimer();
    }

    private void updateDisplay() {
        wordLabel.setText("Word: " + spacedOut(currentCategory.getCurrentProgress()));
        hintLabel.setText("Hint: " + currentCategory.getHint());
        wrongGuessesLabel.setText("Wrong guesses: " + wrongGuesses);
        timerLabel.setText("Time Left: 10");
        guessField.setText("");
        guessField.requestFocus();
    }

    private String spacedOut(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(c).append(' ');
        }
        return sb.toString();
    }

    // This is my multithreading usage
    private void startTimer() {
        timeLeft = 10;
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft);
                if (timeLeft <= 0) {
                    scheduler.shutdown();
                    JOptionPane.showMessageDialog(this, "Time's up! You lose.");
                    setupNewGame();
                }
            });
        }, 1, 1, TimeUnit.SECONDS);
    }

    // This method throws and handles custom exception
    private void processGuess() {
        String input = guessField.getText().trim();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            JOptionPane.showMessageDialog(this, "Please enter a single letter.");
            return;
        }
        char guess = input.charAt(0);

        // Check if letter was already guessed
        if (guessHistory.contains(guess)) {
            JOptionPane.showMessageDialog(this, "You already guessed that letter.");
            return;
        }

        guessHistory.push(guess);

        try {
            boolean correct = currentCategory.makeGuess(guess);
            if (!correct) {
                wrongGuesses++;
            }
            updateDisplay();

            if (currentCategory.getCurrentProgress().equals(currentCategory.getWord())) {
                scheduler.shutdown();
                String name = JOptionPane.showInputDialog(this, "You won! Enter your name:");
                if (name != null && !name.trim().isEmpty()) {
                    HighScoreManager.saveScore(name.trim(), (String) categoryComboBox.getSelectedItem(), wrongGuesses);
                }
                setupNewGame();
            }
        } catch (TooManyWrongGuessesException e) {
            scheduler.shutdown();
            JOptionPane.showMessageDialog(this, e.getMessage() + "\nThe word was: " + currentCategory.getWord());
            setupNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
