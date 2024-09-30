/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Room;


import DAL.DAO;
import Models.RoomStatuses;
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
public class DAORoomStatus extends DAO{
       public List<RoomStatuses> getRoomStatus() {
        List<RoomStatuses> Status = new ArrayList<>();
        String query = "SELECT * FROM roomStatuses";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RoomStatuses status = new RoomStatuses(
                        rs.getInt("statusID"),
                        rs.getString("statusName"),
                        rs.getDate("createdDate")
                );
                Status.add(status);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAORoomType.class.getName()).log(Level.SEVERE, "Error getting rooms  type", e);
        }
        return Status;
    }  
}
