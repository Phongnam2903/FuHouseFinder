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

@WebServlet(name = "ResetPassword", urlPatterns = {"/resetPassword"})
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validation messages
        StringBuilder validationMessages = new StringBuilder();

        if (code == null || code.trim().isEmpty()) {
            validationMessages.append("Verification code can't be empty.<br>");
        } else if (code.contains(" ")) {
            validationMessages.append("Verification code can't contain spaces.<br>");
        }

        if (newPassword == null || newPassword.trim().isEmpty()) {
            validationMessages.append("New Password can't be empty.<br>");
        } else {
            String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
            if (!newPassword.matches(passwordPattern)) {
                validationMessages.append("Password must be 8-15 characters long, include a number, a lowercase letter, an uppercase letter, and a special character.<br>");
            }
        }

        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            validationMessages.append("Confirm Password can't be empty.<br>");
        } else if (!newPassword.equals(confirmPassword)) {
            validationMessages.append("Passwords don't match!<br>");
        }

        if (validationMessages.length() > 0) {
            request.setAttribute("validationMessages", validationMessages.toString());
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        try {
            int resetCode = (int) session.getAttribute("resetCode");
            String userEmail = (String) session.getAttribute("userEmail");

            if (userEmail == null || userEmail.trim().isEmpty()) {
                request.setAttribute("message", "Invalid session. Please request a new password reset.");
                request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
                return;
            }

            if (Integer.parseInt(code) == resetCode) {
                DAOForgot daoForgot = new DAOForgot();
                User user = daoForgot.checkUsersForChangePass(userEmail);

                if (user != null) {
                    daoForgot.changePassword(user.getId(), newPassword);
                    response.sendRedirect(request.getContextPath() + "/login");
                } else {
                    request.setAttribute("message", "Account does not exist.");
                    request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Invalid verification code!");
                request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "Invalid session or code. Please request a new password reset.");
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
        }
    }
}
