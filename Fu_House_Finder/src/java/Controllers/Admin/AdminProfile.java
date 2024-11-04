package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        adminProfile.setUsername(username);
        adminProfile.setEmail(email);
        adminProfile.setPhone(phone);
        adminProfile.setAddress(address);
        adminProfile.setRoleID(1);
        adminProfile.setStatusID(1);
        
        ManageAccount managerAccount = new ManageAccount();
        int update = managerAccount.updateAccount(adminProfile);
        
        if (update > 0) {
            request.getSession().setAttribute("user", adminProfile);
            request.setAttribute("message", "Profile updated successfully");
            request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Failed to update Profile. Please try again.");
            request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
        }
    }
    
}
