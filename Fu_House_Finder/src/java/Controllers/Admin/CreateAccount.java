package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 *
 * @author xuxum
 */
public class CreateAccount extends HttpServlet {

    // Regex for email validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Admin/AdminCreateAccount.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Get data from HTML
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String message = "";

        //Check if password matches
        if (!password.equals(confirmPassword)) {
            message = "Password don't mach!";
        } else if (!Pattern.matches(EMAIL_REGEX, email)) {
            message = "Invalid email format";
        } else {
            User student = new User();
            student.setUsername(username);
            student.setEmail(email);
            student.setPassword(password);
            student.setRoleID(4);
            student.setStatusID(1);
            student.setCreatedDate(new java.util.Date());
            ManageAccount createAcc = new ManageAccount();
            createAcc.insertAccount(student);
            message = "Successfull";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("Views/Admin/AdminCreateAccount.jsp").forward(request, response);
    }
}
