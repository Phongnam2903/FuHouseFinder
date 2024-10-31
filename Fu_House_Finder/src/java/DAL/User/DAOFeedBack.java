package DAL.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAL.DAO;
import Models.Feedback;

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
            System.out.println(ex.getMessage());
        }
        return n;
    }

    public int deleteFeedbackById(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Feedback] WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Feedback getFeedbackById(int id) {
        Feedback feedback = null;
        String sql = "SELECT * FROM [dbo].[Feedback] WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            java.sql.ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                feedback = new Feedback();
                feedback.setId(rs.getInt("ID"));
                feedback.setTitle(rs.getString("Title"));
                feedback.setDescription(rs.getString("Description"));
                feedback.setStatus(rs.getString("Status"));
                feedback.setSentTime(rs.getDate("SentTime"));
                feedback.setCreatedDate(rs.getDate("CreatedDate"));
                feedback.setRenterId(rs.getInt("RenterID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedback;
    }

    public List<Feedback> getAllFeedback() {

        List<Feedback> feedbacks = null;
        String sql = "SELEct f.*, u.Email AS RenterEmail\n"
                + "                 FROM Feedback f \n"
                + "                 JOIN [User] u ON f.renterId = u.id";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            java.sql.ResultSet rs = pre.executeQuery();
            feedbacks = new java.util.ArrayList<>();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("ID"));
                feedback.setTitle(rs.getString("Title"));
                feedback.setDescription(rs.getString("Description"));
                feedback.setStatus(rs.getString("Status"));
                feedback.setSentTime(rs.getDate("SentTime"));
                feedback.setCreatedDate(rs.getDate("CreatedDate"));
                feedback.setRenterId(rs.getInt("RenterID"));
                feedback.setRenterEmail(rs.getString("RenterEmail"));
                feedbacks.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbacks;
    }

    public Feedback getFeedbackDetailsByID(int id) {

        Feedback feedbacks = null;
        String sql = "SELECT \n"
                + "    f.[ID],\n"
                + "    f.[Title],\n"
                + "    f.[Description],\n"
                + "    f.[Status],\n"
                + "    f.[Reply],\n"
                + "    f.[SentTime],\n"
                + "    f.[RepliedTime],\n"
                + "    f.[CreatedDate],\n"
                + "    f.[RenterID],\n"
                + "    u.[FullName] AS RenterName, \n"
                + "    u.[Email] AS RenterEmail,                  \n"
                + "    h.[HouseName],              \n"
                + "    r.[RoomNumber]              \n"
                + "FROM \n"
                + "    [fu_house_finder].[dbo].[Feedback] f\n"
                + "LEFT JOIN \n"
                + "    [fu_house_finder].[dbo].[User] u ON f.[RenterID] = u.[ID] \n"
                + "LEFT JOIN \n"
                + "    [fu_house_finder].[dbo].[Renting] rent ON f.[RenterID] = rent.[RenterID] \n"
                + "    AND rent.[RoomID] IS NOT NULL  -- Kiểm tra RoomID tồn tại\n"
                + "LEFT JOIN \n"
                + "    [fu_house_finder].[dbo].[Room] r ON rent.[RoomID] = r.[ID]  \n"
                + "LEFT JOIN \n"
                + "    [fu_house_finder].[dbo].[House] h ON rent.[HouseID] = h.[ID]  \n"
                + "WHERE \n"
                + "    f.ID = ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            java.sql.ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("ID"));
                feedback.setTitle(rs.getString("Title"));
                feedback.setDescription(rs.getString("Description"));
                feedback.setStatus(rs.getString("Status"));
                feedback.setSentTime(rs.getDate("SentTime"));
                feedback.setCreatedDate(rs.getDate("CreatedDate"));
                feedback.setRenterId(rs.getInt("RenterID"));
                feedback.setRenterEmail(rs.getString("RenterEmail"));
                feedback.setRenterName(rs.getString("RenterName"));
                feedback.setHouseName(rs.getString("HouseName"));
                feedback.setRoomNumber(rs.getInt("RoomNumber"));
                System.out.println(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbacks;
    }

}
