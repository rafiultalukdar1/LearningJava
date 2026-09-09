import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissors extends JFrame
        implements ActionListener {

    JLabel playerChoice;
    JLabel computerChoice;
    JLabel resultLabel;
    JLabel scoreLabel;
    JLabel roundLabel;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton resetButton;

    int playerScore = 0;
    int computerScore = 0;
    int round = 0;

    Random random = new Random();

    RockPaperScissors() {

        setTitle("Rock Paper Scissors");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                20, 20, 20, 20
            )
        );

        // Title
        JLabel title = new JLabel(
            "ROCK PAPER SCISSORS"
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 28)
        );

        title.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        mainPanel.add(
            title,
            BorderLayout.NORTH
        );

        // Center panel
        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(
            new GridLayout(5, 1, 10, 10)
        );

        playerChoice = new JLabel(
            "👤 You: -"
        );

        computerChoice = new JLabel(
            "🤖 Computer: -"
        );

        resultLabel = new JLabel(
            "Choose your move!"
        );

        scoreLabel = new JLabel(
            "You: 0    Computer: 0"
        );

        roundLabel = new JLabel(
            "Round: 0"
        );

        playerChoice.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        computerChoice.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        resultLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        scoreLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        roundLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        resultLabel.setFont(
            new Font("Arial", Font.BOLD, 22)
        );

        centerPanel.add(playerChoice);
        centerPanel.add(computerChoice);
        centerPanel.add(resultLabel);
        centerPanel.add(scoreLabel);
        centerPanel.add(roundLabel);

        mainPanel.add(
            centerPanel,
            BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel = new JPanel();

        rockButton = new JButton("🪨 ROCK");
        paperButton = new JButton("📄 PAPER");
        scissorsButton =
            new JButton("✂ SCISSORS");

        resetButton =
            new JButton("🔄 RESET");

        rockButton.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        paperButton.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        scissorsButton.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        resetButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(resetButton);

        mainPanel.add(
            buttonPanel,
            BorderLayout.SOUTH
        );

        // Button actions
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Reset button
        if (e.getSource() == resetButton) {

            resetGame();

            return;
        }

        String player;

        // Player choice
        if (e.getSource() == rockButton) {

            player = "Rock";

        } else if (e.getSource() == paperButton) {

            player = "Paper";

        } else {

            player = "Scissors";
        }

        // Computer choice
        String[] choices = {
            "Rock",
            "Paper",
            "Scissors"
        };

        String computer =
            choices[random.nextInt(3)];

        // Update UI
        playerChoice.setText(
            "👤 You: " + player
        );

        computerChoice.setText(
            "🤖 Computer: " + computer
        );

        round++;

        roundLabel.setText(
            "Round: " + round
        );

        // Check winner
        if (player.equals(computer)) {

            resultLabel.setText(
                "🤝 DRAW!"
            );

        } else if (
            (player.equals("Rock") &&
             computer.equals("Scissors")) ||

            (player.equals("Paper") &&
             computer.equals("Rock")) ||

            (player.equals("Scissors") &&
             computer.equals("Paper"))
        ) {

            playerScore++;

            resultLabel.setText(
                "🎉 YOU WIN!"
            );

        } else {

            computerScore++;

            resultLabel.setText(
                "🤖 COMPUTER WINS!"
            );
        }

        // Update score
        scoreLabel.setText(
            "You: " + playerScore +
            "    Computer: " +
            computerScore
        );
    }

    // Reset game
    void resetGame() {

        playerScore = 0;
        computerScore = 0;
        round = 0;

        playerChoice.setText(
            "👤 You: -"
        );

        computerChoice.setText(
            "🤖 Computer: -"
        );

        resultLabel.setText(
            "Choose your move!"
        );

        scoreLabel.setText(
            "You: 0    Computer: 0"
        );

        roundLabel.setText(
            "Round: 0"
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    RockPaperScissors game =
                        new RockPaperScissors();

                    game.setVisible(true);
                }
            }
        );
    }
}