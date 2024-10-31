/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * RESETPASSWORD.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-28      1.0                PhongNN          Implement reset password functionality
 */
package Controllers.Login.Login_User;

import DAL.Login.DAOForgot;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ResetPassword handles user requests to reset their password by verifying a
 * code. After verifying, it updates the password in the database.
 * <p>
 * Bugs: None
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/resetPassword"})
public class ResetPassword extends HttpServlet {

    /**
     * Handles HTTP GET requests to load the Reset Password page.
     *
     * @param request The HttpServletRequest object that contains the request
     * from the client.
     * @param response The HttpServletResponse object used to send the response
     * to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when handling
     * the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to Reset Password view (JSP)
        request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests to process the password reset request.
     * Validates the verification code, checks password criteria, and updates
     * the user password if all conditions are met.
     *
     * @param request The HttpServletRequest object that contains the request
     * from the client.
     * @param response The HttpServletResponse object used to send the response
     * to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when handling
     * the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // Initialize session
        String code = request.getParameter("code"); // Get verification code input from user
        String newPassword = request.getParameter("newPassword"); // Get new password input from user
        String confirmPassword = request.getParameter("confirmPassword"); // Get confirm password input from user

        // Initialize message variables to store error feedback for each field
        String message = "", messageCode = "", messageNewPassword = "",
                messageConfirmPassword = "", messageMatchPassword = "",
                messagePattern = "";

        // Validation: Check if verification code is empty
        if (code == null || code.trim().isEmpty()) {
            messageCode = "Verification code can't be empty.";
            request.setAttribute("messageCode", messageCode);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Validation: Check if verification code contains spaces
        if (code.contains(" ")) {
            messageCode = "Verification code can't contain spaces.";
            request.setAttribute("messageCode", messageCode);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Validation: Check if new password is empty
        if (newPassword == null || newPassword.trim().isEmpty()) {
            messageNewPassword = "New Password can't be empty.";
            request.setAttribute("messageNewPassword", messageNewPassword);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Validation: Check if confirm password is empty
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            messageConfirmPassword = "Confirm Password can't be empty.";
            request.setAttribute("messageConfirmPassword", messageConfirmPassword);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Validation: Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            messageMatchPassword = "Passwords don't match!";
            request.setAttribute("messageMatchPassword", messageMatchPassword);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Validation: Check password pattern (8-15 characters, number, lowercase, uppercase, special character)
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
        if (!newPassword.matches(passwordPattern)) {
            messagePattern = "Password must be 8-15 characters long, include a number, a lowercase letter, an uppercase letter, and a special character.";
            request.setAttribute("message", messagePattern);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Retrieve reset code and user email from session, validating session
        int resetCode;
        try {
            resetCode = (int) session.getAttribute("resetCode");
        } catch (Exception e) {
            message = "Invalid session. Please request a new password reset.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null || userEmail.trim().isEmpty()) {
            message = "Invalid session. Please request a new password reset.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        // Check if the provided code matches the reset code
        if (Integer.parseInt(code) == resetCode) {
            // Match confirmed, proceed with password change
            DAOForgot daoForgot = new DAOForgot();
            User user = daoForgot.checkUsersForChangePass(userEmail);

            // Verify if user exists in the system
            if (user != null) {
                daoForgot.changePassword(user.getId(), newPassword); // Update password
                response.sendRedirect(request.getContextPath() + "/login?successMessage=Reset Password successfully!");
            } else {
                message = "Account not exist.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            }
        } else {
            // Code mismatch: Display error
            message = "Invalid verification code!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
        }
    }
}
