import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordinatorDashboard extends JFrame {

    Color navy=new Color(16,42,86), blue=new Color(23,105,224),
          bg=new Color(244,247,251), text=new Color(23,32,51),
          gray=new Color(110,120,135);

    CoordinatorDashboard() {
        setTitle("ClubConnect - Coordinator Dashboard");
        setSize(1200,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main=new JPanel(new BorderLayout()), side=new JPanel();
        main.setBackground(bg);
        side.setPreferredSize(new Dimension(230,720));
        side.setBackground(navy);
        side.setLayout(new BoxLayout(side,BoxLayout.Y_AXIS));
        side.setBorder(new EmptyBorder(25,15,20,15));

        JLabel logo=label("🎓  ClubConnect",21,Color.WHITE);
        logo.setAlignmentX(LEFT_ALIGNMENT);
        side.add(logo);
        side.add(Box.createVerticalStrut(40));

        String[] menu={"▣   Dashboard","🏛   My Club","📅   Activities",
            "👥   Members","📝   Registrations","📢   Announcements","👤   Profile"};

        for(int i=0;i<menu.length;i++){
            JLabel m=label(menu[i],14,Color.WHITE);
            m.setBorder(new EmptyBorder(12,12,12,12));
            if(i==0){m.setOpaque(true);m.setBackground(blue);}
            side.add(m);
            side.add(Box.createVerticalStrut(5));
        }
        main.add(side,BorderLayout.WEST);

        JPanel content=new JPanel(new BorderLayout(0,20));
        content.setBackground(bg);
        content.setBorder(new EmptyBorder(30,30,30,30));

        JPanel top=new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel title=label(
            "<html><b>Coordinator Dashboard</b><br>"+
            "<font size='3' color='gray'>Manage your club activities and members.</font></html>",
            28,text);

        JLabel profile=label("👤  Madhav P  |  Coordinator",14,text);
        top.add(title,BorderLayout.WEST);
        top.add(profile,BorderLayout.EAST);
        content.add(top,BorderLayout.NORTH);

        JPanel center=new JPanel(new BorderLayout(0,20));
        center.setOpaque(false);

        JPanel stats=new JPanel(new GridLayout(1,4,18,0));
        stats.setOpaque(false);

        String[][] s={
            {"🏛","1","My Club"},{"👥","86","Club Members"},
            {"📅","6","Upcoming Activities"},{"📝","120","Total Registrations"}
        };

        for(String[] x:s) stats.add(card(x));

        center.add(stats,BorderLayout.NORTH);

        JPanel bottom=new JPanel(new GridLayout(1,2,20,0));
        bottom.setOpaque(false);
        bottom.add(list("Upcoming Activities",new String[][]{
            {"💻","Hackathon 2026","October 5, 2026 • 9:00 AM"},
            {"☕","Java Workshop","October 8, 2026 • 10:00 AM"},
            {"🌐","Web Development Workshop","October 15, 2026 • 11:00 AM"},
            {"🏆","Inter College Competition","October 20, 2026 • 9:30 AM"}
        },true));

        bottom.add(list("Pending Requests",new String[][]{
            {"","New Member Request","Rahul S • Computer Science"},
            {"","New Member Request","Arjun K • Computer Science"},
            {"","Activity Approval","Technical Workshop"},
            {"","Event Registration","Hackathon 2026"}
        },false));

        center.add(bottom,BorderLayout.CENTER);
        content.add(center,BorderLayout.CENTER);
        main.add(content,BorderLayout.CENTER);
        add(main);
    }

    JLabel label(String s,int size,Color c){
        JLabel l=new JLabel(s);
        l.setFont(new Font("Arial",Font.PLAIN,size));
        l.setForeground(c);
        return l;
    }

    JPanel card(String[] x){
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(18,18,18,18));
        p.add(label(x[0],25,text));
        p.add(Box.createVerticalStrut(8));
        JLabel n=label(x[1],26,text);
        n.setFont(new Font("Arial",Font.BOLD,26));
        p.add(n);
        p.add(Box.createVerticalStrut(3));
        p.add(label(x[2],13,gray));
        return p;
    }

    JPanel list(String title,String[][] data,boolean activity){
        JPanel p=new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(20,20,15,20));
        p.add(label(title,19,text),BorderLayout.NORTH);

        JPanel l=new JPanel();
        l.setOpaque(false);
        l.setLayout(new BoxLayout(l,BoxLayout.Y_AXIS));

        for(String[] x:data){
            String html=activity
                ? "<html>"+x[0]+"  <b>"+x[1]+
                  "</b><br><font color='gray'>"+x[2]+
                  "</font> &nbsp; <font color='#16834B'>Upcoming</font></html>"
                : "<html><b>"+x[1]+"</b><br><font color='gray'>"+
                  x[2]+"</font><br><font color='#A66A00'>Pending</font></html>";

            JLabel r=label(html,13,text);
            r.setBorder(new EmptyBorder(12,0,12,0));
            l.add(r);
            l.add(new JSeparator());
        }

        p.add(l,BorderLayout.CENTER);
        return p;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new CoordinatorDashboard().setVisible(true));
    }
}