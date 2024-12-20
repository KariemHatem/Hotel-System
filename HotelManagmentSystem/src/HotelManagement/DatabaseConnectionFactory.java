/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelManagement;

/**
 *
 * @author Kariem Hatem
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Factory Pattern

public class DatabaseConnectionFactory {
    public static Connection createConnection() {
        Connection con = null;
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Create connection to database
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }
}