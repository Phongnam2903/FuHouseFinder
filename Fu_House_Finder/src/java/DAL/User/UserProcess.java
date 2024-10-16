/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.User;

import DAL.DAO;
import Validations.DataEncryptionSHA256;
import java.sql.*;

/**
 *
 * @author Tuan
 */
public class UserProcess extends DAO {

    public static UserProcess INSTANCE = new UserProcess();

    private UserProcess() {
    }

    /**
     * create new user with role is 3 (user) and status is 1 (active)
     *
     * @param fname
     * @param lname
     * @param email
     * @param phone
     * @param password
     */
    public void create(String fname, String lname, String email, String phone, String password) {
        String sql = "insert into [User] ([FullName], [Email], [PhoneNumber], [Password], [StatusID], [Roleid]) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            System.out.println(fname + lname + ", " + email + ", " + phone + ", " + DataEncryptionSHA256.hashPassword(password));
            ps.setString(1, fname + " " + lname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, DataEncryptionSHA256.hashPassword(password));
            ps.setString(5, "1");
            ps.setString(6, "3");
            int rowsAffected = ps.executeUpdate(); // Sử dụng executeUpdate
            if (rowsAffected > 0) {
                System.out.println("User registered successfully.");
            } else {
                System.out.println("User registration failed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
            status = e.getMessage();
        }
    }

}
