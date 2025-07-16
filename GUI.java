import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JLabel playersLabel;
    private JLabel hostLabel;
    private JLabel phraseLabel;
    private JButton addPlayerButton;
    private JButton setHostButton;
    private JButton startGameButton;

    private ArrayList<Player> players = new ArrayList<>();
    private Host host;

    public GUI() {
        // Window setup
        setTitle("Word Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Player section
        playersLabel = new JLabel("Players: ");
        add(playersLabel);

        addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> addPlayer());
        add(addPlayerButton);

        // Host section
        hostLabel = new JLabel("Host: ");
        add(hostLabel);

        setHostButton = new JButton("Set Host");
        setHostButton.addActionListener(e -> setHost());
        add(setHostButton);

        // Phrase display
        phraseLabel = new JLabel("Phrase: ");
        add(phraseLabel);

        // Start game button
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> startGame());
        add(startGameButton);

        setVisible(true);
    }

    private void addPlayer() {
        String name = JOptionPane.showInputDialog(this, "Enter player name:");
        if (name != null && !name.trim().isEmpty()) {
            players.add(new Player(name));
            updatePlayersLabel();
        }
    }

    private void updatePlayersLabel() {
        StringBuilder names = new StringBuilder("Players: ");
        for (Player p : players) {
            names.append(p.getName()).append(" ");
        }
        playersLabel.setText(names.toString());
    }

    private void setHost() {
        String name = JOptionPane.showInputDialog(this, "Enter host name:");
        String phrase = JOptionPane.showInputDialog(this, "Enter game phrase:");
        if (name != null && phrase != null) {
            host = new Host(name, phrase);
            hostLabel.setText("Host: " + name);
            phraseLabel.setText("Phrase: " + host.getMaskedPhrase());
        }
    }

    private void startGame() {
        if (host == null || players.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add a host and at least one player to start.");
            return;
        }

        boolean gameWon = false;
        int currentPlayer = 0;

        while (!gameWon) {
            Player p = players.get(currentPlayer);
            String guess = JOptionPane.showInputDialog(this, p.getName() + ", guess a letter:");

            if (guess == null || guess.length() != 1) continue;

            char guessedLetter = guess.toLowerCase().charAt(0);
            boolean correct = host.guessLetter(guessedLetter);

            if (correct) {
                JOptionPane.showMessageDialog(this, "Correct!");
                phraseLabel.setText("Phrase: " + host.getMaskedPhrase());
                if (host.isComplete()) {
                    JOptionPane.showMessageDialog(this, p.getName() + " has won!");
                    int choice = JOptionPane.showConfirmDialog(this, "Play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong guess!");
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    private void resetGame() {
        players.clear();
        host = null;
        updatePlayersLabel();
        hostLabel.setText("Host: ");
        phraseLabel.setText("Phrase: ");
    }

    public static void main(String[] args) {
        new GUI();
    }
}

