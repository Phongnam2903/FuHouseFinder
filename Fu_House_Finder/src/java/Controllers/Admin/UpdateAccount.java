/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * UPDATEACCOUNT.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26      1.0                PhongNN          Update Account for Staff
 */
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
 * Servlet to handle updating account details of staff members in the system.
 * The servlet handles both GET and POST requests for updating account
 * information, including input validation for email and phone formats.
 * <p>
 * Bugs: None
 *
 * @author PhongNN
 */
@WebServlet(name = "UpdateAccount", urlPatterns = {"/updateAccount"})
public class UpdateAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to fetch the details of a specific user
     * account and forward the data to the update form for editing.
     *
     * @param request The HttpServletRequest object containing the client's
     * request.
     * @param response The HttpServletResponse object containing the servlet's
     * response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when the
     * servlet handles the GET request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the "id" parameter from the request
        String idParam = request.getParameter("id");

        // Validate the ID parameter
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("error", "ID is not available.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            // Parse the "id" parameter to an integer
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            // If the ID is not valid, set an error message and forward to the view
            request.setAttribute("error", "ID is not available.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Fetch the user details from the database using the ID
        ManageAccount manager = new ManageAccount();
        User user = manager.getAccountById(id);

        // If no user is found, set an error message and forward to the view
        if (user == null) {
            request.setAttribute("error", "Cannot find account with this ID.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Set the user details as request attributes and forward to the JSP for display
        request.setAttribute("user", user);
        request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST request to process the form submission and update
     * the account details in the database. Includes validation for required
     * fields and correct formats.
     *
     * @param request The HttpServletRequest object containing the client's
     * request.
     * @param response The HttpServletResponse object containing the servlet's
     * response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when the
     * servlet handles the POST request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the "id" parameter from the request
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            // If ID is not provided, set an error message and forward back to the form
            request.setAttribute("error", "ID is not available.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            // Parse the "id" parameter to an integer
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            // If the ID is invalid, set an error message and forward to the form
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Invalid ID.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Retrieve and sanitize form parameters
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String address = request.getParameter("address").trim();
        int statusId = Integer.parseInt(request.getParameter("status"));

        // Validate required fields
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty()) {
            // Set an error message if required fields are missing
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Please fill in all required fields.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate username length (max 20 characters)
        if (username.length() > 20) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Full Name is too long (max 20 characters).");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate email length (max 100 characters)
        if (email.length() > 100) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Email is too long (max 100 characters).");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate phone length (max 15 characters)
        if (phone.length() > 10) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Phone number is too long (max 15 characters).");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate email format (must be @gmail.com)
        if (!email.matches("^[\\w.-]+@gmail\\.com$")) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Email must be in the format of @gmail.com.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Validate phone number (must be 10 digits)
        if (!phone.matches("^\\d{10}$")) {
            request.setAttribute("id", idParam);
            request.setAttribute("error", "Phone number must be exactly 10 digits.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
            return;
        }

        // Create a new user object and set its properties
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setStatusID(statusId);
        user.setRoleID(4);  // Assuming 4 represents staff role

        // Update the account in the database
        ManageAccount manager = new ManageAccount();
        int result = manager.updateAccount(user);

        // Check if the update was successful
        if (result > 0) {
            // If update is successful, redirect to the account list with a success message
            response.sendRedirect(request.getContextPath() + "/viewAccountList?successMessage=Account updated successfully");
        } else {
            // If update fails, set an error message and forward to the form
            request.setAttribute("id", idParam);
            request.setAttribute("errorMessage", "Failed to update account.");
            request.getRequestDispatcher("Views/Admin/AdminUpdateAccount.jsp").forward(request, response);
        }
    }
}
