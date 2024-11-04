package Controllers.Staff;

import DAL.House.DAOHouse;
import DAL.User.DAOOrder;
import Models.House;
import Models.Order;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ListOrder", urlPatterns = {"/listOrder"})
public class ListOrder extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOOrder daoOrder = new DAOOrder();
        DAOHouse daoHouse = new DAOHouse();

        //lấy giá trị tìm kiếm
        String search = request.getParameter("search");

        //lấy các giá trị từ form để lọc
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String filterStatus = request.getParameter("filterStatus");
        String sortOrder = request.getParameter("sortOrder");

        //xử lý lấy thông tin chi tiết order
        String orderIdParam = request.getParameter("orderId");
        Order selectedOrder = null;
        if (orderIdParam != null) {
            try {
                int orderId = Integer.parseInt(orderIdParam);
                selectedOrder = daoOrder.getOrderById(orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        //lấy số trang hiện tại từ request, nếu không có thì mặc định là trang 1
        String pageStr = request.getParameter("page");
        int pageNumber = 1;
        if (pageStr != null) {
            try {
                pageNumber = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                pageNumber = 1; //trang mặc định nếu có lỗi
            }
        }

        //số lượng order hiển thị trên mỗi trang
        int pageSize = 10;
        int itemsPerPage = 10;

        //lấy danh sách các order nếu có search hoặc filter
        List<Order> orderList;
        int totalOrders;
        if ((search != null && !search.trim().isEmpty())
                || (fromDate != null && !fromDate.isEmpty())
                || (toDate != null && !toDate.isEmpty())
                || (filterStatus != null && !filterStatus.isEmpty())
                || (sortOrder != null && !sortOrder.isEmpty())) {
            // Tìm kiếm dựa trên từ khóa và filter
            orderList = daoOrder.searchOrdersByQueryAndFilter(search, fromDate, toDate, filterStatus, sortOrder, pageNumber, pageSize);
            totalOrders = daoOrder.getTotalOrdersSearchAndFilter(search, fromDate, toDate, filterStatus);
        } else {
            // Lấy toàn bộ đơn đặt hàng nếu không có tìm kiếm
            orderList = daoOrder.getAllOrders(pageNumber, pageSize);
            totalOrders = daoOrder.getTotalOrders();
        }
        List<House> houseList = daoHouse.getHousesWithPricesAndStarStaff();

        //tính tổng số trang
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        //đặt các thuộc tính cho request để hiển thị trên JSP
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("orderList", orderList);
        request.setAttribute("houseList", houseList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("selectedOrder", selectedOrder);
        request.setAttribute("search", search);
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);
        request.setAttribute("filterStatus", filterStatus);
        request.setAttribute("sortOrder", sortOrder);

        //điều hướng đến JSP
        request.getRequestDispatcher("/Views/Staff/ListOrder.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOOrder daoOrder = new DAOOrder();

        //khởi tạo biến để lưu thông báo và hiển thị lên jsp
        String errorMessage = null;
        String successMessage = null;

        // Lấy các thông tin từ request và kiểm tra
        String orderIdParam = request.getParameter("orderId");
        String orderStatusParam = request.getParameter("orderStatus");
        String houseIdParam = request.getParameter("houseId");

        if (orderStatusParam == null || orderStatusParam.isEmpty() || houseIdParam == null || houseIdParam.isEmpty()) {
            errorMessage = "Dont't have information to solved accommodation!";
        }

        //nếu có lỗi, quay lại với thông báo lỗi
        if (errorMessage != null) {
            response.sendRedirect(request.getContextPath() + "/listOrder?status=error&message=" + errorMessage);
            return;
        }

        //chuyển đổi sang dạng int
        int orderId = Integer.parseInt(orderIdParam);
        int orderStatus = Integer.parseInt(orderStatusParam);
        int houseId = Integer.parseInt(houseIdParam);

        //lấy user từ session, kiểm tra và lấy id
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getId();

        //tạo đối tượng Order để cập nhật
        Order order = new Order();
        order.setId(orderId);
        order.setStatusID(orderStatus);
        order.setHouseID(houseId);
        order.setSolvedBy(userId);
        order.setSolvedDate(new java.sql.Date(System.currentTimeMillis()));

        //cập nhật order
        int result = daoOrder.updateOrder(order);

        //kiểm tra kết quả cập nhật
        if (result > 0) {
            successMessage = "Solve Order Accommodation Successfully!";
        } else {
            errorMessage = "Fail To Solve Order Accommodation!";
        }

        //thiết lập thông báo vào yêu cầu trước khi chuyển hướng
        if (errorMessage != null) {
            //gửi thông báo lỗi qua URL với tham số "error"
            response.sendRedirect(request.getContextPath() + "/listOrder" + "?status=error&message=" + errorMessage);
        } else if (successMessage != null) {
            //gửi thông báo thành công qua URL với tham số "success"
            response.sendRedirect(request.getContextPath() + "/listOrder" + "?status=success&message=" + successMessage);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
