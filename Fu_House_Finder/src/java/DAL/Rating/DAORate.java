package DAL.Rating;

import DAL.DAO;
import Models.Rates;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAORate extends DAO{

    public int addRate(Rates rate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Rates]\n"
                + "           ([Star]\n"
                + "           ,[UserID]\n"
                + "           ,[HouseID]\n"
                + "           ,[CreatedDate])\n"
                + "     VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rate.getStar());
            pre.setInt(2, rate.getUserID());
            pre.setInt(3, rate.getHouseID());
            pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
