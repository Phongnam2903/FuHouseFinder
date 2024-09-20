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
        String testEmail = "phongnnhe176274@fpt.edu.vn";
        String testPassword = "admin";
        Student acc = login.loginUser(testEmail, testPassword);
        if (acc != null) {
            System.out.println("Login successful");
            System.out.println("UserId: " + acc.getId());
            System.out.println("Role: " + acc.getRoleID());
        } else {
            System.out.println("Login Failed!");
        }
    }
}
