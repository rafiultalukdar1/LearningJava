import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApplication extends JFrame implements ActionListener {

    JLabel questionLabel;
    JLabel questionNumberLabel;
    JLabel scoreLabel;
    JLabel progressLabel;

    JRadioButton optionA;
    JRadioButton optionB;
    JRadioButton optionC;
    JRadioButton optionD;

    ButtonGroup options;

    JButton nextButton;
    JButton restartButton;

    JProgressBar progressBar;

    String[] questions = {

        "What is the capital of Bangladesh?",
        "Which language is used for Android development?",
        "Which keyword creates a class in Java?",
        "Which method starts a Java program?",
        "Which data type stores decimal numbers?",
        "Which symbol ends a Java statement?",
        "Which keyword is used for inheritance?",
        "Which keyword creates an object?",
        "Which company developed Java?",
        "Which collection stores unique values?",
        "Which operator means logical AND?",
        "Which keyword handles exceptions?",
        "Which loop executes at least once?",
        "Which keyword prevents a variable from changing?",
        "Which extension is used for Java source code?"
    };

    String[][] answers = {

        {"Dhaka", "Chittagong", "Rajshahi", "Sylhet"},
        {"Java", "HTML", "CSS", "SQL"},
        {"class", "new", "static", "void"},
        {"run()", "start()", "main()", "execute()"},
        {"int", "char", "double", "boolean"},
        {".", ";", ":", ","},
        {"extends", "implements", "inherits", "super"},
        {"new", "class", "object", "create"},
        {"Microsoft", "Apple", "Sun Microsystems", "Google"},
        {"List", "Set", "Array", "String"},
        {"||", "&&", "!", "=="},
        {"try", "catch", "throw", "All of these"},
        {"for", "while", "do-while", "if"},
        {"static", "final", "private", "constant"},
        {".java", ".class", ".exe", ".js"}
    };

    int[] correctAnswers = {

        0, 0, 0, 2, 2,
        1, 0, 0, 2, 1,
        1, 3, 2, 1, 0
    };

    int currentQuestion = 0;
    int score = 0;

    Color backgroundColor = new Color(245, 247, 250);
    Color cardColor = Color.WHITE;
    Color primaryColor = new Color(70, 90, 200);
    Color textColor = new Color(35, 40, 55);

    QuizApplication() {

        setTitle("Quiz Application");
        setSize(720, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main background
        JPanel mainPanel = new JPanel(
            new BorderLayout(20, 20)
        );

        mainPanel.setBackground(
            backgroundColor
        );

        mainPanel.setBorder(
            new EmptyBorder(25, 30, 25, 30)
        );

        // Header
        JPanel header = new JPanel(
            new BorderLayout()
        );

        header.setOpaque(false);

        JLabel title = new JLabel(
            "QUIZ MASTER"
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 30)
        );

        title.setForeground(textColor);

        questionNumberLabel = new JLabel(
            "Question 1 / 15"
        );

        questionNumberLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        questionNumberLabel.setForeground(
            primaryColor
        );

        scoreLabel = new JLabel(
            " SCORE  0 "
        );

        scoreLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        scoreLabel.setForeground(Color.WHITE);

        scoreLabel.setOpaque(true);

        scoreLabel.setBackground(primaryColor);

        scoreLabel.setBorder(
            new EmptyBorder(8, 15, 8, 15)
        );

        header.add(
            title,
            BorderLayout.WEST
        );

        header.add(
            questionNumberLabel,
            BorderLayout.CENTER
        );

        header.add(
            scoreLabel,
            BorderLayout.EAST
        );

        mainPanel.add(
            header,
            BorderLayout.NORTH
        );

        // Center area
        JPanel centerPanel = new JPanel(
            new BorderLayout(15, 15)
        );

        centerPanel.setOpaque(false);

        // Question card
        JPanel questionCard = new JPanel(
            new BorderLayout()
        );

        questionCard.setBackground(cardColor);

        questionCard.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(225, 228, 235),
                    1,
                    true
                ),
                new EmptyBorder(
                    25, 25, 25, 25
                )
            )
        );

        questionLabel = new JLabel();

        questionLabel.setFont(
            new Font("Arial", Font.BOLD, 21)
        );

        questionLabel.setForeground(textColor);

        questionLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        questionCard.add(
            questionLabel,
            BorderLayout.CENTER
        );

        centerPanel.add(
            questionCard,
            BorderLayout.NORTH
        );

        // Options card
        JPanel optionPanel = new JPanel(
            new GridLayout(2, 2, 15, 15)
        );

        optionPanel.setOpaque(false);

        optionA = createOptionButton();
        optionB = createOptionButton();
        optionC = createOptionButton();
        optionD = createOptionButton();

        options = new ButtonGroup();

        options.add(optionA);
        options.add(optionB);
        options.add(optionC);
        options.add(optionD);

        optionPanel.add(optionA);
        optionPanel.add(optionB);
        optionPanel.add(optionC);
        optionPanel.add(optionD);

        centerPanel.add(
            optionPanel,
            BorderLayout.CENTER
        );

        mainPanel.add(
            centerPanel,
            BorderLayout.CENTER
        );

        // Bottom
        JPanel bottomPanel = new JPanel(
            new BorderLayout(10, 10)
        );

        bottomPanel.setOpaque(false);

        // Progress
        JPanel progressPanel = new JPanel(
            new BorderLayout(5, 5)
        );

        progressPanel.setOpaque(false);

        progressLabel = new JLabel(
            "0% Completed"
        );

        progressLabel.setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        progressBar = new JProgressBar(
            0,
            questions.length
        );

        progressBar.setValue(1);

        progressBar.setStringPainted(false);

        progressPanel.add(
            progressLabel,
            BorderLayout.NORTH
        );

        progressPanel.add(
            progressBar,
            BorderLayout.CENTER
        );

        bottomPanel.add(
            progressPanel,
            BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        nextButton = new JButton(
            "NEXT  →"
        );

        restartButton = new JButton(
            "↻  RESTART"
        );

        styleButton(
            nextButton,
            primaryColor,
            Color.WHITE
        );

        styleButton(
            restartButton,
            Color.WHITE,
            textColor
        );

        nextButton.addActionListener(this);
        restartButton.addActionListener(this);

        buttonPanel.add(restartButton);
        buttonPanel.add(nextButton);

        bottomPanel.add(
            buttonPanel,
            BorderLayout.EAST
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        add(mainPanel);

        showQuestion();
    }

    // Create option button
    JRadioButton createOptionButton() {

        JRadioButton button =
            new JRadioButton();

        button.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        button.setForeground(textColor);

        button.setBackground(cardColor);

        button.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(220, 223, 230),
                    1,
                    true
                ),
                new EmptyBorder(
                    15, 15, 15, 15
                )
            )
        );

        button.setFocusPainted(false);

        button.setOpaque(true);

        return button;
    }

    // Style button
    void styleButton(
        JButton button,
        Color background,
        Color foreground
    ) {

        button.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        button.setBackground(background);

        button.setForeground(foreground);

        button.setFocusPainted(false);

        button.setBorder(
            new CompoundBorder(
                new LineBorder(
                    new Color(210, 213, 220),
                    1,
                    true
                ),
                new EmptyBorder(
                    10, 18, 10, 18
                )
            )
        );
    }

    // Show question
    void showQuestion() {

        questionNumberLabel.setText(
            "Question " +
            (currentQuestion + 1) +
            " / " +
            questions.length
        );

        questionLabel.setText(
            "<html><div style='text-align:center;'>"
            +
            questions[currentQuestion]
            +
            "</div></html>"
        );

        optionA.setText(
            "A   " +
            answers[currentQuestion][0]
        );

        optionB.setText(
            "B   " +
            answers[currentQuestion][1]
        );

        optionC.setText(
            "C   " +
            answers[currentQuestion][2]
        );

        optionD.setText(
            "D   " +
            answers[currentQuestion][3]
        );

        options.clearSelection();

        progressBar.setValue(
            currentQuestion + 1
        );

        int percentage =
            ((currentQuestion + 1) * 100)
            / questions.length;

        progressLabel.setText(
            percentage +
            "% Completed"
        );
    }

    // Get selected answer
    int getSelectedAnswer() {

        if (optionA.isSelected()) {
            return 0;
        }

        if (optionB.isSelected()) {
            return 1;
        }

        if (optionC.isSelected()) {
            return 2;
        }

        if (optionD.isSelected()) {
            return 3;
        }

        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Next
        if (e.getSource() == nextButton) {

            int selected =
                getSelectedAnswer();

            if (selected == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select an option!",
                    "No Answer",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (
                selected ==
                correctAnswers[currentQuestion]
            ) {

                score++;

                JOptionPane.showMessageDialog(
                    this,
                    "Correct Answer!",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                String correct =
                    answers[currentQuestion]
                    [correctAnswers[currentQuestion]];

                JOptionPane.showMessageDialog(
                    this,
                    "Wrong Answer!\n\nCorrect Answer: "
                    + correct,
                    "Result",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            scoreLabel.setText(
                " SCORE  " + score + " "
            );

            currentQuestion++;

            if (
                currentQuestion <
                questions.length
            ) {

                showQuestion();

            } else {

                showResult();
            }
        }

        // Restart
        if (e.getSource() == restartButton) {

            restartQuiz();
        }
    }

    // Final result
    void showResult() {

        double percentage =
            (score * 100.0)
            / questions.length;

        String message;

        if (percentage >= 80) {

            message = "Excellent! 🎉";

        } else if (percentage >= 60) {

            message = "Great Job! 👏";

        } else if (percentage >= 40) {

            message = "Good Try! 👍";

        } else {

            message = "Keep Practicing! 💪";
        }

        questionLabel.setText(
            "<html><div style='text-align:center;'>"
            + "QUIZ COMPLETED!<br><br>"
            + message
            + "</div></html>"
        );

        questionNumberLabel.setText(
            "FINAL RESULT"
        );

        progressLabel.setText(
            "100% Completed"
        );

        progressBar.setValue(
            questions.length
        );

        optionA.setVisible(false);
        optionB.setVisible(false);
        optionC.setVisible(false);
        optionD.setVisible(false);

        nextButton.setEnabled(false);

        JOptionPane.showMessageDialog(
            this,
            "QUIZ COMPLETED!\n\n"
            + "Your Score: "
            + score
            + " / "
            + questions.length
            + "\nPercentage: "
            + percentage
            + "%\n\n"
            + message,
            "Final Result",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Restart
    void restartQuiz() {

        currentQuestion = 0;
        score = 0;

        scoreLabel.setText(
            " SCORE  0 "
        );

        optionA.setVisible(true);
        optionB.setVisible(true);
        optionC.setVisible(true);
        optionD.setVisible(true);

        nextButton.setEnabled(true);

        showQuestion();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    QuizApplication app =
                        new QuizApplication();

                    app.setVisible(true);
                }
            }
        );
    }
}