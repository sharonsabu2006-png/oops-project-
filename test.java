import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class test extends JFrame {

    Color navy = new Color(16, 42, 86);
    Color blue = new Color(23, 105, 224);
    Color background = new Color(244, 247, 251);
    Color textColor = new Color(23, 32, 51);
    Color gray = new Color(110, 120, 135);

    CardLayout cardLayout = new CardLayout();
    JPanel centerCards = new JPanel(cardLayout);

    JButton dashBtn;
    JButton actBtn;
    JButton memBtn;
    JButton logoutBtn;

    public CoordinatorDashboard() {
        setTitle("College Club Activity Manager - Coordinator Dashboard");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(background);

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(210, 600));
        sidebar.setBackground(navy);
        sidebar.setLayout(new GridLayout(10, 1, 0, 8));
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));

        JLabel logo = new JLabel("Club Manager");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        sidebar.add(logo);

        dashBtn = createMenuButton("Dashboard", true);
        actBtn = createMenuButton("Activities", false);
        memBtn = createMenuButton("Members", false);
        logoutBtn = createMenuButton("Logout", false);

        sidebar.add(dashBtn);
        sidebar.add(actBtn);
        sidebar.add(memBtn);
        sidebar.add(new JLabel(""));
        sidebar.add(logoutBtn);

        mainPanel.add(sidebar, BorderLayout.WEST);

        JPanel content = new JPanel(new BorderLayout(0, 15));
        content.setBackground(background);
        content.setBorder(new EmptyBorder(20, 25, 20, 25));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JPanel heading = new JPanel(new GridLayout(2, 1));
        heading.setOpaque(false);

        JLabel title = new JLabel("Coordinator Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(textColor);

        JLabel subtitle = new JLabel("Manage your club activities and members");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(gray);

        heading.add(title);
        heading.add(subtitle);
        top.add(heading, BorderLayout.WEST);

        JLabel coordinatorInfo = new JLabel("Madhav P (Coordinator - Tech Club)");
        coordinatorInfo.setFont(new Font("Arial", Font.BOLD, 13));
        coordinatorInfo.setForeground(textColor);
        top.add(coordinatorInfo, BorderLayout.EAST);

        content.add(top, BorderLayout.NORTH);

        centerCards.setOpaque(false);
        centerCards.add(createDashboardView(), "DASHBOARD");
        centerCards.add(createActivitiesView(), "ACTIVITIES");
        centerCards.add(createMembersView(), "MEMBERS");

        content.add(centerCards, BorderLayout.CENTER);
        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);

        dashBtn.addActionListener(e -> switchTab("DASHBOARD", dashBtn));
        actBtn.addActionListener(e -> switchTab("ACTIVITIES", actBtn));
        memBtn.addActionListener(e -> switchTab("MEMBERS", memBtn));
        logoutBtn.addActionListener(e -> dispose());
    }

    private JButton createMenuButton(String text, boolean active) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(active ? blue : navy);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    private void switchTab(String cardName, JButton activeBtn) {
        cardLayout.show(centerCards, cardName);
        dashBtn.setBackground(navy);
        actBtn.setBackground(navy);
        memBtn.setBackground(navy);
        activeBtn.setBackground(blue);
    }

    private JPanel createDashboardView() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setOpaque(false);

        JPanel stats = new JPanel(new GridLayout(1, 4, 15, 0));
        stats.setOpaque(false);
        stats.add(createStatCard("1", "My Club (Tech Club)"));
        stats.add(createStatCard("86", "Club Members"));
        stats.add(createStatCard("4", "Ongoing Activities"));
        stats.add(createStatCard("120", "Total Registrations"));
        panel.add(stats, BorderLayout.NORTH);

        JPanel bottom = new JPanel(new GridLayout(1, 2, 15, 0));
        bottom.setOpaque(false);

        String[] actCols = {"Upcoming Activity", "Date & Time", "Status"};
        Object[][] actData = {
            {"Hackathon 2026", "Oct 5, 2026 - 9:00 AM", "Upcoming"},
            {"Java Workshop", "Oct 8, 2026 - 10:00 AM", "Upcoming"},
            {"Web Dev Workshop", "Oct 15, 2026 - 11:00 AM", "Upcoming"},
            {"Inter College Coding", "Oct 20, 2026 - 9:30 AM", "Upcoming"}
        };
        bottom.add(createTablePanel("Upcoming Club Activities", actCols, actData));

        String[] reqCols = {"Request Type", "Student / Details", "Status"};
        Object[][] reqData = {
            {"New Member", "Rahul S (CSE)", "Pending"},
            {"New Member", "Arjun K (CSE)", "Pending"},
            {"Activity Approval", "Technical Workshop", "Pending"},
            {"Event Registration", "Hackathon 2026", "Pending"}
        };
        bottom.add(createTablePanel("Pending Requests", reqCols, reqData));

        panel.add(bottom, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createActivitiesView() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setOpaque(false);

        JPanel tablesPanel = new JPanel(new GridLayout(2, 1, 0, 12));
        tablesPanel.setOpaque(false);

        String[] ongoingCols = {"Event ID", "Ongoing / Upcoming Event", "Date & Venue", "Registered Students"};
        Object[][] ongoingData = {
            {"E101", "Hackathon 2026", "10-Oct-2026 (Auditorium A)", "45 Students (Rahul S, Arjun K, Sneha R...)"},
            {"E107", "Java Workshop", "08-Oct-2026 (Lab 3)", "30 Students (Vishnu S, Anjali M...)"}
        };
        DefaultTableModel ongoingModel = new DefaultTableModel(ongoingData, ongoingCols);
        JTable ongoingTable = new JTable(ongoingModel);
        ongoingTable.setRowHeight(25);

        JPanel ongoingBox = new JPanel(new BorderLayout(0, 5));
        ongoingBox.setBackground(Color.WHITE);
        ongoingBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel l1 = new JLabel("Ongoing Activities (Registered Students & Event Details)");
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        ongoingBox.add(l1, BorderLayout.NORTH);
        ongoingBox.add(new JScrollPane(ongoingTable), BorderLayout.CENTER);

        String[] compCols = {"Event ID", "Completed Activity", "Date", "Participants Who Took Part"};
        Object[][] compData = {
            {"E095", "Web Design Contest", "12-Sep-2026", "38 Participated (Winner: Pranav S)"},
            {"E088", "Linux Install Fest", "25-Aug-2026", "52 Participated"},
            {"E081", "Intro to Git & GitHub", "10-Aug-2026", "60 Participated"}
        };
        DefaultTableModel compModel = new DefaultTableModel(compData, compCols);
        JTable compTable = new JTable(compModel);
        compTable.setRowHeight(25);

        JPanel compBox = new JPanel(new BorderLayout(0, 5));
        compBox.setBackground(Color.WHITE);
        compBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel l2 = new JLabel("Completed Activities (Participation History)");
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        compBox.add(l2, BorderLayout.NORTH);
        compBox.add(new JScrollPane(compTable), BorderLayout.CENTER);

        tablesPanel.add(ongoingBox);
        tablesPanel.add(compBox);
        panel.add(tablesPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        JButton addBtn = new JButton("Add New Activity");
        JButton removeBtn = new JButton("Remove Selected Activity");

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter New Event Name:");
            if (name != null && !name.trim().isEmpty()) {
                ongoingModel.addRow(new Object[]{"E108", name, "TBA", "0 Students"});
                JOptionPane.showMessageDialog(this, "Activity Added Successfully!");
            }
        });

        removeBtn.addActionListener(e -> {
            int row = ongoingTable.getSelectedRow();
            if (row != -1) {
                ongoingModel.removeRow(row);
                JOptionPane.showMessageDialog(this, "Activity Removed!");
            } else {
                JOptionPane.showMessageDialog(this, "Select an ongoing activity to remove.");
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(removeBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMembersView() {
        String[] memCols = {"Member ID", "Student Name", "Semester", "Department", "Contact Info"};
        Object[][] memData = {
            {"M101", "Rahul S", "S3", "CSE", "9876543210"},
            {"M102", "Arjun K", "S3", "CSE", "9876543211"},
            {"M103", "Sneha R", "S5", "IT", "9876543212"},
            {"M104", "Vishnu S", "S5", "EEE", "9876543213"},
            {"M105", "Anjali M", "S3", "CSE", "9876543214"}
        };
        return createTablePanel("Club Members List (With Contact Information)", memCols, memData);
    }

    private JPanel createStatCard(String number, String label) {
        JPanel card = new JPanel(new GridLayout(2, 1));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        numberLabel.setForeground(blue);

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        textLabel.setForeground(gray);

        card.add(numberLabel);
        card.add(textLabel);
        return card;
    }

    private JPanel createTablePanel(String titleText, String[] cols, Object[][] data) {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel heading = new JLabel(titleText);
        heading.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(heading, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(data, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(26);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CoordinatorDashboard().setVisible(true);
        });
    }
}