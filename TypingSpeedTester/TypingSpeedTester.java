import javax.swing.*;
import java.awt.*;

public class TypingSpeedTester extends JFrame {

    JLabel timerLabel;
    JLabel wpmLabel;
    JLabel accuracyLabel;

    JTextArea sentenceArea;
    JTextArea typingArea;

    JButton startButton;
    JButton resetButton;

    Timer timer;
    int seconds = 0;

    TypingSpeedTester() {

        setTitle("Typing Speed Tester");
        setSize(950, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();
    }

    void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(20, 20)
                );

        mainPanel.setBackground(
                new Color(15, 18, 30)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 25, 25, 25
                )
        );

        // Header
        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "⌨ TYPING SPEED TESTER"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                Color.WHITE
        );

        JLabel status =
                new JLabel(
                        "● READY"
                );

        status.setForeground(
                new Color(
                        45,
                        210,
                        130
                )
        );

        status.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                status,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // Stats
        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        stats.setOpaque(false);

        timerLabel =
                createStatCard(
                        stats,
                        "TIME",
                        "0s"
                );

        wpmLabel =
                createStatCard(
                        stats,
                        "WPM",
                        "0"
                );

        accuracyLabel =
                createStatCard(
                        stats,
                        "ACCURACY",
                        "0%"
                );

        // Text area
        sentenceArea =
                new JTextArea();

        sentenceArea.setLineWrap(true);
        sentenceArea.setWrapStyleWord(true);

        sentenceArea.setEditable(false);

        sentenceArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        sentenceArea.setForeground(
                Color.WHITE
        );

        sentenceArea.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        sentenceArea.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        sentenceArea.setText(
                "The quick brown fox jumps over the lazy dog."
        );

        // Typing area
        typingArea =
                new JTextArea();

        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);

        typingArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        typingArea.setForeground(
                Color.WHITE
        );

        typingArea.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        typingArea.setCaretColor(
                Color.WHITE
        );

        typingArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                15
                        )
                );

        textPanel.setOpaque(false);

        JScrollPane sentenceScroll =
                new JScrollPane(
                        sentenceArea
                );

        JScrollPane typingScroll =
                new JScrollPane(
                        typingArea
                );

        sentenceScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Type This Text"
                )
        );

        typingScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Your Typing"
                )
        );

        textPanel.add(
                sentenceScroll
        );

        textPanel.add(
                typingScroll
        );

        // Buttons
        startButton =
                new JButton(
                        "▶ START TEST"
                );

        resetButton =
                new JButton(
                        "↻ RESET"
                );

        styleButton(
                startButton,
                new Color(
                        124,
                        92,
                        255
                )
        );

        styleButton(
                resetButton,
                new Color(
                        55,
                        61,
                        82
                )
        );

        startButton.addActionListener(
                e -> startTest()
        );

        resetButton.addActionListener(
                e -> resetTest()
        );

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.add(
                startButton
        );

        buttonPanel.add(
                resetButton
        );

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        center.setOpaque(false);

        center.add(
                stats,
                BorderLayout.NORTH
        );

        center.add(
                textPanel,
                BorderLayout.CENTER
        );

        center.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    JLabel createStatCard(
            JPanel parent,
            String title,
            String value
    ) {

        JPanel card =
                new JPanel();

        card.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        20,
                        12,
                        20
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                new Color(
                        150,
                        157,
                        180
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(
                Color.WHITE
        );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(valueLabel);

        parent.add(card);

        return valueLabel;
    }

    void styleButton(
            JButton button,
            Color color
    ) {

        button.setBackground(color);

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        15,
                        12,
                        15
                )
        );
    }

    void startTest() {

        typingArea.setText("");

        typingArea.requestFocus();

        seconds = 0;

        timerLabel.setText("0s");

        timer =
                new Timer(
                        1000,
                        e -> {

                            seconds++;

                            timerLabel.setText(
                                    seconds + "s"
                            );
                        }
                );

        timer.start();

        startButton.setEnabled(false);
    }

    void resetTest() {

        if (timer != null) {

            timer.stop();
        }

        seconds = 0;

        timerLabel.setText("0s");
        wpmLabel.setText("0");
        accuracyLabel.setText("0%");

        typingArea.setText("");

        startButton.setEnabled(true);
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    TypingSpeedTester app =
                            new TypingSpeedTester();

                    app.setVisible(true);
                }
        );
    }
}