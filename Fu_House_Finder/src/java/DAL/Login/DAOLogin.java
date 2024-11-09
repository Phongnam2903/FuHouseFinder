package DAL.Login;

import DAL.DBContext;
import Models.User;
import Validations.DataEncryptionSHA256;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOLogin extends DBContext {

    public User getUserByFacebookId(String facebookUserId) {
        User student = null;
        String sql = "SELECT * FROM [User] WHERE FacebookUserId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, facebookUserId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                student = new User(
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
                        rs.getInt(14)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public void saveUserByFacebook(String facebookUserId, String name, String email) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([FacebookUserId], [GoogleUserId], [FullName], [Password],\n"
                + "           [Email], [PhoneNumber], [DateOfBirth], [Address], [StatusID],\n"
                + "           [Roleid], [Avatar], [CreatedDate], [RoomHistoriesID])\n" // Moved [RoomHistoriesID] inside the column list
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)"; // Using GETDATE() for createdDate

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, facebookUserId);
            statement.setNull(2, java.sql.Types.NVARCHAR);
            statement.setString(3, name);
            statement.setNull(4, java.sql.Types.NVARCHAR);
            statement.setString(5, email);
            statement.setNull(6, java.sql.Types.NVARCHAR);
            statement.setNull(7, java.sql.Types.DATE);
            statement.setNull(8, java.sql.Types.NVARCHAR);
            statement.setInt(9, 1);                        // statusID
            statement.setInt(10, 3);                       // roleid
            statement.setNull(11, java.sql.Types.NVARCHAR); // avatar
            statement.setNull(12, java.sql.Types.INTEGER); // roomHistoriesID
            statement.executeUpdate();
            System.out.println("User successfully saved to database.");
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserByGoogleId(String googleUserId) {
        User student = null;
        String sql = "Select * from [User] Where GoogleUserId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, googleUserId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                student = new User(
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
                        rs.getInt(14)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public void saveUserByGoogle(String googleUserId, String name, String email) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([FacebookUserId], [GoogleUserId], [FullName], [Password],\n"
                + "           [Email], [PhoneNumber], [DateOfBirth], [Address], [StatusID],\n"
                + "           [Roleid], [Avatar], [CreatedDate], [RoomHistoriesID])\n" // Moved [RoomHistoriesID] inside the column list
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)"; // Using GETDATE() for createdDate

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setNull(1, java.sql.Types.NVARCHAR);
            statement.setString(2, googleUserId);
            statement.setString(3, name);
            statement.setNull(4, java.sql.Types.NVARCHAR);
            statement.setString(5, email);
            statement.setNull(6, java.sql.Types.NVARCHAR);
            statement.setNull(7, java.sql.Types.DATE);
            statement.setNull(8, java.sql.Types.NVARCHAR);
            statement.setInt(9, 1);                        // statusID
            statement.setInt(10, 3);                       // roleid
            statement.setNull(11, java.sql.Types.NVARCHAR);
            statement.setNull(12, java.sql.Types.INTEGER);
            statement.executeUpdate();
            System.out.println("User successfully saved to database.");
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User loginUser(String emailOrPhone, String password) {
        String sql = "SELECT * FROM [User] WHERE Email = ? OR PhoneNumber = ? AND Password = ?";
        User student = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, emailOrPhone);
            statement.setString(2, emailOrPhone);
            statement.setString(3, password);
            ResultSet rs = statement.executeQuery();

            // Kiểm tra nếu người dùng tồn tại với email đó
            if (rs.next()) {
                // Nếu tồn tại, hãy lấy thông tin người dùng
                student = new User(
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
                        rs.getInt(14)
                );
                // băm mật khẩu vào
                String hashedInputPassword = DataEncryptionSHA256.hashPassword(password);
                // Nếu mật khẩu đã băm không trùng với dấu * thì xác nhận thành công
                if (hashedInputPassword.equals(student.getPassword())) {
                    // Đăng nhập thành công
                    return student;
                } else {
                    // Mật khẩu không đúng
                    student = null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return student; // Trả về null nếu không có người dùng hoặc mật khẩu không hợp lệ
    }

    //test khi chưa có sign up
    public void saveUserPassword(String fullname, String email, String password, int sid, int roleId) {
        String sql = "INSERT INTO [User] (FullName, Email, Password, StatusID, Roleid) VALUES (? ,?, ?, ?, ?)";
        try {
            // Tạo chuỗi dấu '*' với độ dài bằng mật khẩu
            String maskedPassword = "*".repeat(password.length());

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fullname);
            statement.setString(2, email);
            statement.setString(3, maskedPassword); // Lưu mật khẩu dưới dạng dấu '*'
            statement.setInt(4, sid);
            statement.setInt(5, roleId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOLogin login = new DAOLogin();
//        String testEmail = "phongnnhe176274@fpt.edu.vn";
//        String testPassword = "admin";
//        User acc = login.loginUser(testEmail, testPassword);
//        if (acc != null) {
//            System.out.println("Login successful");
//            System.out.println("UserId: " + acc.getId());
//            System.out.println("Role: " + acc.getRoleID());
//        } else {
//            System.out.println("Login Failed!");
//        }
//        String googleUserId = "114468492892790826988";
//        String name = "Phong Nguyễn Nam";
//        String email = "xuxumanh1@gmail.com";

        login.saveUserPassword("DangPH", "dang542003@gmail.com", "123", 1, 4);

        System.out.println("Test saveUser completed.");
    }
}
