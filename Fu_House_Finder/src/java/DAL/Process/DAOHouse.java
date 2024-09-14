package DAL.Process;

import DAL.DAO;
import Models.House;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            pre.setInt(9, (house.isFingerPrintLock() == true ? 1 : 0));
            pre.setInt(10, (house.isCamera() == true ? 1 : 0));
            pre.setInt(11, (house.isParking() == true ? 1 : 0));
            pre.setDate(12, new java.sql.Date(house.getCreatedDate().getTime()));
            pre.setDate(13, new java.sql.Date(house.getLastModifiedDate().getTime()));
            pre.setString(14, house.getImage());
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
