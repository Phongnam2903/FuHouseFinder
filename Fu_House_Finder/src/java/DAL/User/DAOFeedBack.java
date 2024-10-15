package DAL.User;

import DAL.DAO;
import Models.Feedback;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DuongTD
 */
public class DAOFeedBack extends DAO {

    public int addFeedBack(Feedback feedBack) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Feedback]\n"
                + "           ([Title]\n"
                + "           ,[Description]\n"
                + "           ,[Status]\n"
                + "           ,[SentTime]\n"
                + "           ,[CreatedDate]\n"
                + "           ,[RenterID])\n"  
                + "     VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, feedBack.getTitle());
            pre.setString(2, feedBack.getDescription());
            pre.setString(3, feedBack.getStatus());
            pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            pre.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            pre.setInt(6, feedBack.getRenterId());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
