import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordinatorDashboard extends JFrame {

    Color navy=new Color(16,42,86);
    Color blue=new Color(23,105,224);
    Color bg=new Color(244,247,251);
    Color text=new Color(23,32,51);

    CoordinatorDashboard() {

        setTitle("ClubConnect - Coordinator Dashboard");
        setSize(1000,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main=new JPanel(new BorderLayout());
        main.setBackground(bg);

        // SIDEBAR
        JPanel side=new JPanel();
        side.setPreferredSize(new Dimension(220,650));
        side.setBackground(navy);
        side.setLayout(new BoxLayout(side,BoxLayout.Y_AXIS));
        side.setBorder(new EmptyBorder(25,15,20,15));

        JLabel logo=new JLabel("🎓  ClubConnect");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial",Font.BOLD,20));
        side.add(logo);
        side.add(Box.createVerticalStrut(40));

        String[] menu={
            "▣   Dashboard",
            "🏛   My Club",
            "📅   Activities",
            "👥   Members",
            "📝   Registrations",
            "📢   Announcements",
            "👤   Profile"
        };

        for(int i=0;i<menu.length;i++){
            JLabel m=new JLabel(menu[i]);
            m.setForeground(Color.WHITE);
            m.setFont(new Font("Arial",0,14));
            m.setBorder(new EmptyBorder(12,12,12,12));

            if(i==0){
                m.setOpaque(true);
                m.setBackground(blue);
            }

            side.add(m);
            side.add(Box.createVerticalStrut(5));
        }

        main.add(side,BorderLayout.WEST);

        // CONTENT
        JPanel content=new JPanel(new BorderLayout(0,25));
        content.setBackground(bg);
        content.setBorder(new EmptyBorder(35,35,35,35));

        JPanel top=new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel title=new JLabel(
            "<html><b>Coordinator Dashboard</b><br>"+
            "<font size='3' color='gray'>"+
            "Manage your club activities and members.</font></html>"
        );

        title.setFont(new Font("Arial",Font.BOLD,27));

        JLabel profile=new JLabel("👤  Madhav P  |  Coordinator");
        profile.setFont(new Font("Arial",0,14));

        top.add(title,BorderLayout.WEST);
        top.add(profile,BorderLayout.EAST);

        content.add(top,BorderLayout.NORTH);

        // STATISTICS
        JPanel stats=new JPanel(new GridLayout(2,2,20,20));
        stats.setOpaque(false);

        stats.add(card("🏛","1","My Club"));
        stats.add(card("👥","86","Club Members"));
        stats.add(card("📅","6","Upcoming Activities"));
        stats.add(card("📝","120","Total Registrations"));

        content.add(stats,BorderLayout.CENTER);

        main.add(content,BorderLayout.CENTER);
        add(main);
    }

    JPanel card(String icon,String number,String name){

        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(25,25,25,25));

        JLabel i=new JLabel(icon);
        i.setFont(new Font("Arial",0,28));

        JLabel n=new JLabel(number);
        n.setFont(new Font("Arial",Font.BOLD,28));
        n.setForeground(text);

        JLabel l=new JLabel(name);
        l.setForeground(Color.GRAY);

        p.add(i);
        p.add(Box.createVerticalStrut(10));
        p.add(n);
        p.add(Box.createVerticalStrut(5));
        p.add(l);

        return p;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(
            () -> new CoordinatorDashboard().setVisible(true)
        );
    }
}