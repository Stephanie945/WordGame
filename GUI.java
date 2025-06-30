import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private JFrame frame;
    private JTextArea messageArea;
    private JCheckBox saveMessagesCheckBox;

    public GUI() {
        // Set up the frame
        frame = new JFrame("Word Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Set up Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // Game Menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G); // Alt+G

        JMenuItem addPlayerItem = new JMenuItem("Add Player");
        JMenuItem addHostItem = new JMenuItem("Add Host");
        JMenuItem newGameItem = new JMenuItem("New Game");

        gameMenu.add(addPlayerItem);
        gameMenu.add(addHostItem);
        gameMenu.addSeparator();
        gameMenu.add(newGameItem);

        // About Menu
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A); // Alt+A

        JMenuItem layoutInfoItem = new JMenuItem("Layout");
        aboutMenu.add(layoutInfoItem);

        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);
        frame.setJMenuBar(menuBar);

        // Main panel with layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Center content placeholder (e.g., game board or labels)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(new JLabel("Game area placeholder"));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Game Area"));

        // South panel for messages + checkbox
        JPanel southPanel = new JPanel(new BorderLayout());

        messageArea = new JTextArea(8, 50);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game Messages"));

        saveMessagesCheckBox = new JCheckBox("Save Messages");
        saveMessagesCheckBox.setToolTipText("Check to save all messages. Uncheck to overwrite each message.");

        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.add(saveMessagesCheckBox, BorderLayout.SOUTH);

        // Add components to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        // Add Action Listeners
        addPlayerItem.addActionListener(e -> showMessage("Add Player clicked."));
        addHostItem.addActionListener(e -> showMessage("Add Host clicked."));
        newGameItem.addActionListener(e -> {
            messageArea.setText("");
            showMessage("New game started!");
        });

        layoutInfoItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "This layout uses BorderLayout for structure and BoxLayout for flexibility.\n"
                            + "Center panel is reserved for gameplay, and messages are displayed below with scrolling enabled.",
                    "Layout Explanation", JOptionPane.INFORMATION_MESSAGE);
        });

        // Display the frame
        frame.setVisible(true);
    }

    private void showMessage(String message) {
        if (saveMessagesCheckBox.isSelected()) {
            messageArea.append(message + "\n");
        } else {
            messageArea.setText(message + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
