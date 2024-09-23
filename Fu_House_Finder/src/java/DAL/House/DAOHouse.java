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
        String sql = "INSERT INTO [dbo].[house]\n"
                + "           ([houseName]\n"
                + "           ,[address]\n"
                + "           ,[description]\n"
                + "           ,[distanceToSchool]\n"
                + "           ,[ownerid]\n"
                + "           ,[powerPrice]\n"
                + "           ,[waterPrice]\n"
                + "           ,[otherServicePrice]\n"
                + "           ,[fingerPrintLock]\n"
                + "           ,[camera]\n"
                + "           ,[parking]\n"
                + "           ,[createdDate]\n"
                + "           ,[lastModifiedDate]\n"
                + "           ,[image])\n"
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

    public List<House> getHousesByOwnerId(int ownerId) {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[house] WHERE ownerid = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ownerId); // Đặt giá trị cho ownerId
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

    public int deleteHouseById(int houseId) {
        int result = 0;
        String sql = "DELETE FROM [dbo].[house] WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, houseId);
            result = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
