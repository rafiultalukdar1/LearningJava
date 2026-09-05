import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Moodify extends JFrame {

    JLabel moodLabel;
    JLabel songLabel;
    JLabel statusLabel;

    String currentMood = "Happy";
    String[] songs = {
        "Sunshine",
        "Perfect Day",
        "Good Vibes",
        "Dream World"
    };

    int songIndex = 0;
    boolean playing = false;

    public Moodify() {

        // Window settings
        setTitle("Moodify - Mood Based Music Player");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        // Title
        JLabel title = new JLabel("MOODIFY");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setHorizontalAlignment(JLabel.CENTER);

        JLabel subtitle = new JLabel(
            "Your Mood, Your Music"
        );
        subtitle.setHorizontalAlignment(JLabel.CENTER);

        JPanel topPanel = new JPanel(
            new GridLayout(2, 1)
        );

        topPanel.add(title);
        topPanel.add(subtitle);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Mood buttons
        JPanel moodPanel = new JPanel(
            new GridLayout(2, 3, 10, 10)
        );

        JButton happyButton =
            new JButton("😊 Happy");

        JButton sadButton =
            new JButton("😔 Sad");

        JButton chillButton =
            new JButton("😎 Chill");

        JButton energyButton =
            new JButton("🔥 Energy");

        JButton romanticButton =
            new JButton("❤️ Romantic");

        moodPanel.add(happyButton);
        moodPanel.add(sadButton);
        moodPanel.add(chillButton);
        moodPanel.add(energyButton);
        moodPanel.add(romanticButton);

        mainPanel.add(moodPanel, BorderLayout.CENTER);

        // Bottom player panel
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(
            new BoxLayout(playerPanel, BoxLayout.Y_AXIS)
        );

        moodLabel = new JLabel("Current Mood: Happy");
        moodLabel.setFont(
            new Font("Arial", Font.BOLD, 18)
        );
        moodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        songLabel = new JLabel(
            "🎵 " + songs[songIndex]
        );
        songLabel.setFont(
            new Font("Arial", Font.BOLD, 24)
        );
        songLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("⏸ Paused");
        statusLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // Buttons
        JPanel controlPanel = new JPanel();

        JButton playButton =
            new JButton("▶ PLAY");

        JButton nextButton =
            new JButton("⏭ NEXT");

        controlPanel.add(playButton);
        controlPanel.add(nextButton);

        playerPanel.add(moodLabel);
        playerPanel.add(Box.createVerticalStrut(15));
        playerPanel.add(songLabel);
        playerPanel.add(Box.createVerticalStrut(10));
        playerPanel.add(statusLabel);
        playerPanel.add(Box.createVerticalStrut(15));
        playerPanel.add(controlPanel);

        mainPanel.add(
            playerPanel,
            BorderLayout.SOUTH
        );

        add(mainPanel);

        // Happy button
        happyButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeMood("Happy");
            }
        });

        // Sad button
        sadButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeMood("Sad");
            }
        });

        // Chill button
        chillButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeMood("Chill");
            }
        });

        // Energy button
        energyButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeMood("Energetic");
            }
        });

        // Romantic button
        romanticButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeMood("Romantic");
            }
        });

        // Play button
        playButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (playing) {

                    playing = false;

                    statusLabel.setText("⏸ Paused");

                    playButton.setText("▶ PLAY");

                } else {

                    playing = true;

                    statusLabel.setText("▶ Playing...");

                    playButton.setText("⏸ PAUSE");
                }
            }
        });

        // Next button
        nextButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                songIndex++;

                if (songIndex >= songs.length) {

                    songIndex = 0;
                }

                songLabel.setText(
                    "🎵 " + songs[songIndex]
                );

                statusLabel.setText(
                    "⏸ Paused"
                );

                playing = false;

                playButton.setText("▶ PLAY");
            }
        });
    }

    // Change mood
    void changeMood(String mood) {

        currentMood = mood;

        moodLabel.setText(
            "Current Mood: " + currentMood
        );

        songIndex = 0;

        if (mood.equals("Happy")) {

            songs = new String[]{
                "Sunshine",
                "Perfect Day",
                "Good Vibes",
                "Dream World"
            };

        } else if (mood.equals("Sad")) {

            songs = new String[]{
                "Lonely Nights",
                "Falling Stars",
                "Memories",
                "Broken Dreams"
            };

        } else if (mood.equals("Chill")) {

            songs = new String[]{
                "Midnight Drive",
                "Ocean Waves",
                "Sunset",
                "Slow Motion"
            };

        } else if (mood.equals("Energetic")) {

            songs = new String[]{
                "Energy Boost",
                "Run Wild",
                "Power Up",
                "Never Stop"
            };

        } else if (mood.equals("Romantic")) {

            songs = new String[]{
                "Love Story",
                "Forever",
                "Heartbeat",
                "Dream Love"
            };
        }

        songLabel.setText(
            "🎵 " + songs[songIndex]
        );

        statusLabel.setText("⏸ Paused");

        playing = false;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                Moodify app = new Moodify();

                app.setVisible(true);
            }
        });
    }
}