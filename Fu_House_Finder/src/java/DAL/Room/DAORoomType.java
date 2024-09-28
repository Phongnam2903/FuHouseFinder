/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Room;

import DAL.DAO;
import DAL.DBContext;
import Models.Room;
import Models.RoomTypes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author My Lap
 */
public class DAORoomType extends DAO{
     public List<RoomTypes> getRoomTypes() {
        List<RoomTypes> roomList = new ArrayList<>();
        String query = "SELECT * FROM roomTypes";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RoomTypes room = new RoomTypes(
                        rs.getInt("roomTypeID"),
                        rs.getString("roomTypeName"),
                        rs.getDate("createdDate")
                );
                roomList.add(room);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAORoomType.class.getName()).log(Level.SEVERE, "Error getting rooms  type", e);
        }
        return roomList;
    }
}
