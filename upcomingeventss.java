import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class upcomingeventss extends JFrame {

    private JTable yourClubsTable, otherClubsTable;
    private DefaultTableModel yourClubsModel, otherClubsModel;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;

    public upcomingeventss() {
        setTitle("Campus Events Portal - Student View");
        setSize(980, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

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

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        searchField = new JTextField(15);
        String[] categories = {"All Categories", "Coding & Tech", "Arts & Culture", "Sports & Athletics", "Literary & Debate", "Social Service"};
        categoryFilter = new JComboBox<>(categories);

        filterPanel.add(new JLabel("Search:"));
        filterPanel.add(searchField);
        filterPanel.add(new JLabel("Category:"));
        filterPanel.add(categoryFilter);
        filterPanel.add(new JButton("Filter"));
        centerPanel.add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"Event ID", "Event Name", "Host Club", "Date", "Time", "Venue", "Status"};

        Object[][] yourClubsData = {
            {"E101", "Hackathon 2026", "Tech Club", "10-Oct-2026", "09:00 AM", "Auditorium A", "Open"},
            {"E103", "Inter-Department Football", "Sports Club", "18-Oct-2026", "03:00 PM", "Sports Ground", "Registered"},
            {"E106", "Tech Symposium 2026", "IEEE Student Branch", "25-Oct-2026", "10:00 AM", "Main Seminar Hall", "Open"}
        };

        Object[][] otherClubsData = {
            {"E102", "Annual Music Night", "Cultural Club", "15-Oct-2026", "05:00 PM", "Open Air Theatre", "Open"},
            {"E104", "Debate Championship", "Literary Society", "22-Oct-2026", "11:00 AM", "Seminar Hall 2", "Open"},
            {"E105", "Blood Donation Camp", "NSS Unit", "28-Oct-2026", "09:30 AM", "Student Activity Center", "Open"}
        };

        yourClubsModel = new DefaultTableModel(yourClubsData, columnNames) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        otherClubsModel = new DefaultTableModel(otherClubsData, columnNames) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        yourClubsTable = new JTable(yourClubsModel);
        otherClubsTable = new JTable(otherClubsModel);

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

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        JButton myRegistrationsBtn = new JButton("My Registered Events");
        JButton viewDetailsBtn = new JButton("View Event Details");
        JButton registerBtn = new JButton("Register Now");

        myRegistrationsBtn.setBackground(new Color(52, 152, 219));
        myRegistrationsBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(new Color(46, 204, 113));
        registerBtn.setForeground(Color.WHITE);

        actionPanel.add(myRegistrationsBtn);
        actionPanel.add(viewDetailsBtn);
        actionPanel.add(registerBtn);
        add(actionPanel, BorderLayout.SOUTH);

        registerBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Select an event from the table and confirm registration."));
        viewDetailsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Event details view clicked."));
        myRegistrationsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening registered events view..."));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new upcomingeventss().setVisible(true));
    }
}