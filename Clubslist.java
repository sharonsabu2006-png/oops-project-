import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Clubslist {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ClubConnect - Clubs List");
        frame.setSize(600, 540); 
        frame.setLayout(new BorderLayout(10, 10)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel mainTitle = new JLabel("College Clubs", JLabel.CENTER);
        frame.add(mainTitle, BorderLayout.NORTH);

        JPanel clubsGrid = new JPanel(new GridLayout(2, 2, 10, 10));

        // IEEE Panel
        JPanel ieeePanel = new JPanel(new GridLayout(3, 1));
        ieeePanel.add(new JLabel("IEEE Student Branch", JLabel.CENTER));
        JTextArea ieeeDesc = new JTextArea("Tech & Hardware\n120 Members");
        ieeeDesc.setEditable(false);
        ieeePanel.add(ieeeDesc);
        JButton ieeeBtn = new JButton("View Club");
        ieeeBtn.addActionListener(e -> {
            ClubDetails.main(null);
            frame.dispose();
        });
        ieeePanel.add(ieeeBtn); 
        clubsGrid.add(ieeePanel);

        // NSS Panel
        JPanel nssPanel = new JPanel(new GridLayout(3, 1));
        nssPanel.add(new JLabel("NSS Unit", JLabel.CENTER));
        JTextArea nssDesc = new JTextArea("Social Service\n75 Members");
        nssDesc.setEditable(false);
        nssPanel.add(nssDesc);
        JButton nssBtn = new JButton("View Club");
        nssBtn.addActionListener(e -> {
            ClubDetails.main(null);
            frame.dispose();
        });
        nssPanel.add(nssBtn);
        clubsGrid.add(nssPanel);

        // Coding Club Panel
        JPanel codingPanel = new JPanel(new GridLayout(3, 1));
        codingPanel.add(new JLabel("Coding Club", JLabel.CENTER));
        JTextArea codingDesc = new JTextArea("Programming & Hackathons\n86 Members");
        codingDesc.setEditable(false);
        codingPanel.add(codingDesc);
        JButton codingBtn = new JButton("View Club");
        codingBtn.addActionListener(e -> {
            ClubDetails.main(null);
            frame.dispose();
        });
        codingPanel.add(codingBtn);
        clubsGrid.add(codingPanel);

        // Sports Club Panel
        JPanel sportsPanel = new JPanel(new GridLayout(3, 1));
        sportsPanel.add(new JLabel("Sports Club", JLabel.CENTER));
        JTextArea sportsDesc = new JTextArea("Football & Athletics\n90 Members");
        sportsDesc.setEditable(false);
        sportsPanel.add(sportsDesc);
        JButton sportsBtn = new JButton("View Club");
        sportsBtn.addActionListener(e -> {
            ClubDetails.main(null);
            frame.dispose();
        });
        sportsPanel.add(sportsBtn);
        clubsGrid.add(sportsPanel);

        frame.add(clubsGrid, BorderLayout.CENTER);

        // Back Button
        JPanel bottomPanel = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
              DashboardFrame.main(null);
            frame.dispose();
        });
        bottomPanel.add(backBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}