import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ClubsList {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ClubConnect - Clubs List");
        frame.setSize(600, 500); 
        frame.setLayout(new BorderLayout(10, 10)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel mainTitle = new JLabel("College Clubs", JLabel.CENTER);
        frame.add(mainTitle, BorderLayout.NORTH);

        JPanel clubsGrid = new JPanel(new GridLayout(2, 2, 10, 10));

        JPanel ieeePanel = new JPanel(new GridLayout(3, 1));
        ieeePanel.add(new JLabel("IEEE Student Branch", JLabel.CENTER));
        JTextArea ieeeDesc = new JTextArea("Tech & Hardware\n120 Members");
        ieeeDesc.setEditable(false);
        ieeePanel.add(ieeeDesc);
        ieeePanel.add(new JButton("View Club")); 
        clubsGrid.add(ieeePanel);

        JPanel nssPanel = new JPanel(new GridLayout(3, 1));
        nssPanel.add(new JLabel("NSS Unit", JLabel.CENTER));
        JTextArea nssDesc = new JTextArea("Social Service\n75 Members");
        nssDesc.setEditable(false);
        nssPanel.add(nssDesc);
        nssPanel.add(new JButton("View Club"));
        clubsGrid.add(nssPanel);

        JPanel codingPanel = new JPanel(new GridLayout(3, 1));
        codingPanel.add(new JLabel("Coding Club", JLabel.CENTER));
        JTextArea codingDesc = new JTextArea("Programming & Hackathons\n86 Members");
        codingDesc.setEditable(false);
        codingPanel.add(codingDesc);
        codingPanel.add(new JButton("View Club"));
        clubsGrid.add(codingPanel);

        JPanel sportsPanel = new JPanel(new GridLayout(3, 1));
        sportsPanel.add(new JLabel("Sports Club", JLabel.CENTER));
        JTextArea sportsDesc = new JTextArea("Football & Athletics\n90 Members");
        sportsDesc.setEditable(false);
        sportsPanel.add(sportsDesc);
        sportsPanel.add(new JButton("View Club"));
        clubsGrid.add(sportsPanel);

        frame.add(clubsGrid, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
