import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoList extends JFrame implements ActionListener {

    JTextField taskField;
    DefaultListModel<String> taskModel;
    JList<String> taskList;

    JLabel totalLabel;
    JLabel completedLabel;
    JLabel pendingLabel;

    JButton addButton;
    JButton completeButton;
    JButton deleteButton;
    JButton clearButton;

    int completedTasks = 0;

    TodoList() {

        setTitle("My To-Do List");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(
            new BorderLayout(15, 15)
        );

        mainPanel.setBackground(
            new Color(245, 247, 250)
        );

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                25, 25, 25, 25
            )
        );

        // Title
        JLabel title = new JLabel(
            "MY TO-DO LIST"
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 30)
        );

        title.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        mainPanel.add(
            title,
            BorderLayout.NORTH
        );

        // Input area
        JPanel inputPanel = new JPanel(
            new BorderLayout(10, 10)
        );

        inputPanel.setOpaque(false);

        taskField = new JTextField();

        taskField.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        addButton = new JButton(
            "ADD TASK"
        );

        addButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        addButton.addActionListener(this);

        taskField.addActionListener(this);

        inputPanel.add(
            taskField,
            BorderLayout.CENTER
        );

        inputPanel.add(
            addButton,
            BorderLayout.EAST
        );

        mainPanel.add(
            inputPanel,
            BorderLayout.BEFORE_FIRST_LINE
        );

        // Task list
        taskModel =
            new DefaultListModel<>();

        taskList =
            new JList<>(taskModel);

        taskList.setFont(
            new Font("Arial", Font.PLAIN, 17)
        );

        taskList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        taskList.setFixedCellHeight(40);

        JScrollPane scrollPane =
            new JScrollPane(taskList);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder(
                "Tasks"
            )
        );

        mainPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        completeButton =
            new JButton("✓ COMPLETE");

        deleteButton =
            new JButton("DELETE");

        clearButton =
            new JButton("CLEAR ALL");

        completeButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Double click task
        taskList.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    if (e.getClickCount() == 2) {

                        completeTask();
                    }
                }
            }
        );

        // Statistics
        JPanel statsPanel = new JPanel(
            new GridLayout(1, 3, 10, 10)
        );

        statsPanel.setOpaque(false);

        totalLabel = new JLabel(
            "Total: 0",
            SwingConstants.CENTER
        );

        completedLabel = new JLabel(
            "Completed: 0",
            SwingConstants.CENTER
        );

        pendingLabel = new JLabel(
            "Pending: 0",
            SwingConstants.CENTER
        );

        totalLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        completedLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        pendingLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        statsPanel.add(totalLabel);
        statsPanel.add(completedLabel);
        statsPanel.add(pendingLabel);

        // Bottom area
        JPanel bottomPanel = new JPanel(
            new BorderLayout(10, 10)
        );

        bottomPanel.setOpaque(false);

        bottomPanel.add(
            statsPanel,
            BorderLayout.NORTH
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

    @Override
    public void actionPerformed(
        ActionEvent e
    ) {

        // Add task
        if (
            e.getSource() == addButton ||
            e.getSource() == taskField
        ) {

            addTask();
        }

        // Complete task
        if (
            e.getSource() == completeButton
        ) {

            completeTask();
        }

        // Delete task
        if (
            e.getSource() == deleteButton
        ) {

            deleteTask();
        }

        // Clear all
        if (
            e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Add new task
    void addTask() {

        String task =
            taskField.getText().trim();

        if (task.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a task!"
            );

            return;
        }

        taskModel.addElement(
            "○ " + task
        );

        taskField.setText("");

        updateStats();
    }

    // Complete selected task
    void completeTask() {

        int index =
            taskList.getSelectedIndex();

        if (index == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a task!"
            );

            return;
        }

        String task =
            taskModel.getElementAt(index);

        if (!task.startsWith("✓")) {

            task =
                task.replaceFirst(
                    "○ ",
                    "✓ "
                );

            taskModel.set(
                index,
                task
            );

            completedTasks++;

            updateStats();
        }
    }

    // Delete selected task
    void deleteTask() {

        int index =
            taskList.getSelectedIndex();

        if (index == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a task!"
            );

            return;
        }

        String task =
            taskModel.getElementAt(index);

        if (task.startsWith("✓")) {

            completedTasks--;
        }

        taskModel.remove(index);

        updateStats();
    }

    // Clear all tasks
    void clearAll() {

        if (taskModel.isEmpty()) {

            return;
        }

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Delete all tasks?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
            );

        if (
            answer ==
            JOptionPane.YES_OPTION
        ) {

            taskModel.clear();

            completedTasks = 0;

            updateStats();
        }
    }

    // Update statistics
    void updateStats() {

        int total =
            taskModel.size();

        int pending =
            total - completedTasks;

        totalLabel.setText(
            "Total: " + total
        );

        completedLabel.setText(
            "Completed: " +
            completedTasks
        );

        pendingLabel.setText(
            "Pending: " +
            pending
        );
    }

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    TodoList app =
                        new TodoList();

                    app.setVisible(true);
                }
            }
        );
    }
}