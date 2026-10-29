import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class CoordinatorActivities extends JFrame {

    private JTable activitiesTable;
    private JTable invitesTable;
    private DefaultTableModel activitiesModel;
    private DefaultTableModel invitesModel;

    public CoordinatorActivities() {
        setTitle("ClubConnect - Activities & Pending Invites");
        setSize(920, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(16, 42, 86));

        JLabel titleLabel = new JLabel("Club Activities & Requests Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel subLabel = new JLabel("Track your scheduled club events and review pending student join requests", JLabel.CENTER);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(new Color(200, 215, 240));

        headerPanel.add(titleLabel);
        headerPanel.add(subLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Center Panel with Two Tables
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 10));

        // Table 1: Club Activities
        String[] activityCols = {"Activity ID", "Activity Name", "Date", "Time", "Venue", "Status"};
        Object[][] activityData = {
            {"ACT-101", "Hardware Hack Night", "05-Nov-2026", "04:30 PM", "Lab 3", "Scheduled"},
            {"ACT-102", "Circuit Debugging Sprint", "12-Nov-2026", "10:00 AM", "Seminar Hall", "Active"},
            {"ACT-103", "Tech Paper Showcase", "20-Nov-2026", "02:00 PM", "Auditorium", "Upcoming"}
        };

        activitiesModel = new DefaultTableModel(activityData, activityCols) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        activitiesTable = new JTable(activitiesModel);

        JPanel activitiesPanel = new JPanel(new BorderLayout(0, 5));
        JLabel activitiesLabel = new JLabel("Your Club's Upcoming Activities");
        activitiesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        activitiesLabel.setForeground(new Color(16, 42, 86));
        activitiesPanel.add(activitiesLabel, BorderLayout.NORTH);
        activitiesPanel.add(new JScrollPane(activitiesTable), BorderLayout.CENTER);

        // Table 2: Pending Invites / Membership Requests
        String[] inviteCols = {"Request ID", "Student Name", "Student ID", "Department", "Applied Date", "Status"};
        Object[][] inviteData = {
            {"REQ-501", "Rahul Kumar", "S5-CSE-42", "CSE", "24-Oct-2026", "Pending"},
            {"REQ-502", "Ananya Menon", "S3-ECE-18", "ECE", "26-Oct-2026", "Pending"},
            {"REQ-503", "Karthik V", "S5-EEE-09", "EEE", "27-Oct-2026", "Pending"}
        };

        invitesModel = new DefaultTableModel(inviteData, inviteCols) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        invitesTable = new JTable(invitesModel);

        JPanel invitesPanel = new JPanel(new BorderLayout(0, 5));
        JLabel invitesLabel = new JLabel("Pending Student Join Invites / Membership Requests");
        invitesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        invitesLabel.setForeground(new Color(192, 57, 43));
        invitesPanel.add(invitesLabel, BorderLayout.NORTH);
        invitesPanel.add(new JScrollPane(invitesTable), BorderLayout.CENTER);

        centerPanel.add(activitiesPanel);
        centerPanel.add(invitesPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom Action Panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        JButton backBtn = new JButton("Back to Dashboard");
        JButton approveBtn = new JButton("Approve Invite");
        JButton rejectBtn = new JButton("Reject Invite");

        approveBtn.setBackground(new Color(46, 204, 113));
        approveBtn.setForeground(Color.WHITE);
        rejectBtn.setBackground(new Color(231, 76, 60));
        rejectBtn.setForeground(Color.WHITE);

        actionPanel.add(backBtn);
        actionPanel.add(rejectBtn);
        actionPanel.add(approveBtn);
        add(actionPanel, BorderLayout.SOUTH);

        // Action: Approve Selected Student Request
        approveBtn.addActionListener(e -> {
            int selectedRow = invitesTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student request from the pending table first.", "Selection Needed", JOptionPane.WARNING_MESSAGE);
            } else {
                String studentName = invitesModel.getValueAt(selectedRow, 1).toString();
                invitesModel.setValueAt("Approved", selectedRow, 5);
                JOptionPane.showMessageDialog(this, "Request for " + studentName + " has been approved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Action: Reject Selected Student Request
        rejectBtn.addActionListener(e -> {
            int selectedRow = invitesTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student request from the pending table first.", "Selection Needed", JOptionPane.WARNING_MESSAGE);
            } else {
                String studentName = invitesModel.getValueAt(selectedRow, 1).toString();
                invitesModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Request for " + studentName + " was removed.", "Request Rejected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Action: Back to Coordinator Dashboard
        backBtn.addActionListener(e -> {
            new CoordinatorDashboard();
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CoordinatorActivities();
    }
}