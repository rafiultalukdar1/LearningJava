import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SmartLockerSystem extends JFrame {

    ArrayList<Locker> lockers = new ArrayList<>();

    JPanel lockerPanel;
    JLabel totalLabel;
    JLabel availableLabel;
    JLabel occupiedLabel;
    JLabel statusLabel;

    JTextField userField;
    JTextField itemField;
    JPasswordField pinField;
    JComboBox<String> lockerBox;

    Color background = new Color(15, 18, 30);
    Color cardColor = new Color(25, 29, 45);
    Color sidebarColor = new Color(20, 23, 38);
    Color purple = new Color(124, 92, 255);
    Color textColor = new Color(235, 238, 245);
    Color secondaryText = new Color(150, 157, 180);
    Color green = new Color(45, 210, 130);
    Color red = new Color(255, 85, 105);

    SmartLockerSystem() {

        for (int i = 1; i <= 12; i++) {
            lockers.add(
                    new Locker(
                            "Locker-" + i
                    )
            );
        }

        setTitle("Smart Locker System");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();
        updateDashboard();
    }

    void buildUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(background);

        main.add(createSidebar(), BorderLayout.WEST);
        main.add(createDashboard(), BorderLayout.CENTER);

        add(main);
    }

    JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 750));
        sidebar.setBackground(sidebarColor);
        sidebar.setLayout(new BoxLayout(
                sidebar,
                BoxLayout.Y_AXIS
        ));

        JLabel logo = new JLabel("  🔐 SMART LOCKER");

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        logo.setForeground(textColor);
        logo.setBorder(
                new EmptyBorder(
                        30,
                        15,
                        40,
                        10
                )
        );

        sidebar.add(logo);

        sidebar.add(
                createMenuButton("▣   Dashboard")
        );

        sidebar.add(
                createMenuButton("▤   All Lockers")
        );

        sidebar.add(
                createMenuButton("◉   Users")
        );

        sidebar.add(
                createMenuButton("⌕   Search")
        );

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(
                createMenuButton("⚙   Settings")
        );

        JLabel version =
                new JLabel("  v1.0.0");

        version.setForeground(
                secondaryText
        );

        version.setBorder(
                new EmptyBorder(
                        20,
                        15,
                        25,
                        10
                )
        );

        sidebar.add(version);

        return sidebar;
    }

    JButton createMenuButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        220,
                        50
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setForeground(
                secondaryText
        );

        button.setBackground(
                sidebarColor
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        10
                )
        );

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        button.addMouseListener(
                new MouseAdapter() {

                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setForeground(
                                textColor
                        );
                    }

                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setForeground(
                                secondaryText
                        );
                    }
                }
        );

        return button;
    }

    JPanel createDashboard() {

        JPanel dashboard = new JPanel(
                new BorderLayout(
                        20,
                        20
                )
        );

        dashboard.setBackground(background);

        dashboard.setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        dashboard.add(
                createHeader(),
                BorderLayout.NORTH
        );

        dashboard.add(
                createCenter(),
                BorderLayout.CENTER
        );

        return dashboard;
    }

    JPanel createHeader() {

        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setOpaque(false);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);
        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Welcome back 👋"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(textColor);

        JLabel subtitle =
                new JLabel(
                        "Manage your smart lockers easily"
                );

        subtitle.setForeground(
                secondaryText
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        titlePanel.add(title);
        titlePanel.add(
                Box.createVerticalStrut(5)
        );
        titlePanel.add(subtitle);

        JPanel onlinePanel =
                new JPanel();

        onlinePanel.setOpaque(false);

        JLabel online =
                new JLabel(
                        "● SYSTEM ONLINE"
                );

        online.setForeground(green);

        online.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        onlinePanel.add(online);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.add(
                onlinePanel,
                BorderLayout.EAST
        );

        return header;
    }

    JPanel createCenter() {

        JPanel center = new JPanel(
                new BorderLayout(
                        20,
                        20
                )
        );

        center.setOpaque(false);

        center.add(
                createStats(),
                BorderLayout.NORTH
        );

        lockerPanel = new JPanel(
                new GridLayout(
                        3,
                        4,
                        15,
                        15
                )
        );

        lockerPanel.setOpaque(false);

        JScrollPane scroll =
                new JScrollPane(
                        lockerPanel
                );

        scroll.setBorder(null);

        scroll.getViewport()
                .setBackground(background);

        center.add(
                scroll,
                BorderLayout.CENTER
        );

        center.add(
                createAssignPanel(),
                BorderLayout.SOUTH
        );

        return center;
    }

    JPanel createStats() {

        JPanel stats = new JPanel(
                new GridLayout(
                        1,
                        3,
                        15,
                        15
                )
        );

        stats.setOpaque(false);

        totalLabel =
                new JLabel("12");

        availableLabel =
                new JLabel("12");

        occupiedLabel =
                new JLabel("0");

        stats.add(
                createStatCard(
                        "TOTAL LOCKERS",
                        totalLabel,
                        "All lockers"
                )
        );

        stats.add(
                createStatCard(
                        "AVAILABLE",
                        availableLabel,
                        "Ready to use"
                )
        );

        stats.add(
                createStatCard(
                        "OCCUPIED",
                        occupiedLabel,
                        "Currently active"
                )
        );

        return stats;
    }

    JPanel createStatCard(
            String title,
            JLabel number,
            String subtitle
    ) {

        JPanel card = new JPanel();

        card.setBackground(cardColor);

        card.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                secondaryText
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        number.setForeground(textColor);

        number.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        JLabel sub =
                new JLabel(subtitle);

        sub.setForeground(
                secondaryText
        );

        sub.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        card.add(titleLabel);
        card.add(
                Box.createVerticalStrut(5)
        );
        card.add(number);
        card.add(sub);

        return card;
    }

    JPanel createAssignPanel() {

        JPanel panel = new JPanel(
                new BorderLayout(
                        15,
                        10
                )
        );

        panel.setBackground(cardColor);

        panel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        JPanel fields =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                10,
                                5
                        )
                );

        fields.setOpaque(false);

        userField =
                createTextField(
                        "User name"
                );

        itemField =
                createTextField(
                        "Item name"
                );

        pinField =
                new JPasswordField();

        pinField.setBackground(
                new Color(35, 40, 58)
        );

        pinField.setForeground(textColor);

        pinField.setBorder(
                BorderFactory.createTitledBorder(
                        "PIN"
                )
        );

        lockerBox =
                new JComboBox<>();

        for (Locker locker : lockers) {

            lockerBox.addItem(
                    locker.number
            );
        }

        styleComboBox(lockerBox);

        fields.add(userField);
        fields.add(itemField);
        fields.add(lockerBox);
        fields.add(pinField);

        JButton assignButton =
                new JButton(
                        "+ ASSIGN LOCKER"
                );

        assignButton.setBackground(
                purple
        );

        assignButton.setForeground(
                Color.WHITE
        );

        assignButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        assignButton.setFocusPainted(false);

        assignButton.addActionListener(
                e -> assignLocker()
        );

        panel.add(
                fields,
                BorderLayout.CENTER
        );

        panel.add(
                assignButton,
                BorderLayout.EAST
        );

        return panel;
    }

    JTextField createTextField(
            String title
    ) {

        JTextField field =
                new JTextField();

        field.setBackground(
                new Color(35, 40, 58)
        );

        field.setForeground(textColor);

        field.setCaretColor(Color.WHITE);

        field.setBorder(
                BorderFactory.createTitledBorder(
                        title
                )
        );

        return field;
    }

    void styleComboBox(
            JComboBox<String> box
    ) {

        box.setBackground(
                new Color(35, 40, 58)
        );

        box.setForeground(textColor);

        box.setBorder(
                BorderFactory.createTitledBorder(
                        "Locker"
                )
        );
    }

    void refreshLockers() {

        lockerPanel.removeAll();

        for (Locker locker : lockers) {

            lockerPanel.add(
                    createLockerCard(
                            locker
                    )
            );
        }

        lockerPanel.revalidate();
        lockerPanel.repaint();

        updateDashboard();
    }

    JPanel createLockerCard(
            Locker locker
    ) {

        JPanel card = new JPanel(
                new BorderLayout(
                        10,
                        8
                )
        );

        card.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        if (locker.occupied) {

            card.setBackground(
                    new Color(
                            38,
                            32,
                            52
                    )
            );

        } else {

            card.setBackground(
                    cardColor
            );
        }

        JLabel number =
                new JLabel(
                        locker.number
                );

        number.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        number.setForeground(textColor);

        JLabel icon =
                new JLabel(
                        locker.occupied
                                ? "🔒"
                                : "🔓"
                );

        icon.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        28
                )
        );

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        top.add(
                number,
                BorderLayout.WEST
        );

        top.add(
                icon,
                BorderLayout.EAST
        );

        card.add(
                top,
                BorderLayout.NORTH
        );

        if (locker.occupied) {

            JLabel user =
                    new JLabel(
                            locker.user
                                    + " • "
                                    + locker.item
                    );

            user.setForeground(textColor);

            JLabel status =
                    new JLabel(
                            "● OCCUPIED"
                    );

            status.setForeground(red);

            JPanel info =
                    new JPanel();

            info.setOpaque(false);

            info.setLayout(
                    new BoxLayout(
                            info,
                            BoxLayout.Y_AXIS
                    )
            );

            info.add(user);
            info.add(
                    Box.createVerticalStrut(5)
            );
            info.add(status);

            card.add(
                    info,
                    BorderLayout.CENTER
            );

        } else {

            JLabel free =
                    new JLabel(
                            "Available"
                    );

            free.setForeground(
                    secondaryText
            );

            JLabel status =
                    new JLabel(
                            "● READY"
                    );

            status.setForeground(green);

            JPanel info =
                    new JPanel();

            info.setOpaque(false);

            info.setLayout(
                    new BoxLayout(
                            info,
                            BoxLayout.Y_AXIS
                    )
            );

            info.add(free);
            info.add(
                    Box.createVerticalStrut(5)
            );
            info.add(status);

            card.add(
                    info,
                    BorderLayout.CENTER
            );
        }

        JButton action =
                new JButton(
                        locker.occupied
                                ? "OPEN"
                                : "ASSIGN"
                );

        action.setFocusPainted(false);

        action.setForeground(
                Color.WHITE
        );

        action.setBackground(
                locker.occupied
                        ? new Color(
                                55,
                                61,
                                82
                        )
                        : purple
        );

        action.addActionListener(
                e -> {

                    if (locker.occupied) {

                        openLocker(locker);

                    } else {

                        lockerBox.setSelectedItem(
                                locker.number
                        );

                        userField.requestFocus();
                    }
                }
        );

        card.add(
                action,
                BorderLayout.SOUTH
        );

        return card;
    }

    void assignLocker() {

        String user =
                userField.getText().trim();

        String item =
                itemField.getText().trim();

        String pin =
                new String(
                        pinField.getPassword()
                );

        String lockerNumber =
                lockerBox
                        .getSelectedItem()
                        .toString();

        if (
                user.isEmpty()
                        ||
                item.isEmpty()
                        ||
                pin.isEmpty()
        ) {

            showMessage(
                    "Please fill all fields!"
            );

            return;
        }

        if (
                !pin.matches("\\d{4}")
        ) {

            showMessage(
                    "PIN must be exactly 4 digits!"
            );

            return;
        }

        for (Locker locker : lockers) {

            if (
                    locker.number.equals(
                            lockerNumber
                    )
            ) {

                if (locker.occupied) {

                    showMessage(
                            "This locker is already occupied!"
                    );

                    return;
                }

                locker.user = user;
                locker.item = item;
                locker.pin = pin;
                locker.occupied = true;

                break;
            }
        }

        userField.setText("");
        itemField.setText("");
        pinField.setText("");

        refreshLockers();

        showMessage(
                "Locker assigned successfully!"
        );
    }

    void openLocker(
            Locker locker
    ) {

        String pin =
                JOptionPane.showInputDialog(
                        this,
                        "Enter PIN for "
                                + locker.number
                );

        if (pin == null) {

            return;
        }

        if (
                pin.equals(
                        locker.pin
                )
        ) {

            Object[] options = {
                    "Release Locker",
                    "Close"
            };

            int result =
                    JOptionPane.showOptionDialog(
                            this,
                            "Locker: "
                                    + locker.number
                                    + "\n"
                                    + "User: "
                                    + locker.user
                                    + "\n"
                                    + "Item: "
                                    + locker.item,
                            "Locker Opened",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[1]
                    );

            if (result == 0) {

                locker.occupied = false;
                locker.user = "";
                locker.item = "";
                locker.pin = "";

                refreshLockers();

                showMessage(
                        "Locker released successfully!"
                );
            }

        } else {

            showMessage(
                    "Incorrect PIN!"
            );
        }
    }

    void updateDashboard() {

        int occupied = 0;

        for (Locker locker : lockers) {

            if (locker.occupied) {

                occupied++;
            }
        }

        int available =
                lockers.size()
                        - occupied;

        totalLabel.setText(
                String.valueOf(
                        lockers.size()
                )
        );

        availableLabel.setText(
                String.valueOf(
                        available
                )
        );

        occupiedLabel.setText(
                String.valueOf(
                        occupied
                )
        );
    }

    void showMessage(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message
        );
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    SmartLockerSystem app =
                            new SmartLockerSystem();

                    app.setVisible(true);

                    app.refreshLockers();
                }
        );
    }

    static class Locker {

        String number;
        String user = "";
        String item = "";
        String pin = "";
        boolean occupied = false;

        Locker(
                String number
        ) {

            this.number = number;
        }
    }
}