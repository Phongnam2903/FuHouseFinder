package DAL.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAL.DAO;
import Models.Room;

public class DAORoom extends DAO {

    // Lấy danh sách phòng thuộc một nhà (houseId)
    public List<Room> getRoomsByHouseId(int houseId) {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Room WHERE houseId = ? AND deleted = 0";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, houseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("id"),
                    rs.getInt("roomNumber"),
                    rs.getInt("floorNumber"),
                    rs.getInt("houseId"),
                    rs.getString("description"),
                    rs.getString("image"),
                    rs.getDouble("price"),
                    rs.getFloat("area"),
                    rs.getBoolean("liveInHouseOwner"),
                    rs.getBoolean("fridge"),
                    rs.getBoolean("bed"),
                    rs.getBoolean("desk"),
                    rs.getBoolean("kitchen"),
                    rs.getBoolean("closedToilet"),
                    rs.getBoolean("washingMachine"),
                    rs.getDate("createdDate"),
                    rs.getDate("lastModifiedDate"),
                    rs.getInt("statusId"),
                    rs.getInt("roomTypeId"),
                    rs.getBoolean("deleted")
                );
                roomList.add(room);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, "Error getting rooms by houseId", e);
        }

        return roomList;
    }
    
    public List<Room> getRooms() {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Room WHERE deleted = 0";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("id"),
                    rs.getInt("roomNumber"),
                    rs.getInt("floorNumber"),
                    rs.getInt("houseId"),
                    rs.getString("description"),
                    rs.getString("image"),
                    rs.getDouble("price"),
                    rs.getFloat("area"),
                    rs.getBoolean("liveInHouseOwner"),
                    rs.getBoolean("fridge"),
                    rs.getBoolean("bed"),
                    rs.getBoolean("desk"),
                    rs.getBoolean("kitchen"),
                    rs.getBoolean("closedToilet"),
                    rs.getBoolean("washingMachine"),
                    rs.getDate("createdDate"),
                    rs.getDate("lastModifiedDate"),
                    rs.getInt("statusId"),
                    rs.getInt("roomTypeId"),
                    rs.getBoolean("deleted")
                );
                roomList.add(room);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, "Error getting rooms by houseId", e);
        }

        return roomList;
    }

    // Xóa phòng theo roomId
    public int deleteRoomById(int roomId) {
        int result = 0;
        String query = "UPDATE Room SET deleted = 1 WHERE id = ?"; // Use soft delete

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roomId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, "Error deleting room", e);
        }
        return result;
    }

    //Thêm phòng
    public int addRoom(Room room) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[room]\n" +
"           ([roomNumber]\n" +
"           ,[floorNumber]\n" +
"           ,[houseId]\n" +
"           ,[description]\n" +
"           ,[image]\n" +
"           ,[price]\n" +
"           ,[area]\n" +
"           ,[liveInHouseOwner]\n" +
"           ,[fridge]\n" +
"           ,[bed]\n" +
"           ,[desk]\n" +
"           ,[kitchen]\n" +
"           ,[closedToilet]\n" +
"           ,[washingMachine]\n" +
"           ,[createdDate]\n" +
"           ,[lastModifiedDate]\n" +
"           ,[statusID]\n" +
"           ,[roomTypeID]\n" +
"           ,[deleted])\n" +
"     VALUES\n" +
"           (?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,?" +
"           ,GETDATE()" +
"           ,GETDATE()" +
"           ,?" +
"           ,?" +
"           ,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, room.getRoomNumber());
            pre.setInt(2, room.getFloorNumber());
            pre.setInt(3, room.getHouseId());
            pre.setString(4, room.getDescription());
            pre.setString(5, room.getImage());
            pre.setDouble(6, room.getPrice());
            pre.setDouble(7, room.getArea());
            pre.setBoolean(8, room.isLiveInHouseOwner());
            pre.setBoolean(9, room.isFridge());
            pre.setBoolean(10, room.isBed());   
            pre.setBoolean(11, room.isDesk());
            pre.setBoolean(12, room.isKitchen());
            pre.setBoolean(13, room.isClosedToilet());
            pre.setBoolean(14, room.isWashingMachine());
            pre.setInt(15, room.getStatusId());
            pre.setInt(16, room.getRoomTypeId());
            pre.setBoolean(17, room.getDeleted());
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
    

