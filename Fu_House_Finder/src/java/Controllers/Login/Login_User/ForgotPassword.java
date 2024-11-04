/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * FORGOTPASSWORD.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-28      1.0                PhongNN          Implement forgot password functionality
 */
package Controllers.Login.Login_User;

import DAL.Login.DAOForgot;
import Models.User;
import Validations.RandomCode;
import Validations.SendEmail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ForgotPassword handles user requests to reset their password. It sends a
 * verification code to the user's email to verify their identity.
 * <p>
 * Bugs: None
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotPassword"})
public class ForgotPassword extends HttpServlet {

    /**
     * Handles HTTP GET requests to load the Forgot Password page.
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
        // Forward the user to the Forgot Password view (JSP)
        request.getRequestDispatcher("Views/Login/ForgotPassword.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests to initiate the password reset process.
     * Validates user email, generates a reset code, and sends it to the user's
     * email.
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
        String email = request.getParameter("email"); // Retrieve email input from the user
        String message = ""; // Initialize message variable for error feedback

        // DAO to check if user with this email exists
        DAOForgot daoForgot = new DAOForgot();
        User user = daoForgot.checkUsersForChangePass(email); // Check if user exists

        if (user != null) {
            // Generate a 6-digit random code for password reset
            int code = RandomCode.randomCode(6);
            String newCode = String.valueOf(code); // Convert code to String format

            // Define the email content to be sent to the user
            String subject = "Code Reset Request"; // Email subject line
            String content = "<h1>Code to Reset Password!!</h1>"
                    + "<p>Your Code is: <strong>" + newCode + "</strong></p>";

            // Send the reset code via email
            SendEmail.sendMail(email, subject, content);

            // Store the reset code and email in session attributes for validation in the next step
            session.setAttribute("resetCode", code);
            session.setAttribute("userEmail", email);

            // Redirect to the Reset Password page
            response.sendRedirect("Views/Login/ResetPassword.jsp");
        } else {
            // If email is not found, set an error message
            message = "Account not exist!";
            request.setAttribute("message", message); // Store message in request for displaying on JSP

            // Forward back to Forgot Password page with error message
            request.getRequestDispatcher("Views/Login/ForgotPassword.jsp").forward(request, response);
        }
    }
}
