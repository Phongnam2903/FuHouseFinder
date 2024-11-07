package DAL.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAL.DAO;
import Models.Feedback;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public int updateFeedbackStatus(int id) {
        int n = 0;
        String sql = "UPDATE [fu_house_finder].[dbo].[Feedback] SET [Status] = 'Solved' WHERE [ID] = ? AND [Status] = 'Pending'";
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

    public List<Feedback> getAllFeedbackByRenterID(int renterId) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, u.Email AS RenterEmail "
                + "FROM Feedback f "
                + "JOIN [User] u ON f.renterId = u.id "
                + "WHERE f.renterId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, renterId);  // Set the renterId parameter
            java.sql.ResultSet rs = pre.executeQuery();

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

        Feedback feedback = null;
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
                feedback = new Feedback();
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedback;
    }

    public Feedback searchFeedbackByTitle(String title) {
        Feedback feedback = null;
        String sql = "SELECT f.*, u.Email AS RenterEmail\r\n"
                + //
                "                           FROM Feedback f \r\n"
                + //
                "                           JOIN [User] u ON f.renterId = u.id WHERE Title LIKE ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
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
                feedback.setRenterEmail(rs.getString("RenterEmail"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedback;
    }

    public List<Feedback> searchFeedbackByRenterEmail(String email) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, u.Email AS RenterEmail "
                + "FROM Feedback f "
                + "JOIN [User] u ON f.renterId = u.id "
                + "WHERE u.Email LIKE ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + email + "%"); // sử dụng LIKE để tìm kiếm
            java.sql.ResultSet rs = pre.executeQuery();
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

    public List<Feedback> getAllFeedbackByStatus(String status) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, u.Email AS RenterEmail "
                + "FROM Feedback f "
                + "JOIN [User] u ON f.renterId = u.id WHERE f.Status = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, status); // Đặt giá trị cho tham số status
            java.sql.ResultSet rs = pre.executeQuery();
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

    public List<Feedback> getAllFeedbackBySentTime(Date sentTimestart, Date sentTimeend) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, u.Email AS RenterEmail "
                + "FROM Feedback f "
                + "JOIN [User] u ON f.renterId = u.id "
                + "WHERE f.SentTime BETWEEN ? AND ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            // Chuyển đổi java.util.Date thành java.sql.Date
            pre.setDate(1, new java.sql.Date(sentTimestart.getTime()));
            pre.setDate(2, new java.sql.Date(sentTimeend.getTime()));

            java.sql.ResultSet rs = pre.executeQuery();
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

    public static void main(String[] args) {
        // Initialize DAOFeedBack with a valid database connection
        DAOFeedBack daoFeedBack = new DAOFeedBack();

        // Retrieve all feedback
        List<Feedback> feedbackList = daoFeedBack.getAllFeedback();

        // Check if feedbackList is null or empty
        if (feedbackList != null && !feedbackList.isEmpty()) {
            for (Feedback feedback : feedbackList) {
                System.out.println("Feedback ID: " + feedback.getId());
                System.out.println("Title: " + feedback.getTitle());
                System.out.println("Description: " + feedback.getDescription());
                System.out.println("Status: " + feedback.getStatus());
                System.out.println("Sent Time: " + feedback.getSentTime());
                System.out.println("Created Date: " + feedback.getCreatedDate());
                System.out.println("Renter ID: " + feedback.getRenterId());
                System.out.println("Renter Email: " + feedback.getRenterEmail());
                System.out.println("---------------");
            }
        } else {
            System.out.println("No feedback records found.");
        }
    }

}
