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
        String message = "", messageCode = "", messageNewPassword = "",
                messageConfirmPassword = "", messageMatchPassword = "",
                messagePattern = "";

        if (code == null || code.trim().isEmpty()) {
            messageCode = "Verification code can't be empty.";
            request.setAttribute("messageCode", messageCode);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        if (code.contains(" ")) {
            messageCode = "Verification code can't be contain spaces.";
            request.setAttribute("messageCode", messageCode);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        if (newPassword == null || newPassword.trim().isEmpty()) {
            messageNewPassword = "New Password can't be empty.";
            request.setAttribute("messageNewPassword", messageNewPassword);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            messageConfirmPassword = "Confirm Password can't be empty.";
            request.setAttribute("messageConfirmPassword", messageConfirmPassword);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            messageMatchPassword = "Password don't match!";
            request.setAttribute("messageMatchPassword", messageMatchPassword);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
        if (!newPassword.matches(passwordPattern)) {
            messagePattern = "Password must be 8-15 characters long, include a number, a lowercase letter, an uppercase letter, and a special character.";
            request.setAttribute("message", messagePattern);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            return;
        }

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

        if (Integer.parseInt(code) == resetCode) {
            if (newPassword.equals(confirmPassword)) {
                DAOForgot daoForgot = new DAOForgot();
                User user = daoForgot.checkUsersForChangePass(userEmail);
                //check user
                if (user != null) {
                    daoForgot.changePassword(user.getId(), newPassword);
                    message = "Password changed successfully!";
                    response.sendRedirect(request.getContextPath() + "/login");
                } else {
                    message = "Account not exist";
                    request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
                }
            } else {
                message = "Password does not match.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
            }
        } else {
            message = "Invalid verification code!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/ResetPassword.jsp").forward(request, response);
        }
    }

}
