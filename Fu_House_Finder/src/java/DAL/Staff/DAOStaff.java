package DAL.Staff;

import DAL.Admin.ManageAccount;
import DAL.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStaff extends DBContext {

    public int getTotalCapacity() {
        String sql = "SELECT rt.RoomTypeName, COUNT(h.ID) AS HouseCount\n"
                + "FROM Room r\n"
                + "JOIN RoomTypes rt ON r.RoomTypeID = rt.RoomTypeID\n"
                + "JOIN House h ON r.HouseID = h.ID\n"
                + "GROUP BY rt.RoomTypeName;";
        int totalCapacity = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String roomTypename = rs.getString("roomTypeName");
                int houseCount = rs.getInt("HouseCount");
                int capacityPerRoom = getCapacityByRoomTypes(roomTypename);
                totalCapacity += capacityPerRoom * houseCount;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCapacity;
    }

    public int getHouseCount() {
        String sql = "SELECT COUNT(*) FROM House";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getRoomCount() {
        String sql = "SELECT COUNT(*) FROM Room";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getUserCountByRole(int roleID) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE Roleid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roleID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private int getCapacityByRoomTypes(String roomTypeName) {
        switch (roomTypeName) {
            case "single":
                return 1;
            case "Double":
                return 2;
            case "Triple":
                return 3;
            case "Quad":
                return 4;
            case "Mini Apartment":
                return 3;
            case "Full House":
                return 6;
            default:
                return 0;
        }
    }
}
