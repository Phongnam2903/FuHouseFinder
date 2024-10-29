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
 *
 * @author xuxum
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotPassword"})
public class ForgotPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String message = "";

        DAOForgot daoForgot = new DAOForgot();
        User user = daoForgot.checkUsersForChangePass(email);
        if (user != null) {
            int code = RandomCode.randomCode(6);
            String newCode = String.valueOf(code);

            //Send Email
            String subject = "Code Reset Resquest";
            String content = "<h1>Code to Reset Password!!</h1>"
                    + "<p>Your Code is: <strong>" + newCode + "</strong></p>";
            SendEmail.sendMail(email, subject, content);

            session.setAttribute("resetCode", code);
            session.setAttribute("userEmail", email);
            response.sendRedirect("Views/Login/ResetPassword.jsp");
        } else {
            message = "Account not exist!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("Views/Login/ForgotPassword.jsp").forward(request, response);
        }
    }

}
