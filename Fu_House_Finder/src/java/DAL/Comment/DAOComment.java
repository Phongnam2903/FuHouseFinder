package DAL.Comment;

import DAL.DAO;
import Models.Comment;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOComment extends DAO {

    public int addComment(Comment cmt) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([Description]\n"
                + "           ,[CreatedDate]\n"
                + "           ,[UserID]\n"
                + "           ,[HouseID]\n"
                + "           ,[RoomID])\n"
                + "     VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cmt.getDescription());
            pre.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            pre.setInt(3, cmt.getUserID());
            pre.setInt(4, cmt.getHouseID());
            pre.setInt(5, cmt.getRoomID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
