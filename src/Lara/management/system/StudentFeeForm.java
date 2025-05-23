package Lara.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class StudentFeeForm extends JFrame implements ActionListener {

    Choice crollno;
    JTextField tfname, tftotal, tfdue;
    JComboBox<String> cbcourse, cbbranch, cbsemester;
    JButton update, pay, back;

    StudentFeeForm() {
        setSize(900, 500);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(new Color(72, 61, 139));

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/laralogo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);

        JLabel lblrollnumber = new JLabel("Select Roll No :");
        lblrollnumber.setBounds(40, 60, 150, 20);
        lblrollnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblrollnumber.setForeground(Color.WHITE);
        add(lblrollnumber);

        crollno = new Choice();
        crollno.setBounds(200, 60, 150, 20);
        add(crollno);

        JLabel lblname = new JLabel("Name              :");
        lblname.setBounds(40, 100, 150, 20);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblname.setForeground(Color.white);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 100, 150, 20);
        tfname.setEditable(false);
        add(tfname);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT rollno,name FROM studentdb");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(
                            "SELECT name FROM studentdb WHERE rollno='" + crollno.getSelectedItem() + "'");
                    if (rs.next()) {
                        tfname.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel lblcourse = new JLabel("Course            :");
        lblcourse.setBounds(40, 140, 150, 20);
        lblcourse.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblcourse.setForeground(Color.white);
        add(lblcourse);

        String course[] = { "BTech", "BBA", "BCA", "BSc", "MSc", "MBA", "MCA", "MCom", "MA", "BA" };
        cbcourse = new JComboBox<>(course);
        cbcourse.setBounds(200, 140, 150, 20);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);

        JLabel lblbranch = new JLabel("Branch            :");
        lblbranch.setBounds(40, 180, 150, 20);
        lblbranch.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblbranch.setForeground(Color.white);
        add(lblbranch);

        String branch[] = { "Computer Science", "Electronics", "Mechanical", "Civil", "IT" };
        cbbranch = new JComboBox<>(branch);
        cbbranch.setBounds(200, 180, 150, 20);
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);

        JLabel lblsemester = new JLabel("Semester        :");
        lblsemester.setBounds(40, 220, 150, 20);
        lblsemester.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblsemester.setForeground(Color.white);
        add(lblsemester);

        String semester[] = { "Semester1", "Semester2", "Semester3", "Semester4", "Semester5", "Semester6", "Semester7",
                "Semester8" };
        cbsemester = new JComboBox<>(semester);
        cbsemester.setBounds(200, 220, 150, 20);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        JLabel lbltotal = new JLabel("Paid Amount   :");
        lbltotal.setBounds(40, 260, 150, 20);
        lbltotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbltotal.setForeground(Color.white);
        add(lbltotal);

        tftotal = new JTextField();
        tftotal.setBounds(200, 260, 150, 20);
        add(tftotal);

        JLabel lbldue = new JLabel("Due Amount   :");
        lbldue.setBounds(40, 300, 150, 20);
        lbldue.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbldue.setForeground(Color.white);
        add(lbldue);

        tfdue = new JTextField();
        tfdue.setBounds(200, 300, 150, 20);
        add(tfdue);

        update = new JButton("Update");
        update.setBounds(30, 380, 100, 25);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        pay = new JButton("Pay Fee");
        pay.setBounds(150, 380, 100, 25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(270, 380, 100, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            tftotal.setText("50000"); // Dummy value
            tfdue.setText("10000"); // Dummy value
        } else if (ae.getSource() == pay) {
            JOptionPane.showMessageDialog(null, "Payment process initiated.");
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
