/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

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
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            String service = request.getParameter("service");

            if (service.equals("changePass")) {
                request.getRequestDispatcher("/Views/User/ChangePassword.jsp").forward(request, response);
            } else {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/Views/User/UserProfile.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login");
        }
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
        String service = request.getParameter("service");

        if (service.equals("updateProfile")) {
            User user = (User) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("login");
                return;
            }

            int userId = user.getId();

            String email = request.getParameter("email").trim();
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            user.setId(userId);

            ManageAccount updateProfile = new ManageAccount();
            int result = updateProfile.updateAccount(user);

            if (result > 0) {
                request.setAttribute("message", "Update Profile Successfully!");
                request.setAttribute("alertClass", "alert-success");
            } else {
                request.setAttribute("message", "Fail To Update Profile!");
                request.setAttribute("alertClass", "alert-danger");
            }

            request.getRequestDispatcher("/Views/User/UserProfile.jsp").forward(request, response);
        }

        if (service.equals("changePass")) {
            User user = (User) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("login");
                return;
            }

            int userId = user.getId();
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            ManageAccount manageAccount = new ManageAccount();

            if (manageAccount.checkOldPassword(userId, oldPassword)) {
                //kiểm tra mật khẩu mới và xác nhận mật khẩu
                if (newPassword != null && newPassword.equals(confirmPassword)) {
                    user.setPassword(newPassword);

                    //cập nhật mật khẩu trong cơ sở dữ liệu
                    int result = manageAccount.changePassword(user);

                    if (result > 0) {
                        request.setAttribute("message", "Change Password Successfully!");
                        request.setAttribute("alertClass", "alert-success");
                    } else {
                        request.setAttribute("message", "Fail To Change Password!");
                        request.setAttribute("alertClass", "alert-danger");
                    }
                } else {
                    request.setAttribute("message", "New password and confirm password do not match!");
                    request.setAttribute("alertClass", "alert-danger");
                }
            } else {
                request.setAttribute("message", "Old password is incorrect!");
                request.setAttribute("alertClass", "alert-danger");
            }

            request.getRequestDispatcher("/Views/User/ChangePassword.jsp").forward(request, response);
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
