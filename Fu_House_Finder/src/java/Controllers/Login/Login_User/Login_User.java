package Controllers.Login.Login_User;

import DAL.Login.DAOLogin;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "Login_User", urlPatterns = {"/login"})
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

        if (submit != null) {
            boolean hasError = false;

            // Clear previous error messages
            request.removeAttribute("emailError");
            request.removeAttribute("passwordError");
            request.removeAttribute("emailFormatError");
            request.removeAttribute("loginError");

            // Validate Email
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("emailError", "Email can't be empty");
                hasError = true;
            } else if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                request.setAttribute("emailFormatError", "Invalid email format! Please check your email.");
                hasError = true;
            }

            // Validate Password
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("passwordError", "Password can't be empty");
                hasError = true;
            }

            if (hasError) {
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                return;
            }

            try {
                User account = login.loginUser(email, password);
                if (account != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", account);
                    switch (account.getRoleID()) {
                        case 1 ->
                            response.sendRedirect("adminDashboard");
                        case 3 ->
                            response.sendRedirect("homePage");
                        case 4 ->
                            response.sendRedirect("ListHouse");
                        default -> {
                        }
                    }
                } else {
                    request.setAttribute("loginError", "Email or Password is incorrect!");
                    request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                }
            } catch (ServletException | IOException e) {
                request.setAttribute("exceptionError", "An error occurred. Please try again.");
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
            }
        }
    }

}
