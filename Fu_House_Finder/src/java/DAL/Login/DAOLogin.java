package DAL.Login;

import DAL.DBContext;
import Models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOLogin extends DBContext {

    public User getUserByGoogleId(String googleUserId) {
        User student = null;
        String sql = "Select * from [User] Where GoogleUserId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, googleUserId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                student = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9),
                        rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getDate(13), rs.getInt(14));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public void saveUser(String googleUserId, String name, String email) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([FacebookUserId], [GoogleUserId], [FullName], [Password],\n"
                + "           [Email], [Phone], [DateOfBirth], [Address], [StatusID],\n"
                + "           [Roleid], [Avatar], [CreatedDate], [RoomHistoriesID])\n" // Moved [RoomHistoriesID] inside the column list
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)"; // Using GETDATE() for createdDate

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            // Facebook ID is not provided, so we set it as null
            statement.setNull(1, java.sql.Types.NVARCHAR); // facebookUserId

            // Set Google user ID
            statement.setString(2, googleUserId);          // googleUserId

            // Set username (same as name)
            statement.setString(3, name);                  // username

            // No password for Google login, set as null
            statement.setNull(4, java.sql.Types.NVARCHAR); // password

            // Set email
            statement.setString(5, email);                 // email

            // Phone is not provided, set as null
            statement.setNull(6, java.sql.Types.NVARCHAR); // phone

            // Date of birth is not provided, set as null
            statement.setNull(7, java.sql.Types.DATE);     // dateOfBirth

            // Address is not provided, set as null
            statement.setNull(8, java.sql.Types.NVARCHAR); // address

            // Status ID, assume it's active by default
            statement.setInt(9, 1);                        // statusID

            // Role ID is 3 for Google users
            statement.setInt(10, 3);                       // roleid

            // Avatar is not provided, set as null
            statement.setNull(11, java.sql.Types.NVARCHAR); // avatar

            // RoomHistoriesID is not provided, set as null
            statement.setNull(12, java.sql.Types.INTEGER); // roomHistoriesID

            // Execute the query
            statement.executeUpdate();

            System.out.println("User successfully saved to database.");

        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User loginUser(String email, String password) {
        String sql = "SELECT * FROM [User] WHERE Email = ?";
        User student = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            // Kiểm tra nếu người dùng tồn tại với email đó
            if (rs.next()) {
                // Nếu tồn tại, hãy lấy thông tin người dùng
                student = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getDate(13), rs.getInt(14));

                // Kiểm tra mật khẩu
                // Nếu mật khẩu đã băm không trùng với dấu * thì xác nhận thành công
                if (password.length() > 0 && student.getPassword().equals("*".repeat(password.length()))) {
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
    public void saveUserPassword(String fullname, String email, String password, int sid) {
        String sql = "INSERT INTO [User] (FullName, Email, Password, StatusID) VALUES (? ,?, ?, ?)";
        try {
            // Tạo chuỗi dấu '*' với độ dài bằng mật khẩu
            String maskedPassword = "*".repeat(password.length());

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fullname);
            statement.setString(2, email);
            statement.setString(3, maskedPassword); // Lưu mật khẩu dưới dạng dấu '*'
            statement.setInt(4, sid);
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
//
//        login.saveUser(googleUserId, name, email);

        login.saveUserPassword("Trần Đại Dương", "duongtdhe172344@fpt.edu.vn", "duong03", 1);
        System.out.println("Test saveUser completed.");
    }
}
