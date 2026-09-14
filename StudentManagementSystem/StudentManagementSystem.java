import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementSystem extends JFrame
        implements ActionListener {

    JTextField rollField;
    JTextField nameField;
    JTextField ageField;
    JTextField gpaField;
    JTextField searchField;

    JComboBox<String> departmentBox;

    JTable studentTable;
    DefaultTableModel tableModel;

    JButton addButton;
    JButton searchButton;
    JButton deleteButton;
    JButton clearButton;

    JLabel totalLabel;

    StudentManagementSystem() {

        setTitle("Student Management System");
        setSize(850, 650);
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
                        20, 20, 20, 20
                )
        );

        // Title
        JLabel title = new JLabel(
                "STUDENT MANAGEMENT SYSTEM"
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
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
                new GridLayout(2, 5, 10, 10)
        );

        inputPanel.setOpaque(false);

        // Labels
        inputPanel.add(
                new JLabel("Roll Number")
        );

        inputPanel.add(
                new JLabel("Student Name")
        );

        inputPanel.add(
                new JLabel("Department")
        );

        inputPanel.add(
                new JLabel("Age")
        );

        inputPanel.add(
                new JLabel("GPA")
        );

        // Fields
        rollField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        gpaField = new JTextField();

        departmentBox = new JComboBox<>(
                new String[]{
                        "CSE",
                        "EEE",
                        "Civil",
                        "Mechanical",
                        "Architecture",
                        "Electronics"
                }
        );

        inputPanel.add(rollField);
        inputPanel.add(nameField);
        inputPanel.add(departmentBox);
        inputPanel.add(ageField);
        inputPanel.add(gpaField);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Roll",
                "Name",
                "Department",
                "Age",
                "GPA"
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

        studentTable =
                new JTable(tableModel);

        studentTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        studentTable.setRowHeight(32);

        studentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(studentTable);

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

        searchField = new JTextField(12);

        searchButton = new JButton(
                "SEARCH ROLL"
        );

        searchButton.addActionListener(this);

        searchPanel.add(
                new JLabel("Search:")
        );

        searchPanel.add(searchField);

        searchPanel.add(searchButton);

        bottomPanel.add(
                searchPanel,
                BorderLayout.WEST
        );

        // Buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        addButton = new JButton(
                "ADD STUDENT"
        );

        deleteButton = new JButton(
                "DELETE"
        );

        clearButton = new JButton(
                "CLEAR ALL"
        );

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // Total
        totalLabel = new JLabel(
                "Total Students: 0"
        );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        bottomPanel.add(
                totalLabel,
                BorderLayout.EAST
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

        // Add student
        if (
                e.getSource() == addButton
        ) {

            addStudent();
        }

        // Search student
        else if (
                e.getSource() == searchButton
        ) {

            searchStudent();
        }

        // Delete student
        else if (
                e.getSource() == deleteButton
        ) {

            deleteStudent();
        }

        // Clear all
        else if (
                e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Add student
    void addStudent() {

        String roll =
                rollField.getText().trim();

        String name =
                nameField.getText().trim();

        String department =
                departmentBox
                        .getSelectedItem()
                        .toString();

        String ageText =
                ageField.getText().trim();

        String gpaText =
                gpaField.getText().trim();

        if (
                roll.isEmpty() ||
                name.isEmpty() ||
                ageText.isEmpty() ||
                gpaText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            int age =
                    Integer.parseInt(ageText);

            double gpa =
                    Double.parseDouble(gpaText);

            if (
                    age <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid age!"
                );

                return;
            }

            if (
                    gpa < 0 ||
                    gpa > 4
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "GPA must be between 0 and 4!"
                );

                return;
            }

            // Check duplicate roll
            for (
                    int i = 0;
                    i < tableModel.getRowCount();
                    i++
            ) {

                String existingRoll =
                        tableModel
                                .getValueAt(i, 0)
                                .toString();

                if (
                        existingRoll.equals(roll)
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "This roll already exists!"
                    );

                    return;
                }
            }

            // Add student
            tableModel.addRow(
                    new Object[]{
                            roll,
                            name,
                            department,
                            age,
                            String.format(
                                    "%.2f",
                                    gpa
                            )
                    }
            );

            updateTotal();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age and GPA must be numbers!"
            );
        }
    }

    // Search student
    void searchStudent() {

        String roll =
                searchField.getText().trim();

        if (
                roll.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a roll number!"
            );

            return;
        }

        boolean found = false;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingRoll =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    existingRoll.equals(roll)
            ) {

                studentTable
                        .setRowSelectionInterval(
                                i,
                                i
                        );

                studentTable
                        .scrollRectToVisible(
                                studentTable
                                        .getCellRect(
                                                i,
                                                0,
                                                true
                                        )
                        );

                found = true;

                break;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student not found!"
            );
        }
    }

    // Delete selected student
    void deleteStudent() {

        int row =
                studentTable
                        .getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student!"
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected student?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.removeRow(row);

            updateTotal();
        }
    }

    // Clear all students
    void clearAll() {

        if (
                tableModel.getRowCount() == 0
        ) {

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete all students?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            updateTotal();
        }
    }

    // Clear input fields
    void clearFields() {

        rollField.setText("");
        nameField.setText("");
        ageField.setText("");
        gpaField.setText("");

        departmentBox
                .setSelectedIndex(0);
    }

    // Update student count
    void updateTotal() {

        totalLabel.setText(
                "Total Students: "
                        + tableModel.getRowCount()
        );
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        StudentManagementSystem app =
                                new StudentManagementSystem();

                        app.setVisible(true);
                    }
                }
        );
    }
}