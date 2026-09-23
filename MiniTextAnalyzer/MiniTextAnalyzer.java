import javax.swing.*;
import java.awt.*;

public class MiniTextAnalyzer extends JFrame {

    JTextArea textArea;

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

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
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
                        12, 15, 12, 15
                )
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