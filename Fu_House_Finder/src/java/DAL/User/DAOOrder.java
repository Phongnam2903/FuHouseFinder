package DAL.User;

import DAL.DAO;
import Models.Order;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOOrder extends DAO {

    public int addOrder(Order order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserID]\n"
                + "           ,[FullName]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Email]\n"
                + "           ,[OrderContent]\n"
                + "           ,[StatusID]\n"
                + "           ,[OrderedDate])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, order.getUserID());
            pre.setString(2, order.getFullName());
            pre.setString(3, order.getPhoneNumber());
            pre.setString(4, order.getEmail());
            pre.setString(5, order.getOrderContent());
            pre.setInt(6, order.getStatusID());
            pre.setDate(7, new java.sql.Date(System.currentTimeMillis()));

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<Order> getAllOrders(int pageNumber, int pageSize) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Order] \n"
                + "ORDER BY ID DESC\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // Phân trang

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            // Tính toán giá trị offset dựa trên số trang và kích thước trang
            int offset = (pageNumber - 1) * pageSize;

            pre.setInt(1, offset);
            pre.setInt(2, pageSize);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                // Lấy thông tin từ bảng Order
                order.setId(rs.getInt("ID"));
                order.setUserID(rs.getInt("UserID"));
                order.setFullName(rs.getString("FullName"));
                order.setPhoneNumber(rs.getString("PhoneNumber"));
                order.setEmail(rs.getString("Email"));
                order.setOrderContent(rs.getString("OrderContent"));
                order.setStatusID(rs.getInt("StatusID"));
                order.setOrderedDate(rs.getDate("OrderedDate"));
                order.setSolvedDate(rs.getDate("SolvedDate"));
                order.setSolvedBy(rs.getInt("SolvedBy"));
                order.setHouseID(rs.getInt("HouseID"));

                orderList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    // Phương thức đếm tổng số order để hỗ trợ phân trang
    public int getTotalOrders() {
        int totalOrders = 0;
        String sql = "SELECT COUNT(*) AS total FROM [dbo].[Order]";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalOrders;
    }
}
