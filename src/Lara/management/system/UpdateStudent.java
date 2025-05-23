package Lara.management.system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStudent extends JFrame implements ActionListener, ItemListener {

    JTextField tfname, tffname, tfroll, tfdob, tfaddress, tfphone, tfemail, tfx, tfxii, tfaadhar;
    JButton submit, cancel;
    Choice crollno;
    JComboBox<String> cbcourse, cbbranch;

    UpdateStudent() {
        setSize(900, 650);
        setLocation(350, 50);
        setLayout(null);

        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 100, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);

        crollno = new Choice();
        crollno.setBounds(250, 100, 200, 20);
        add(crollno);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from studentdb");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        addLabelAndTextField("Name", 50, 150, tfname = new JTextField());
        addLabelAndTextField("Father's Name", 400, 150, tffname = new JTextField());
        addLabelAndTextField("Roll Number", 50, 200, tfroll = new JTextField());
        addLabelAndTextField("Date of Birth", 400, 200, tfdob = new JTextField());
        addLabelAndTextField("Address", 50, 250, tfaddress = new JTextField());
        addLabelAndTextField("Phone", 400, 250, tfphone = new JTextField());
        addLabelAndTextField("Email", 50, 300, tfemail = new JTextField());
        addLabelAndTextField("Class X (%)", 400, 300, tfx = new JTextField());
        addLabelAndTextField("Class XII (%)", 50, 350, tfxii = new JTextField());
        addLabelAndTextField("Aadhar Number", 400, 350, tfaadhar = new JTextField());


        JLabel lblcourse = new JLabel("Course");
        lblcourse.setBounds(50, 400, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);

        String course[] = { "B.Tech", "BBA", "BCA", "Bsc", "Msc", "MBA", "MCA", "MCom", "MA", "BA" };
        cbcourse = new JComboBox<>(course);
        cbcourse.setBounds(200, 400, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);

        JLabel lblbranch = new JLabel("Branch");
        lblbranch.setBounds(400, 400, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);

        String branch[] = { "Computer Science", "ECE", "EEE", "Mechanical", "Civil", "IT" };
        cbbranch = new JComboBox<>(branch);
        cbbranch.setBounds(550, 400, 150, 30);
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);

        populateFields(); // Populate fields based on selected roll number

        crollno.addItemListener(this);

        submit = new JButton("Update");
        submit.setBounds(250, 500, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    private void addLabelAndTextField(String labelText, int x, int y, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("serif", Font.BOLD, 20));
        add(label);
        
        textField.setBounds(x + 150, y, 150, 30);
        add(textField);
    }

    private void populateFields() {
        try {
            Conn c = new Conn();
            String query = "select * from studentdb where rollno='" + crollno.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                tfroll.setText(rs.getString("rollno"));
                tfdob.setText(rs.getString("dob"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfx.setText(rs.getString("x"));
                tfxii.setText(rs.getString("xii"));
                tfaadhar.setText(rs.getString("aadhar"));
                cbcourse.setSelectedItem(rs.getString("course"));
                cbbranch.setSelectedItem(rs.getString("branch"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String rollno = tfroll.getText();
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = tfdob.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String classX = tfx.getText();
            String classXII = tfxii.getText();
            String aadhar = tfaadhar.getText();
            String course = (String) cbcourse.getSelectedItem();
            String branch = (String) cbbranch.getSelectedItem();

            try {
                String query = "UPDATE studentdb SET " +
                        "name='" + name + "', " +
                        "fname='" + fname + "', " +
                        "dob='" + dob + "', " +
                        "address='" + address + "', " +
                        "phone='" + phone + "', " +
                        "email='" + email + "', " +
                        "x='" + classX + "', " +
                        "xii='" + classXII + "', " +
                        "aadhar='" + aadhar + "', " +
                        "course='" + course + "', " +
                        "branch='" + branch + "' " +
                        "WHERE rollno='" + rollno + "'";

                Conn con = new Conn();
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        populateFields();
    }

    public static void main(String[] args) {
        new UpdateStudent();
    }
}