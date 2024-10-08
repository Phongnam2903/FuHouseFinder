package DAL.Rating;

import DAL.DAO;
import Models.Rates;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAORate extends DAO {

    public int addRate(Rates rate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Rates]\n"
                + "           ([Star]\n"
                + "           ,[UserID]\n"
                + "           ,[HouseID]\n"
                + "           ,[CreatedDate]\n"
                + "           ,[Description]\n"
                + "           ,[HouseOwnerReply]\n"
                + "           ,[LastModifiedDate]\n"
                + "           ,[LastModifiedBy])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rate.getStar());
            pre.setInt(2, rate.getUserID());
            pre.setInt(3, rate.getHouseID());
            pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            pre.setString(5, rate.getDecription());
            pre.setString(6, rate.getHouseOwnerReply());
            pre.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            pre.setInt(8, rate.getLastModifiedBy());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<Rates> getRatesByHouse(int houseId) {
        List<Rates> rateList = new ArrayList<>();
        String sql = "SELECT r.ID, r.Star, r.Description, r.CreatedDate, u.FullName, r.HouseOwnerReply "
                + "FROM [Rates] r "
                + "JOIN [User] u ON r.UserID = u.ID "
                + "WHERE r.HouseID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, houseId);  // Gán houseId cho câu truy vấn
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Rates rate = new Rates();
                rate.setId(rs.getInt("ID"));
                rate.setStar(rs.getInt("Star"));
                rate.setDecription(rs.getString("Description"));
                rate.setCreatedDate(rs.getDate("CreatedDate"));
                rate.setUserName(rs.getString("FullName"));
                rate.setHouseOwnerReply(rs.getString("HouseOwnerReply"));

                // Thêm đối tượng rate vào danh sách
                rateList.add(rate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rateList;
    }

    public boolean updateRateReply(int rateId, String replyText) {
        String sql = "UPDATE Rates SET HouseOwnerReply = ?, LastModifiedDate = ? WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, replyText);
            pre.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            pre.setInt(3, rateId);

            int rowsUpdated = pre.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAORate.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
}
