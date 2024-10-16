package DAL.User;

import DAL.DAO;
import Models.Order;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOOrder extends DAO {

    public int addOrder(Order order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserID]\n"
                + "           ,[FullName]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Email]\n"
                + "           ,[OrderContent]\n"
                + "           ,[StatusID]\n"
                + "           ,[OrderedDate])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, order.getUserID());
            pre.setString(2, order.getFullName());
            pre.setString(3, order.getPhoneNumber());
            pre.setString(4, order.getEmail());
            pre.setString(5, order.getOrderContent());
            pre.setInt(6, order.getStatusID());
            pre.setDate(7, new java.sql.Date(System.currentTimeMillis()));

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
