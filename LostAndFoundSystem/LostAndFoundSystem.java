import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LostAndFoundSystem extends JFrame
        implements ActionListener {

    JTextField itemField;
    JTextField ownerField;
    JTextField locationField;
    JTextField searchField;

    JComboBox<String> categoryBox;
    JComboBox<String> typeBox;

    JTable itemTable;
    DefaultTableModel tableModel;

    JButton addButton;
    JButton foundButton;
    JButton returnedButton;
    JButton searchButton;
    JButton deleteButton;
    JButton clearButton;

    JLabel totalLabel;
    JLabel lostLabel;
    JLabel foundLabel;
    JLabel returnedLabel;

    LostAndFoundSystem() {

        setTitle("Lost & Found System");
        setSize(1050, 700);

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
                "LOST & FOUND SYSTEM"
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
                new GridLayout(2, 5, 10, 10)
        );

        inputPanel.setOpaque(false);

        inputPanel.add(
                new JLabel("Item Name")
        );

        inputPanel.add(
                new JLabel("Owner Name")
        );

        inputPanel.add(
                new JLabel("Category")
        );

        inputPanel.add(
                new JLabel("Location")
        );

        inputPanel.add(
                new JLabel("Type")
        );

        itemField = new JTextField();
        ownerField = new JTextField();
        locationField = new JTextField();

        categoryBox = new JComboBox<>(
                new String[]{
                        "Mobile",
                        "Wallet",
                        "Bag",
                        "ID Card",
                        "Keys",
                        "Book",
                        "Other"
                }
        );

        typeBox = new JComboBox<>(
                new String[]{
                        "Lost",
                        "Found"
                }
        );

        inputPanel.add(itemField);
        inputPanel.add(ownerField);
        inputPanel.add(categoryBox);
        inputPanel.add(locationField);
        inputPanel.add(typeBox);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Item",
                "Owner",
                "Category",
                "Location",
                "Type",
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

        itemTable =
                new JTable(tableModel);

        itemTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        itemTable.setRowHeight(32);

        itemTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(itemTable);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // Bottom panel
        JPanel bottomPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        bottomPanel.setOpaque(false);

        // Search panel
        JPanel searchPanel = new JPanel();

        searchPanel.setOpaque(false);

        searchField =
                new JTextField(10);

        searchButton =
                createButton("SEARCH");

        searchPanel.add(
                new JLabel("Search Item:")
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
                createButton("ADD ITEM");

        foundButton =
                createButton("MARK FOUND");

        returnedButton =
                createButton("MARK RETURNED");

        deleteButton =
                createButton("DELETE");

        clearButton =
                createButton("CLEAR ALL");

        buttonPanel.add(addButton);
        buttonPanel.add(foundButton);
        buttonPanel.add(returnedButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // Statistics
        JPanel statsPanel = new JPanel(
                new GridLayout(4, 1)
        );

        statsPanel.setOpaque(false);

        totalLabel =
                new JLabel(
                        "Total: 0",
                        SwingConstants.CENTER
                );

        lostLabel =
                new JLabel(
                        "Lost: 0",
                        SwingConstants.CENTER
                );

        foundLabel =
                new JLabel(
                        "Found: 0",
                        SwingConstants.CENTER
                );

        returnedLabel =
                new JLabel(
                        "Returned: 0",
                        SwingConstants.CENTER
                );

        statsPanel.add(totalLabel);
        statsPanel.add(lostLabel);
        statsPanel.add(foundLabel);
        statsPanel.add(returnedLabel);

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
    JButton createButton(
            String text
    ) {

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

            addItem();

        } else if (
                e.getSource() == foundButton
        ) {

            markFound();

        } else if (
                e.getSource() == returnedButton
        ) {

            markReturned();

        } else if (
                e.getSource() == searchButton
        ) {

            searchItem();

        } else if (
                e.getSource() == deleteButton
        ) {

            deleteItem();

        } else if (
                e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Add item
    void addItem() {

        String item =
                itemField.getText().trim();

        String owner =
                ownerField.getText().trim();

        String category =
                categoryBox
                        .getSelectedItem()
                        .toString();

        String location =
                locationField.getText().trim();

        String type =
                typeBox
                        .getSelectedItem()
                        .toString();

        if (
                item.isEmpty() ||
                owner.isEmpty() ||
                location.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        String status;

        if (
                type.equals("Lost")
        ) {

            status = "Lost";

        } else {

            status = "Found";
        }

        tableModel.addRow(
                new Object[]{
                        item,
                        owner,
                        category,
                        location,
                        type,
                        status
                }
        );

        updateStatistics();
        clearFields();

        JOptionPane.showMessageDialog(
                this,
                "Item added successfully!"
        );
    }

    // Mark found
    void markFound() {

        int row =
                itemTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an item!"
            );

            return;
        }

        String status =
                tableModel
                        .getValueAt(row, 5)
                        .toString();

        if (
                status.equals("Returned")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This item has already been returned!"
            );

            return;
        }

        tableModel.setValueAt(
                "Found",
                row,
                5
        );

        tableModel.setValueAt(
                "Found",
                row,
                4
        );

        updateStatistics();

        JOptionPane.showMessageDialog(
                this,
                "Item marked as FOUND!"
        );
    }

    // Mark returned
    void markReturned() {

        int row =
                itemTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an item!"
            );

            return;
        }

        String status =
                tableModel
                        .getValueAt(row, 5)
                        .toString();

        if (
                status.equals("Lost")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Item must be found before returning!"
            );

            return;
        }

        if (
                status.equals("Returned")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This item is already returned!"
            );

            return;
        }

        tableModel.setValueAt(
                "Returned",
                row,
                5
        );

        updateStatistics();

        JOptionPane.showMessageDialog(
                this,
                "Item marked as RETURNED!"
        );
    }

    // Search item
    void searchItem() {

        String search =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        if (
                search.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter an item name!"
            );

            return;
        }

        boolean found = false;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String item =
                    tableModel
                            .getValueAt(i, 0)
                            .toString()
                            .toLowerCase();

            if (
                    item.contains(search)
            ) {

                itemTable
                        .setRowSelectionInterval(
                                i,
                                i
                        );

                itemTable
                        .scrollRectToVisible(
                                itemTable.getCellRect(
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
                    "Item not found!"
            );
        }
    }

    // Delete item
    void deleteItem() {

        int row =
                itemTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an item!"
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected item?",
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
                        "Clear all records?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            updateStatistics();
        }
    }

    // Update statistics
    void updateStatistics() {

        int total =
                tableModel.getRowCount();

        int lost = 0;
        int found = 0;
        int returned = 0;

        for (
                int i = 0;
                i < total;
                i++
        ) {

            String status =
                    tableModel
                            .getValueAt(i, 5)
                            .toString();

            if (
                    status.equals("Lost")
            ) {

                lost++;

            } else if (
                    status.equals("Found")
            ) {

                found++;

            } else if (
                    status.equals("Returned")
            ) {

                returned++;
            }
        }

        totalLabel.setText(
                "Total: " + total
        );

        lostLabel.setText(
                "Lost: " + lost
        );

        foundLabel.setText(
                "Found: " + found
        );

        returnedLabel.setText(
                "Returned: " + returned
        );
    }

    // Clear fields
    void clearFields() {

        itemField.setText("");
        ownerField.setText("");
        locationField.setText("");
        searchField.setText("");

        categoryBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        LostAndFoundSystem app =
                                new LostAndFoundSystem();

                        app.setVisible(true);
                    }
                }
        );
    }
}