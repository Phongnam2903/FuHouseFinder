package DAL.Admin;

import DAL.DBContext;
import Models.Student;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageAccount extends DBContext {

    public void deleteAccount(String accID) {
        String sql = "DELETE FROM [dob].[Student] Where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertAccount(Student student) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([facebookUserId]\n"
                + "           ,[googleUserId]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[dateofbirth]\n"
                + "           ,[address]\n"
                + "           ,[statusID]\n"
                + "           ,[roleid]\n"
                + "           ,[avatar]\n"
                + "           ,[position]\n"
                + "           ,[createdDate])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement prestate = null;
        try {
            // Kiểm tra kết nối
            if (connection == null || connection.isClosed()) {
                throw new SQLException("Database connection is not available.");
            }

            prestate = connection.prepareStatement(sql);
            prestate.setString(1, student.getFacebookUserid());
            prestate.setString(2, student.getGoogleUserid());
            prestate.setString(3, student.getUsername());
            prestate.setString(4, student.getPassword());
            prestate.setString(5, student.getEmail());
            prestate.setString(6, student.getPhone());

            // Chuyển đổi java.util.Date sang java.sql.Date
            if (student.getDateOfBirth() != null) {
                prestate.setDate(7, new java.sql.Date(student.getDateOfBirth().getTime()));
            } else {
                prestate.setNull(7, java.sql.Types.DATE);
            }

            prestate.setString(8, student.getAddress());
            prestate.setInt(9, student.getStatusID());
            prestate.setInt(10, student.getRoleID());
            prestate.setString(11, student.getAvatar());
            prestate.setString(12, student.getPosition());
            prestate.setDate(13, new java.sql.Date(student.getCreatedDate().getTime()));

            // Thực hiện lệnh insert
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            // Ghi chi tiết lỗi vào log
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, "Error inserting account: " + ex.getMessage(), ex);
        } finally {
            // Đóng PreparedStatement nếu không còn sử dụng
            if (prestate != null) {
                try {
                    prestate.close();
                } catch (SQLException e) {
                    Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement: " + e.getMessage(), e);
                }
            }
        }
        return n;
    }

    public List<Student> getAccountsByPage(int page, int pageSize) {
        List<Student> accounts = new ArrayList<>();
        return accounts;
    }

    public static void main(String[] args) {
        // Tạo đối tượng Student với các thông tin mẫu
        Student student = new Student();
        student.setUsername("Nguyen Van A");
        student.setPassword("123");
        student.setEmail("phongnnhe176274@fpt.edu.vn");

        // Sử dụng java.util.Date cho ngày sinh
        student.setCreatedDate(new Date()); // Đặt ngày sinh là ngày hiện tại

        student.setStatusID(1); // Trạng thái hoạt động
        student.setRoleID(4);

        student.setPosition("Admissions Department Officer.");

        // Gọi phương thức insertAccount
        ManageAccount createAccount = new ManageAccount();
        int result = createAccount.insertAccount(student);

        // Kiểm tra kết quả
        if (result > 0) {
            System.out.println("Tạo tài khoản thành công!");
        } else {
            System.out.println("Tạo tài khoản thất bại!");
        }
    }
}
