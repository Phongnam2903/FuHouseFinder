package DAL.Admin;

import DAL.DBContext;
import Models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageAccount extends DBContext {

    public User getAccountById(int id) {
        User student = null;
        String sql = "SELECT * FROM [User] WHERE id = ?";
        try {
            // Chuẩn bị câu lệnh truy vấn
            PreparedStatement statement = connection.prepareStatement(sql);
            // Thiết lập giá trị ID cho câu truy vấn
            statement.setInt(1, id);
            // Thực thi truy vấn
            ResultSet rs = statement.executeQuery();
            // Kiểm tra nếu có kết quả trả về
            if (rs.next()) {
                // Lấy các giá trị từ kết quả truy vấn
                String facebookUserid = rs.getString(2);
                String googleUserid = rs.getString(3);
                String username = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                String phone = rs.getString(7);
                Date dateOfBirth = rs.getDate(8);
                String address = rs.getString(9);
                int StatusID = rs.getInt(10);
                int roleID = rs.getInt(11);
                String avatar = rs.getString(12);
                Date createdDate = rs.getDate(13);
                int roomHistoriesID = rs.getInt(14);
                // Tạo đối tượng User với các giá trị vừa lấy
                student = new User(id, facebookUserid, googleUserid,
                        username, password, email, phone,
                        dateOfBirth, address, StatusID, roleID,
                        avatar, createdDate, roomHistoriesID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Trả về đối tượng User hoặc null nếu không tìm thấy
        return student;
    }

    public int updateAccount(User student) {
        String sql = "UPDATE [dbo].[User] SET "
                + "[FacebookUserId] = ?, "
                + "[GoogleUserId] = ?, "
                + "[FullName] = ?, "
                + "[Password] = ?, "
                + "[Email] = ?, "
                + "[PhoneNumber] = ?, "
                + "[DateOfBirth] = ?, "
                + "[Address] = ?, "
                + "[StatusID] = ?, "
                + "[Roleid] = ?, "
                + "[Avatar] = ?, "
                + "[CreatedDate] = ?, "
                + "[RoomHistoriesID] = ? "
                + "WHERE id = ?";

        int rowsUpdated = 0;
        PreparedStatement prestate = null;

        try {
            prestate = connection.prepareStatement(sql);
            // Thiết lập các tham số theo đúng thứ tự trong câu lệnh SQL
            prestate.setString(1, student.getFacebookUserid());
            prestate.setString(2, student.getGoogleUserid());
            prestate.setString(3, student.getUsername());
            prestate.setString(4, student.getPassword());
            prestate.setString(5, student.getEmail());
            prestate.setString(6, student.getPhone());

            // Chuyển đổi java.util.Date sang java.sql.Date cho DateOfBirth
            if (student.getDateOfBirth() != null) {
                prestate.setDate(7, new java.sql.Date(student.getDateOfBirth().getTime()));
            } else {
                prestate.setNull(7, java.sql.Types.DATE);
            }

            prestate.setString(8, student.getAddress());
            prestate.setInt(9, student.getStatusID());
            prestate.setInt(10, student.getRoleID());
            prestate.setString(11, student.getAvatar());

            // Kiểm tra và chuyển đổi CreatedDate
            if (student.getCreatedDate() != null) {
                prestate.setDate(12, new java.sql.Date(student.getCreatedDate().getTime()));
            } else {
                prestate.setNull(12, java.sql.Types.DATE);
            }

            prestate.setInt(13, student.getRoomHistoriesID());

            // Thiết lập tham số thứ 14 cho WHERE clause
            prestate.setInt(14, student.getId());

            // Thực thi câu lệnh cập nhật
            rowsUpdated = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestate != null) {
                try {
                    prestate.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsUpdated;
    }

    public boolean deleteAccountById(int id) {
        String sql = "DELETE FROM [User] WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0; // Trả về true nếu ít nhất một dòng bị xóa
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false nếu có lỗi
        }
    }

    public int insertAccount(User student) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([FacebookUserId]\n"
                + "           ,[GoogleUserId]\n"
                + "           ,[FullName]\n"
                + "           ,[Password]\n"
                + "           ,[Email]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[DateOfBirth]\n"
                + "           ,[Address]\n"
                + "           ,[StatusID]\n"
                + "           ,[Roleid]\n"
                + "           ,[Avatar]\n"
                + "           ,[CreatedDate]\n"
                + "           ,[RoomHistoriesID])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement prestate = null;
        try {
            // Kiểm tra kết nối
            if (connection == null || connection.isClosed()) {
                throw new SQLException("Database connection is not available.");
            }

            // Hash the password using SHA-256 (assuming hashPasswordToBytes returns byte[])
            byte[] hashedPassword = hashPasswordToBytes(student.getPassword());

            // Create a string of '*' with the same length as the original password
            String maskedPassword = "*".repeat(student.getPassword().length());

            prestate = connection.prepareStatement(sql);
            prestate.setString(1, student.getFacebookUserid());
            prestate.setString(2, student.getGoogleUserid());
            prestate.setString(3, student.getUsername());
            prestate.setString(4, maskedPassword);  // Use masked password here
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
            prestate.setDate(12, new java.sql.Date(student.getCreatedDate().getTime()));
            prestate.setInt(13, student.getRoomHistoriesID());

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

    private byte[] hashPasswordToBytes(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }

    public int getAccountCount() {
        String sql = "SELECT COUNT(*) FROM [User]";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<User> getAccountsByPage(int page, int pageSize) {
        List<User> accounts = new ArrayList<>();
        String sql = """
                     select * from [User] where roleid = 4 order by id offset ?
                     rows fetch next ? rows only""";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String facebookUserid = rs.getString(2);
                String googleUserid = rs.getString(3);
                String username = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                String phone = rs.getString(7);
                Date dateOfBirth = rs.getDate(8);
                String address = rs.getString(9);
                int StatusID = rs.getInt(10);
                int roleID = rs.getInt(11);
                String avatar = rs.getString(12);
                Date createdDate = rs.getDate(13);
                int roomHistoriesID = rs.getInt(14);
                User stu = new User(id, facebookUserid, googleUserid, username, password, email, phone,
                        dateOfBirth, address, StatusID, roleID, avatar, createdDate, roomHistoriesID);
                accounts.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public static void main(String[] args) {
        ManageAccount managerAcc = new ManageAccount();
        // Tạo đối tượng User với các thông tin mẫu
        User student = new User();
        student.setUsername("Nguyen Van A");
        student.setPassword("admin");
        student.setEmail("abc@fpt.edu.vn");

        // Sử dụng java.util.Date cho ngày sinh
        student.setCreatedDate(new Date()); // Đặt ngày sinh là ngày hiện tại

        student.setStatusID(1); // Trạng thái hoạt động
        student.setRoleID(4);

        // Gọi phương thức insertAccount
        ManageAccount createAccount = new ManageAccount();
        int result = createAccount.insertAccount(student);

        // Kiểm tra kết quả
        if (result > 0) {
            System.out.println("Tạo tài khoản thành công!");
        } else {
            System.out.println("Tạo tài khoản thất bại!");
        }

        //test getAccountCount
//        System.out.println("==Get Account Count Test ==");
//        int totalAccounts = managerAcc.getAccountCount();
//        System.out.println("Total Accounts: " + totalAccounts);
        //test getAccountByPage
//        System.out.println("== Get Accounts by Page Test ==");
//        int page = 1;
//        int pageSize = 5;
//        List<User> accounts = managerAcc.getAccountsByPage(page, pageSize);
//        System.out.println("Accounts on Page" + page + ":");
//        for (User account : accounts) {
//            System.out.println("ID: " + account.getId() + ",Username: " + account.getUsername() + ",StatusID: " + account.getStatusID());
//        }
    }
}
