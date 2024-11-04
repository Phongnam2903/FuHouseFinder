/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * DELETE.JAVA:
 *  FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26       1.0                PhongNN          Delete Account for Staff
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
 * Servlet for deleting a user account based on the provided account ID. Handles
 * HTTP GET requests to perform the deletion.
 *
 * @author xuxum
 */
@WebServlet(name = "DeleteAccount", urlPatterns = {"/deleteAccount"})
public class DeleteAccount extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method for deleting an account.
     * Retrieves the account ID from the request and calls the delete method in
     * ManageAccount.
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
        //Check Session 
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        // Retrieve the account ID from the request parameter
        String idStr = request.getParameter("id");

        // Check if the ID exists in the request
        if (idStr != null) {
            try {
                // Convert the ID to an integer
                int id = Integer.parseInt(idStr);

                // Call the method to delete the account from ManageAccount
                ManageAccount managerAcc = new ManageAccount();
                boolean result = managerAcc.deleteAccountById(id); // Boolean result to check if deletion is successful

                if (result) {
                    // If deletion is successful, redirect back to the account list
                    response.sendRedirect("viewAccountList");
                } else {
                    // If deletion fails, set an error message and forward to the error page
                    request.setAttribute("error", "Failed to delete account.");
                    request.getRequestDispatcher("Views/Admin/error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // If the account ID is invalid (not an integer), forward to the error page
                request.setAttribute("error", "Invalid account ID.");
                request.getRequestDispatcher("Views/Admin/error.jsp").forward(request, response);
            }
        } else {
            // If the ID is missing from the request, set an error message and forward to the error page
            request.setAttribute("error", "Account ID is missing.");
            request.getRequestDispatcher("Views/Admin/error.jsp").forward(request, response);
        }
    }
}
