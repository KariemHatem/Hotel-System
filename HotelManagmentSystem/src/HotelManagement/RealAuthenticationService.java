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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Proxy Pttern

  public class RealAuthenticationService implements AuthenticationService {
    
    private Connection con;

    public RealAuthenticationService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
