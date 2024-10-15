package Controllers.Staff;

import DAL.User.DAOOrder;
import Models.Order;
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

        // Lấy số trang hiện tại từ request, nếu không có thì mặc định là trang 1
        String pageStr = request.getParameter("page");
        int pageNumber = 1;
        if (pageStr != null) {
            try {
                pageNumber = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                pageNumber = 1; // Trang mặc định nếu có lỗi
            }
        }

        int pageSize = 10;  // Số lượng đơn hàng hiển thị trên mỗi trang

        // Lấy danh sách các đơn hàng
        List<Order> orderList = daoOrder.getAllOrders(pageNumber, pageSize);

        // Lấy tổng số đơn hàng để tính tổng số trang
        int totalOrders = daoOrder.getTotalOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        // Đặt các thuộc tính cho request để hiển thị trên JSP
        request.setAttribute("orderList", orderList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);

        // Điều hướng đến JSP
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
        request.getRequestDispatcher("/Views/Staff/ListOrder.jsp").forward(request, response);
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
