package Lara.management.system;

import java.sql.*;


public class Conn {
    
    Connection c;
    Statement s;

    Conn () {
        try {
            String DB_URL = "jdbc:postgresql://localhost:1234/student";
            String USER = "postgres";
            String PASS = "password";
            c = DriverManager.getConnection(DB_URL, USER, PASS);
            s = c.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
