import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class SmartGroceryCalculator extends JFrame
        implements ActionListener {

    JTextField itemField;
    JTextField priceField;
    JTextField quantityField;
    JTextField discountField;

    JTable itemTable;
    DefaultTableModel tableModel;

    JButton addButton;
    JButton removeButton;
    JButton clearButton;
    JButton calculateButton;

    JLabel subtotalLabel;
    JLabel discountLabel;
    JLabel totalLabel;
    JLabel itemCountLabel;

    SmartGroceryCalculator() {

        setTitle("Smart Grocery Calculator");
        setSize(900, 650);

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
                "SMART GROCERY CALCULATOR"
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
                new GridLayout(2, 4, 10, 10)
        );

        inputPanel.setOpaque(false);

        inputPanel.add(
                new JLabel("Item Name")
        );

        inputPanel.add(
                new JLabel("Price")
        );

        inputPanel.add(
                new JLabel("Quantity")
        );

        inputPanel.add(
                new JLabel("Discount %")
        );

        itemField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        discountField = new JTextField();

        discountField.setText("0");

        inputPanel.add(itemField);
        inputPanel.add(priceField);
        inputPanel.add(quantityField);
        inputPanel.add(discountField);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Item",
                "Price",
                "Quantity",
                "Total"
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
                        15
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

        // Buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        addButton =
                createButton("ADD ITEM");

        removeButton =
                createButton("REMOVE ITEM");

        calculateButton =
                createButton("CALCULATE");

        clearButton =
                createButton("CLEAR ALL");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.NORTH
        );

        // Result panel
        JPanel resultPanel = new JPanel(
                new GridLayout(2, 2, 20, 8)
        );

        resultPanel.setOpaque(false);

        itemCountLabel =
                new JLabel(
                        "Items: 0"
                );

        subtotalLabel =
                new JLabel(
                        "Subtotal: ৳0.00"
                );

        discountLabel =
                new JLabel(
                        "Discount: ৳0.00"
                );

        totalLabel =
                new JLabel(
                        "Final Total: ৳0.00"
                );

        itemCountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        subtotalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        discountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        resultPanel.add(itemCountLabel);
        resultPanel.add(subtotalLabel);
        resultPanel.add(discountLabel);
        resultPanel.add(totalLabel);

        bottomPanel.add(
                resultPanel,
                BorderLayout.CENTER
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
                e.getSource() == addButton
        ) {

            addItem();

        } else if (
                e.getSource() == removeButton
        ) {

            removeItem();

        } else if (
                e.getSource() == calculateButton
        ) {

            calculateTotal();

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

        String priceText =
                priceField.getText().trim();

        String quantityText =
                quantityField.getText().trim();

        if (
                item.isEmpty() ||
                priceText.isEmpty() ||
                quantityText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill Item, Price and Quantity!"
            );

            return;
        }

        try {

            double price =
                    Double.parseDouble(
                            priceText
                    );

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            if (
                    price <= 0 ||
                    quantity <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price and Quantity must be positive!"
                );

                return;
            }

            double total =
                    price * quantity;

            tableModel.addRow(
                    new Object[]{
                            item,
                            String.format(
                                    "৳%.2f",
                                    price
                            ),
                            quantity,
                            String.format(
                                    "৳%.2f",
                                    total
                            )
                    }
            );

            updateItemCount();
            calculateTotal();
            clearInputFields();

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price and Quantity must be valid numbers!"
            );
        }
    }

    // Calculate total
    void calculateTotal() {

        double subtotal = 0;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String totalText =
                    tableModel
                            .getValueAt(i, 3)
                            .toString();

            double itemTotal =
                    Double.parseDouble(
                            totalText
                                    .replace(
                                            "৳",
                                            ""
                                    )
                    );

            subtotal += itemTotal;
        }

        double discountPercent = 0;

        try {

            if (
                    !discountField
                            .getText()
                            .trim()
                            .isEmpty()
            ) {

                discountPercent =
                        Double.parseDouble(
                                discountField
                                        .getText()
                                        .trim()
                        );
            }

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid discount!"
            );

            return;
        }

        if (
                discountPercent < 0 ||
                discountPercent > 100
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Discount must be between 0 and 100!"
            );

            return;
        }

        double discountAmount =
                subtotal
                        * discountPercent
                        / 100;

        double finalTotal =
                subtotal
                        - discountAmount;

        subtotalLabel.setText(
                String.format(
                        "Subtotal: ৳%.2f",
                        subtotal
                )
        );

        discountLabel.setText(
                String.format(
                        "Discount: ৳%.2f",
                        discountAmount
                )
        );

        totalLabel.setText(
                String.format(
                        "Final Total: ৳%.2f",
                        finalTotal
                )
        );

        updateItemCount();
    }

    // Remove item
    void removeItem() {

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

        tableModel.removeRow(row);

        calculateTotal();
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
                        "Clear all items?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            discountField.setText("0");

            calculateTotal();
        }
    }

    // Update item count
    void updateItemCount() {

        itemCountLabel.setText(
                "Items: "
                        + tableModel.getRowCount()
        );
    }

    // Clear input fields
    void clearInputFields() {

        itemField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        SmartGroceryCalculator app =
                                new SmartGroceryCalculator();

                        app.setVisible(true);
                    }
                }
        );
    }
}