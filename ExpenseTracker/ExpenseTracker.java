import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ExpenseTracker extends JFrame implements ActionListener {

    JTextField expenseField;
    JTextField amountField;

    JComboBox<String> categoryBox;

    JTable expenseTable;
    DefaultTableModel tableModel;

    JLabel totalLabel;
    JLabel countLabel;
    JLabel highestLabel;

    JButton addButton;
    JButton deleteButton;
    JButton clearButton;

    double totalExpense = 0;
    double highestExpense = 0;

    ExpenseTracker() {

        setTitle("Expense Tracker");
        setSize(750, 600);
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
            "EXPENSE TRACKER"
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

        // Input panel
        JPanel inputPanel = new JPanel(
            new GridLayout(2, 4, 10, 10)
        );

        inputPanel.setOpaque(false);

        inputPanel.add(
            new JLabel("Expense Name")
        );

        inputPanel.add(
            new JLabel("Category")
        );

        inputPanel.add(
            new JLabel("Amount")
        );

        inputPanel.add(
            new JLabel("")
        );

        expenseField = new JTextField();

        categoryBox = new JComboBox<>(
            new String[] {
                "Food",
                "Transport",
                "Shopping",
                "Education",
                "Entertainment",
                "Bills",
                "Other"
            }
        );

        amountField = new JTextField();

        addButton = new JButton(
            "ADD EXPENSE"
        );

        addButton.addActionListener(this);

        amountField.addActionListener(this);

        inputPanel.add(expenseField);
        inputPanel.add(categoryBox);
        inputPanel.add(amountField);
        inputPanel.add(addButton);

        mainPanel.add(
            inputPanel,
            BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
            "Expense",
            "Category",
            "Amount"
        };

        tableModel =
            new DefaultTableModel(columns, 0) {

                @Override
                public boolean isCellEditable(
                    int row,
                    int column
                ) {
                    return false;
                }
            };

        expenseTable =
            new JTable(tableModel);

        expenseTable.setFont(
            new Font("Arial", Font.PLAIN, 15)
        );

        expenseTable.setRowHeight(35);

        expenseTable.getTableHeader()
            .setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    15
                )
            );

        JScrollPane scrollPane =
            new JScrollPane(expenseTable);

        mainPanel.add(
            scrollPane,
            BorderLayout.CENTER
        );

        // Statistics
        JPanel statsPanel = new JPanel(
            new GridLayout(1, 3, 10, 10)
        );

        statsPanel.setOpaque(false);

        totalLabel = new JLabel(
            "Total: ৳0.00",
            SwingConstants.CENTER
        );

        countLabel = new JLabel(
            "Expenses: 0",
            SwingConstants.CENTER
        );

        highestLabel = new JLabel(
            "Highest: ৳0.00",
            SwingConstants.CENTER
        );

        totalLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        countLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        highestLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        statsPanel.add(totalLabel);
        statsPanel.add(countLabel);
        statsPanel.add(highestLabel);

        // Buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        deleteButton = new JButton(
            "DELETE SELECTED"
        );

        clearButton = new JButton(
            "CLEAR ALL"
        );

        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Bottom panel
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

        // Add expense
        if (
            e.getSource() == addButton ||
            e.getSource() == amountField
        ) {

            addExpense();
        }

        // Delete selected
        if (
            e.getSource() == deleteButton
        ) {

            deleteExpense();
        }

        // Clear all
        if (
            e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Add expense
    void addExpense() {

        String expense =
            expenseField.getText().trim();

        String category =
            categoryBox.getSelectedItem()
            .toString();

        String amountText =
            amountField.getText().trim();

        if (expense.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter expense name!"
            );

            return;
        }

        if (amountText.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter amount!"
            );

            return;
        }

        try {

            double amount =
                Double.parseDouble(amountText);

            if (amount <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Amount must be greater than 0!"
                );

                return;
            }

            // Add row
            tableModel.addRow(
                new Object[] {
                    expense,
                    category,
                    String.format(
                        "৳%.2f",
                        amount
                    )
                }
            );

            totalExpense += amount;

            if (amount > highestExpense) {

                highestExpense = amount;
            }

            updateStats();

            expenseField.setText("");
            amountField.setText("");

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid amount!"
            );
        }
    }

    // Delete expense
    void deleteExpense() {

        int row =
            expenseTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select an expense!"
            );

            return;
        }

        String amountText =
            tableModel
            .getValueAt(row, 2)
            .toString();

        double amount =
            Double.parseDouble(
                amountText
                .replace("৳", "")
                .replace(",", "")
        );

        totalExpense -= amount;

        tableModel.removeRow(row);

        calculateHighest();

        updateStats();
    }

    // Calculate highest expense
    void calculateHighest() {

        highestExpense = 0;

        for (
            int i = 0;
            i < tableModel.getRowCount();
            i++
        ) {

            String amountText =
                tableModel
                .getValueAt(i, 2)
                .toString();

            double amount =
                Double.parseDouble(
                    amountText
                    .replace("৳", "")
                    .replace(",", "")
                );

            if (amount > highestExpense) {

                highestExpense = amount;
            }
        }
    }

    // Clear all expenses
    void clearAll() {

        if (
            tableModel.getRowCount() == 0
        ) {

            return;
        }

        int result =
            JOptionPane.showConfirmDialog(
                this,
                "Delete all expenses?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
            );

        if (
            result ==
            JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            totalExpense = 0;
            highestExpense = 0;

            updateStats();
        }
    }

    // Update statistics
    void updateStats() {

        totalLabel.setText(
            String.format(
                "Total: ৳%.2f",
                totalExpense
            )
        );

        countLabel.setText(
            "Expenses: " +
            tableModel.getRowCount()
        );

        highestLabel.setText(
            String.format(
                "Highest: ৳%.2f",
                highestExpense
            )
        );
    }

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    ExpenseTracker app =
                        new ExpenseTracker();

                    app.setVisible(true);
                }
            }
        );
    }
}