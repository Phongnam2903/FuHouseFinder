/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * LISTOFROOM.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-19      1.0                PhongNN          Display a list of rooms for staff to view details
 */
package Controllers.Staff;

import DAL.Staff.DAOHouseOwner;
import DAL.Staff.DAORoomForStaff;
import Models.House;
import Models.Room;
import Models.User;
import java.util.List;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ListOfRoom servlet is responsible for handling HTTP GET requests to display
 * the list of rooms for a specific house. It retrieves the house and its
 * associated rooms from the database and forwards the information to the JSP
 * for presentation.
 * <p>
 * Bugs: None
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "ListOfRoom", urlPatterns = {"/listOfRoom"})
public class ListOfRoom extends HttpServlet {

    /**
     * Handles the HTTP GET request for displaying the list of rooms associated
     * with a specific house. It retrieves the house and room details from the
     * database using the house ID and forwards the data to a JSP for rendering.
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAORoomForStaff roomDAO = new DAORoomForStaff(); // DAO for handling room data operations

        try {
            // Retrieve the house ID from the request and validate
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                // If no ID is provided, set an error message and forward to the JSP
                request.setAttribute("error", "House ID is required.");
                request.getRequestDispatcher("Views/Staff/ListOfRooms.jsp").forward(request, response);
                return;
            }

            // Convert house ID to integer
            int houseId = Integer.parseInt(idParam);

            // Retrieve house details based on the house ID
            House houseDetail = roomDAO.getHouseById(houseId);
            // Retrieve the list of rooms for the specified house
            List<Room> roomList = roomDAO.getRoomsByHouseId(houseId);

            // If house is not found, set an error message
            if (houseDetail == null) {
                request.setAttribute("error", "House not found.");
            } else {
                // Split the image URLs (if multiple images) and store them in an array
                String[] imageList = houseDetail.getImage().split(",");

                // Set attributes for the house details, images, and room list
                request.setAttribute("imageList", imageList);
                request.setAttribute("roomDetail", houseDetail);
                request.setAttribute("roomList", roomList); // Update the room list
            }

        } catch (NumberFormatException e) {
            // Handle invalid house ID (non-numeric)
            request.setAttribute("error", "Invalid House ID.");
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            request.setAttribute("error", "An error occurred while retrieving house details.");
        }

        // Forward the request and response to the JSP page for rendering
        request.getRequestDispatcher("Views/Staff/ListOfRooms.jsp").forward(request, response);
    }
}
