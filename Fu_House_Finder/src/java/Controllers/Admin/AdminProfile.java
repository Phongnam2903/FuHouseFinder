package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminProfile", urlPatterns = {"/admin_profile"})
public class AdminProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User admin = (User) request.getSession().getAttribute("user");
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User adminProfile = (User) request.getSession().getAttribute("user");

        // Kiểm tra xem adminProfile có tồn tại trong session không
        if (adminProfile == null) {
            request.setAttribute("message", "User not found in session, please log in.");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
            return; // Chặn tiếp tục xử lý nếu không có user trong session
        }

        // Lấy thông tin từ form và cập nhật vào đối tượng adminProfile
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        adminProfile.getId();
        adminProfile.setUsername(username);
        adminProfile.setEmail(email);
        adminProfile.setPhone(phone);
        adminProfile.setAddress(address);
        adminProfile.setStatusID(1); // Giả sử StatusID là 1 cho admin
        adminProfile.setRoleID(1);   // Giả sử RoleID là 1 cho admin

        ManageAccount update = new ManageAccount();
        int updatedRows = update.updateAccount(adminProfile); // Cập nhật adminProfile

        if (updatedRows > 0) {
            // Cập nhật lại session nếu thành công
            request.getSession().setAttribute("user", adminProfile);
            request.setAttribute("message", "Profile updated successfully!");
            Logger.getLogger(AdminProfile.class.getName()).log(Level.INFO, "Profile updated for user: " + username);
        } else {
            request.setAttribute("message", "Profile update failed! No rows updated.");
            Logger.getLogger(AdminProfile.class.getName()).log(Level.WARNING, "Failed to update profile for user: " + username);
        }

        request.setAttribute("admin", adminProfile);
        request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
    }

}
