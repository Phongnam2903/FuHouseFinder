/*
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26       1.0                Nam Phong            Servlet to handle user login 
 */
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

    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP <code>GET</code> method. Forwards the request to the
     * Login JSP page.
     *
     * @param request The HttpServletRequest object that contains the client's
     * request.
     * @param response The HttpServletResponse object that contains the
     * servlet's response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error is detected.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Processes the login form
     * submission, validates input, authenticates the user, and manages the
     * session with a timeout.
     *
     * @param request The HttpServletRequest object that contains the client's
     * request.
     * @param response The HttpServletResponse object that contains the
     * servlet's response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error is detected.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOLogin login = new DAOLogin();

        // Retrieve form parameters
        String emailOrPhone = request.getParameter("emailOrPhone");
        String password = request.getParameter("password");
        String submit = request.getParameter("submit");

        if (submit != null) {
            boolean hasError = false;

            // Clear previous error messages
            request.removeAttribute("emailError");
            request.removeAttribute("passwordError");
            request.removeAttribute("emailFormatError");
            request.removeAttribute("loginError");
            request.removeAttribute("exceptionError");

            // Check if emailOrPhone is empty
            if (emailOrPhone == null || emailOrPhone.trim().isEmpty()) {
                request.setAttribute("emailError", "Email or phone number can't be empty");
                hasError = true;
            } else {
                // Validate if emailOrPhone is an email or phone number
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                String phoneRegex = "^[0-9]{10,12}$";  // Change this according to your required phone format

                if (!emailOrPhone.matches(emailRegex) && !emailOrPhone.matches(phoneRegex)) {
                    request.setAttribute("emailFormatError", "Invalid email or phone number format");
                    hasError = true;
                }
            }

            // Validate Password
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("passwordError", "Password can't be empty");
                hasError = true;
            }

            if (hasError) {
                // Forward back to login page with error messages
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                return;
            }

            try {
                // Attempt to authenticate the user with either email or phone
                User account = login.loginUser(emailOrPhone, password);
                if (account != null) {
                    // Check account status
                    int statusID = account.getStatusID();
                    switch (statusID) {
                        case 2 -> {
                            request.setAttribute("loginError", "Your account is inactive. Please contact support.");
                            request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                        }
                        case 3 -> {
                            request.setAttribute("loginError", "Your account is banned.");
                            request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                        }
                        default -> {
                            // Authentication successful
                            HttpSession session = request.getSession();
                            session.setAttribute("user", account);
                            session.setMaxInactiveInterval(30 * 60); // Set session timeout to 30 minutes
                            // Redirect based on user role
                            switch (account.getRoleID()) {
                                case 1 ->
                                    response.sendRedirect("viewAccountList");
                                case 3 ->
                                    response.sendRedirect("homePage");
                                case 4 ->
                                    response.sendRedirect("staffDashboard");
                                case 5 ->
                                    response.sendRedirect("ListHouse");
                                default ->
                                    response.sendRedirect("login");
                            }
                        }
                    }
                } else {
                    // Authentication failed
                    request.setAttribute("loginError", "Email/Phone or Password is incorrect!");
                    request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                // Handle exceptions gracefully
                e.printStackTrace();
                request.setAttribute("exceptionError", "An error occurred. Please try again.");
                request.getRequestDispatcher("Views/Login/Login.jsp").forward(request, response);
            }
        }
    }
}
