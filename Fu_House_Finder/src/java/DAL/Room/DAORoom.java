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
    public List<Room> getRoomsByHouseId(int houseId, int pageNumber, int pageSize) {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE HouseID = ? AND Deleted = 0 AND StatusID = 1 "
                + "ORDER BY id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, houseId);
            ps.setInt(2, (pageNumber - 1) * pageSize);
            ps.setInt(3, pageSize);
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

    public int getTotalRoomsByHouseId(int houseId) {
        int totalRooms = 0;
        String sql = "SELECT COUNT(*) FROM Room WHERE HouseID = ? AND Deleted = 0 AND StatusID = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, houseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalRooms = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRooms;
    }

    public Room getRoomsById(int id) {

        String query = "SELECT * FROM Room WHERE id = ? ";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
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
                return room;
            }
        } catch (SQLException e) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, "Error getting rooms by houseId", e);
        }

        return null;
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

    public int countRoomsByHouseId(int houseId) {
        String query = "SELECT COUNT(*) FROM Room WHERE deleted = 0 and HouseID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, houseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, "Error getting rooms by houseId", e);
        }

        return 0;
    }

    public List<Room> getRoomsByHouseIdPaging(int houseId, int pageIndex, int pageSize) {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Room WHERE deleted = 0 and HouseID = ? order by CreatedDate desc offset ? rows fetch next ? rows only";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, houseId);
            ps.setInt(2, (pageIndex - 1) * pageSize);
            ps.setInt(3, pageSize);
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
    public void deleteRoom(int id) {

        String query = "DELETE FROM [dbo].[room]\n"
                + "      WHERE id=?"; // Use soft delete

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int updateRoom(Room room) {
        int result = 0;
        String sql = "UPDATE [dbo].[room]\n"
                + "   SET [roomNumber] = ?\n"
                + "      ,[floorNumber] = ?\n"
                + "      ,[houseid] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[area] = ?\n"
                + "      ,[liveInHouseOwner] = ?\n"
                + "      ,[fridge] = ?\n"
                + "      ,[bed] = ?\n"
                + "      ,[desk] = ?\n"
                + "      ,[kitchen] = ?\n"
                + "      ,[closedToilet] = ?\n"
                + "      ,[washingMachine] = ?\n"
                + "      ,[lastModifiedDate] = GETDATE()\n"
                + "      ,[statusID] = ?\n"
                + "      ,[roomTypeID] = ?\n"
                + "      ,[deleted] = ?\n"
                + " WHERE id = ?";

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
            pre.setBoolean(17, room.isDeleted());
            pre.setInt(18, room.getId());
            result = pre.executeUpdate();
            System.out.println(result);
        } catch (SQLException ex) {
            Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            ex.printStackTrace();
        }
        return result;
    }

    //Thêm phòng
    public int addRoom(Room room) {
    int result = 0;

    // SQL để kiểm tra xem houseId có tồn tại không
    String checkHouseSql = "SELECT COUNT(*) FROM [dbo].[house] WHERE houseId = ?";
    
    // SQL để thêm phòng
    String insertRoomSql = "INSERT INTO [dbo].[room]\n"
            + "           ([roomNumber]\n"
            + "           ,[floorNumber]\n"
            + "           ,[houseId]\n"
            + "           ,[description]\n"
            + "           ,[image]\n"
            + "           ,[price]\n"
            + "           ,[area]\n"
            + "           ,[liveInHouseOwner]\n"
            + "           ,[fridge]\n"
            + "           ,[bed]\n"
            + "           ,[desk]\n"
            + "           ,[kitchen]\n"
            + "           ,[closedToilet]\n"
            + "           ,[washingMachine]\n"
            + "           ,[createdDate]\n"
            + "           ,[lastModifiedDate]\n"
            + "           ,[statusID]\n"
            + "           ,[roomTypeID]\n"
            + "           ,[deleted])\n"
            + "     VALUES\n"
            + "           (?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,?"
            + "           ,GETDATE()"
            + "           ,GETDATE()"
            + "           ,?"
            + "           ,?"
            + "           ,?)";

    try {
        // Kiểm tra xem houseId có tồn tại không
        PreparedStatement checkHouseStmt = connection.prepareStatement(checkHouseSql);
        checkHouseStmt.setInt(1, room.getHouseId());
        ResultSet rs = checkHouseStmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            // Nếu houseId tồn tại, thực hiện thêm phòng
            PreparedStatement pre = connection.prepareStatement(insertRoomSql);
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
            pre.setBoolean(17, room.isDeleted());

            result = pre.executeUpdate();
        } else {
            System.out.println("House ID không tồn tại, không thể thêm phòng.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAORoom.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result;
}


}
