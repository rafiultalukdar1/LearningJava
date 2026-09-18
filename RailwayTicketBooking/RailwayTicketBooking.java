import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RailwayTicketBooking extends JFrame
        implements ActionListener {

    JTextField passengerField;
    JTextField idField;
    JTextField quantityField;
    JTextField searchField;

    JComboBox<String> fromBox;
    JComboBox<String> toBox;
    JComboBox<String> trainBox;
    JComboBox<String> classBox;

    JTable ticketTable;
    DefaultTableModel tableModel;

    JButton bookButton;
    JButton cancelButton;
    JButton searchButton;
    JButton fareButton;
    JButton clearButton;

    JLabel totalTicketLabel;
    JLabel totalRevenueLabel;
    JLabel seatLabel;

    int ticketNumber = 1001;
    int availableSeats = 100;
    double totalRevenue = 0;

    RailwayTicketBooking() {

        setTitle("Railway Ticket Booking System");
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
                "RAILWAY TICKET BOOKING SYSTEM"
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
                new GridLayout(4, 4, 10, 10)
        );

        inputPanel.setOpaque(false);

        inputPanel.add(
                new JLabel("Passenger Name")
        );

        inputPanel.add(
                new JLabel("Passenger ID")
        );

        inputPanel.add(
                new JLabel("From")
        );

        inputPanel.add(
                new JLabel("To")
        );

        passengerField = new JTextField();
        idField = new JTextField();

        fromBox = new JComboBox<>(
                new String[]{
                        "Dhaka",
                        "Chittagong",
                        "Sylhet",
                        "Rajshahi",
                        "Khulna",
                        "Rangpur"
                }
        );

        toBox = new JComboBox<>(
                new String[]{
                        "Chittagong",
                        "Dhaka",
                        "Sylhet",
                        "Rajshahi",
                        "Khulna",
                        "Rangpur"
                }
        );

        inputPanel.add(passengerField);
        inputPanel.add(idField);
        inputPanel.add(fromBox);
        inputPanel.add(toBox);

        inputPanel.add(
                new JLabel("Train")
        );

        inputPanel.add(
                new JLabel("Seat Class")
        );

        inputPanel.add(
                new JLabel("Quantity")
        );

        inputPanel.add(
                new JLabel("Ticket Fare")
        );

        trainBox = new JComboBox<>(
                new String[]{
                        "Subarna Express",
                        "Sonar Bangla Express",
                        "Parabat Express",
                        "Padma Express",
                        "Ekota Express"
                }
        );

        classBox = new JComboBox<>(
                new String[]{
                        "Shuvon",
                        "Shuvon Chair",
                        "First Class",
                        "AC Seat"
                }
        );

        quantityField = new JTextField();

        JButton fareInfoButton =
                new JButton("CHECK FARE");

        fareInfoButton.addActionListener(
                e -> showFare()
        );

        inputPanel.add(trainBox);
        inputPanel.add(classBox);
        inputPanel.add(quantityField);
        inputPanel.add(fareInfoButton);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Ticket",
                "Passenger",
                "ID",
                "From",
                "To",
                "Train",
                "Class",
                "Qty",
                "Fare"
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

        ticketTable =
                new JTable(tableModel);

        ticketTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        ticketTable.setRowHeight(30);

        ticketTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(ticketTable);

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
                new JButton("SEARCH");

        searchButton.addActionListener(this);

        searchPanel.add(
                new JLabel("Ticket No:")
        );

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        bottomPanel.add(
                searchPanel,
                BorderLayout.WEST
        );

        // Buttons
        JPanel buttonPanel = new JPanel(
                new GridLayout(1, 3, 8, 8)
        );

        buttonPanel.setOpaque(false);

        bookButton =
                createButton("BOOK TICKET");

        cancelButton =
                createButton("CANCEL TICKET");

        clearButton =
                createButton("CLEAR");

        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
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

        totalTicketLabel =
                new JLabel(
                        "Tickets: 0",
                        SwingConstants.CENTER
                );

        totalRevenueLabel =
                new JLabel(
                        "Revenue: ৳0.00",
                        SwingConstants.CENTER
                );

        seatLabel =
                new JLabel(
                        "Seats: 100",
                        SwingConstants.CENTER
                );

        statsPanel.add(totalTicketLabel);
        statsPanel.add(totalRevenueLabel);
        statsPanel.add(seatLabel);

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
                e.getSource() == bookButton
        ) {

            bookTicket();

        } else if (
                e.getSource() == cancelButton
        ) {

            cancelTicket();

        } else if (
                e.getSource() == searchButton
        ) {

            searchTicket();

        } else if (
                e.getSource() == clearButton
        ) {

            clearFields();
        }
    }

    // Book ticket
    void bookTicket() {

        String passenger =
                passengerField.getText().trim();

        String id =
                idField.getText().trim();

        String from =
                fromBox.getSelectedItem()
                        .toString();

        String to =
                toBox.getSelectedItem()
                        .toString();

        String train =
                trainBox.getSelectedItem()
                        .toString();

        String seatClass =
                classBox.getSelectedItem()
                        .toString();

        String quantityText =
                quantityField.getText().trim();

        if (
                passenger.isEmpty() ||
                id.isEmpty() ||
                quantityText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        if (
                from.equals(to)
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "From and To cannot be same!"
            );

            return;
        }

        try {

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            if (
                    quantity <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid quantity!"
                );

                return;
            }

            if (
                    quantity > availableSeats
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Not enough seats available!"
                );

                return;
            }

            double farePerTicket =
                    calculateFare();

            double totalFare =
                    farePerTicket * quantity;

            String ticket =
                    String.valueOf(
                            ticketNumber++
                    );

            tableModel.addRow(
                    new Object[]{
                            ticket,
                            passenger,
                            id,
                            from,
                            to,
                            train,
                            seatClass,
                            quantity,
                            String.format(
                                    "৳%.2f",
                                    totalFare
                            )
                    }
            );

            availableSeats -= quantity;
            totalRevenue += totalFare;

            updateStatistics();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Ticket booked successfully!\n\n"
                            + "Ticket No: "
                            + ticket
                            + "\nPassenger: "
                            + passenger
                            + String.format(
                                    "\nTotal Fare: ৳%.2f",
                                    totalFare
                            )
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a number!"
            );
        }
    }

    // Calculate fare
    double calculateFare() {

        String seatClass =
                classBox.getSelectedItem()
                        .toString();

        double fare;

        if (
                seatClass.equals("Shuvon")
        ) {

            fare = 300;

        } else if (
                seatClass.equals(
                        "Shuvon Chair"
                )
        ) {

            fare = 500;

        } else if (
                seatClass.equals(
                        "First Class"
                )
        ) {

            fare = 800;

        } else {

            fare = 1200;
        }

        return fare;
    }

    // Show fare
    void showFare() {

        String seatClass =
                classBox.getSelectedItem()
                        .toString();

        double fare =
                calculateFare();

        JOptionPane.showMessageDialog(
                this,
                "Seat Class: "
                        + seatClass
                        + String.format(
                                "\nFare per ticket: ৳%.2f",
                                fare
                        ),
                "Ticket Fare",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Search ticket
    void searchTicket() {

        String ticket =
                searchField.getText().trim();

        if (
                ticket.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Ticket Number!"
            );

            return;
        }

        boolean found = false;

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingTicket =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    existingTicket.equals(ticket)
            ) {

                ticketTable
                        .setRowSelectionInterval(
                                i,
                                i
                        );

                ticketTable
                        .scrollRectToVisible(
                                ticketTable.getCellRect(
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
                    "Ticket not found!"
            );
        }
    }

    // Cancel ticket
    void cancelTicket() {

        int row =
                ticketTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a ticket!"
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Cancel selected ticket?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            int quantity =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            row,
                                            7
                                    )
                                    .toString()
                    );

            String fareText =
                    tableModel
                            .getValueAt(
                                    row,
                                    8
                            )
                            .toString();

            double fare =
                    Double.parseDouble(
                            fareText
                                    .replace(
                                            "৳",
                                            ""
                                    )
                    );

            availableSeats += quantity;
            totalRevenue -= fare;

            tableModel.removeRow(row);

            updateStatistics();

            JOptionPane.showMessageDialog(
                    this,
                    "Ticket cancelled successfully!"
            );
        }
    }

    // Update statistics
    void updateStatistics() {

        totalTicketLabel.setText(
                "Tickets: "
                        + tableModel.getRowCount()
        );

        totalRevenueLabel.setText(
                String.format(
                        "Revenue: ৳%.2f",
                        totalRevenue
                )
        );

        seatLabel.setText(
                "Available Seats: "
                        + availableSeats
        );
    }

    // Clear fields
    void clearFields() {

        passengerField.setText("");
        idField.setText("");
        quantityField.setText("");
        searchField.setText("");

        fromBox.setSelectedIndex(0);
        toBox.setSelectedIndex(0);
        trainBox.setSelectedIndex(0);
        classBox.setSelectedIndex(0);
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        RailwayTicketBooking app =
                                new RailwayTicketBooking();

                        app.setVisible(true);
                    }
                }
        );
    }
}