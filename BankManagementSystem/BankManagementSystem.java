import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BankManagementSystem extends JFrame
        implements ActionListener {

    JTextField accountField;
    JTextField nameField;
    JTextField amountField;
    JTextField searchField;

    JComboBox<String> typeBox;

    JTable accountTable;
    DefaultTableModel tableModel;

    JButton createButton;
    JButton depositButton;
    JButton withdrawButton;
    JButton balanceButton;
    JButton searchButton;
    JButton historyButton;
    JButton deleteButton;
    JButton clearButton;

    JLabel totalAccountLabel;
    JLabel totalBalanceLabel;

    ArrayList<String> transactions =
            new ArrayList<>();

    BankManagementSystem() {

        setTitle("Bank Management System");
        setSize(950, 700);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
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
                        20, 20, 20, 20
                )
        );

        // Title
        JLabel title = new JLabel(
                "BANK MANAGEMENT SYSTEM"
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
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
                new JLabel("Account Number")
        );

        inputPanel.add(
                new JLabel("Customer Name")
        );

        inputPanel.add(
                new JLabel("Account Type")
        );

        inputPanel.add(
                new JLabel("Initial Deposit")
        );

        accountField = new JTextField();
        nameField = new JTextField();
        amountField = new JTextField();

        typeBox = new JComboBox<>(
                new String[]{
                        "Savings",
                        "Current",
                        "Student"
                }
        );

        inputPanel.add(accountField);
        inputPanel.add(nameField);
        inputPanel.add(typeBox);
        inputPanel.add(amountField);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Account No",
                "Name",
                "Type",
                "Balance"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        accountTable =
                new JTable(tableModel);

        accountTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        accountTable.setRowHeight(32);

        accountTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(accountTable);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Bottom area
        JPanel bottomPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        bottomPanel.setOpaque(false);

        // Search
        JPanel searchPanel = new JPanel();

        searchPanel.setOpaque(false);

        searchField =
                new JTextField(10);

        searchButton =
                new JButton("SEARCH");

        searchButton.addActionListener(this);

        searchPanel.add(
                new JLabel("Search Account:")
        );

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        bottomPanel.add(
                searchPanel,
                BorderLayout.WEST
        );

        // Buttons
        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 4, 8, 8)
        );

        buttonPanel.setOpaque(false);

        createButton =
                createButton("CREATE ACCOUNT");

        depositButton =
                createButton("DEPOSIT");

        withdrawButton =
                createButton("WITHDRAW");

        balanceButton =
                createButton("BALANCE");

        historyButton =
                createButton("HISTORY");

        deleteButton =
                createButton("DELETE");

        clearButton =
                createButton("CLEAR ALL");

        buttonPanel.add(createButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // Statistics
        JPanel statsPanel = new JPanel(
                new GridLayout(2, 1)
        );

        statsPanel.setOpaque(false);

        totalAccountLabel =
                new JLabel(
                        "Accounts: 0",
                        SwingConstants.CENTER
                );

        totalBalanceLabel =
                new JLabel(
                        "Total: ৳0.00",
                        SwingConstants.CENTER
                );

        totalAccountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        totalBalanceLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        statsPanel.add(totalAccountLabel);
        statsPanel.add(totalBalanceLabel);

        bottomPanel.add(
                statsPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // Create button
    JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.addActionListener(this);

        return button;
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {

        if (
                e.getSource() == createButton
        ) {

            createAccount();

        } else if (
                e.getSource() == depositButton
        ) {

            deposit();

        } else if (
                e.getSource() == withdrawButton
        ) {

            withdraw();

        } else if (
                e.getSource() == balanceButton
        ) {

            checkBalance();

        } else if (
                e.getSource() == searchButton
        ) {

            searchAccount();

        } else if (
                e.getSource() == historyButton
        ) {

            showHistory();

        } else if (
                e.getSource() == deleteButton
        ) {

            deleteAccount();

        } else if (
                e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Create account
    void createAccount() {

        String account =
                accountField.getText().trim();

        String name =
                nameField.getText().trim();

        String type =
                typeBox.getSelectedItem()
                        .toString();

        String amountText =
                amountField.getText().trim();

        if (
                account.isEmpty() ||
                name.isEmpty() ||
                amountText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText
                    );

            if (amount < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid deposit amount!"
                );

                return;
            }

            // Check duplicate account
            for (
                    int i = 0;
                    i < tableModel.getRowCount();
                    i++
            ) {

                String existingAccount =
                        tableModel
                                .getValueAt(i, 0)
                                .toString();

                if (
                        existingAccount.equals(
                                account
                        )
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Account already exists!"
                    );

                    return;
                }
            }

            tableModel.addRow(
                    new Object[]{
                            account,
                            name,
                            type,
                            String.format(
                                    "৳%.2f",
                                    amount
                            )
                    }
            );

            transactions.add(
                    "Account created: "
                            + account
            );

            updateStats();
            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!"
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount!"
            );
        }
    }

    // Find account row
    int findAccount(
            String account
    ) {

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingAccount =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    existingAccount.equals(
                            account
                    )
            ) {

                return i;
            }
        }

        return -1;
    }

    // Deposit
    void deposit() {

        String account =
                JOptionPane.showInputDialog(
                        this,
                        "Enter account number:"
                );

        if (account == null) {
            return;
        }

        int row =
                findAccount(account.trim());

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account not found!"
            );

            return;
        }

        String amountText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter deposit amount:"
                );

        if (amountText == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText
                    );

            if (amount <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid amount!"
                );

                return;
            }

            double oldBalance =
                    getBalance(row);

            double newBalance =
                    oldBalance + amount;

            tableModel.setValueAt(
                    String.format(
                            "৳%.2f",
                            newBalance
                    ),
                    row,
                    3
            );

            transactions.add(
                    String.format(
                            "Deposit ৳%.2f to %s",
                            amount,
                            account
                    )
            );

            updateStats();

            JOptionPane.showMessageDialog(
                    this,
                    String.format(
                            "Deposit successful!\n\n"
                                    + "Amount: ৳%.2f\n"
                                    + "New Balance: ৳%.2f",
                            amount,
                            newBalance
                    )
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount!"
            );
        }
    }

    // Withdraw
    void withdraw() {

        String account =
                JOptionPane.showInputDialog(
                        this,
                        "Enter account number:"
                );

        if (account == null) {
            return;
        }

        int row =
                findAccount(account.trim());

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account not found!"
            );

            return;
        }

        String amountText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter withdrawal amount:"
                );

        if (amountText == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            amountText
                    );

            double oldBalance =
                    getBalance(row);

            if (amount <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid amount!"
                );

                return;
            }

            if (amount > oldBalance) {

                JOptionPane.showMessageDialog(
                        this,
                        "Insufficient balance!"
                );

                return;
            }

            double newBalance =
                    oldBalance - amount;

            tableModel.setValueAt(
                    String.format(
                            "৳%.2f",
                            newBalance
                    ),
                    row,
                    3
            );

            transactions.add(
                    String.format(
                            "Withdraw ৳%.2f from %s",
                            amount,
                            account
                    )
            );

            updateStats();

            JOptionPane.showMessageDialog(
                    this,
                    String.format(
                            "Withdrawal successful!\n\n"
                                    + "Amount: ৳%.2f\n"
                                    + "Remaining: ৳%.2f",
                            amount,
                            newBalance
                    )
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount!"
            );
        }
    }

    // Get balance
    double getBalance(int row) {

        String balanceText =
                tableModel
                        .getValueAt(row, 3)
                        .toString();

        return Double.parseDouble(
                balanceText
                        .replace("৳", "")
                        .replace(",", "")
        );
    }

    // Balance check
    void checkBalance() {

        int row =
                accountTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an account!"
            );

            return;
        }

        String account =
                tableModel
                        .getValueAt(row, 0)
                        .toString();

        String name =
                tableModel
                        .getValueAt(row, 1)
                        .toString();

        double balance =
                getBalance(row);

        JOptionPane.showMessageDialog(
                this,
                "Account: " + account
                        + "\nName: " + name
                        + String.format(
                                "\nBalance: ৳%.2f",
                                balance
                        ),
                "Account Balance",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Search account
    void searchAccount() {

        String account =
                searchField.getText().trim();

        if (account.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter account number!"
            );

            return;
        }

        int row =
                findAccount(account);

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account not found!"
            );

            return;
        }

        accountTable
                .setRowSelectionInterval(
                        row,
                        row
                );

        accountTable.scrollRectToVisible(
                accountTable.getCellRect(
                        row,
                        0,
                        true
                )
        );
    }

    // Transaction history
    void showHistory() {

        if (
                transactions.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No transactions yet!"
            );

            return;
        }

        StringBuilder history =
                new StringBuilder();

        history.append(
                "TRANSACTION HISTORY\n\n"
        );

        for (
                int i = 0;
                i < transactions.size();
                i++
        ) {

            history.append(
                    (i + 1)
                            + ". "
                            + transactions.get(i)
                            + "\n"
            );
        }

        JTextArea textArea =
                new JTextArea(
                        history.toString()
                );

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(450, 300)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "History",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Delete account
    void deleteAccount() {

        int row =
                accountTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an account!"
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected account?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            String account =
                    tableModel
                            .getValueAt(row, 0)
                            .toString();

            tableModel.removeRow(row);

            transactions.add(
                    "Account deleted: "
                            + account
            );

            updateStats();
        }
    }

    // Clear all accounts
    void clearAll() {

        if (
                tableModel.getRowCount() == 0
        ) {

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete all accounts?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            transactions.add(
                    "All accounts cleared"
            );

            updateStats();
        }
    }

    // Update statistics
    void updateStats() {

        double total = 0;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            total += getBalance(i);
        }

        totalAccountLabel.setText(
                "Accounts: "
                        + tableModel.getRowCount()
        );

        totalBalanceLabel.setText(
                String.format(
                        "Total: ৳%.2f",
                        total
                )
        );
    }

    // Clear input fields
    void clearFields() {

        accountField.setText("");
        nameField.setText("");
        amountField.setText("");

        typeBox.setSelectedIndex(0);
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        BankManagementSystem app =
                                new BankManagementSystem();

                        app.setVisible(true);
                    }
                }
        );
    }
}