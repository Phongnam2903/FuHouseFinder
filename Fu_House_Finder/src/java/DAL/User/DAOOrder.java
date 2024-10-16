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
        String sql = "SELECT o.*, u.FullName AS SolvedByUser "
                + "FROM [Order] o "
                + "LEFT JOIN [User] u ON o.SolvedBy = u.ID "
                + "ORDER BY o.ID DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
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
                order.setSolvedByName(rs.getString("SolvedByUser"));

                orderList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    public int getTotalOrders() {
        int totalOrders = 0;
        String sql = "SELECT COUNT(*) AS total FROM [dbo].[Order]";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalOrders;
    }

    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM [ORDER] WHERE ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, orderId);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("ID"));
                order.setFullName(rs.getString("FullName"));
                order.setPhoneNumber(rs.getString("PhoneNumber"));
                order.setEmail(rs.getString("Email"));
                order.setOrderContent(rs.getString("OrderContent"));
                order.setOrderedDate(rs.getDate("OrderedDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;
    }

    public int updateOrder(Order order) {
        int n = 0;
        String sql = "UPDATE [Order] SET StatusID = ?, SolvedDate = ?, SolvedBy = ?, HouseID = ? WHERE ID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, order.getStatusID());
            pre.setDate(2, new java.sql.Date(order.getSolvedDate().getTime()));
            pre.setInt(3, order.getSolvedBy());
            pre.setInt(4, order.getHouseID());
            pre.setInt(5, order.getId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<Order> searchOrdersByQuery(String search, int pageNumber, int pageSize) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT o.*, u.fullName AS SolvedByUser\n"
                + "FROM [Order] o\n"
                + "LEFT JOIN [User] u ON o.solvedBy = u.id\n"
                + "WHERE o.fullName LIKE ? \n"
                + "   OR o.phoneNumber LIKE ? \n"
                + "   OR o.email LIKE ?\n"
                + "ORDER BY o.orderedDate DESC \n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            String searchPattern = "%" + search + "%";
            pre.setString(1, searchPattern);
            pre.setString(2, searchPattern);
            pre.setString(3, searchPattern);
            pre.setInt(4, (pageNumber - 1) * pageSize);
            pre.setInt(5, pageSize);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng Order từ kết quả truy vấn
                Order order = new Order();
                order.setId(rs.getInt("ID"));
                order.setFullName(rs.getString("fullName"));
                order.setPhoneNumber(rs.getString("phoneNumber"));
                order.setEmail(rs.getString("email"));
                order.setOrderedDate(rs.getDate("orderedDate"));
                order.setOrderContent(rs.getString("orderContent"));
                order.setStatusID(rs.getInt("StatusID"));
                order.setSolvedDate(rs.getDate("SolvedDate"));
                order.setSolvedByName(rs.getString("SolvedByUser"));
                // Thêm đối tượng Order vào danh sách
                orderList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    public int getTotalOrdersSearch(String search) {
        int totalOrders = 0;
        String sql = "SELECT COUNT(*) FROM [Order] o "
                + "LEFT JOIN [User] u ON o.userId = u.id "
                + "WHERE o.fullName LIKE ? OR o.phoneNumber LIKE ? OR o.email LIKE ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            String searchPattern = "%" + search + "%";
            pre.setString(1, searchPattern);
            pre.setString(2, searchPattern);
            pre.setString(3, searchPattern);

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalOrders;
    }
}
