import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DailyHabitTracker extends JFrame {

    ArrayList<Habit> habits = new ArrayList<>();

    JTextField habitField;

    JPanel habitPanel;

    JLabel totalLabel;
    JLabel completedLabel;
    JLabel progressLabel;

    DailyHabitTracker() {

        setTitle("Daily Habit Tracker");
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
                        new BorderLayout(
                                20,
                                20
                        )
                );

        mainPanel.setBackground(
                new Color(15, 18, 30)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        // Header
        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "✦ DAILY HABIT TRACKER"
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
                        "Build better habits, one day at a time"
                );

        subtitle.setForeground(
                new Color(
                        150,
                        157,
                        180
                )
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);

        JLabel status =
                new JLabel(
                        "● TODAY"
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
                titlePanel,
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

        // Center
        JPanel center =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        center.setOpaque(false);

        center.add(
                createStatsPanel(),
                BorderLayout.NORTH
        );

        habitPanel =
                new JPanel();

        habitPanel.setLayout(
                new BoxLayout(
                        habitPanel,
                        BoxLayout.Y_AXIS
                )
        );

        habitPanel.setBackground(
                new Color(
                        15,
                        18,
                        30
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        habitPanel
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Today's Habits"
                )
        );

        center.add(
                scrollPane,
                BorderLayout.CENTER
        );

        center.add(
                createAddPanel(),
                BorderLayout.SOUTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    JPanel createStatsPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        panel.setOpaque(false);

        totalLabel =
                createStatCard(
                        panel,
                        "TOTAL HABITS",
                        "0"
                );

        completedLabel =
                createStatCard(
                        panel,
                        "COMPLETED",
                        "0"
                );

        progressLabel =
                createStatCard(
                        panel,
                        "PROGRESS",
                        "0%"
                );

        return panel;
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
                        15,
                        20,
                        15,
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
                        28
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

    JPanel createAddPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        panel.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        habitField =
                new JTextField();

        habitField.setForeground(
                Color.WHITE
        );

        habitField.setBackground(
                new Color(
                        35,
                        40,
                        58
                )
        );

        habitField.setCaretColor(
                Color.WHITE
        );

        habitField.setBorder(
                BorderFactory.createTitledBorder(
                        "Enter a new habit"
                )
        );

        JButton addButton =
                new JButton(
                        "+ ADD HABIT"
                );

        styleButton(
                addButton,
                new Color(
                        124,
                        92,
                        255
                )
        );

        addButton.addActionListener(
                e -> addHabit()
        );

        panel.add(
                habitField,
                BorderLayout.CENTER
        );

        panel.add(
                addButton,
                BorderLayout.EAST
        );

        return panel;
    }

    void addHabit() {

        String name =
                habitField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a habit!"
            );

            return;
        }

        habits.add(
                new Habit(name)
        );

        habitField.setText("");

        refreshHabits();
    }

    void refreshHabits() {

        habitPanel.removeAll();

        for (
                Habit habit : habits
        ) {

            habitPanel.add(
                    createHabitCard(habit)
            );

            habitPanel.add(
                    Box.createVerticalStrut(10)
            );
        }

        updateStats();

        habitPanel.revalidate();
        habitPanel.repaint();
    }

    JPanel createHabitCard(
            Habit habit
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                5
                        )
                );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );

        card.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        15,
                        12,
                        15
                )
        );

        JLabel habitName =
                new JLabel(
                        "○  " + habit.name
                );

        habitName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        habitName.setForeground(
                Color.WHITE
        );

        JButton completeButton =
                new JButton(
                        "✓ COMPLETE"
                );

        styleButton(
                completeButton,
                new Color(
                        45,
                        180,
                        120
                )
        );

        card.add(
                habitName,
                BorderLayout.CENTER
        );

        card.add(
                completeButton,
                BorderLayout.EAST
        );

        return card;
    }

    void updateStats() {

        int total =
                habits.size();

        int completed = 0;

        for (
                Habit habit : habits
        ) {

            if (habit.completed) {

                completed++;
            }
        }

        int progress = 0;

        if (total > 0) {

            progress =
                    completed * 100 / total;
        }

        totalLabel.setText(
                String.valueOf(total)
        );

        completedLabel.setText(
                String.valueOf(completed)
        );

        progressLabel.setText(
                progress + "%"
        );
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
                        12
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );
    }

    static class Habit {

        String name;

        boolean completed;

        Habit(String name) {

            this.name = name;

            completed = false;
        }
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    DailyHabitTracker app =
                            new DailyHabitTracker();

                    app.setVisible(true);
                }
        );
    }
}