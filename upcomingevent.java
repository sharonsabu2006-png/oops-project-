import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class upcomingevent extends JFrame {

    private JTable eventsTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;

    public upcomingevent() {
        setTitle("Campus Events Portal - Student View");
        setSize(980, 620);
        setMinimumSize(new Dimension(850, 520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Color primaryColor = new Color(52, 152, 219);
        Color lightBgColor = new Color(248, 249, 250);

        getContentPane().setBackground(lightBgColor);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Explore Campus Events");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Discover upcoming activities, register for events, and track your participation");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(235, 245, 255));

        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
        titleContainer.setOpaque(false);
        titleContainer.add(titleLabel);
        titleContainer.add(Box.createVerticalStrut(3));
        titleContainer.add(subtitleLabel);

        headerPanel.add(titleContainer, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filterPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField = new JTextField(15);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        String[] categories = {"All Categories", "Coding & Tech", "Arts & Culture", "Sports & Athletics", "Literary & Debate", "Social Service"};
        categoryFilter = new JComboBox<>(categories);

        JButton applyFilterBtn = new JButton("Filter");

        filterPanel.add(searchLabel);
        filterPanel.add(searchField);
        filterPanel.add(Box.createHorizontalStrut(10));
        filterPanel.add(categoryLabel);
        filterPanel.add(categoryFilter);
        filterPanel.add(applyFilterBtn);

        centerPanel.add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"Event ID", "Event Name", "Host Club", "Date", "Time", "Venue", "Status"};

        Object[][] sampleData = {
            {"E101", "Hackathon 2026", "Tech Club", "10-Oct-2026", "09:00 AM", "Auditorium A", "Open"},
            {"E102", "Annual Music Night", "Cultural Club", "15-Oct-2026", "05:00 PM", "Open Air Theatre", "Open"},
            {"E103", "Inter-Department Football", "Sports Club", "18-Oct-2026", "03:00 PM", "Sports Ground", "Registered"},
            {"E104", "Debate Championship", "Literary Society", "22-Oct-2026", "11:00 AM", "Seminar Hall 2", "Open"},
            {"E105", "Blood Donation Camp", "NSS Unit", "28-Oct-2026", "09:30 AM", "Student Activity Center", "Open"}
        };

        tableModel = new DefaultTableModel(sampleData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        eventsTable = new JTable(tableModel);
        eventsTable.setRowHeight(30);
        eventsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        eventsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        eventsTable.getTableHeader().setBackground(new Color(230, 238, 248));
        eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        eventsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        eventsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        eventsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        eventsTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(eventsTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        actionPanel.setBackground(new Color(238, 242, 246));
        actionPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(215, 220, 225)));

        JButton myRegistrationsBtn = new JButton("My Registered Events");
        JButton viewDetailsBtn = new JButton("View Event Details");
        JButton registerBtn = new JButton("Register Now");

        registerBtn.setOpaque(true);
        registerBtn.setContentAreaFilled(true);
        registerBtn.setBorderPainted(false);
        registerBtn.setBackground(new Color(46, 204, 113));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        myRegistrationsBtn.setOpaque(true);
        myRegistrationsBtn.setContentAreaFilled(true);
        myRegistrationsBtn.setBorderPainted(false);
        myRegistrationsBtn.setBackground(primaryColor);
        myRegistrationsBtn.setForeground(Color.WHITE);
        myRegistrationsBtn.setFocusPainted(false);
        myRegistrationsBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        actionPanel.add(myRegistrationsBtn);
        actionPanel.add(viewDetailsBtn);
        actionPanel.add(registerBtn);

        add(actionPanel, BorderLayout.SOUTH);

        registerBtn.addActionListener(e -> {
            int selectedRow = eventsTable.getSelectedRow();
            if (selectedRow != -1) {
                String eventName = (String) tableModel.getValueAt(selectedRow, 1);
                String clubName = (String) tableModel.getValueAt(selectedRow, 2);
                openStudentRegisterDialog(eventName, clubName);
            } else {
                JOptionPane.showMessageDialog(upcomingevent.this,
                        "Please select an event from the table to register.",
                        "No Event Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        viewDetailsBtn.addActionListener(e -> {
            int selectedRow = eventsTable.getSelectedRow();
            if (selectedRow != -1) {
                String eventName = (String) tableModel.getValueAt(selectedRow, 1);
                String clubName = (String) tableModel.getValueAt(selectedRow, 2);
                String date = (String) tableModel.getValueAt(selectedRow, 3);
                String time = (String) tableModel.getValueAt(selectedRow, 4);
                String venue = (String) tableModel.getValueAt(selectedRow, 5);
                openEventDetailsDialog(eventName, clubName, date, time, venue);
            } else {
                JOptionPane.showMessageDialog(upcomingevent.this,
                        "Please select an event to view details.",
                        "No Event Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        myRegistrationsBtn.addActionListener(e -> openMyRegistrationsDialog());
    }

    private void openStudentRegisterDialog(String eventName, String club) {
        JDialog regDialog = new JDialog(this, "Confirm Registration", true);
        regDialog.setSize(400, 320);
        regDialog.setLocationRelativeTo(this);
        regDialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextField studentIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();

        formPanel.add(new JLabel("Event Name:"));
        formPanel.add(new JLabel("<html><b>" + eventName + "</b></html>"));
        formPanel.add(new JLabel("Hosted By:"));
        formPanel.add(new JLabel(club));
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(studentIdField);
        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email Address:"));
        formPanel.add(emailField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmBtn = new JButton("Confirm Registration");
        JButton cancelBtn = new JButton("Cancel");

        confirmBtn.setOpaque(true);
        confirmBtn.setContentAreaFilled(true);
        confirmBtn.setBorderPainted(false);
        confirmBtn.setBackground(new Color(46, 204, 113));
        confirmBtn.setForeground(Color.WHITE);

        confirmBtn.addActionListener(e -> regDialog.dispose());
        cancelBtn.addActionListener(e -> regDialog.dispose());

        buttonPanel.add(confirmBtn);
        buttonPanel.add(cancelBtn);

        regDialog.add(formPanel, BorderLayout.CENTER);
        regDialog.add(buttonPanel, BorderLayout.SOUTH);
        regDialog.setVisible(true);
    }

    private void openEventDetailsDialog(String name, String club, String date, String time, String venue) {
        JDialog detailsDialog = new JDialog(this, "Event Details", true);
        detailsDialog.setSize(380, 300);
        detailsDialog.setLocationRelativeTo(this);
        detailsDialog.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        infoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Event Name:"));
        infoPanel.add(new JLabel("<html><b>" + name + "</b></html>"));
        infoPanel.add(new JLabel("Organized By:"));
        infoPanel.add(new JLabel(club));
        infoPanel.add(new JLabel("Date:"));
        infoPanel.add(new JLabel(date));
        infoPanel.add(new JLabel("Time:"));
        infoPanel.add(new JLabel(time));
        infoPanel.add(new JLabel("Venue:"));
        infoPanel.add(new JLabel(venue));

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> detailsDialog.dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(closeBtn);

        detailsDialog.add(infoPanel, BorderLayout.CENTER);
        detailsDialog.add(btnPanel, BorderLayout.SOUTH);
        detailsDialog.setVisible(true);
    }

    private void openMyRegistrationsDialog() {
        JDialog myRegDialog = new JDialog(this, "My Registered Events", true);
        myRegDialog.setSize(500, 350);
        myRegDialog.setLocationRelativeTo(this);
        myRegDialog.setLayout(new BorderLayout());

        String[] cols = {"Event Name", "Club", "Date", "Venue"};
        Object[][] myEventsData = {
            {"Inter-Department Football", "Sports Club", "18-Oct-2026", "Sports Ground"}
        };

        DefaultTableModel myModel = new DefaultTableModel(myEventsData, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable myTable = new JTable(myModel);
        myTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(myTable);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelRegBtn = new JButton("Cancel Registration");
        JButton closeBtn = new JButton("Close");

        cancelRegBtn.addActionListener(e -> myRegDialog.dispose());
        closeBtn.addActionListener(e -> myRegDialog.dispose());

        bottomPanel.add(cancelRegBtn);
        bottomPanel.add(closeBtn);

        myRegDialog.add(scrollPane, BorderLayout.CENTER);
        myRegDialog.add(bottomPanel, BorderLayout.SOUTH);
        myRegDialog.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            upcomingevent frame = new upcomingevent();
            frame.setVisible(true);
        });
    }
}