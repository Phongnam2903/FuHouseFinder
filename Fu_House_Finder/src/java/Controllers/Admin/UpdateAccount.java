/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * UPDATEACCOUNT.JAVA:
 *  FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26       1.0                PhongNN          Update Account for Staff
 */
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

@WebServlet(name = "UpdateAccount", urlPatterns = {"/updateAccount"})
public class UpdateAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("error", "ID is not available.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID is not available.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Give information account from data
        ManageAccount manager = new ManageAccount();
        User user = manager.getAccountById(id);

        if (user == null) {
            request.setAttribute("error", "Cannot find account with this ID.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("error", "ID is not available.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Invalid ID.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Lấy các tham số từ form
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String statusParam = request.getParameter("status");
        String address = request.getParameter("address").trim();
        String dateOfBirthStr = request.getParameter("dateOfBirth");

        // Kiểm tra các tham số bắt buộc
        if (username == null || username.isEmpty()
                || email == null || email.isEmpty()
                || phone == null || phone.isEmpty()
                || statusParam == null || statusParam.isEmpty()) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Please fill in all required fields.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate length
        if (username.length() > 50) {
            request.setAttribute("id", idParam);

            request.setAttribute("error", "Username is too long (max 50 characters).");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }
        if (email.length() > 100) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Email is too long (max 100 characters).");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }
        if (phone.length() > 15) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Phone number is too long (max 15 characters).");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate phone number format
        if (!phone.matches("^\\d{10}$")) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Phone number is only 10 digits.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        int statusID;
        try {
            statusID = Integer.parseInt(statusParam);
        } catch (NumberFormatException e) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Invalid status.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Parse dateOfBirth string to Date
        Date dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                dateOfBirth = sdf.parse(dateOfBirthStr);
            } catch (ParseException ex) {
                request.setAttribute("id", idParam);
                request.setAttribute("error", "Invalid date format (yyyy-MM-dd).");
                request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
                return;
            }
        }

        // Tạo đối tượng User với thông tin cập nhật
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setStatusID(statusID);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setRoleID(4);

        // Cập nhật tài khoản trong cơ sở dữ liệu
        ManageAccount manager = new ManageAccount();
        int result = manager.updateAccount(user);

        if (result > 0) {
            // Nếu cập nhật thành công, đặt thông báo thành công
            request.setAttribute("successMessage", "Account updated successfully.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
        } else {
            // Nếu cập nhật thất bại, hiển thị thông báo lỗi
            request.setAttribute("id", idParam);
            request.setAttribute("errorMessage", "Failed to update account.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
        }

    }

}
