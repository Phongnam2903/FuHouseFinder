package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dateOfBirthStr = request.getParameter("dateOfBirth");

        Date dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateOfBirth = dateFormat.parse(dateOfBirthStr);
            } catch (ParseException ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        User adminProfile = (User) request.getSession().getAttribute("user");
        adminProfile.setUsername(username);
        adminProfile.setEmail(email);
        adminProfile.setPhone(phone);
        adminProfile.setAddress(address);
        adminProfile.setDateOfBirth(dateOfBirth);
        adminProfile.setStatusID(1);
        adminProfile.setRoleID(1);
        ManageAccount update = new ManageAccount();
        int updatedRows = update.updateAccount(adminProfile); // Giả sử phương thức này trả về true/false

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
