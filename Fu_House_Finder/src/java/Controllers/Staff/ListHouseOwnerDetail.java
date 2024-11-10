/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * ListHouseOwnerDetail.java:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-13      1.0                Nam Phong         Initial implementation of ListHouseOwnerDetail servlet
 */
package Controllers.Staff;

import DAL.Staff.DAOHouseOwner;
import Models.House;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet responsible for displaying the details of a house owner along with
 * the list of houses they own. Supports pagination to efficiently display
 * information.
 * <p>
 * Bugs: None
 *
 * @Nguyen Nam Phong
 */
@WebServlet(name = "ListHouseOwnerDetail", urlPatterns = {"/listHouseOwnerDetail"})
public class ListHouseOwnerDetail extends HttpServlet {

    /**
     * Handles the HTTP GET request to display house owner details and their
     * associated houses. Supports pagination with a default page size of 7.
     *
     * @param request The HttpServletRequest object containing the client's
     * request.
     * @param response The HttpServletResponse object containing the servlet's
     * response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOHouseOwner houseOwnerDAO = new DAOHouseOwner();
        
        int landlordId;
        try {
            // Retrieve the "id" parameter from the request and parse it as an integer
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Landlord ID is required");
                return;
            }
            landlordId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            // If the "id" parameter is invalid, send a 400 Bad Request error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid landlord ID");
            return;
        }

        int page = 1;          // Current page number, default is 1
        int pageSize = 7;      // Number of items to display per page

        // Check if the "page" parameter is present in the request
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1; // Ensure the page number is not less than 1
                }
            } catch (NumberFormatException e) {
                // If the "page" parameter is invalid, send a 400 Bad Request error
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number");
                return;
            }
        }

        // Retrieve the landlord's details based on the landlordId
        User landlordDetail = houseOwnerDAO.getLandlordDetailById(landlordId);
        if (landlordDetail == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Landlord not found");
            return;
        }

        // Retrieve the total number of houses for this landlord
        int totalAccount = houseOwnerDAO.getHouseOwnerCount();
        // Calculate the total number of pages based on the total count and page size
        int totalPages = (int) Math.ceil((double) totalAccount / pageSize);

        // Retrieve the list of houses for the landlord for the current page
        List<House> listHouses = houseOwnerDAO.getHouseByPage(landlordId, page, pageSize);

        // Set attributes to be used in the JSP for rendering
        request.setAttribute("houseOwnerId", landlordId); // Giả sử landlordId là ID của nhà chủ
        request.setAttribute("listHouses", listHouses);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPages);
        request.setAttribute("landlordDetail", landlordDetail);

        // Forward the request to the JSP page for rendering the details
        request.getRequestDispatcher("Views/Staff/ListHouseOwnerDetail.jsp").forward(request, response);
    }
}
