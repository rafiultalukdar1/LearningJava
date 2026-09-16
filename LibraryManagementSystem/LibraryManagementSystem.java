import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryManagementSystem extends JFrame
        implements ActionListener {

    JTextField idField;
    JTextField titleField;
    JTextField authorField;
    JTextField searchField;

    JComboBox<String> categoryBox;

    JTable bookTable;
    DefaultTableModel tableModel;

    JButton addButton;
    JButton borrowButton;
    JButton returnButton;
    JButton searchButton;
    JButton deleteButton;
    JButton historyButton;
    JButton clearButton;

    JLabel totalBookLabel;
    JLabel availableBookLabel;

    ArrayList<String> transactions =
            new ArrayList<>();

    LibraryManagementSystem() {

        setTitle("Library Management System");
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
                "LIBRARY MANAGEMENT SYSTEM"
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
                new JLabel("Book ID")
        );

        inputPanel.add(
                new JLabel("Book Title")
        );

        inputPanel.add(
                new JLabel("Author")
        );

        inputPanel.add(
                new JLabel("Category")
        );

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();

        categoryBox = new JComboBox<>(
                new String[]{
                        "Programming",
                        "Science",
                        "History",
                        "Novel",
                        "Database",
                        "Networking"
                }
        );

        inputPanel.add(idField);
        inputPanel.add(titleField);
        inputPanel.add(authorField);
        inputPanel.add(categoryBox);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Category",
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

        bookTable =
                new JTable(tableModel);

        bookTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        bookTable.setRowHeight(32);

        bookTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(bookTable);

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
                new JButton("SEARCH");

        searchButton.addActionListener(this);

        searchPanel.add(
                new JLabel("Search Book ID:")
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

        addButton =
                createButton("ADD BOOK");

        borrowButton =
                createButton("BORROW");

        returnButton =
                createButton("RETURN");

        deleteButton =
                createButton("DELETE");

        historyButton =
                createButton("HISTORY");

        clearButton =
                createButton("CLEAR ALL");

        buttonPanel.add(addButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(historyButton);
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

        totalBookLabel =
                new JLabel(
                        "Total Books: 0",
                        SwingConstants.CENTER
                );

        availableBookLabel =
                new JLabel(
                        "Available: 0",
                        SwingConstants.CENTER
                );

        totalBookLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        availableBookLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        statsPanel.add(totalBookLabel);
        statsPanel.add(availableBookLabel);

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

            addBook();

        } else if (
                e.getSource() == borrowButton
        ) {

            borrowBook();

        } else if (
                e.getSource() == returnButton
        ) {

            returnBook();

        } else if (
                e.getSource() == searchButton
        ) {

            searchBook();

        } else if (
                e.getSource() == deleteButton
        ) {

            deleteBook();

        } else if (
                e.getSource() == historyButton
        ) {

            showHistory();

        } else if (
                e.getSource() == clearButton
        ) {

            clearAll();
        }
    }

    // Add book
    void addBook() {

        String id =
                idField.getText().trim();

        String title =
                titleField.getText().trim();

        String author =
                authorField.getText().trim();

        String category =
                categoryBox
                        .getSelectedItem()
                        .toString();

        if (
                id.isEmpty() ||
                title.isEmpty() ||
                author.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        // Check duplicate ID
        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingId =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    existingId.equals(id)
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book ID already exists!"
                );

                return;
            }
        }

        tableModel.addRow(
                new Object[]{
                        id,
                        title,
                        author,
                        category,
                        "Available"
                }
        );

        transactions.add(
                "Book added: "
                        + title
        );

        updateStats();
        clearFields();

        JOptionPane.showMessageDialog(
                this,
                "Book added successfully!"
        );
    }

    // Find book
    int findBook(
            String id
    ) {

        for (
                int i = 0;
                i < tableModel.getRowCount();
                i++
        ) {

            String existingId =
                    tableModel
                            .getValueAt(i, 0)
                            .toString();

            if (
                    existingId.equals(id)
            ) {

                return i;
            }
        }

        return -1;
    }

    // Borrow book
    void borrowBook() {

        int row =
                bookTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book!"
            );

            return;
        }

        String status =
                tableModel
                        .getValueAt(row, 4)
                        .toString();

        if (
                status.equals("Borrowed")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This book is already borrowed!"
            );

            return;
        }

        String bookId =
                tableModel
                        .getValueAt(row, 0)
                        .toString();

        String bookTitle =
                tableModel
                        .getValueAt(row, 1)
                        .toString();

        tableModel.setValueAt(
                "Borrowed",
                row,
                4
        );

        transactions.add(
                "Book borrowed: "
                        + bookTitle
                        + " ("
                        + bookId
                        + ")"
        );

        updateStats();

        JOptionPane.showMessageDialog(
                this,
                "Book borrowed successfully!"
        );
    }

    // Return book
    void returnBook() {

        int row =
                bookTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book!"
            );

            return;
        }

        String status =
                tableModel
                        .getValueAt(row, 4)
                        .toString();

        if (
                status.equals("Available")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This book is already available!"
            );

            return;
        }

        String bookId =
                tableModel
                        .getValueAt(row, 0)
                        .toString();

        String bookTitle =
                tableModel
                        .getValueAt(row, 1)
                        .toString();

        tableModel.setValueAt(
                "Available",
                row,
                4
        );

        transactions.add(
                "Book returned: "
                        + bookTitle
                        + " ("
                        + bookId
                        + ")"
        );

        updateStats();

        JOptionPane.showMessageDialog(
                this,
                "Book returned successfully!"
        );
    }

    // Search book
    void searchBook() {

        String id =
                searchField.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a Book ID!"
            );

            return;
        }

        int row =
                findBook(id);

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book not found!"
            );

            return;
        }

        bookTable.setRowSelectionInterval(
                row,
                row
        );

        bookTable.scrollRectToVisible(
                bookTable.getCellRect(
                        row,
                        0,
                        true
                )
        );
    }

    // Delete book
    void deleteBook() {

        int row =
                bookTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book!"
            );

            return;
        }

        String title =
                tableModel
                        .getValueAt(row, 1)
                        .toString();

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this book?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.removeRow(row);

            transactions.add(
                    "Book deleted: "
                            + title
            );

            updateStats();
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
                "LIBRARY TRANSACTION HISTORY\n\n"
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
                new Dimension(500, 300)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Transaction History",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Clear all books
    void clearAll() {

        if (
                tableModel.getRowCount() == 0
        ) {

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete all books?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            tableModel.setRowCount(0);

            transactions.add(
                    "All books cleared"
            );

            updateStats();
        }
    }

    // Update statistics
    void updateStats() {

        int total =
                tableModel.getRowCount();

        int available = 0;

        for (
                int i = 0;
                i < total;
                i++
        ) {

            String status =
                    tableModel
                            .getValueAt(i, 4)
                            .toString();

            if (
                    status.equals("Available")
            ) {

                available++;
            }
        }

        totalBookLabel.setText(
                "Total Books: "
                        + total
        );

        availableBookLabel.setText(
                "Available: "
                        + available
        );
    }

    // Clear fields
    void clearFields() {

        idField.setText("");
        titleField.setText("");
        authorField.setText("");

        categoryBox.setSelectedIndex(0);
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        LibraryManagementSystem app =
                                new LibraryManagementSystem();

                        app.setVisible(true);
                    }
                }
        );
    }
}