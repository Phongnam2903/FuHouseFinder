package DAL.Login;

import DAL.DBContext;
import Models.Student;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOLogin extends DBContext {

    public Student getUserByGoogleId(String googleUserId) {
        Student stu = null;
        String sql = "Select * from Student Where googleUserId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, googleUserId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                stu = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getDate(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getString(12), rs.getDate(13));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stu;
    }

    public void saveUser(String googleUserId, String name, String email) {
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([facebookUserId], [googleUserId], [username], [password],\n"
                + "           [email], [phone], [dateofbirth], [address], [statusID],\n"
                + "           [roleid], [avatar], [createdDate])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())"; // Using GETDATE() for createdDate

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
            statement.setNull(7, java.sql.Types.DATE);     // dateofbirth

            // Address is not provided, set as null
            statement.setNull(8, java.sql.Types.NVARCHAR); // address

            // Status ID, assume it's active by default
            statement.setInt(9, 1);                        // statusID

            // Role ID is 3 for Google users
            statement.setInt(10, 3);                       // roleid

            // Avatar is not provided, set as null
            statement.setNull(11, java.sql.Types.NVARCHAR); // avatar

            // Execute the query
            statement.executeUpdate();

            System.out.println("User successfully saved to database.");

        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student loginUser(String email, String password) {
        String sql = "SELECT * FROM Student WHERE email = ? AND password = ?";
        Student stu = null;

        try {
            // Hash the input password using SHA-256 and convert to binary format
            byte[] hashedPasswordBytes = hashPasswordToBytes(password);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setBytes(2, hashedPasswordBytes);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                stu = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getDate(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getString(12), rs.getDate(13));
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return stu;
    }

    private byte[] hashPasswordToBytes(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes());
    }

    public static void main(String[] args) {
        DAOLogin login = new DAOLogin();
//        String testEmail = "phongnnhe176274@fpt.edu.vn";
//        String testPassword = "admin";
//        Student acc = login.loginUser(testEmail, testPassword);
//        if (acc != null) {
//            System.out.println("Login successful");
//            System.out.println("UserId: " + acc.getId());
//            System.out.println("Role: " + acc.getRoleID());
//        } else {
//            System.out.println("Login Failed!");
//        }
        String googleUserId = "114468492892790826988";
        String name = "Phong Nguyễn Nam";
        String email = "xuxumanh1@gmail.com";

        login.saveUser(googleUserId, name, email);
        System.out.println("Test saveUser completed.");
    }
}
