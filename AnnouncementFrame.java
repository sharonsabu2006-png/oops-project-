import javax.swing.*;

public class AnnouncementFrame {

    public static void main(String[] args) {

            JFrame f = new JFrame("ClubConnect - Announcements");

                    JPanel p = new JPanel();
                            p.setBounds(20, 20, 550, 330);
                                    p.setLayout(null);

                                            JLabel title = new JLabel("Announcements");
                                                    title.setBounds(20, 10, 200, 30);

                                                            JLabel l1 = new JLabel("Annual College Fest");
                                                                    l1.setBounds(30, 55, 200, 25);

                                                                            JTextField t1 = new JTextField(
                                                                                            "Registration for annual college fest is open");
                                                                                                    t1.setBounds(30, 85, 350, 30);

                                                                                                            JButton b1 = new JButton("View");
                                                                                                                    b1.setBounds(400, 85, 90, 30);

                                                                                                                            JLabel l2 = new JLabel("Hackathon Registration");
                                                                                                                                    l2.setBounds(30, 130, 200, 25);

                                                                                                                                            JTextField t2 = new JTextField(
                                                                                                                                                            "Last date to register is October 2");
                                                                                                                                                                    t2.setBounds(30, 160, 350, 30);

                                                                                                                                                                            JButton b2 = new JButton("View");
                                                                                                                                                                                    b2.setBounds(400, 160, 90, 30);

                                                                                                                                                                                            JLabel l3 = new JLabel("Club Recruitment");
                                                                                                                                                                                                    l3.setBounds(30, 205, 200, 25);

                                                                                                                                                                                                            JTextField t3 = new JTextField(
                                                                                                                                                                                                                            "New member recruitment has started");
                                                                                                                                                                                                                                    t3.setBounds(30, 235, 350, 30);

                                                                                                                                                                                                                                            JButton b3 = new JButton("View");
                                                                                                                                                                                                                                                    b3.setBounds(400, 235, 90, 30);

                                                                                                                                                                                                                                                            p.add(title);

                                                                                                                                                                                                                                                                    p.add(l1);
                                                                                                                                                                                                                                                                            p.add(t1);
                                                                                                                                                                                                                                                                                    p.add(b1);

                                                                                                                                                                                                                                                                                            p.add(l2);
                                                                                                                                                                                                                                                                                                    p.add(t2);
                                                                                                                                                                                                                                                                                                            p.add(b2);

                                                                                                                                                                                                                                                                                                                    p.add(l3);
                                                                                                                                                                                                                                                                                                                            p.add(t3);
                                                                                                                                                                                                                                                                                                                                    p.add(b3);

                                                                                                                                                                                                                                                                                                                                            f.add(p);

                                                                                                                                                                                                                                                                                                                                                    f.setSize(600, 400);
                                                                                                                                                                                                                                                                                                                                                            f.setLayout(null);
                                                                                                                                                                                                                                                                                                                                                                    f.setVisible(true);
                                                                                                                                                                                                                                                                                                                                                                            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                }