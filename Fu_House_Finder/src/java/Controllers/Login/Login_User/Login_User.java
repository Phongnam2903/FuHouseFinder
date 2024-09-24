/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Login.Login_User;

import DAL.Login.DAOLogin;
import Models.Student;
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
@WebServlet(name = "Login_User", urlPatterns = {"/login_user"})
public class Login_User extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOLogin login = new DAOLogin();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String submit = request.getParameter("submit");
        //check that button submit is not empty
        if (submit != null) {
            //Check that email isn't empty
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("message", "Email can't be empty");
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                return;
            }
            //Check that password isn't empty
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("message", "Password can't be empty");
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                return;
            }
            //check format email
            if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                request.setAttribute("message", "Invalid email format!. Please check email again");
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
            }
            try {
                Student account = login.loginUser(email, password);
                if (account != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
                    if (account.getRoleID() == 1) {
                        response.sendRedirect("viewAccountList");
                    } else {
                        response.sendRedirect("staff");
                    }
                } else {
                    request.setAttribute("message", "Email or Password is not correct!");
                    request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mess", "An error occurred. Please try again.");
                request.getRequestDispatcher("Views/Login/login.jsp").forward(request, response);
            }
        }
    }

}
