package DAL.House;

import DAL.DAO;
import Models.House;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOHouse extends DAO {

    public List<House> getHousesWithPricesAndStar() {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT House.ID, House.[Address], House.[Image], House.DistanceToSchool, House.HouseName, "
                + "MIN(Room.Price) AS MinPrice, MAX(Room.Price) AS MaxPrice, AVG(CAST(Rates.Star AS FLOAT)) AS AvgStar "
                + "FROM House "
                + "JOIN Room ON House.ID = Room.HouseID "
                + "LEFT JOIN Rates ON House.ID = Rates.HouseID "
                + "GROUP BY House.ID, House.[Address], House.[Image], House.DistanceToSchool, House.HouseName";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery(); // Thực hiện truy vấn

            while (rs.next()) {
                House house = new House();
                house.setId(rs.getInt("ID"));
                house.setHouseName(rs.getString("HouseName"));
                house.setAddress(rs.getString("Address"));
                house.setDistanceToSchool(rs.getFloat("DistanceToSchool"));

                // Set giá từ bảng Room
                house.setMinPrice(rs.getDouble("MinPrice"));
                house.setMaxPrice(rs.getDouble("MaxPrice"));
                house.setAverageStar(rs.getDouble("AvgStar"));
                house.setImage(rs.getString("image"));
                houses.add(house);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return houses;
    }

    public House getHouseSummary(int ownerId) {
        House houseSummary = new House();  // Sử dụng House để lưu thông tin tổng hợp
        String sql = "SELECT COUNT(DISTINCT House.ID) AS TotalHouses, "
                + "                 COUNT(Room.ID) AS TotalRooms, "
                + "                 COUNT(CASE WHEN Room.StatusID = 1 THEN 1 END) AS TotalAvailableRooms "
                + "                 FROM House "
                + "                 LEFT JOIN Room ON House.ID = Room.HouseID "
                + "                 WHERE House.OwnerID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                houseSummary.setTotalHouse(rs.getInt("TotalHouses"));
                houseSummary.setTotalRooms(rs.getInt("TotalRooms"));
                houseSummary.setTotalAvailableRooms(rs.getInt("TotalAvailableRooms"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return houseSummary;
    }

    public int addHouse(House house) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[House]\n"
                + "           ([HouseName]\n"
                + "           ,[Address]\n"
                + "           ,[Description]\n"
                + "           ,[DistanceToSchool]\n"
                + "           ,[Ownerid]\n"
                + "           ,[PowerPrice]\n"
                + "           ,[WaterPrice]\n"
                + "           ,[OtherServicePrice]\n"
                + "           ,[FingerPrintLock]\n"
                + "           ,[Camera]\n"
                + "           ,[Parking]\n"
                + "           ,[CreatedDate]\n"
                + "           ,[LastModifiedDate]\n"
                + "           ,[Image])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, house.getHouseName());
            pre.setString(2, house.getAddress());
            pre.setString(3, house.getDescription());
            pre.setFloat(4, house.getDistanceToSchool());
            pre.setInt(5, house.getOwnerId());
            pre.setDouble(6, house.getPowerPrice());
            pre.setDouble(7, house.getWaterPrice());
            pre.setDouble(8, house.getOtherServicePrice());
            pre.setInt(9, (house.isFingerPrintLock() ? 1 : 0));
            pre.setInt(10, (house.isCamera() ? 1 : 0));
            pre.setInt(11, (house.isParking() ? 1 : 0));
            pre.setTimestamp(12, new Timestamp(house.getCreatedDate().getTime()));
            pre.setTimestamp(13, new Timestamp(house.getLastModifiedDate().getTime()));
            pre.setString(14, house.getImage());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getTotalHousesByOwnerId(int ownerId) {
        int totalHouses = 0;
        String sql = "SELECT COUNT(*) AS Total FROM [dbo].[House] WHERE Ownerid = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId);

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                totalHouses = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return totalHouses;
    }

    public List<House> getHousesByOwnerId(int ownerId, int pageNumber, int pageSize) {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT House.ID, House.HouseName, House.FingerPrintLock, House.Camera, House.Parking, "
                + "SUM(Room.Price) AS TotalRoomPrice "
                + "FROM House "
                + "LEFT JOIN Room ON House.ID = Room.HouseID "
                + "WHERE House.OwnerID = ? "
                + "GROUP BY House.ID, House.HouseName, House.FingerPrintLock, House.Camera, House.Parking "
                + "ORDER BY House.ID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId);
            pre.setInt(2, (pageNumber - 1) * pageSize);
            pre.setInt(3, pageSize);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                House house = new House();
                house.setId(rs.getInt("ID"));
                house.setHouseName(rs.getString("HouseName"));
                house.setFingerPrintLock(rs.getInt("FingerPrintLock") == 1);
                house.setCamera(rs.getInt("Camera") == 1);
                house.setParking(rs.getInt("Parking") == 1);
                house.setTotalPrice(rs.getDouble("TotalRoomPrice"));

                houses.add(house);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return houses;
    }

    public int deleteHouseById(int houseId) {
        int result = 0;
        String sql = "DELETE FROM [dbo].[House] WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, houseId);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public House getHouseById(int id) {
        House house = null;
        String sql = "SELECT h.*, u.FullName, u.PhoneNumber "
                + "FROM [dbo].[House] h "
                + "JOIN [dbo].[User] u ON h.Ownerid = u.ID "
                + "WHERE h.ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                house = new House();
                house.setId(rs.getInt("ID"));
                house.setHouseName(rs.getString("HouseName"));
                house.setAddress(rs.getString("Address"));
                house.setDescription(rs.getString("Description"));
                house.setDistanceToSchool(rs.getFloat("DistanceToSchool"));
                house.setOwnerId(rs.getInt("Ownerid"));
                house.setPowerPrice(rs.getDouble("PowerPrice"));
                house.setWaterPrice(rs.getDouble("WaterPrice"));
                house.setOtherServicePrice(rs.getDouble("OtherServicePrice"));
                house.setFingerPrintLock(rs.getInt("FingerPrintLock") == 1);
                house.setCamera(rs.getInt("Camera") == 1);
                house.setParking(rs.getInt("Parking") == 1);
                house.setCreatedDate(rs.getDate("CreatedDate"));
                house.setLastModifiedDate(rs.getDate("LastModifiedDate"));
                house.setImage(rs.getString("Image"));
                house.setOwnerName(rs.getString("FullName"));
                house.setOwnerPhoneNumber(rs.getString("PhoneNumber"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return house;
    }

    public List<House> getHouses() {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[House]";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery(); // Thực hiện truy vấn

            while (rs.next()) {
                House house = new House();
                house.setId(rs.getInt("id"));
                house.setHouseName(rs.getString("houseName"));
                house.setAddress(rs.getString("address"));
                house.setDescription(rs.getString("description"));
                house.setDistanceToSchool(rs.getFloat("distanceToSchool"));
                house.setOwnerId(rs.getInt("ownerid"));
                house.setPowerPrice(rs.getDouble("powerPrice"));
                house.setWaterPrice(rs.getDouble("waterPrice"));
                house.setOtherServicePrice(rs.getDouble("otherServicePrice"));
                house.setFingerPrintLock(rs.getInt("fingerPrintLock") == 1);
                house.setCamera(rs.getInt("camera") == 1);
                house.setParking(rs.getInt("parking") == 1);
                house.setCreatedDate(rs.getDate("createdDate"));
                house.setLastModifiedDate(rs.getDate("lastModifiedDate"));
                house.setImage(rs.getString("image"));

                houses.add(house);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return houses;
    }

    public int updateHouse(House house) {
        int n = 0;
        String sql = "UPDATE [dbo].[House] SET "
                + "HouseName = ?, Address = ?, Description = ?, DistanceToSchool = ?, "
                + "Ownerid = ?, PowerPrice = ?, WaterPrice = ?, OtherServicePrice = ?, "
                + "FingerPrintLock = ?, Camera = ?, Parking = ?, "
                + "LastModifiedDate = ?, Image = ? "
                + "WHERE ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, house.getHouseName());
            pre.setString(2, house.getAddress());
            pre.setString(3, house.getDescription());
            pre.setFloat(4, house.getDistanceToSchool());
            pre.setInt(5, house.getOwnerId());
            pre.setDouble(6, house.getPowerPrice());
            pre.setDouble(7, house.getWaterPrice());
            pre.setDouble(8, house.getOtherServicePrice());
            pre.setInt(9, house.isFingerPrintLock() ? 1 : 0);
            pre.setInt(10, house.isCamera() ? 1 : 0);
            pre.setInt(11, house.isParking() ? 1 : 0);
            pre.setDate(12, new java.sql.Date(house.getLastModifiedDate().getTime()));
            pre.setString(13, house.getImage());
            pre.setInt(14, house.getId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public List<House> searchHouses(int ownerId, String search, int pageNumber, int pageSize) {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT House.ID, House.HouseName, House.FingerPrintLock, House.Camera, House.Parking, "
                + "SUM(Room.Price) AS TotalRoomPrice "
                + "FROM [dbo].[House] "
                + "LEFT JOIN [dbo].[Room] ON House.ID = Room.HouseID "
                + "WHERE House.Ownerid = ? AND House.HouseName LIKE ? "
                + "GROUP BY House.ID, House.HouseName, House.FingerPrintLock, House.Camera, House.Parking "
                + "ORDER BY House.ID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId);
            pre.setString(2, "%" + search + "%");
            pre.setInt(3, (pageNumber - 1) * pageSize);
            pre.setInt(4, pageSize);

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                House house = new House();
                house.setId(rs.getInt("ID"));
                house.setHouseName(rs.getString("HouseName"));
                house.setFingerPrintLock(rs.getInt("FingerPrintLock") == 1);
                house.setCamera(rs.getInt("Camera") == 1);
                house.setParking(rs.getInt("Parking") == 1);
                house.setTotalPrice(rs.getInt("TotalRoomPrice"));

                houses.add(house);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return houses;
    }

    public int getTotalHouseBySearch(int ownerId, String search) {
        int totalHouses = 0;
        String sql = "SELECT COUNT(*) AS Total FROM [dbo].[House] WHERE Ownerid = ? AND HouseName LIKE ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId);
            pre.setString(2, "%" + search + "%");

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                totalHouses = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return totalHouses;
    }
}
