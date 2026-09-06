import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartStudyPlanner extends JFrame {

    JTextField taskField;
    JComboBox<String> priorityBox;
    JPanel taskPanel;
    JLabel totalLabel;
    JLabel completedLabel;
    JLabel progressLabel;
    JProgressBar progressBar;

    int totalTasks = 0;
    int completedTasks = 0;

    SmartStudyPlanner() {

        setTitle("Smart Study Planner");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Title
        JLabel title = new JLabel("SMART STUDY PLANNER");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel(
            "Organize your study. Complete your goals."
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel header = new JPanel(
            new GridLayout(2, 1)
        );

        header.add(title);
        header.add(subtitle);

        mainPanel.add(header, BorderLayout.NORTH);

        // Task area
        taskPanel = new JPanel();
        taskPanel.setLayout(
            new BoxLayout(taskPanel, BoxLayout.Y_AXIS)
        );

        JScrollPane scrollPane = new JScrollPane(taskPanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom area
        JPanel bottomPanel = new JPanel(
            new BorderLayout(10, 10)
        );

        // Input area
        JPanel inputPanel = new JPanel(
            new FlowLayout()
        );

        taskField = new JTextField(18);

        priorityBox = new JComboBox<>(
            new String[]{
                "HIGH",
                "MEDIUM",
                "LOW"
            }
        );

        JButton addButton =
            new JButton("+ ADD TASK");

        inputPanel.add(taskField);
        inputPanel.add(priorityBox);
        inputPanel.add(addButton);

        // Statistics
        JPanel statsPanel = new JPanel(
            new GridLayout(2, 4, 10, 5)
        );

        totalLabel = new JLabel("Total: 0");
        completedLabel = new JLabel("Completed: 0");
        progressLabel = new JLabel("Progress: 0%");

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        statsPanel.add(totalLabel);
        statsPanel.add(completedLabel);
        statsPanel.add(progressLabel);
        statsPanel.add(progressBar);

        bottomPanel.add(
            inputPanel,
            BorderLayout.NORTH
        );

        bottomPanel.add(
            statsPanel,
            BorderLayout.SOUTH
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        add(mainPanel);

        // Add button click
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                addTask();
            }
        });

        // Enter key দিয়ে Task add
        taskField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                addTask();
            }
        });
    }

    // Add new task
    void addTask() {

        String taskName =
            taskField.getText().trim();

        if (taskName.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a task!"
            );

            return;
        }

        String priority =
            priorityBox.getSelectedItem().toString();

        createTaskCard(taskName, priority);

        taskField.setText("");

        totalTasks++;

        updateProgress();
    }

    // Create task card
    void createTaskCard(
        String taskName,
        String priority
    ) {

        JPanel card = new JPanel(
            new BorderLayout(10, 5)
        );

        card.setBorder(
            BorderFactory.createLineBorder(
                Color.GRAY,
                1
            )
        );

        card.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                80
            )
        );

        // Task information
        JPanel infoPanel = new JPanel(
            new GridLayout(2, 1)
        );

        JLabel taskLabel =
            new JLabel("📚 " + taskName);

        taskLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        JLabel priorityLabel =
            new JLabel("Priority: " + priority);

        infoPanel.add(taskLabel);
        infoPanel.add(priorityLabel);

        // Buttons
        JPanel buttonPanel = new JPanel(
            new FlowLayout()
        );

        JButton completeButton =
            new JButton("Complete");

        JButton deleteButton =
            new JButton("Delete");

        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);

        card.add(
            infoPanel,
            BorderLayout.CENTER
        );

        card.add(
            buttonPanel,
            BorderLayout.EAST
        );

        taskPanel.add(card);

        taskPanel.revalidate();
        taskPanel.repaint();

        // Complete button
        completeButton.addActionListener(
            new ActionListener() {

                boolean completed = false;

                public void actionPerformed(
                    ActionEvent e
                ) {

                    if (!completed) {

                        completed = true;

                        completedTasks++;

                        taskLabel.setText(
                            "✓ " + taskName
                        );

                        completeButton.setText(
                            "Completed"
                        );

                        completeButton.setEnabled(
                            false
                        );

                        updateProgress();
                    }
                }
            }
        );

        // Delete button
        deleteButton.addActionListener(
            new ActionListener() {

                public void actionPerformed(
                    ActionEvent e
                ) {

                    int answer =
                        JOptionPane.showConfirmDialog(
                            SmartStudyPlanner.this,
                            "Delete this task?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION
                        );

                    if (answer ==
                        JOptionPane.YES_OPTION) {

                        taskPanel.remove(card);

                        totalTasks--;

                        taskPanel.revalidate();
                        taskPanel.repaint();

                        updateProgress();
                    }
                }
            }
        );
    }

    // Update progress
    void updateProgress() {

        totalLabel.setText(
            "Total: " + totalTasks
        );

        completedLabel.setText(
            "Completed: " + completedTasks
        );

        int progress = 0;

        if (totalTasks > 0) {

            progress =
                (completedTasks * 100)
                / totalTasks;
        }

        progressLabel.setText(
            "Progress: " + progress + "%"
        );

        progressBar.setValue(progress);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    new SmartStudyPlanner()
                        .setVisible(true);
                }
            }
        );
    }
}