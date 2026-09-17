import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class VotingSystem extends JFrame
        implements ActionListener {

    JTextField voterIdField;
    JTextField voterNameField;
    JTextField searchField;

    JRadioButton candidate1;
    JRadioButton candidate2;
    JRadioButton candidate3;

    ButtonGroup candidateGroup;

    JTable voterTable;
    DefaultTableModel tableModel;

    JButton voteButton;
    JButton searchButton;
    JButton resultButton;
    JButton deleteButton;
    JButton resetButton;

    JLabel totalVoterLabel;
    JLabel totalVoteLabel;

    int candidate1Votes = 0;
    int candidate2Votes = 0;
    int candidate3Votes = 0;

    VotingSystem() {

        setTitle("Voting System");
        setSize(950, 700);

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
                "VOTING SYSTEM"
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
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
                new JLabel("Voter ID")
        );

        inputPanel.add(
                new JLabel("Voter Name")
        );

        inputPanel.add(
                new JLabel("Candidate")
        );

        voterIdField = new JTextField();
        voterNameField = new JTextField();

        inputPanel.add(voterIdField);
        inputPanel.add(voterNameField);

        // Candidate panel
        JPanel candidatePanel = new JPanel();

        candidatePanel.setOpaque(false);

        candidate1 =
                new JRadioButton(
                        "Candidate A"
                );

        candidate2 =
                new JRadioButton(
                        "Candidate B"
                );

        candidate3 =
                new JRadioButton(
                        "Candidate C"
                );

        candidateGroup =
                new ButtonGroup();

        candidateGroup.add(candidate1);
        candidateGroup.add(candidate2);
        candidateGroup.add(candidate3);

        candidatePanel.add(candidate1);
        candidatePanel.add(candidate2);
        candidatePanel.add(candidate3);

        inputPanel.add(candidatePanel);

        mainPanel.add(
                inputPanel,
                BorderLayout.BEFORE_FIRST_LINE
        );

        // Table
        String[] columns = {
                "Voter ID",
                "Voter Name",
                "Voted Candidate"
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

        voterTable =
                new JTable(tableModel);

        voterTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        voterTable.setRowHeight(32);

        voterTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(voterTable);

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
                new JButton("SEARCH VOTER");

        searchButton.addActionListener(this);

        searchPanel.add(
                new JLabel("Voter ID:")
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

        voteButton =
                createButton("CAST VOTE");

        resultButton =
                createButton("VIEW RESULT");

        deleteButton =
                createButton("DELETE VOTER");

        resetButton =
                createButton("RESET ELECTION");

        buttonPanel.add(voteButton);
        buttonPanel.add(resultButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(resetButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // Statistics
        JPanel statsPanel = new JPanel(
                new GridLayout(2, 1)
        );

        statsPanel.setOpaque(false);

        totalVoterLabel =
                new JLabel(
                        "Voters: 0",
                        SwingConstants.CENTER
                );

        totalVoteLabel =
                new JLabel(
                        "Votes: 0",
                        SwingConstants.CENTER
                );

        totalVoterLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        totalVoteLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        statsPanel.add(totalVoterLabel);
        statsPanel.add(totalVoteLabel);

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
                e.getSource() == voteButton
        ) {

            castVote();

        } else if (
                e.getSource() == searchButton
        ) {

            searchVoter();

        } else if (
                e.getSource() == resultButton
        ) {

            showResult();

        } else if (
                e.getSource() == deleteButton
        ) {

            deleteVoter();

        } else if (
                e.getSource() == resetButton
        ) {

            resetElection();
        }
    }

    // Cast vote
    void castVote() {

        String voterId =
                voterIdField.getText().trim();

        String voterName =
                voterNameField.getText().trim();

        if (
                voterId.isEmpty() ||
                voterName.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Voter ID and Name!"
            );

            return;
        }

        // Check duplicate voter
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
                    existingId.equals(voterId)
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "This voter has already voted!"
                );

                return;
            }
        }

        String selectedCandidate = "";

        if (
                candidate1.isSelected()
        ) {

            selectedCandidate =
                    "Candidate A";

        } else if (
                candidate2.isSelected()
        ) {

            selectedCandidate =
                    "Candidate B";

        } else if (
                candidate3.isSelected()
        ) {

            selectedCandidate =
                    "Candidate C";
        }

        if (
                selectedCandidate.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a candidate!"
            );

            return;
        }

        // Increase vote
        if (
                selectedCandidate.equals(
                        "Candidate A"
                )
        ) {

            candidate1Votes++;

        } else if (
                selectedCandidate.equals(
                        "Candidate B"
                )
        ) {

            candidate2Votes++;

        } else {

            candidate3Votes++;
        }

        // Add voter
        tableModel.addRow(
                new Object[]{
                        voterId,
                        voterName,
                        selectedCandidate
                }
        );

        updateStatistics();

        clearFields();

        JOptionPane.showMessageDialog(
                this,
                "Vote submitted successfully!"
        );
    }

    // Search voter
    void searchVoter() {

        String voterId =
                searchField.getText().trim();

        if (
                voterId.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Voter ID!"
            );

            return;
        }

        boolean found = false;

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
                    existingId.equals(voterId)
            ) {

                voterTable
                        .setRowSelectionInterval(
                                i,
                                i
                        );

                voterTable
                        .scrollRectToVisible(
                                voterTable.getCellRect(
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
                    "Voter not found!"
            );
        }
    }

    // Show result
    void showResult() {

        int totalVotes =
                candidate1Votes
                        + candidate2Votes
                        + candidate3Votes;

        if (
                totalVotes == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No votes have been cast yet!"
            );

            return;
        }

        String result =
                "ELECTION RESULT\n\n"
                        + "Candidate A: "
                        + candidate1Votes
                        + " votes\n\n"
                        + "Candidate B: "
                        + candidate2Votes
                        + " votes\n\n"
                        + "Candidate C: "
                        + candidate3Votes
                        + " votes\n\n"
                        + "Total Votes: "
                        + totalVotes;

        JOptionPane.showMessageDialog(
                this,
                result,
                "Voting Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Delete voter
    void deleteVoter() {

        int row =
                voterTable.getSelectedRow();

        if (
                row == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a voter!"
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected voter?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            String candidate =
                    tableModel
                            .getValueAt(
                                    row,
                                    2
                            )
                            .toString();

            // Decrease vote
            if (
                    candidate.equals(
                            "Candidate A"
                    )
            ) {

                candidate1Votes--;

            } else if (
                    candidate.equals(
                            "Candidate B"
                    )
            ) {

                candidate2Votes--;

            } else {

                candidate3Votes--;
            }

            tableModel.removeRow(row);

            updateStatistics();
        }
    }

    // Reset election
    void resetElection() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Reset the entire election?",
                        "Confirm Reset",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            candidate1Votes = 0;
            candidate2Votes = 0;
            candidate3Votes = 0;

            tableModel.setRowCount(0);

            updateStatistics();

            JOptionPane.showMessageDialog(
                    this,
                    "Election has been reset!"
            );
        }
    }

    // Update statistics
    void updateStatistics() {

        int totalVotes =
                candidate1Votes
                        + candidate2Votes
                        + candidate3Votes;

        totalVoterLabel.setText(
                "Voters: "
                        + tableModel.getRowCount()
        );

        totalVoteLabel.setText(
                "Votes: "
                        + totalVotes
        );
    }

    // Clear fields
    void clearFields() {

        voterIdField.setText("");
        voterNameField.setText("");

        searchField.setText("");

        candidateGroup.clearSelection();
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        VotingSystem app =
                                new VotingSystem();

                        app.setVisible(true);
                    }
                }
        );
    }
}