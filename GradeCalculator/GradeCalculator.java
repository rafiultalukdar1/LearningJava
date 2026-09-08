import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculator extends JFrame implements ActionListener {

    JTextField rollField;
    JTextField nameField;
    JTextField banglaField;
    JTextField englishField;
    JTextField mathField;
    JTextField scienceField;
    JTextField socialScienceField;
    JTextField ictField;

    JLabel resultLabel;
    JLabel gradeLabel;
    JLabel gpaLabel;

    JButton calculateButton;
    JButton clearButton;

    GradeCalculator() {

        setTitle("Smart Grade Calculator");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                20, 20, 20, 20
            )
        );

        // Title
        JLabel title = new JLabel(
            "GRADE CALCULATOR"
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 28)
        );

        title.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        mainPanel.add(
            title,
            BorderLayout.NORTH
        );

        // Input panel
        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(
            new GridLayout(8, 2, 10, 10)
        );

        rollField = new JTextField();
        nameField = new JTextField();

        banglaField = new JTextField();
        englishField = new JTextField();
        mathField = new JTextField();
        scienceField = new JTextField();
        socialScienceField = new JTextField();
        ictField = new JTextField();

        inputPanel.add(
            new JLabel("Roll Number:")
        );

        inputPanel.add(rollField);

        inputPanel.add(
            new JLabel("Student Name:")
        );

        inputPanel.add(nameField);

        inputPanel.add(
            new JLabel("Bangla:")
        );

        inputPanel.add(banglaField);

        inputPanel.add(
            new JLabel("English:")
        );

        inputPanel.add(englishField);

        inputPanel.add(
            new JLabel("Mathematics:")
        );

        inputPanel.add(mathField);

        inputPanel.add(
            new JLabel("Science:")
        );

        inputPanel.add(scienceField);

        inputPanel.add(
            new JLabel("Social Science:")
        );

        inputPanel.add(
            socialScienceField
        );

        inputPanel.add(
            new JLabel("ICT:")
        );

        inputPanel.add(ictField);

        mainPanel.add(
            inputPanel,
            BorderLayout.CENTER
        );

        // Result panel
        JPanel resultPanel = new JPanel();

        resultPanel.setLayout(
            new GridLayout(3, 1, 5, 5)
        );

        resultLabel = new JLabel(
            "Total Marks: -"
        );

        gradeLabel = new JLabel(
            "Grade: -"
        );

        gpaLabel = new JLabel(
            "GPA: -"
        );

        resultLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        gradeLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        gpaLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        resultPanel.add(resultLabel);
        resultPanel.add(gradeLabel);
        resultPanel.add(gpaLabel);

        // Buttons
        JPanel buttonPanel = new JPanel();

        calculateButton =
            new JButton("CALCULATE");

        clearButton =
            new JButton("CLEAR");

        calculateButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        // Bottom panel
        JPanel bottomPanel = new JPanel(
            new BorderLayout()
        );

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateButton) {

            calculateGrade();

        } else if (e.getSource() == clearButton) {

            clearFields();
        }
    }

    // Calculate grade
    void calculateGrade() {

        try {

            String roll =
                rollField.getText().trim();

            String name =
                nameField.getText().trim();

            if (
                roll.isEmpty() ||
                name.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter Roll and Name!"
                );

                return;
            }

            double bangla =
                Double.parseDouble(
                    banglaField.getText()
                );

            double english =
                Double.parseDouble(
                    englishField.getText()
                );

            double math =
                Double.parseDouble(
                    mathField.getText()
                );

            double science =
                Double.parseDouble(
                    scienceField.getText()
                );

            double socialScience =
                Double.parseDouble(
                    socialScienceField.getText()
                );

            double ict =
                Double.parseDouble(
                    ictField.getText()
                );

            // Check marks
            if (
                bangla < 0 || bangla > 100 ||
                english < 0 || english > 100 ||
                math < 0 || math > 100 ||
                science < 0 || science > 100 ||
                socialScience < 0 ||
                socialScience > 100 ||
                ict < 0 || ict > 100
            ) {

                JOptionPane.showMessageDialog(
                    this,
                    "Marks must be between 0 and 100!"
                );

                return;
            }

            // Calculate total
            double total =
                bangla +
                english +
                math +
                science +
                socialScience +
                ict;

            // Calculate average
            double average =
                total / 6;

            String grade;
            double gpa;

            // Grade calculation
            if (average >= 80) {

                grade = "A+";
                gpa = 5.00;

            } else if (average >= 70) {

                grade = "A";
                gpa = 4.00;

            } else if (average >= 60) {

                grade = "A-";
                gpa = 3.50;

            } else if (average >= 50) {

                grade = "B";
                gpa = 3.00;

            } else if (average >= 40) {

                grade = "C";
                gpa = 2.00;

            } else if (average >= 33) {

                grade = "D";
                gpa = 1.00;

            } else {

                grade = "F";
                gpa = 0.00;
            }

            // Show result
            resultLabel.setText(
                "Roll: " + roll +
                " | " + name +
                " | Total: " +
                total + " / 600"
            );

            gradeLabel.setText(
                "Average: " +
                average +
                " | Grade: " +
                grade
            );

            gpaLabel.setText(
                "GPA: " + gpa
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter valid marks!"
            );
        }
    }

    // Clear all fields
    void clearFields() {

        rollField.setText("");
        nameField.setText("");
        banglaField.setText("");
        englishField.setText("");
        mathField.setText("");
        scienceField.setText("");
        socialScienceField.setText("");
        ictField.setText("");

        resultLabel.setText(
            "Total Marks: -"
        );

        gradeLabel.setText(
            "Grade: -"
        );

        gpaLabel.setText(
            "GPA: -"
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            new Runnable() {

                public void run() {

                    GradeCalculator app =
                        new GradeCalculator();

                    app.setVisible(true);
                }
            }
        );
    }
}