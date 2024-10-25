package DAL.Login;

import DAL.DBContext;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOForgot extends DBContext {

    public User checkUsersForChangePass(String email) {
        String sql = "Select * from [User] Where email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getDate(13),
                        rs.getInt(14));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOForgot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        DAOForgot dao = new DAOForgot();
        String email = "phongnnhe176274@fpt.edu.vn";

        User user = dao.checkUsersForChangePass(email);
        if (user != null) {
            System.out.println("User found:" + email);
        } else {
            System.out.println("User not found!");
        }
    }
}
