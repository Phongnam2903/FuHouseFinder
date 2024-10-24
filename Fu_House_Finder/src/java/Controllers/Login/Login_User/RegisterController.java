package Controllers.Login.Login_User;

import DAL.User.UserProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "register", value = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("confirmPass");

        // Validation flags
        boolean hasError = false;

        // Initialize error messages for each field
        String errorfNameMessage = "", errorlNameMessage = "", errorEmailMessage = "",
                errorPhoneMessage = "", errorPasswordMessage = "", errorConfirmMessage = "";

        // Validate first name
        if (fname == null || fname.trim().isEmpty()) {
            errorfNameMessage = "First name is required.";
            hasError = true;
        }

        // Validate last name
        if (lname == null || lname.trim().isEmpty()) {
            errorlNameMessage = "Last name is required.";
            hasError = true;
        }

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            errorEmailMessage = "Email is required.";
            hasError = true;
        } else if (!isValidEmail(email)) {
            errorEmailMessage = "Invalid email format.";
            hasError = true;
        }

        // Validate phone number
        if (phone == null || phone.trim().isEmpty()) {
            errorPhoneMessage = "Phone number is required.";
            hasError = true;
        } else if (!isValidPhone(phone)) {
            errorPhoneMessage = "Invalid phone number format.";
            hasError = true;
        }

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            errorPasswordMessage = "Password is required.";
            hasError = true;
        } else if (password.length() < 6) {
            errorPasswordMessage = "Password must be at least 6 characters.";
            hasError = true;
        }

        // Validate confirm password
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            errorConfirmMessage = "Confirm password is required.";
            hasError = true;
        } else if (!password.equals(confirmPassword)) {
            errorConfirmMessage = "Passwords do not match.";
            hasError = true;
        }

        // If there is an error, set attributes for each error and forward back to form
        if (hasError) {
            request.setAttribute("errorfNameMessage", errorfNameMessage);
            request.setAttribute("errorlNameMessage", errorlNameMessage);
            request.setAttribute("errorEmailMessage", errorEmailMessage);
            request.setAttribute("errorPhoneMessage", errorPhoneMessage);
            request.setAttribute("errorPasswordMessage", errorPasswordMessage);
            request.setAttribute("errorConfirmMessage", errorConfirmMessage);
            request.getRequestDispatcher("Views/Login/Register.jsp").forward(request, response);
        } else {
            // If validation passes, proceed with user registration
            if (request.getParameter("btnRegister") != null) {
                UserProcess.INSTANCE.create(fname, lname, email, phone, password);
                response.sendRedirect(request.getContextPath() + "/homePage");
            }
        }
    }

    // Utility method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Utility method to validate phone number format (Vietnam phone numbers as example)
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^(03|05|07|08|09)+([0-9]{8})$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
    }
}
