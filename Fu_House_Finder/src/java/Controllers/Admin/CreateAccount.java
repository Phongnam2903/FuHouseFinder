package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CreateAccount", urlPatterns = {"/createAccount"})
public class CreateAccount extends HttpServlet {

    // Regex cho việc kiểm tra định dạng email cơ bản
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    // Regex cho việc kiểm tra miền email cụ thể
    private static final String DOMAIN_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(gmail\\.com|fpt\\.edu\\.vn)$";

    private static final Logger LOGGER = Logger.getLogger(CreateAccount.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Admin/AdminCreateAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form HTML
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Khởi tạo các thông báo lỗi và thông báo thành công
        String errorUsername = "";
        String errorPassword = "";
        String errorEmail = "";
        String errorPhoneNumber = "";
        String successMessage = "";

        // Kiểm tra đầu vào
        boolean hasError = false;

        // Kiểm tra tên người dùng
        if (username == null || username.trim().isEmpty()) {
            errorUsername = "Tên người dùng không được để trống!";
            hasError = true;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (password == null || confirmPassword == null || password.isEmpty() || confirmPassword.isEmpty()) {
            errorPassword = "Mật khẩu và xác nhận mật khẩu không được để trống!";
            hasError = true;
        } else if (!password.equals(confirmPassword)) {
            errorPassword = "Mật khẩu không khớp!";
            hasError = true;
        } else if (password.length() < 8 || !Pattern.compile("[A-Za-z]").matcher(password).find()
                || !Pattern.compile("[0-9]").matcher(password).find()) {
            errorPassword = "Mật khẩu phải ít nhất 8 ký tự và bao gồm cả chữ cái và số.";
            hasError = true;
        }

        // Kiểm tra định dạng email cơ bản
        if (email == null || email.trim().isEmpty()) {
            errorEmail = "Email không được để trống!";
            hasError = true;
        } else if (!Pattern.matches(EMAIL_REGEX, email)) {
            errorEmail = "Định dạng email không hợp lệ!";
            hasError = true;
        } else if (!Pattern.matches(DOMAIN_REGEX, email)) {
            errorEmail = "Email phải kết thúc bằng @gmail.com hoặc @fpt.edu.vn!";
            hasError = true;
        }

        // Kiểm tra địa chỉ
        if (address == null || address.trim().isEmpty()) {
            // Bạn có thể thêm thông báo lỗi nếu cần
            // Ví dụ:
            // errorAddress = "Địa chỉ không được để trống!";
            // hasError = true;
        }

        if (!hasError) {
            // Tạo đối tượng User mới
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password); // Giả sử ManageAccount sẽ mã hóa mật khẩu
            user.setPhone(phone);
            user.setRoleID(4); // Đặt role ID theo yêu cầu
            user.setStatusID(1); // Đặt status ID theo yêu cầu
            user.setAddress(address);
            user.setCreatedDate(new java.util.Date());

            // Thiết lập các trường không có trong form
            user.setFacebookUserid(null);
            user.setGoogleUserid(null);
            user.setDateOfBirth(null);
            user.setAvatar(null);
            user.setRoomHistoriesID(0);

            // Tạo tài khoản mới
            ManageAccount manageAccount = new ManageAccount();
            int rowsAffected = manageAccount.insertAccount(user); // Phương thức trả về int

            if (rowsAffected > 0) {
                successMessage = "Tạo tài khoản thành công!";
            } else {
                // Nếu chèn thất bại
                errorPassword = "Không thể tạo tài khoản. Vui lòng thử lại!";
            }
        }

        // Đặt các thuộc tính để sử dụng trong JSP
        request.setAttribute("successMessage", successMessage);
        request.setAttribute("errorUsername", errorUsername);
        request.setAttribute("errorPassword", errorPassword);
        request.setAttribute("errorEmail", errorEmail);
        request.setAttribute("errorPhoneNumber", errorPhoneNumber);

        // Chuyển hướng lại trang JSP
        request.getRequestDispatcher("Views/Admin/AdminCreateAccount.jsp").forward(request, response);
    }
}
