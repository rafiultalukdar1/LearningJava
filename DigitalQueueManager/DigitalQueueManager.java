import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class DigitalQueueManager extends JFrame
        implements ActionListener {

    JTextField nameField;
    JTextField searchField;

    JComboBox<String> serviceBox;

    JTable queueTable;
    DefaultTableModel tableModel;

    JButton addButton;
    JButton nextButton;
    JButton serveButton;
    JButton removeButton;
    JButton searchButton;
    JButton clearButton;

    JLabel currentLabel;
    JLabel waitingLabel;
    JLabel servedLabel;

    int queueNumber = 1;
    int servedCount = 0;

    DigitalQueueManager() {

        setTitle("Digital Queue Manager");
        setSize(1000, 700);

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
                "DIGITAL QUEUE MANAGER"
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
                new GridLayout(2, 3, 10, 10)
        );

        inputPanel.setOpaque(false);

        inputPanel.add(
                new JLabel("Customer Name")
        );

        inputPanel.add(
                new JLabel("Service Type")
        );

        inputPanel.add(
                new JLabel("Queue Number")
        );

        nameField = new JTextField();

        serviceBox = new JComboBox<>(
                new String[]{
                        "General Service",
                        "Account Service",
                        "Payment",
                        "Customer Support",
                        "Information"
                }
        );

        JLabel queuePreview =
                new JLabel(
                        "Auto Generated"
                );

        inputPanel.add(nameField);
        inputPanel.add(serviceBox);
        inputPanel.add(queuePreview);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Queue No",
                "Customer",
                "Service",
                "Status"
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

        queueTable =
                new JTable(tableModel);

        queueTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        queueTable.setRowHeight(32);

        queueTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(queueTable);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Bottom panel
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
                createButton("SEARCH");

        searchPanel.add(
                new JLabel("Queue No:")
        );

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        bottomPanel.add(
                searchPanel,
                BorderLayout.WEST
        );

        // Buttons
        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 3, 8, 8)
        );

        buttonPanel.setOpaque(false);

        addButton =
                createButton("ADD TO QUEUE");

        nextButton =
                createButton("CALL NEXT");

        serveButton =
                createButton("SERVE");

        removeButton =
                createButton("REMOVE");

        clearButton =
                createButton("CLEAR ALL");

        buttonPanel.add(addButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(serveButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // Statistics
        JPanel statsPanel = new JPanel(
                new GridLayout(3, 1)
        );

        statsPanel.setOpaque(false);

        currentLabel =
                new JLabel(
                        "Now Serving: None",
                        SwingConstants.CENTER
                );

        waitingLabel =
                new JLabel(
                        "Waiting: 0",
                        SwingConstants.CENTER
                );

        servedLabel =
                new JLabel(
                        "Served: 0",
                        SwingConstants.CENTER
                );

        currentLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        waitingLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        servedLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        statsPanel.add(currentLabel);
        statsPanel.add(waitingLabel);
        statsPanel.add(servedLabel);

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
                        12
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
                e.getSource() == addButton
        ) {

            addCustomer();

        } else if (
                e.getSource() == nextButton
        ) {

            callNext();

        } else if (
                e.getSource() == serveButton
        ) {

            serveCustomer();

        } else if (
                e.getSource() == removeButton
        ) {

            removeCustomer();

        } else if (
                e.getSource() == searchButton
        ) {

            searchQueue();

        } else if (
                e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Add customer
    void addCustomer() {

        String name =
                nameField.getText().trim();

        String service =
                serviceBox
                        .getSelectedItem()
                        .toString();

        if (
                name.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter customer name!"
            );

            return;
        }

        String queue =
                String.format(
                        "Q%03d",
                        queueNumber++
                );

        tableModel.addRow(
                new Object[]{
                        queue,
                        name,
                        service,
                        "Waiting"
                }
        );

        updateStatistics();

        nameField.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Customer added successfully!\n\n"
                        + "Queue Number: "
                        + queue
        );
    }

    // Call next customer
    void callNext() {

        int nextRow = -1;

        // Find first waiting customer
        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String status =
                    tableModel
                            .getValueAt(i, 3)
                            .toString();

            if (
                    status.equals("Waiting")
            ) {

                nextRow = i;
                break;
            }
        }

        if (
                nextRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No customer is waiting!"
            );

            return;
        }

        String queue =
                tableModel
                        .getValueAt(
                                nextRow,
                                0
                        )
                        .toString();

        String name =
                tableModel
                        .getValueAt(
                                nextRow,
                                1
                        )
                        .toString();

        tableModel.setValueAt(
                "Serving",
                nextRow,
                3
        );

        currentLabel.setText(
                "Now Serving: "
                        + queue
                        + " - "
                        + name
        );

        queueTable
                .setRowSelectionInterval(
                        nextRow,
                        nextRow
                );

        updateStatistics();

        JOptionPane.showMessageDialog(
                this,
                "Now Serving\n\n"
                        + queue
                        + " - "
                        + name
        );
    }

    // Serve customer
    void serveCustomer() {

        int row =
                queueTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer!"
            );

            return;
        }

        String status =
                tableModel
                        .getValueAt(row, 3)
                        .toString();

        if (
                !status.equals("Serving")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please call the customer first!"
            );

            return;
        }

        String queue =
                tableModel
                        .getValueAt(row, 0)
                        .toString();

        String name =
                tableModel
                        .getValueAt(row, 1)
                        .toString();

        tableModel.setValueAt(
                "Served",
                row,
                3
        );

        servedCount++;

        currentLabel.setText(
                "Now Serving: None"
        );

        updateStatistics();

        JOptionPane.showMessageDialog(
                this,
                queue
                        + " - "
                        + name
                        + "\n\nService completed!"
        );
    }

    // Remove customer
    void removeCustomer() {

        int row =
                queueTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer!"
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Remove this customer from queue?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.removeRow(row);

            updateStatistics();
        }
    }

    // Search queue
    void searchQueue() {

        String search =
                searchField
                        .getText()
                        .trim()
                        .toUpperCase();

        if (
                search.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter queue number!"
            );

            return;
        }

        boolean found = false;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String queue =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    queue.equals(search)
            ) {

                queueTable
                        .setRowSelectionInterval(
                                i,
                                i
                        );

                queueTable
                        .scrollRectToVisible(
                                queueTable.getCellRect(
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
                    "Queue number not found!"
            );
        }
    }

    // Clear all
    void clearAll() {

        if (
                tableModel.getRowCount() == 0
        ) {

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Clear all queue records?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            queueNumber = 1;
            servedCount = 0;

            currentLabel.setText(
                    "Now Serving: None"
            );

            updateStatistics();
        }
    }

    // Update statistics
    void updateStatistics() {

        int waiting = 0;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String status =
                    tableModel
                            .getValueAt(i, 3)
                            .toString();

            if (
                    status.equals("Waiting")
            ) {

                waiting++;
            }
        }

        waitingLabel.setText(
                "Waiting: "
                        + waiting
        );

        servedLabel.setText(
                "Served: "
                        + servedCount
        );
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        DigitalQueueManager app =
                                new DigitalQueueManager();

                        app.setVisible(true);
                    }
                }
        );
    }
}