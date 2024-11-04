/*
 * COPYRIGHT(C) 2024, Group2-SE1866-KS.
 * REGISTERCONTROLLER.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version            AUTHOR            DESCRIPTION
 * 2024-10-28      1.0                PhongNN           Implement user registration controller
 */
package Controllers.Login.Login_User;

import DAL.User.UserProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * RegisterController class handles user registration requests and validation.
 * <p>
 * This class validates user input, checks format, and if no errors are found,
 * saves the new user data.
 * </p>
 * <p>
 * Bugs: None known
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "register", value = "/register")
public class RegisterController extends HttpServlet {

    /**
     * Handles HTTP GET requests to display the registration page.
     *
     * @param request The HttpServletRequest object from the client.
     * @param response The HttpServletResponse object sent to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs during the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward request to the registration view (JSP)
        request.getRequestDispatcher("Views/Login/Register.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests to process the user registration form. This
     * method validates each input field and returns errors if any validation
     * fails.
     *
     * @param request The HttpServletRequest object from the client.
     * @param response The HttpServletResponse object sent to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs during the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form input parameters
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validation flags and error message placeholders
        boolean hasError = false;
        String errorfNameMessage = "", errorlNameMessage = "", errorEmailMessage = "",
                errorPhoneMessage = "", errorPasswordMessage = "", errorConfirmMessage = "";

        // Define maximum length constraints for each field
        int maxFNameLength = 50;
        int maxLNameLength = 50;
        int maxEmailLength = 100;
        int maxPhoneLength = 10;
        int maxPasswordLength = 15;

        // Validate first name
        if (fname == null || fname.trim().isEmpty()) {
            errorfNameMessage = "First name is required.";
            hasError = true;
        } else if (fname.length() > maxFNameLength) {
            errorfNameMessage = "First name can't exceed " + maxFNameLength + " characters.";
            hasError = true;
        }

        // Validate last name
        if (lname == null || lname.trim().isEmpty()) {
            errorlNameMessage = "Last name is required.";
            hasError = true;
        } else if (lname.length() > maxLNameLength) {
            errorlNameMessage = "Last name can't exceed " + maxLNameLength + " characters.";
            hasError = true;
        }

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            errorEmailMessage = "Email is required.";
            hasError = true;
        } else if (!isValidEmail(email)) {  // Check email format
            errorEmailMessage = "Invalid email format.";
            hasError = true;
        } else if (email.length() > maxEmailLength) {
            errorEmailMessage = "Email can't exceed " + maxEmailLength + " characters.";
            hasError = true;
        }

        // Validate phone number
        if (phone == null || phone.trim().isEmpty()) {
            errorPhoneMessage = "Phone number is required.";
            hasError = true;
        } else if (!isValidPhone(phone)) {  // Check phone format (Vietnam phone number)
            errorPhoneMessage = "Invalid phone number format.";
            hasError = true;
        } else if (phone.length() > maxPhoneLength) {
            errorPhoneMessage = "Phone number can't exceed " + maxPhoneLength + " characters.";
            hasError = true;
        }

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            errorPasswordMessage = "Password is required.";
            hasError = true;
        } else if (password.length() < 6) {  // Check minimum password length
            errorPasswordMessage = "Password must be at least 6 characters.";
            hasError = true;
        } else if (password.length() > maxPasswordLength) {
            errorPasswordMessage = "Password can't exceed " + maxPasswordLength + " characters.";
            hasError = true;
        }

        // Validate confirm password
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            errorConfirmMessage = "Confirm password is required.";
            hasError = true;
        } else if (!password.equals(confirmPassword)) {  // Check password match
            errorConfirmMessage = "Passwords do not match.";
            hasError = true;
        }

        // If there are validation errors, redirect to the registration page with error messages
        if (hasError) {
            request.setAttribute("errorfNameMessage", errorfNameMessage);
            request.setAttribute("errorlNameMessage", errorlNameMessage);
            request.setAttribute("errorEmailMessage", errorEmailMessage);
            request.setAttribute("errorPhoneMessage", errorPhoneMessage);
            request.setAttribute("errorPasswordMessage", errorPasswordMessage);
            request.setAttribute("errorConfirmMessage", errorConfirmMessage);

            // Re-populate fields with the input values in case of errors
            request.setAttribute("fname", fname);
            request.setAttribute("lname", lname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);

            request.getRequestDispatcher("Views/Login/Register.jsp").forward(request, response);
        } else {
            // No errors found: proceed with user registration
            if (request.getParameter("btnRegister") != null) {
                UserProcess.INSTANCE.create(fname, lname, email, phone, password); // Register new user
                response.sendRedirect(request.getContextPath() + "/login?successMessage= Create account successfully");
            }
        }
    }

    /**
     * Utility method to validate email format based on regex pattern.
     *
     * @param email The email to validate.
     * @return True if the email matches the pattern, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    /**
     * Utility method to validate phone number format based on Vietnam phone
     * number pattern.
     *
     * @param phone The phone number to validate.
     * @return True if the phone number matches the pattern, false otherwise.
     */
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^(03|05|07|08|09)+([0-9]{8})$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
    }
}
