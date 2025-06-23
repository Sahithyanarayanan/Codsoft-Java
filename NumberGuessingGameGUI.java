import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {
    private int targetNumber, attempts, maxAttempts = 7, totalRounds = 0, totalScore = 0;
    private JTextField guessInput;
    private JLabel messageLabel, attemptsLabel, scoreLabel;
    private JButton guessButton, newGameButton;

    public NumberGuessingGameGUI() {
        setTitle("🎮 Number Guessing Game - CODSOFT");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));
        getContentPane().setBackground(new Color(230, 245, 255));

        messageLabel = new JLabel("Guess a number between 1 and 100", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(messageLabel);

        guessInput = new JTextField();
        add(guessInput);

        guessButton = new JButton("Submit Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        attemptsLabel = new JLabel("Attempts Left: " + maxAttempts, JLabel.CENTER);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0 | Rounds: 0", JLabel.CENTER);
        add(scoreLabel);

        newGameButton = new JButton("Start New Game");
        newGameButton.setEnabled(false);
        newGameButton.addActionListener(e -> startNewGame());
        add(newGameButton);

        startNewGame();
        setVisible(true);
    }

    private void startNewGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts Left: " + maxAttempts);
        guessInput.setText("");
        guessInput.setEnabled(true);
        guessButton.setEnabled(true);
        newGameButton.setEnabled(false);
        totalRounds++;
    }

    public void actionPerformed(ActionEvent e) {
        String guessText = guessInput.getText().trim();
        if (guessText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Please enter a number.");
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "❌ Invalid input! Enter a valid number.");
            return;
        }

        attempts++;

        if (guess == targetNumber) {
            int roundScore = 10 - attempts;
            totalScore += roundScore;
            messageLabel.setText("✅ Correct! You guessed it in " + attempts + " tries.");
            scoreLabel.setText("Score: " + totalScore + " | Rounds: " + totalRounds);
            guessInput.setEnabled(false);
            guessButton.setEnabled(false);
            newGameButton.setEnabled(true);
        } else if (guess < targetNumber) {
            messageLabel.setText("📉 Too low! Try again.");
        } else {
            messageLabel.setText("📈 Too high! Try again.");
        }

        attemptsLabel.setText("Attempts Left: " + (maxAttempts - attempts));

        if (attempts >= maxAttempts && guess != targetNumber) {
            messageLabel.setText("❌ Out of attempts! Number was " + targetNumber);
            guessInput.setEnabled(false);
            guessButton.setEnabled(false);
            newGameButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
