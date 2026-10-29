import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class upcomingeventss extends JFrame {

    private JTable yourClubsTable, otherClubsTable;
    private DefaultTableModel yourClubsModel, otherClubsModel;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;

    private final String[] columnNames = {"Event ID", "Event Name", "Host Club", "Date", "Time", "Venue", "Status"};

    public upcomingeventss() {
        setTitle("Campus Events Portal - Student View");
        setSize(980, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(52, 152, 219));

        JLabel titleLabel = new JLabel("Explore Campus Events", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Discover upcoming activities from your clubs and across campus", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Filter / Search Toolbar
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        searchField = new JTextField(15);
        String[] categories = {"All Categories", "Coding & Tech", "Arts & Culture", "Sports & Athletics", "Literary & Debate", "Social Service"};
        categoryFilter = new JComboBox<>(categories);
        JButton filterBtn = new JButton("Filter");

        filterPanel.add(new JLabel("Search:"));
        filterPanel.add(searchField);
        filterPanel.add(new JLabel("Category:"));
        filterPanel.add(categoryFilter);
        filterPanel.add(filterBtn);
        centerPanel.add(filterPanel, BorderLayout.NORTH);

        // Table Models
        yourClubsModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        otherClubsModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        yourClubsTable = new JTable(yourClubsModel);
        otherClubsTable = new JTable(otherClubsModel);

        // Deselect other table when one is clicked
        yourClubsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && yourClubsTable.getSelectedRow() != -1) {
                otherClubsTable.clearSelection();
            }
        });
        otherClubsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && otherClubsTable.getSelectedRow() != -1) {
                yourClubsTable.clearSelection();
            }
        });

        // Layout Tables
        JPanel tablesContainer = new JPanel(new GridLayout(2, 1, 0, 10));

        JPanel yourClubsPanel = new JPanel(new BorderLayout(0, 5));
        JLabel yourClubsLabel = new JLabel("Upcoming Events from Your Joined Clubs");
        yourClubsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        yourClubsLabel.setForeground(new Color(41, 128, 185));
        yourClubsPanel.add(yourClubsLabel, BorderLayout.NORTH);
        yourClubsPanel.add(new JScrollPane(yourClubsTable), BorderLayout.CENTER);

        JPanel otherClubsPanel = new JPanel(new BorderLayout(0, 5));
        JLabel otherClubsLabel = new JLabel("Upcoming Events from Other Campus Clubs");
        otherClubsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        otherClubsLabel.setForeground(Color.DARK_GRAY);
        otherClubsPanel.add(otherClubsLabel, BorderLayout.NORTH);
        otherClubsPanel.add(new JScrollPane(otherClubsTable), BorderLayout.CENTER);

        tablesContainer.add(yourClubsPanel);
        tablesContainer.add(otherClubsPanel);
        centerPanel.add(tablesContainer, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Action Buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        JButton backBtn = new JButton("Back");
        JButton myRegistrationsBtn = new JButton("My Registered Events");
        JButton registerBtn = new JButton("Register Now");

        myRegistrationsBtn.setBackground(new Color(52, 152, 219));
        myRegistrationsBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(new Color(46, 204, 113));
        registerBtn.setForeground(Color.WHITE);

        actionPanel.add(backBtn);
        actionPanel.add(myRegistrationsBtn);
        actionPanel.add(registerBtn);
        add(actionPanel, BorderLayout.SOUTH);

        // Filter Action
        filterBtn.addActionListener(e -> loadEventsData());

        backBtn.addActionListener(e -> {
            new DashboardFrame();
            dispose();
        });

        registerBtn.addActionListener(e -> {
            int selYour = yourClubsTable.getSelectedRow();
            int selOther = otherClubsTable.getSelectedRow();

            if (selYour == -1 && selOther == -1) {
                JOptionPane.showMessageDialog(this, "Please select an event from one of the tables first.", "No Event Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String selectedEventId = (selYour != -1) 
                ? yourClubsModel.getValueAt(selYour, 0).toString()
                : otherClubsModel.getValueAt(selOther, 0).toString();

            String selectedEventName = (selYour != -1)
                ? yourClubsModel.getValueAt(selYour, 1).toString()
                : otherClubsModel.getValueAt(selOther, 1).toString();

            // Open EventRegistration with selected event details
            EventRegistration.main(new String[]{selectedEventId, selectedEventName});
            dispose();
        });

        myRegistrationsBtn.addActionListener(e -> {
            RegistrationFrame.main(null);
            dispose();
        });

        // Load data on startup
        loadEventsData();
    }

    private void loadEventsData() {
        yourClubsModel.setRowCount(0);
        otherClubsModel.setRowCount(0);

        String searchText = searchField.getText().trim();
        String selectedCategory = (String) categoryFilter.getSelectedItem();

        StringBuilder query = new StringBuilder("SELECT event_id, event_name, club_name, event_date, event_time, venue, status FROM events WHERE 1=1");

        if (!searchText.isEmpty()) {
            query.append(" AND (event_name LIKE ? OR club_name LIKE ?)");
        }
        if (selectedCategory != null && !selectedCategory.equals("All Categories")) {
            query.append(" AND category = ?");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            int paramIndex = 1;
            if (!searchText.isEmpty()) {
                stmt.setString(paramIndex++, "%" + searchText + "%");
                stmt.setString(paramIndex++, "%" + searchText + "%");
            }
            if (selectedCategory != null && !selectedCategory.equals("All Categories")) {
                stmt.setString(paramIndex++, selectedCategory);
            }

            ResultSet rs = stmt.executeQuery();

            // Default joined clubs for demonstration
            String defaultJoinedClub = "Coding Club";

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("event_id"));
                row.add(rs.getString("event_name"));
                String hostClub = rs.getString("club_name");
                row.add(hostClub);
                row.add(rs.getString("event_date"));
                row.add(rs.getString("event_time"));
                row.add(rs.getString("venue"));
                row.add(rs.getString("status"));

                if (hostClub.equalsIgnoreCase(defaultJoinedClub)) {
                    yourClubsModel.addRow(row);
                } else {
                    otherClubsModel.addRow(row);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load events: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new upcomingeventss().setVisible(true));
    }
}