package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "UpdateAccount", urlPatterns = {"/updateAccount"})
public class UpdateAccount extends HttpServlet {

    // This method will handle GET requests, typically to show the update form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the account ID from the request
        int id = Integer.parseInt(request.getParameter("id"));

        // Fetch the account details using the ManageAccount class
        ManageAccount manager = new ManageAccount();
        User student = manager.getAccountById(id);

        // Set the student object in the request scope so that it can be displayed in the form
        request.setAttribute("student", student);

        // Forward the request to the update form (JSP page)
        request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
    }

    // This method will handle POST requests to actually update the account
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get values from the form
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int roleID = Integer.parseInt(request.getParameter("role"));

        // Create a User object with the updated information
        User student = new User();
        student.setId(id);
        student.setUsername(username);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAddress(address);
        student.setRoleID(roleID);

        // Update the account using ManageAccount
        ManageAccount manager = new ManageAccount();
        int result = manager.updateAccount(student); // Use updateAccount instead of insertAccount

        if (result > 0) {
            // If update is successful, redirect to the account list page
            response.sendRedirect("accountList");
        } else {
            // If update fails, forward to an error page
            request.setAttribute("error", "Failed to update the account.");
            request.getRequestDispatcher("Views/Admin/Error.jsp").forward(request, response);
        }
    }
}
