/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * UPDATELANDLORDSTATUS.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-18      1.0                PhongNN          Update landlord status by staff
 */
package Controllers.Staff;

import DAL.Staff.DAOStaff;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UpdateLandlordStatus is a servlet that handles the HTTP POST request to
 * update the status of a landlord. It receives the landlord's ID and the new
 * status, then processes the update in the database.
 * <p>
 * Bugs: None
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "UpdateLandlordStatus", urlPatterns = {"/updateLandlordStatus"})
public class UpdateLandlordStatus extends HttpServlet {

    /**
     * Handles the HTTP POST request to update the status of a landlord.
     * Retrieves the ID and new status from the request, updates the landlord's
     * status using DAOStaff, and redirects to the list of landlords.
     *
     * @param request The HttpServletRequest object that contains the request
     * from the client.
     * @param response The HttpServletResponse object used to send the response
     * to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when handling
     * the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Extract landlord ID, new status, and the current page number from the request parameters
        int id = Integer.parseInt(request.getParameter("id")); // Landlord ID
        int newStatus = Integer.parseInt(request.getParameter("status")); // New status to be updated
        int currentPage = Integer.parseInt(request.getParameter("currentPage")); // Current page number for redirection

        // Create an instance of DAOStaff to perform database operations
        DAOStaff daoStaff = new DAOStaff();

        // Call the method to update the landlord's status in the database
        boolean results = daoStaff.updateLandlordStatus(id, newStatus);

        // If the update is successful, set a success message in the session
        if (results) {
            request.getSession().setAttribute("message", "Status updated successfully!");
        } else {
            // If the update fails, set a failure message in the session
            request.getSession().setAttribute("message", "Failed to update status.");
        }

        // Redirect the user to the list of house owners with the same current page
        response.sendRedirect(request.getContextPath() + "/listhouseowner?page=" + currentPage);
    }
}
