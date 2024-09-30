package DAL.House;

import DAL.DAO;
import Models.House;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOHouse extends DAO {

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
            pre.setDate(12, new java.sql.Date(house.getCreatedDate().getTime()));
            pre.setDate(13, new java.sql.Date(house.getLastModifiedDate().getTime()));
            pre.setString(14, house.getImage());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<House> getHousesByOwnerId(int ownerId, int pageNumber, int pageSize) {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[House] WHERE Ownerid = ? order by ID"
                + "offset ? rows fetch next ? rows only";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId);
            int offset = (pageNumber - 1) * pageSize;
            pre.setInt(2, offset);
            pre.setInt(3, pageSize);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                House house = new House();
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
        String sql = "SELECT * FROM [dbo].[House] WHERE ID = ?";

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
}
