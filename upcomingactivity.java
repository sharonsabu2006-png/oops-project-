import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpcomingEventsFrame extends JFrame {

    private JTable eventsTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;

    public UpcomingEventsFrame() {
        setTitle("College Club Activity Manager - Upcoming Events");
        setSize(950, 600);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Color primaryColor = new Color(41, 128, 185);
        Color lightBgColor = new Color(245, 247, 250);

        getContentPane().setBackground(lightBgColor);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Upcoming Club Events");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("View and manage upcoming campus activities and registrations");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(220, 230, 242));

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

        JLabel searchLabel = new JLabel("Search Event:");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField = new JTextField(15);

        JLabel categoryLabel = new JLabel("Club Category:");
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

        String[] columnNames = {"Event ID", "Event Name", "Club Name", "Date", "Time", "Venue", "Category"};
        
        Object[][] sampleData = {
            {"E101", "Hackathon 2026", "Tech Club", "10-Oct-2026", "09:00 AM", "Auditorium A", "Coding & Tech"},
            {"E102", "Annual Music Night", "Cultural Club", "15-Oct-2026", "05:00 PM", "Open Air Theatre", "Arts & Culture"},
            {"E103", "Inter-Department Football", "Sports Club", "18-Oct-2026", "03:00 PM", "Sports Ground", "Sports & Athletics"},
            {"E104", "Debate Championship", "Literary Society", "22-Oct-2026", "11:00 AM", "Seminar Hall 2", "Literary & Debate"},
            {"E105", "Blood Donation Camp", "NSS Unit", "28-Oct-2026", "09:30 AM", "Student Activity Center", "Social Service"}
        };

        tableModel = new DefaultTableModel(sampleData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        eventsTable = new JTable(tableModel);
        eventsTable.setRowHeight(28);
        eventsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        eventsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        eventsTable.getTableHeader().setBackground(new Color(230, 235, 245));
        eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        eventsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        eventsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        eventsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(eventsTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        actionPanel.setBackground(new Color(235, 238, 242));
        actionPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(210, 215, 220)));

        JButton registerBtn = new JButton("Register Student");
        JButton viewDetailsBtn = new JButton("View Details");
        JButton addEventBtn = new JButton("+ Add New Event");
        JButton deleteEventBtn = new JButton("Delete Event");

        addEventBtn.setBackground(new Color(39, 174, 96));
        addEventBtn.setForeground(Color.WHITE);
        addEventBtn.setFocusPainted(false);

        registerBtn.setBackground(primaryColor);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);

        actionPanel.add(registerBtn);
        actionPanel.add(viewDetailsBtn);
        actionPanel.add(addEventBtn);
        actionPanel.add(deleteEventBtn);

        add(actionPanel, BorderLayout.SOUTH);

        addEventBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddEventDialog();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String eventName = (String) tableModel.getValueAt(selectedRow, 1);
                    openRegisterStudentDialog(eventName);
                } else {
                    JOptionPane.showMessageDialog(UpcomingEventsFrame.this,
                            "Please select an event from the table first.",
                            "No Event Selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        viewDetailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = eventsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String eventName = (String) tableModel.getValueAt(selectedRow, 1);
                    String clubName = (String) tableModel.getValueAt(selectedRow, 2);
                    String date = (String) tableModel.getValueAt(selectedRow, 3);
                    String time = (String) tableModel.getValueAt(selectedRow, 4);
                    String venue = (String) tableModel.getValueAt(selectedRow, 5);

                    openEventDetailsDialog(eventName, clubName, date, time, venue);
                } else {
                    JOptionPane.showMessageDialog(UpcomingEventsFrame.this,
                            "Please select an event to view details.",
                            "No Event Selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void openAddEventDialog() {
        JDialog addDialog = new JDialog(this, "Schedule New Event", true);
        addDialog.setSize(400, 420);
        addDialog.setLocationRelativeTo(this);
        addDialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField clubField = new JTextField();
        JTextField dateField = new JTextField("DD-MMM-YYYY");
        JTextField timeField = new JTextField("HH:MM AM/PM");
        JTextField venueField = new JTextField();
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{
            "Coding & Tech", "Arts & Culture", "Sports & Athletics", "Literary & Debate", "Social Service"
        });

        formPanel.add(new JLabel("Event Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Hosting Club:"));
        formPanel.add(clubField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryBox);
        formPanel.add(new JLabel("Date:"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Time:"));
        formPanel.add(timeField);
        formPanel.add(new JLabel("Venue:"));
        formPanel.add(venueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save Event");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(e -> addDialog.dispose());
        cancelBtn.addActionListener(e -> addDialog.dispose());

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        addDialog.add(formPanel, BorderLayout.CENTER);
        addDialog.add(buttonPanel, BorderLayout.SOUTH);
        addDialog.setVisible(true);
    }

    private void openRegisterStudentDialog(String eventName) {
        JDialog regDialog = new JDialog(this, "Event Registration", true);
        regDialog.setSize(380, 300);
        regDialog.setLocationRelativeTo(this);
        regDialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel eventLabel = new JLabel(eventName);
        eventLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField studentNameField = new JTextField();
        JTextField studentIdField = new JTextField();
        JTextField emailField = new JTextField();

        formPanel.add(new JLabel("Selected Event:"));
        formPanel.add(eventLabel);
        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(studentNameField);
        formPanel.add(new JLabel("Student ID / Roll No:"));
        formPanel.add(studentIdField);
        formPanel.add(new JLabel("Email Address:"));
        formPanel.add(emailField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submitBtn = new JButton("Submit Registration");
        JButton closeBtn = new JButton("Cancel");

        submitBtn.addActionListener(e -> regDialog.dispose());
        closeBtn.addActionListener(e -> regDialog.dispose());

        buttonPanel.add(submitBtn);
        buttonPanel.add(closeBtn);

        regDialog.add(formPanel, BorderLayout.CENTER);
        regDialog.add(buttonPanel, BorderLayout.SOUTH);
        regDialog.setVisible(true);
    }

    private void openEventDetailsDialog(String name, String club, String date, String time, String venue) {
        JDialog detailsDialog = new JDialog(this, "Event Information", true);
        detailsDialog.setSize(350, 280);
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

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            UpcomingEventsFrame frame = new UpcomingEventsFrame();
            frame.setVisible(true);
        });
    }
}
