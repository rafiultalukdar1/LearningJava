import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ATMSystem extends JFrame implements ActionListener {

    // Login components
    JTextField pinField;
    JButton loginButton;
    JLabel pinHintLabel;

    // ATM components
    JLabel welcomeLabel;
    JLabel balanceLabel;

    JButton balanceButton;
    JButton withdrawButton;
    JButton depositButton;
    JButton historyButton;
    JButton changePinButton;
    JButton logoutButton;

    // ATM data
    double balance = 10000.00;
    String pin = "1234";

    ArrayList<String> transactions =
        new ArrayList<>();

    JPanel mainPanel;

    ATMSystem() {

        setTitle("ATM System");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLoginScreen();
    }

    // Login screen
    void showLoginScreen() {

        getContentPane().removeAll();

        JPanel panel = new JPanel(
            new GridBagLayout()
        );

        panel.setBackground(
            new Color(245, 247, 250)
        );

        GridBagConstraints gbc =
            new GridBagConstraints();

        gbc.insets =
            new Insets(10, 10, 10, 10);

        // Title
        JLabel title = new JLabel(
            "ATM SYSTEM"
        );

        title.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                32
            )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc);

        // PIN label
        JLabel pinLabel = new JLabel(
            "Enter PIN:"
        );

        pinLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(pinLabel, gbc);

        // PIN field
        pinField = new JTextField(15);

        pinField.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                18
            )
        );

        gbc.gridx = 1;

        panel.add(pinField, gbc);

        // Login button
        loginButton = new JButton(
            "LOGIN"
        );

        loginButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        loginButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(loginButton, gbc);

        // Dynamic PIN hint
        pinHintLabel = new JLabel(
            "Current PIN: " + pin
        );

        pinHintLabel.setForeground(
            Color.GRAY
        );

        pinHintLabel.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                14
            )
        );

        gbc.gridy = 3;

        panel.add(
            pinHintLabel,
            gbc
        );

        add(panel);

        revalidate();
        repaint();
    }

    // ATM dashboard
    void showDashboard() {

        getContentPane().removeAll();

        mainPanel = new JPanel(
            new BorderLayout(20, 20)
        );

        mainPanel.setBackground(
            new Color(245, 247, 250)
        );

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                25, 30, 25, 30
            )
        );

        // Header
        JPanel header = new JPanel(
            new BorderLayout()
        );

        header.setOpaque(false);

        welcomeLabel = new JLabel(
            "WELCOME TO ATM"
        );

        welcomeLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                28
            )
        );

        balanceLabel = new JLabel();

        balanceLabel.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                20
            )
        );

        balanceLabel.setForeground(
            new Color(40, 120, 80)
        );

        header.add(
            welcomeLabel,
            BorderLayout.WEST
        );

        header.add(
            balanceLabel,
            BorderLayout.EAST
        );

        mainPanel.add(
            header,
            BorderLayout.NORTH
        );

        // Buttons
        JPanel buttonPanel = new JPanel(
            new GridLayout(
                3,
                2,
                20,
                20
            )
        );

        buttonPanel.setOpaque(false);

        balanceButton =
            createButton(
                "BALANCE CHECK"
            );

        withdrawButton =
            createButton(
                "WITHDRAW"
            );

        depositButton =
            createButton(
                "DEPOSIT"
            );

        historyButton =
            createButton(
                "TRANSACTION HISTORY"
            );

        changePinButton =
            createButton(
                "CHANGE PIN"
            );

        logoutButton =
            createButton(
                "LOGOUT"
            );

        buttonPanel.add(
            balanceButton
        );

        buttonPanel.add(
            withdrawButton
        );

        buttonPanel.add(
            depositButton
        );

        buttonPanel.add(
            historyButton
        );

        buttonPanel.add(
            changePinButton
        );

        buttonPanel.add(
            logoutButton
        );

        mainPanel.add(
            buttonPanel,
            BorderLayout.CENTER
        );

        add(mainPanel);

        updateBalance();

        revalidate();
        repaint();
    }

    // Create button
    JButton createButton(
        String text
    ) {

        JButton button =
            new JButton(text);

        button.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        button.setFocusPainted(false);

        button.addActionListener(this);

        return button;
    }

    // Update balance
    void updateBalance() {

        balanceLabel.setText(
            String.format(
                "Balance: ৳%.2f",
                balance
            )
        );
    }

    @Override
    public void actionPerformed(
        ActionEvent e
    ) {

        // Login
        if (
            e.getSource() ==
            loginButton
        ) {

            login();
        }

        // Balance
        else if (
            e.getSource() ==
            balanceButton
        ) {

            showBalance();
        }

        // Withdraw
        else if (
            e.getSource() ==
            withdrawButton
        ) {

            withdrawMoney();
        }

        // Deposit
        else if (
            e.getSource() ==
            depositButton
        ) {

            depositMoney();
        }

        // History
        else if (
            e.getSource() ==
            historyButton
        ) {

            showHistory();
        }

        // Change PIN
        else if (
            e.getSource() ==
            changePinButton
        ) {

            changePin();
        }

        // Logout
        else if (
            e.getSource() ==
            logoutButton
        ) {

            logout();
        }
    }

    // Login
    void login() {

        String enteredPin =
            pinField.getText();

        if (
            enteredPin.equals(pin)
        ) {

            transactions.add(
                "Login successful"
            );

            showDashboard();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Incorrect PIN!",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );

            pinField.setText("");
        }
    }

    // Balance check
    void showBalance() {

        JOptionPane.showMessageDialog(
            this,
            String.format(
                "Your Current Balance:\n\n৳%.2f",
                balance
            ),
            "Balance",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Withdraw
    void withdrawMoney() {

        String amountText =
            JOptionPane.showInputDialog(
                this,
                "Enter withdrawal amount:"
            );

        if (
            amountText == null
        ) {

            return;
        }

        try {

            double amount =
                Double.parseDouble(
                    amountText
                );

            if (
                amount <= 0
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount!"
                );

                return;
            }

            if (
                amount > balance
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Insufficient balance!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            balance -= amount;

            transactions.add(
                String.format(
                    "Withdraw: ৳%.2f",
                    amount
                )
            );

            updateBalance();

            JOptionPane.showMessageDialog(
                this,
                String.format(
                    "Successfully withdrawn!\n\n"
                    + "Amount: ৳%.2f\n"
                    + "Remaining: ৳%.2f",
                    amount,
                    balance
                )
            );

        } catch (
            NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid number!"
            );
        }
    }

    // Deposit
    void depositMoney() {

        String amountText =
            JOptionPane.showInputDialog(
                this,
                "Enter deposit amount:"
            );

        if (
            amountText == null
        ) {

            return;
        }

        try {

            double amount =
                Double.parseDouble(
                    amountText
                );

            if (
                amount <= 0
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount!"
                );

                return;
            }

            balance += amount;

            transactions.add(
                String.format(
                    "Deposit: ৳%.2f",
                    amount
                )
            );

            updateBalance();

            JOptionPane.showMessageDialog(
                this,
                String.format(
                    "Successfully deposited!\n\n"
                    + "Amount: ৳%.2f\n"
                    + "New Balance: ৳%.2f",
                    amount,
                    balance
                )
            );

        } catch (
            NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid number!"
            );
        }
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

        textArea.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        textArea.setEditable(false);

        JScrollPane scrollPane =
            new JScrollPane(
                textArea
            );

        scrollPane.setPreferredSize(
            new Dimension(400, 300)
        );

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Transaction History",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Change PIN
    void changePin() {

        String oldPin =
            JOptionPane.showInputDialog(
                this,
                "Enter current PIN:"
            );

        if (
            oldPin == null
        ) {

            return;
        }

        if (
            !oldPin.equals(pin)
        ) {

            JOptionPane.showMessageDialog(
                this,
                "Current PIN is incorrect!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String newPin =
            JOptionPane.showInputDialog(
                this,
                "Enter new 4-digit PIN:"
            );

        if (
            newPin == null
        ) {

            return;
        }

        if (
            newPin.length() != 4 ||
            !newPin.matches("\\d{4}")
        ) {

            JOptionPane.showMessageDialog(
                this,
                "PIN must contain 4 digits!"
            );

            return;
        }

        if (
            newPin.equals(pin)
        ) {

            JOptionPane.showMessageDialog(
                this,
                "New PIN must be different!"
            );

            return;
        }

        // Update PIN
        pin = newPin;

        transactions.add(
            "PIN changed successfully"
        );

        JOptionPane.showMessageDialog(
            this,
            "PIN changed successfully!"
        );

        // Go to login screen
        showLoginScreen();
    }

    // Logout
    void logout() {

        int result =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
            );

        if (
            result ==
            JOptionPane.YES_OPTION
        ) {

            showLoginScreen();
        }
    }

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    ATMSystem atm =
                        new ATMSystem();

                    atm.setVisible(true);
                }
            }
        );
    }
}