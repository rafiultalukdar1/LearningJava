import javax.swing.*;
import java.awt.*;

public class MiniTextAnalyzer extends JFrame {

    JTextArea textArea;

    JLabel characterLabel;
    JLabel wordLabel;
    JLabel sentenceLabel;
    JLabel vowelLabel;
    JLabel consonantLabel;
    JLabel numberLabel;
    JLabel spaceLabel;
    JLabel longestLabel;

    MiniTextAnalyzer() {

        setTitle("Mini Text Analyzer");
        setSize(900, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();
    }

    void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

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
                new JPanel();

        header.setOpaque(false);

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "✦ MINI TEXT ANALYZER"
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

        JLabel subtitle =
                new JLabel(
                        "Write something and analyze it instantly"
                );

        subtitle.setForeground(
                new Color(150, 157, 180)
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        header.add(title);
        header.add(
                Box.createVerticalStrut(5)
        );
        header.add(subtitle);

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // Text area
        textArea =
                new JTextArea();

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        textArea.setForeground(
                Color.WHITE
        );

        textArea.setBackground(
                new Color(25, 29, 45)
        );

        textArea.setCaretColor(
                Color.WHITE
        );

        textArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Enter Your Text"
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Result panel
        JPanel resultPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                12,
                                12
                        )
                );

        resultPanel.setOpaque(false);

        characterLabel =
                createResultLabel(
                        "Characters",
                        "0"
                );

        wordLabel =
                createResultLabel(
                        "Words",
                        "0"
                );

        sentenceLabel =
                createResultLabel(
                        "Sentences",
                        "0"
                );

        vowelLabel =
                createResultLabel(
                        "Vowels",
                        "0"
                );

        consonantLabel =
                createResultLabel(
                        "Consonants",
                        "0"
                );

        numberLabel =
                createResultLabel(
                        "Numbers",
                        "0"
                );

        spaceLabel =
                createResultLabel(
                        "Spaces",
                        "0"
                );

        longestLabel =
                createResultLabel(
                        "Longest Word",
                        "-"
                );

        resultPanel.add(characterLabel);
        resultPanel.add(wordLabel);
        resultPanel.add(sentenceLabel);
        resultPanel.add(vowelLabel);

        resultPanel.add(consonantLabel);
        resultPanel.add(numberLabel);
        resultPanel.add(spaceLabel);
        resultPanel.add(longestLabel);

        // Buttons
        JButton analyzeButton =
                new JButton(
                        "ANALYZE TEXT"
                );

        JButton clearButton =
                new JButton(
                        "CLEAR"
                );

        styleButton(
                analyzeButton,
                new Color(124, 92, 255)
        );

        styleButton(
                clearButton,
                new Color(55, 61, 82)
        );

        analyzeButton.addActionListener(
                e -> analyzeText()
        );

        clearButton.addActionListener(
                e -> clearText()
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

        buttonPanel.add(analyzeButton);
        buttonPanel.add(clearButton);

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        bottomPanel.setOpaque(false);

        bottomPanel.add(
                resultPanel,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    JLabel createResultLabel(
            String title,
            String value
    ) {

        JLabel label =
                new JLabel(
                        "<html>"
                                + "<center>"
                                + "<b>"
                                + title
                                + "</b><br>"
                                + "<font size='5'>"
                                + value
                                + "</font>"
                                + "</center>"
                                + "</html>",
                        SwingConstants.CENTER
                );

        label.setOpaque(true);

        label.setBackground(
                new Color(25, 29, 45)
        );

        label.setForeground(
                Color.WHITE
        );

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        5,
                        10,
                        5
                )
        );

        return label;
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

    void analyzeText() {

        String text =
                textArea.getText();

        if (
                text.trim().isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter some text!"
            );

            return;
        }

        int characters =
                text.length();

        int words = 0;
        int sentences = 0;
        int vowels = 0;
        int consonants = 0;
        int numbers = 0;
        int spaces = 0;

        // Count characters
        for (
                int i = 0;
                i < text.length();
                i++
        ) {

            char ch =
                    text.charAt(i);

            // Space count
            if (
                    Character.isWhitespace(ch)
            ) {

                spaces++;
            }

            // Number count
            if (
                    Character.isDigit(ch)
            ) {

                numbers++;
            }

            // Vowel and consonant
            if (
                    Character.isLetter(ch)
            ) {

                char lower =
                        Character.toLowerCase(ch);

                if (
                        lower == 'a'
                                ||
                        lower == 'e'
                                ||
                        lower == 'i'
                                ||
                        lower == 'o'
                                ||
                        lower == 'u'
                ) {

                    vowels++;

                } else {

                    consonants++;
                }
            }

            // Sentence count
            if (
                    ch == '.'
                            ||
                    ch == '!'
                            ||
                    ch == '?'
            ) {

                sentences++;
            }
        }

        // Word count
        String trimmed =
                text.trim();

        if (!trimmed.isEmpty()) {

            String[] wordArray =
                    trimmed.split(
                            "\\s+"
                    );

            words =
                    wordArray.length;

            // Find longest word
            String longestWord = "";

            for (
                    String word : wordArray
            ) {

                String cleanWord =
                        word.replaceAll(
                                "[^a-zA-Z0-9]",
                                ""
                        );

                if (
                        cleanWord.length()
                                >
                        longestWord.length()
                ) {

                    longestWord =
                            cleanWord;
                }
            }

            updateLabel(
                    longestLabel,
                    "Longest Word",
                    longestWord
            );
        }

        updateLabel(
                characterLabel,
                "Characters",
                String.valueOf(
                        characters
                )
        );

        updateLabel(
                wordLabel,
                "Words",
                String.valueOf(
                        words
                )
        );

        updateLabel(
                sentenceLabel,
                "Sentences",
                String.valueOf(
                        sentences
                )
        );

        updateLabel(
                vowelLabel,
                "Vowels",
                String.valueOf(
                        vowels
                )
        );

        updateLabel(
                consonantLabel,
                "Consonants",
                String.valueOf(
                        consonants
                )
        );

        updateLabel(
                numberLabel,
                "Numbers",
                String.valueOf(
                        numbers
                )
        );

        updateLabel(
                spaceLabel,
                "Spaces",
                String.valueOf(
                        spaces
                )
        );
    }

    void updateLabel(
            JLabel label,
            String title,
            String value
    ) {

        label.setText(
                "<html>"
                        + "<center>"
                        + "<b>"
                        + title
                        + "</b><br>"
                        + "<font size='5'>"
                        + value
                        + "</font>"
                        + "</center>"
                        + "</html>"
        );
    }

    void clearText() {

        textArea.setText("");

        updateLabel(
                characterLabel,
                "Characters",
                "0"
        );

        updateLabel(
                wordLabel,
                "Words",
                "0"
        );

        updateLabel(
                sentenceLabel,
                "Sentences",
                "0"
        );

        updateLabel(
                vowelLabel,
                "Vowels",
                "0"
        );

        updateLabel(
                consonantLabel,
                "Consonants",
                "0"
        );

        updateLabel(
                numberLabel,
                "Numbers",
                "0"
        );

        updateLabel(
                spaceLabel,
                "Spaces",
                "0"
        );

        updateLabel(
                longestLabel,
                "Longest Word",
                "-"
        );
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    MiniTextAnalyzer app =
                            new MiniTextAnalyzer();

                    app.setVisible(true);
                }
        );
    }
}