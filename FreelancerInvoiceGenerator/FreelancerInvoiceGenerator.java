import javax.swing.*;
import java.awt.*;

public class FreelancerInvoiceGenerator extends JFrame {

    JTextField freelancerField;
    JTextField clientField;
    JTextField projectField;
    JTextField serviceField;
    JTextField quantityField;
    JTextField rateField;
    JTextField taxField;
    JTextField discountField;

    JTextArea invoiceArea;

    FreelancerInvoiceGenerator() {

        setTitle("Freelancer Invoice Generator");
        setSize(1000, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();
    }

    void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        mainPanel.setBackground(
                new Color(15, 18, 30)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        // Header
        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "✦ FREELANCER INVOICE"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                Color.WHITE
        );

        JLabel status =
                new JLabel(
                        "● DRAFT"
                );

        status.setForeground(
                new Color(
                        255,
                        190,
                        70
                )
        );

        status.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                status,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // Main content
        JPanel content =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        content.setOpaque(false);

        content.add(
                createFormPanel()
        );

        content.add(
                createPreviewPanel()
        );

        mainPanel.add(
                content,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    JPanel createFormPanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        panel.setLayout(
                new GridLayout(
                        8,
                        1,
                        10,
                        10
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        freelancerField =
                createField(
                        "Freelancer Name"
                );

        clientField =
                createField(
                        "Client Name"
                );

        projectField =
                createField(
                        "Project Name"
                );

        serviceField =
                createField(
                        "Service / Item"
                );

        quantityField =
                createField(
                        "Quantity"
                );

        rateField =
                createField(
                        "Rate"
                );

        taxField =
                createField(
                        "Tax (%)"
                );

        discountField =
                createField(
                        "Discount (%)"
                );

        panel.add(freelancerField);
        panel.add(clientField);
        panel.add(projectField);
        panel.add(serviceField);
        panel.add(quantityField);
        panel.add(rateField);
        panel.add(taxField);
        panel.add(discountField);

        return panel;
    }

    JTextField createField(
            String title
    ) {

        JTextField field =
                new JTextField();

        field.setForeground(
                Color.WHITE
        );

        field.setBackground(
                new Color(
                        35,
                        40,
                        58
                )
        );

        field.setCaretColor(
                Color.WHITE
        );

        field.setBorder(
                BorderFactory.createTitledBorder(
                        title
                )
        );

        return field;
    }

    JPanel createPreviewPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                new Color(
                        25,
                        29,
                        45
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel previewTitle =
                new JLabel(
                        "INVOICE PREVIEW"
                );

        previewTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        previewTitle.setForeground(
                Color.WHITE
        );

        invoiceArea =
                new JTextArea();

        invoiceArea.setEditable(false);

        invoiceArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        invoiceArea.setForeground(
                Color.WHITE
        );

        invoiceArea.setBackground(
                new Color(
                        18,
                        21,
                        34
                )
        );

        invoiceArea.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        invoiceArea.setText(
                "================================\n"
                        + "        FREELANCER INVOICE\n"
                        + "================================\n\n"
                        + "Freelancer: -\n"
                        + "Client:     -\n"
                        + "Project:    -\n\n"
                        + "Service     Qty     Rate     Total\n"
                        + "--------------------------------\n"
                        + "No items yet...\n\n"
                        + "--------------------------------\n"
                        + "Subtotal:   $0.00\n"
                        + "Tax:        $0.00\n"
                        + "Discount:   $0.00\n"
                        + "--------------------------------\n"
                        + "TOTAL:      $0.00\n"
                        + "================================"
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        invoiceArea
                );

        JButton generateButton =
                new JButton(
                        "GENERATE INVOICE"
                );

        JButton clearButton =
                new JButton(
                        "CLEAR"
                );

        styleButton(
                generateButton,
                new Color(
                        124,
                        92,
                        255
                )
        );

        styleButton(
                clearButton,
                new Color(
                        55,
                        61,
                        82
                )
        );

        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        buttons.setOpaque(false);

        buttons.add(
                generateButton
        );

        buttons.add(
                clearButton
        );

        generateButton.addActionListener(
                e -> generateInvoice()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        panel.add(
                previewTitle,
                BorderLayout.NORTH
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        panel.add(
                buttons,
                BorderLayout.SOUTH
        );

        return panel;
    }

    void styleButton(
            JButton button,
            Color color
    ) {

        button.setBackground(color);

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        15,
                        12,
                        15
                )
        );
    }

    void generateInvoice() {

        String freelancer =
                freelancerField.getText();

        String client =
                clientField.getText();

        String project =
                projectField.getText();

        String service =
                serviceField.getText();

        invoiceArea.setText(
                "================================\n"
                        + "        FREELANCER INVOICE\n"
                        + "================================\n\n"
                        + "Freelancer: "
                        + freelancer
                        + "\n"
                        + "Client:     "
                        + client
                        + "\n"
                        + "Project:    "
                        + project
                        + "\n\n"
                        + "Service: "
                        + service
                        + "\n\n"
                        + "Invoice calculation will be added\n"
                        + "in the next step.\n"
                        + "================================"
        );
    }

    void clearForm() {

        freelancerField.setText("");
        clientField.setText("");
        projectField.setText("");
        serviceField.setText("");
        quantityField.setText("");
        rateField.setText("");
        taxField.setText("");
        discountField.setText("");

        invoiceArea.setText(
                "INVOICE PREVIEW\n\n"
                        + "No invoice generated yet."
        );
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    FreelancerInvoiceGenerator app =
                            new FreelancerInvoiceGenerator();

                    app.setVisible(true);
                }
        );
    }
}