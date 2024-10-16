/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * FHF.JAVA:
 *  FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26       1.0                PhongNN          View Staff Dashboard
 */
package Controllers.Staff;

import DAL.Staff.DAOStaff;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class handles requests to view the staff dashboard. It forwards requests
 * to the appropriate JSP page for rendering the dashboard UI.
 * <p>
 * Bugs: Still have some issues related to searching staff by address.
 *
 * @author Nguyen Nam Phong
 */
public class StaffDashboard extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. This method simply forwards the
     * request to the Staff Dashboard JSP page to display the staff dashboard.
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

        DAOStaff daoStaff = new DAOStaff();

        int totalHouse = daoStaff.getHouseCount();
        int totalRoom = daoStaff.getRoomCount();
        int totalLandlord = daoStaff.getUserCountByRole(5);
        int totalCapacity = daoStaff.getTotalCapacity();

        request.setAttribute("totalLandlord", totalLandlord);
        request.setAttribute("totalRoom", totalRoom);
        request.setAttribute("totalHouse", totalHouse);
        request.setAttribute("totalCapacity", totalCapacity);

        request.getRequestDispatcher("Views/Staff/StaffDashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Since this servlet only
     * forwards requests to the staff dashboard page, both GET and POST requests
     * are handled the same way by calling <code>doGet</code>.
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
        // Forward POST requests to doGet to handle them in the same way
        doGet(request, response);
    }
}
