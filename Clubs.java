
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Clubs {

    public Clubs() {
        JFrame frame = new JFrame("Club Details Manager");
        frame.setSize(700, 450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("College Club Information");
        titleLabel.setBounds(20, 20, 200, 25);
        frame.add(titleLabel);

        JLabel nameLabel = new JLabel("Search Club:");
        nameLabel.setBounds(20, 60, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField("IEEE Student Branch");
        nameField.setBounds(120, 60, 160, 25);
        frame.add(nameField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(290, 60, 80, 25);
        frame.add(searchButton);

        JLabel coordLabel = new JLabel("Coordinator:");
        coordLabel.setBounds(20, 105, 100, 25);
        frame.add(coordLabel);

        JTextField coordField = new JTextField("Arun (ECE)");
        coordField.setBounds(120, 105, 250, 25);
        coordField.setEditable(false);
        frame.add(coordField);

        JLabel descLabel = new JLabel("Club Details:");
        descLabel.setBounds(20, 145, 100, 25);
        frame.add(descLabel);

        JTextArea descArea = new JTextArea(
            "Club Name: IEEE Student Branch\n" +
            "Category: Professional / Technical\n" +
            "Members: 120\n\n" +
            "IEEE focuses on advancing technology,\n" +
            "technical paper presentations, and hardware projects.\n\n" +
            "Upcoming: Tech Symposium 2026"
        );
        descArea.setBounds(20, 175, 350, 150);
        descArea.setEditable(false);
        frame.add(descArea);

        JButton joinButton = new JButton("Register / Join");
        joinButton.setBounds(130, 345, 150, 30);
        frame.add(joinButton);

        JLabel activeTitleLabel = new JLabel("Active Campus Clubs");
        activeTitleLabel.setBounds(400, 20, 200, 25);
        frame.add(activeTitleLabel);

        JLabel filterLabel = new JLabel("Sort By:");
        filterLabel.setBounds(400, 60, 60, 25);
        frame.add(filterLabel);

        String[] filters = {"Most Active (Popularity)", "Alphabetical (A-Z)"};
        JComboBox<String> filterBox = new JComboBox<>(filters);
        filterBox.setBounds(460, 60, 200, 25);
        frame.add(filterBox);

        JLabel listLabel = new JLabel("Select a club from the list:");
        listLabel.setBounds(400, 105, 200, 25);
        frame.add(listLabel);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        String[] popularOrder = {
            "1. IEEE Student Branch (120 Members)",
            "2. Tech Club (95 Members)",
            "3. Cultural Club (88 Members)",
            "4. Sports Club (86 Members)",
            "5. NSS Unit (75 Members)",
            "6. Literary Society (60 Members)"
        };

        String[] alphaOrder = {
            "Cultural Club (88 Members)",
            "IEEE Student Branch (120 Members)",
            "Literary Society (60 Members)",
            "NSS Unit (75 Members)",
            "Sports Club (86 Members)",
            "Tech Club (95 Members)"
        };

        for (String club : popularOrder) {
            listModel.addElement(club);
        }

        JList<String> clubList = new JList<>(listModel);
        clubList.setSelectedIndex(0);
        JScrollPane listScrollPane = new JScrollPane(clubList);
        listScrollPane.setBounds(400, 135, 260, 190);
        frame.add(listScrollPane);

        filterBox.addActionListener(e -> {
            String currentClub = nameField.getText().trim();
            listModel.clear();
            if (filterBox.getSelectedIndex() == 0) {
                for (String club : popularOrder) {
                    listModel.addElement(club);
                }
            } else {
                for (String club : alphaOrder) {
                    listModel.addElement(club);
                }
            }
            highlightMatchingClub(currentClub, clubList, listModel);
        });

        clubList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && clubList.getSelectedValue() != null) {
                String selected = clubList.getSelectedValue();
                updateClubDetails(selected, nameField, coordField, descArea, clubList, listModel);
            }
        });

        searchButton.addActionListener(e -> {
            String query = nameField.getText().trim();
            updateClubDetails(query, nameField, coordField, descArea, clubList, listModel);
        });

        joinButton.addActionListener(e -> {
            String currentClub = nameField.getText().trim();
            openClubJoinDialog(frame, currentClub);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void highlightMatchingClub(String clubName, JList<String> clubList, DefaultListModel<String> listModel) {
        String lower = clubName.toLowerCase();
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.getElementAt(i).toLowerCase().contains(lower)) {
                if (clubList.getSelectedIndex() != i) {
                    clubList.setSelectedIndex(i);
                }
                return;
            }
        }
    }

    private void updateClubDetails(String clubText, JTextField nameField, JTextField coordField, JTextArea descArea, JList<String> clubList, DefaultListModel<String> listModel) {
        String lower = clubText.toLowerCase();
        if (lower.contains("ieee")) {
            nameField.setText("IEEE Student Branch");
            coordField.setText("Arun (ECE)");
            descArea.setText(
                "Club Name: IEEE Student Branch\n" +
                "Category: Professional / Technical\n" +
                "Members: 120\n\n" +
                "IEEE focuses on advancing technology,\n" +
                "technical paper presentations, and hardware projects.\n\n" +
                "Upcoming: Tech Symposium 2026"
            );
            highlightMatchingClub("IEEE Student Branch", clubList, listModel);
        } else if (lower.contains("tech")) {
            nameField.setText("Tech Club");
            coordField.setText("Madhav P (CSE)");
            descArea.setText(
                "Club Name: Tech Club\n" +
                "Category: Coding & Software\n" +
                "Members: 95\n\n" +
                "Tech Club organizes hackathons, coding bootcamps,\n" +
                "and software development workshops.\n\n" +
                "Upcoming: Hackathon 2026"
            );
            highlightMatchingClub("Tech Club", clubList, listModel);
        } else if (lower.contains("cultural")) {
            nameField.setText("Cultural Club");
            coordField.setText("Sneha R (IT)");
            descArea.setText(
                "Club Name: Cultural Club\n" +
                "Category: Arts & Music\n" +
                "Members: 88\n\n" +
                "Cultural Club hosts music nights, dance fests,\n" +
                "and campus-wide artistic celebrations.\n\n" +
                "Upcoming: Annual Music Night"
            );
            highlightMatchingClub("Cultural Club", clubList, listModel);
        } else if (lower.contains("sports")) {
            nameField.setText("Sports Club");
            coordField.setText("Rahul K (ME)");
            descArea.setText(
                "Club Name: Sports Club\n" +
                "Category: Sports & Athletics\n" +
                "Members: 86\n\n" +
                "Sports Club manages inter-department tournaments,\n" +
                "football leagues, and athletic meets.\n\n" +
                "Upcoming: Inter-Department Football"
            );
            highlightMatchingClub("Sports Club", clubList, listModel);
        } else if (lower.contains("nss")) {
            nameField.setText("NSS Unit");
            coordField.setText("Vishnu S (EEE)");
            descArea.setText(
                "Club Name: NSS Unit\n" +
                "Category: Social Service\n" +
                "Members: 75\n\n" +
                "NSS Unit organizes blood donation camps,\n" +
                "tree plantation drives, and community volunteering.\n\n" +
                "Upcoming: Blood Donation Camp"
            );
            highlightMatchingClub("NSS Unit", clubList, listModel);
        } else if (lower.contains("literary")) {
            nameField.setText("Literary Society");
            coordField.setText("Anjali M (CSE)");
            descArea.setText(
                "Club Name: Literary Society\n" +
                "Category: Debate & Writing\n" +
                "Members: 60\n\n" +
                "Literary Society conducts debates, quiz competitions,\n" +
                "and creative writing workshops.\n\n" +
                "Upcoming: Debate Championship"
            );
            highlightMatchingClub("Literary Society", clubList, listModel);
        } else {
            clubList.clearSelection();
            JOptionPane.showMessageDialog(null, "Club not found. Please select from the Active Clubs list.");
        }
    }

    private void openClubJoinDialog(JFrame parent, String clubName) {
        JDialog joinDialog = new JDialog(parent, "Club Sign In - Join " + clubName, true);
        joinDialog.setSize(380, 280);
        joinDialog.setLocationRelativeTo(parent);
        joinDialog.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 12));
        JTextField idField = new JTextField();
        JTextField studentNameField = new JTextField();
        JTextField semField = new JTextField();
        JTextField contactField = new JTextField();

        formPanel.add(new JLabel("  Club Name:"));
        formPanel.add(new JLabel(clubName));
        formPanel.add(new JLabel("  Member ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("  Full Name:"));
        formPanel.add(studentNameField);
        formPanel.add(new JLabel("  Semester:"));
        formPanel.add(semField);
        formPanel.add(new JLabel("  Contact Info:"));
        formPanel.add(contactField);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmBtn = new JButton("Confirm Join");
        JButton cancelBtn = new JButton("Cancel");

        confirmBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(joinDialog, "Registered Successfully in " + clubName);
            joinDialog.dispose();
        });
        cancelBtn.addActionListener(e -> joinDialog.dispose());

        btnPanel.add(confirmBtn);
        btnPanel.add(cancelBtn);

        joinDialog.add(formPanel, BorderLayout.CENTER);
        joinDialog.add(btnPanel, BorderLayout.SOUTH);
        joinDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new Clubs();
    }
}