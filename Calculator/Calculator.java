import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField display;

    double firstNumber = 0;
    String operator = "";
    boolean newNumber = true;

    Calculator() {

        setTitle("Smart Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Display
        display = new JTextField("0");

        display.setFont(
            new Font("Arial", Font.BOLD, 32)
        );

        display.setHorizontalAlignment(
            JTextField.RIGHT
        );

        display.setEditable(false);

        mainPanel.add(
            display,
            BorderLayout.NORTH
        );

        // Button panel
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
            new GridLayout(5, 4, 8, 8)
        );

        String[] buttons = {
            "AC", "DEL", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "−",
            "1", "2", "3", "+",
            "0", ".", "=", "√"
        };

        // Create buttons
        for (String text : buttons) {

            JButton button = new JButton(text);

            button.setFont(
                new Font("Arial", Font.BOLD, 20)
            );

            button.addActionListener(this);

            buttonPanel.add(button);
        }

        mainPanel.add(
            buttonPanel,
            BorderLayout.CENTER
        );

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command =
            e.getActionCommand();

        // Number buttons
        if (command.matches("[0-9]")) {

            if (newNumber ||
                display.getText().equals("0")) {

                display.setText(command);

                newNumber = false;

            } else {

                display.setText(
                    display.getText() + command
                );
            }

            return;
        }

        // Decimal
        if (command.equals(".")) {

            if (newNumber) {

                display.setText("0.");
                newNumber = false;

            } else if (
                !display.getText().contains(".")
            ) {

                display.setText(
                    display.getText() + "."
                );
            }

            return;
        }

        // Clear
        if (command.equals("AC")) {

            display.setText("0");

            firstNumber = 0;
            operator = "";

            newNumber = true;

            return;
        }

        // Delete
        if (command.equals("DEL")) {

            String value =
                display.getText();

            if (value.length() > 1) {

                display.setText(
                    value.substring(
                        0,
                        value.length() - 1
                    )
                );

            } else {

                display.setText("0");
                newNumber = true;
            }

            return;
        }

        // Square root
        if (command.equals("√")) {

            double number =
                Double.parseDouble(
                    display.getText()
                );

            if (number < 0) {

                display.setText("Error");

            } else {

                double result =
                    Math.sqrt(number);

                display.setText(
                    format(result)
                );
            }

            newNumber = true;

            return;
        }

        // Percentage
        if (command.equals("%")) {

            double number =
                Double.parseDouble(
                    display.getText()
                );

            double result =
                number / 100;

            display.setText(
                format(result)
            );

            newNumber = true;

            return;
        }

        // Operators
        if (
            command.equals("+") ||
            command.equals("−") ||
            command.equals("×") ||
            command.equals("÷")
        ) {

            firstNumber =
                Double.parseDouble(
                    display.getText()
                );

            operator = command;

            newNumber = true;

            return;
        }

        // Equal
        if (command.equals("=")) {

            calculate();
        }
    }

    // Calculate result
    void calculate() {

        if (operator.equals("")) {
            return;
        }

        double secondNumber =
            Double.parseDouble(
                display.getText()
            );

        double result = 0;

        switch (operator) {

            case "+":

                result =
                    firstNumber + secondNumber;

                break;

            case "−":

                result =
                    firstNumber - secondNumber;

                break;

            case "×":

                result =
                    firstNumber * secondNumber;

                break;

            case "÷":

                if (secondNumber == 0) {

                    display.setText(
                        "Cannot divide by zero"
                    );

                    operator = "";
                    newNumber = true;

                    return;
                }

                result =
                    firstNumber / secondNumber;

                break;
        }

        display.setText(
            format(result)
        );

        firstNumber = result;

        operator = "";

        newNumber = true;
    }

    // Remove unnecessary decimal
    String format(double number) {

        if (number == (long) number) {

            return String.valueOf(
                (long) number
            );
        }

        return String.valueOf(number);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    Calculator calculator =
                        new Calculator();

                    calculator.setVisible(true);
                }
            }
        );
    }
}