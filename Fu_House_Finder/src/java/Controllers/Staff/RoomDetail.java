/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * ROOMDETAIL.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-19      1.0                PhongNN          Display room details for staff
 */
package Controllers.Staff;

import DAL.Staff.DAORoomForStaff;
import Models.Room;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RoomDetail servlet is responsible for handling HTTP GET requests to display
 * details of a specific room. It retrieves the room details from the database
 * and forwards the information to the JSP for rendering.
 * <p>
 * Bugs: None
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "RoomDetail", urlPatterns = {"/roomDetail"})
public class RoomDetail extends HttpServlet {

    /**
     * Handles the HTTP GET request for displaying room details. It retrieves
     * the room data based on the room ID from the request parameter, then
     * forwards the information to a JSP for presentation.
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

        DAORoomForStaff roomDAO = new DAORoomForStaff(); // DAO for handling room-related operations

        try {
            // Retrieve the room ID from the request and validate it
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                // If no ID is provided, set an error message and forward to the JSP
                request.setAttribute("error", "Room ID is required.");
                request.getRequestDispatcher("Views/Staff/ListOfRoomDetail.jsp").forward(request, response);
                return;
            }

            // Convert the room ID to an integer
            int roomId = Integer.parseInt(idParam);

            // Retrieve room details by room ID
            Room roomDetail = roomDAO.getRoomById(roomId);  // Fetches a single Room object

            // If room is not found, set an error message
            if (roomDetail == null) {
                request.setAttribute("error", "Room not found.");
            } else {
                // Set the room details attribute to be forwarded to the JSP
                request.setAttribute("roomDetails", roomDetail);
            }

            // Forward the request and response to the JSP page to display room details
            request.getRequestDispatcher("Views/Staff/RoomDetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle case where the room ID is invalid (non-numeric)
            request.setAttribute("error", "Invalid Room ID.");
            request.getRequestDispatcher("Views/Staff/RoomDetail.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle any other exceptions that may occur during data retrieval
            request.setAttribute("error", "An error occurred while retrieving room details.");
            request.getRequestDispatcher("Views/Staff/RoomDetail.jsp").forward(request, response);
        }
    }

}
