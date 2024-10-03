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

@WebServlet(name = "UpdateAccount", urlPatterns = {"/updateAccount"})
public class UpdateAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("error", "ID không hợp lệ.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID không hợp lệ.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Lấy thông tin tài khoản từ cơ sở dữ liệu
        ManageAccount manager = new ManageAccount();
        User user = manager.getAccountById(id);

        if (user == null) {
            request.setAttribute("error", "Không tìm thấy tài khoản với ID này.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Đặt đối tượng 'user' vào phạm vi yêu cầu để JSP có thể truy cập
        request.setAttribute("user", user);
        request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("error", "ID không hợp lệ.");
            request.getRequestDispatcher("Views/Admin/Error.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID không hợp lệ.");
            request.getRequestDispatcher("Views/Admin/Error.jsp").forward(request, response);
            return;
        }

        // Lấy các tham số từ form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String statusParam = request.getParameter("status");
        String address = request.getParameter("address");
        String dateOfBirthStr = request.getParameter("dateOfBirth");

        // Kiểm tra các tham số bắt buộc
        if (username == null || username.isEmpty()
                || email == null || email.isEmpty()
                || phone == null || phone.isEmpty()
                || statusParam == null || statusParam.isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("Views/Admin/Error.jsp").forward(request, response);
            return;
        }

        int statusID;
        try {
            statusID = Integer.parseInt(statusParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Status không hợp lệ.");
            request.getRequestDispatcher("Views/Admin/Error.jsp").forward(request, response);
            return;
        }

        // Parse dateOfBirth string to Date
        Date dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Assuming the date format is 'yyyy-MM-dd'
            try {
                dateOfBirth = sdf.parse(dateOfBirthStr);
            } catch (ParseException ex) {
                Logger.getLogger(UpdateAccount.class.getName()).log(Level.SEVERE, null, ex);
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
            // Nếu cập nhật thành công, chuyển hướng đến danh sách tài khoản
            response.sendRedirect("viewAccountList");
        } else {
            // Nếu cập nhật thất bại, hiển thị thông báo lỗi
            request.setAttribute("error", "Cập nhật tài khoản thất bại.");
            request.getRequestDispatcher("Views/Admin/Error.jsp").forward(request, response);
        }
    }

}
