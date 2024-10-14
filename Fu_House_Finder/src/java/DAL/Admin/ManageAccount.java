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
        User user = null;
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
                int statusID = rs.getInt(10);
                int roleID = rs.getInt(11);
                String avatar = rs.getString(12);
                Date createdDate = rs.getDate(13);
                int roomHistoriesID = rs.getInt(14);
                int totalHouses = rs.getInt(15); // Assuming you have these fields
                int totalRooms = rs.getInt(16);  // in your User class definition
                int emptyRooms = rs.getInt(17);

                // Tạo đối tượng User với các giá trị vừa lấy
                user = new User(id, facebookUserid, googleUserid, username, password, email, phone,
                        dateOfBirth, address, statusID, roleID, avatar, createdDate, roomHistoriesID,
                        totalHouses, totalRooms, emptyRooms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Trả về đối tượng User hoặc null nếu không tìm thấy
        return user;
    }

    public int updateAccount(User student) {
        int n = 0;
        String sql = "UPDATE [dbo].[User]"
                + "   SET [FacebookUserId] = ?,[GoogleUserId] = ?,[FullName] = ?,[Email] = ?,[PhoneNumber] =?,[DateOfBirth] = ?,[Address] =?,"
                + "[StatusID] =?,[Roleid] = ?,[Avatar] = ?,[CreatedDate] = ?,[RoomHistoriesID] = ? WHERE ID = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getFacebookUserid());
            statement.setString(2, student.getGoogleUserid());
            statement.setString(3, student.getUsername());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            if (student.getDateOfBirth() != null) {
                statement.setDate(6, new java.sql.Date(student.getDateOfBirth().getTime()));
            } else {
                statement.setNull(6, java.sql.Types.DATE);
            }
            statement.setString(7, student.getAddress());
            statement.setInt(8, student.getStatusID());
            statement.setInt(9, student.getRoleID());
            statement.setString(10, student.getAvatar());
            if (student.getCreatedDate() != null) {
                statement.setDate(11, new java.sql.Date(student.getCreatedDate().getTime()));
            } else {
                statement.setNull(11, java.sql.Types.DATE);
            }
            statement.setInt(12, student.getRoomHistoriesID());
            statement.setInt(13, student.getId());
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
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
        String sql = "SELECT COUNT(*) FROM [User] Where roleid = 4";
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
                 SELECT 
                    u.*, 
                    COUNT(DISTINCT h.ID) AS totalHouses,                         
                    COUNT(r.ID) AS totalRooms,                                   
                    COUNT(CASE WHEN r.StatusID = 1 THEN 1 END) AS emptyRooms     
                 FROM 
                    [User] u
                 LEFT JOIN 
                    House h ON u.ID = h.OwnerID
                 LEFT JOIN 
                    Room r ON h.ID = r.HouseID
                 WHERE 
                    u.RoleID = 4
                 GROUP BY 
                    u.ID, u.FacebookUserID, u.GoogleUserID, u.FullName, u.Password, u.Email, 
                    u.PhoneNumber, u.DateOfBirth, u.Address, u.StatusID, u.RoleID, u.Avatar, 
                    u.CreatedDate, u.RoomHistoriesID
                 ORDER BY 
                    u.ID
                 OFFSET ? ROWS 
                 FETCH NEXT ? ROWS ONLY;
                 """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String facebookUserid = rs.getString("FacebookUserID");
                String googleUserid = rs.getString("GoogleUserID");
                String username = rs.getString("FullName");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String phone = rs.getString("PhoneNumber");
                Date dateOfBirth = rs.getDate("DateOfBirth");
                String address = rs.getString("Address");
                int StatusID = rs.getInt("StatusID");
                int roleID = rs.getInt("RoleID");
                String avatar = rs.getString("Avatar");
                Date createdDate = rs.getDate("CreatedDate");
                int roomHistoriesID = rs.getInt("RoomHistoriesID");

                // Các thông tin lấy từ join
                int totalHouses = rs.getInt("totalHouses");
                int totalRooms = rs.getInt("totalRooms");
                int emptyRooms = rs.getInt("emptyRooms");

                // Tạo đối tượng User và thêm vào danh sách
                User user = new User(id, facebookUserid, googleUserid, username, password, email, phone,
                        dateOfBirth, address, StatusID, roleID, avatar, createdDate, roomHistoriesID, totalHouses, totalRooms, emptyRooms);
                accounts.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public static void main(String[] args) {
//        User student = new User();
//        student.setUsername("Phong Nguyễn");
//        student.setEmail("phongnnhe176274@fpt.edu.vn");
//        student.setPhone("0398601399");
//        student.setAddress("Hiệp Hòa, Bắc Giang");
//        student.setStatusID(1); // Ví dụ về trạng thái ID
//        student.setRoleID(1);   // Ví dụ về role ID
//        student.setCreatedDate(new Date()); // Ngày tạo hiện tại
//        student.setId(19);
        ManageAccount manageAccount = new ManageAccount();
        int page = 1;
        int pageSize = 7;
        int listaccount = manageAccount.getAccountCount();
        List<User> listAcc = manageAccount.getAccountsByPage(page, pageSize);
        System.out.println(listAcc.toString());
//        int result = manageAccount.updateAccount(student);
//
//        if (listaccount > 0) {
//            System.out.println(" thành công!");
//        } else {
//            System.out.println("Không có bản ghi nào được cập nhật.");
//        }
    }

}
