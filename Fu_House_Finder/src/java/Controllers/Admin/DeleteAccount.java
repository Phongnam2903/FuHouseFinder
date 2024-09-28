package Controllers.Admin;

import DAL.Admin.ManageAccount;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteAccount", urlPatterns = {"/deleteAccount"})
public class DeleteAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID của tài khoản từ request
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);

                // Gọi phương thức xóa tài khoản trong ManageAccount
                ManageAccount managerAcc = new ManageAccount();
                boolean result = managerAcc.deleteAccountById(id); // Kiểm tra kiểu boolean

                if (result) {
                    // Nếu xóa thành công, quay lại danh sách tài khoản
                    response.sendRedirect("viewAccountList");
                } else {
                    // Nếu xóa thất bại, chuyển đến trang lỗi hoặc thông báo
                    request.setAttribute("error", "Failed to delete account.");
                    request.getRequestDispatcher("Views/Admin/error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Nếu ID không hợp lệ, chuyển đến trang lỗi
                request.setAttribute("error", "Invalid account ID.");
                request.getRequestDispatcher("Views/Admin/error.jsp").forward(request, response);
            }
        } else {
            // Nếu ID không tồn tại trong request, chuyển đến trang lỗi
            request.setAttribute("error", "Account ID is missing.");
            request.getRequestDispatcher("Views/Admin/error.jsp").forward(request, response);
        }
    }
}
