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
        String sql = "SELECT * FROM [dbo].[Feedback]";
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
                feedbacks.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbacks;
    }

}
