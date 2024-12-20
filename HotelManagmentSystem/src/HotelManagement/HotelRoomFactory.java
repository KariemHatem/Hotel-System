/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelManagement;

/**
 *
 * @author Kariem Hatem
 */
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;

// For Factory Pattern


public class HotelRoomFactory {

    // Create a HotelRoom object using the Builder
    public static HotelRoom createHotelRoom(String roomNo, String roomType, String bedType, int amount) {
        return new HotelRoom.HotelRoomBuilder()
                .setRoomNo(roomNo)
                .setRoomType(roomType)
                .setBedType(bedType)
                .setAmount(amount)
                .build();
    }

    // Fetch a HotelRoom from the database
    public static HotelRoom fetchHotelRoomFromDatabase(Connection con, String roomNo) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM room WHERE rid = ?");
            pst.setString(1, roomNo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new HotelRoom.HotelRoomBuilder()
                        .setRoomNo(rs.getString("rid"))
                        .setRoomType(rs.getString("rtype"))
                        .setBedType(rs.getString("btype"))
                        .setAmount(rs.getInt("amount"))
                        .build();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Load all rooms into a JTable
    public static void loadRoomsIntoTable(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM room");
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("rid"),
                        rs.getString("rtype"),
                        rs.getString("btype"),
                        rs.getInt("amount")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Save a HotelRoom object to the database
    public static void saveHotelRoomToDatabase(Connection con, HotelRoom room) {
        try {
            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO room(rid, rtype, btype, amount) VALUES (?, ?, ?, ?)"
            );
            pst.setString(1, room.getRoomNo());
            pst.setString(2, room.getRoomType());
            pst.setString(3, room.getBedType());
            pst.setInt(4, room.getAmount());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Added Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Add a new HotelRoom to the database
    public static void addHotelRoom(Connection con, String roomno, String roomtype, String bedtype, String amount) {
        try {
            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO room(rid, rtype, btype, amount) VALUES (?, ?, ?, ?)"
            );
            pst.setString(1, roomno);
            pst.setString(2, roomtype);
            pst.setString(3, bedtype);
            pst.setInt(4, Integer.parseInt(amount)); // Convert the amount to integer
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Added Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //  HotelRoom in the database
    public static void updateHotelRoom(Connection con, String roomno, String roomtype, String bedtype, String amount) {
        try {
            PreparedStatement pst = con.prepareStatement(
                    "UPDATE room SET rtype = ?, btype = ?, amount = ? WHERE rid = ?"
            );
            pst.setString(1, roomtype);
            pst.setString(2, bedtype);
            pst.setInt(3, Integer.parseInt(amount)); // Convert amount to integer
            pst.setString(4, roomno);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Updated Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Delete a HotelRoom from the database
    public static void deleteHotelRoom(Connection con, String roomno) {
        try {
            PreparedStatement pst = con.prepareStatement(
                    "DELETE FROM room WHERE rid = ?"
            );
            pst.setString(1, roomno);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Deleted Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Generate Auto ID for Room
    public static void generateAutoID(Connection con, JLabel label) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT MAX(rid) FROM room");
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String lastID = rs.getString(1);
                if (lastID == null) {
                    label.setText("R0001");
                } else {
                    int newID = Integer.parseInt(lastID.substring(1)) + 1;
                    label.setText("R" + String.format("%04d", newID)); // Format to R0002, R0003, etc.
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}