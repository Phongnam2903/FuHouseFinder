/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * FHF.JAVA:
 *  FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-11       1.0                PhongNN          List House Owner 
 */
package Controllers.Staff;

import DAL.Staff.DAOStaff;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet implementation for listing house owners. This servlet handles
 * requests to display information about house owners, houses, rooms, and
 * landlords. It supports pagination for displaying house owner accounts.
 *
 * @author Nguyen Nam Phong
 */
@WebServlet(name = "ListHouseOwner", urlPatterns = {"/listhouseowner"})
public class ListHouseOwner extends HttpServlet {

    /**
     * Handles the HTTP GET request to retrieve and display house owner
     * information. Fetches data on total houses, rooms, empty rooms, and
     * landlords, and manages pagination for the list of house owner accounts.
     *
     * @param request HttpServletRequest object containing the request made by
     * the client.
     * @param response HttpServletResponse object containing the response sent
     * by the servlet.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Create an instance of DAOStaff to interact with the database
        DAOStaff daoStaff = new DAOStaff();

        // Retrieve the total number of houses, rooms, and empty rooms from the database
        int totalHouse = daoStaff.getHouseCount();
        int totalRoom = daoStaff.getRoomCount();
        int totalRoomEmpty = daoStaff.getRoomEmpty(1); // 2 represents a specific room status

        // Retrieve the total number of landlords (role = 5) from the database
        int totalLandlord = daoStaff.getUserCountByRole(5);

        // Initialize pagination variables
        int page = 1; // Default page number
        int pageSize = 5; // Number of accounts to display per page

        // If a page number is specified in the request, parse and set the page number
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Calculate the total number of accounts and determine the total number of pages
        int totalAccount = daoStaff.getAccountCount();
        int totalPages = (int) Math.ceil((double) totalAccount / pageSize);

        // Retrieve the list of landlord accounts for the current page
        List<User> listAccLandlord = daoStaff.getAccountsByPage(page, pageSize);

        // Set attributes to be forwarded to the JSP page for display
        request.setAttribute("listAccLandlord", listAccLandlord);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPages);
        request.setAttribute("totalHouse", totalHouse);
        request.setAttribute("totalLandlord", totalLandlord);
        request.setAttribute("totalRoom", totalRoom);
        request.setAttribute("totalRoomEmpty", totalRoomEmpty);

        // Forward the request and response to the JSP page for rendering the list of house owners
        request.getRequestDispatcher("Views/Staff/ListHouseOwner.jsp").forward(request, response);
    }
}
