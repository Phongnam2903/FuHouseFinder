package DAL.Staff;

import DAL.Admin.ManageAccount;
import DAL.DBContext;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStaff extends DBContext {

    public boolean updateLandlordStatus(int id, int status) {
        String sql = "UPDATE [User] SET StatusID = ? WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, status);
            ps.setInt(2, id);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getAccountCount() {
        String sql = "SELECT COUNT(*) FROM [User]";
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

    public List<User> getAccountsByPage(int page, int pageSize) {
        List<User> accounts = new ArrayList<>();

        // Updated SQL query to include totalHouses, totalRooms, and emptyRooms
        String sql = """
       SELECT 
                    u.ID,
                    u.FacebookUserId,
                    u.GoogleUserId,
                    u.FullName,
                    u.[Password],
                    u.Email,
                    u.PhoneNumber,
                    u.DateOfBirth,
                    u.[Address],
                    u.StatusID,
                    u.Roleid,
                    u.Avatar,
                    u.CreatedDate,
                    u.RoomHistoriesID,
                    COUNT(DISTINCT h.ID) AS totalHouses,
                    COUNT(r.ID) AS totalRooms,
                    COUNT(CASE WHEN r.StatusID = 2 THEN 1 END) AS emptyRooms
                FROM [User] u
                LEFT JOIN House h ON h.OwnerID = u.ID
                LEFT JOIN Room r ON r.HouseID = h.ID
                WHERE u.roleID = 5
                GROUP BY 
                    u.ID,
                    u.FacebookUserId,
                    u.GoogleUserId,
                    u.FullName,
                    u.[Password],
                    u.Email,
                    u.PhoneNumber,
                    u.DateOfBirth,
                    u.[Address],
                    u.StatusID,
                    u.Roleid,
                    u.Avatar,
                    u.CreatedDate,
                    u.RoomHistoriesID
                ORDER BY u.ID
                OFFSET ? ROWS
                FETCH NEXT ? ROWS ONLY;
    """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int offset = (page - 1) * pageSize;
            if (offset < 0) {
                offset = 0;
            }
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String facebookUserid = rs.getString("facebookUserid");
                String googleUserid = rs.getString("googleUserid");
                String username = rs.getString("FullName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("PhoneNumber");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String address = rs.getString("address");
                int statusID = rs.getInt("StatusID");
                int roleID = rs.getInt("roleID");
                String avatar = rs.getString("avatar");
                Date createdDate = rs.getDate("createdDate");
                int roomHistoriesID = rs.getInt("roomHistoriesID");
                int totalHouses = rs.getInt("totalHouses");
                int totalRooms = rs.getInt("totalRooms");
                int emptyRooms = rs.getInt("emptyRooms");

                // Create User object with all fields including new counts
                User user = new User(id, facebookUserid, googleUserid, username, password, email, phone,
                        dateOfBirth, address, statusID, roleID, avatar, createdDate, roomHistoriesID,
                        totalHouses, totalRooms, emptyRooms);

                accounts.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public int getRoomEmpty(int StatusID) {
        String sql = "Select * from Room Where StatusID = ?";
        int totalRoomEmpty = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, StatusID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRoomEmpty;
    }

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
        return switch (roomTypeName) {
            case "single" ->
                1;
            case "Double" ->
                2;
            case "Triple" ->
                3;
            case "Quad" ->
                4;
            case "Mini Apartment" ->
                3;
            case "Full House" ->
                6;
            default ->
                0;
        };
    }

    public static void main(String[] args) {
        // Tạo đối tượng DAOStaff
        DAOStaff daoStaff = new DAOStaff();

        int testID = 41;
        int newStatus = 2;

        boolean result = daoStaff.updateLandlordStatus(testID, newStatus);
        if (result) {
            System.out.println("Status updated successfully for user ID: " + testID);
        } else {
            System.out.println("Failed to update status for user ID: " + testID);
        }
    }

}
