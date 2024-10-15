package Controllers.Staff;

import DAL.Staff.DAORoomForStaff;
import Models.Room;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RoomDetail", urlPatterns = {"/roomDetail"})
public class RoomDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAORoomForStaff roomDAO = new DAORoomForStaff();
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("error", "Room ID is required.");
                request.getRequestDispatcher("Views/Staff/ListOfRoomDetail.jsp").forward(request, response);
                return;
            }

            int roomId = Integer.parseInt(idParam);
            Room roomDetail = roomDAO.getRoomById(roomId);  // Lấy một đối tượng Room duy nhất

            if (roomDetail == null) {
                request.setAttribute("error", "Room not found.");
            } else {
                request.setAttribute("roomDetails", roomDetail);
            }
            request.getRequestDispatcher("Views/Staff/RoomDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Room ID.");
            request.getRequestDispatcher("Views/Staff/RoomDetail.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while retrieving room details.");
            request.getRequestDispatcher("Views/Staff/RoomDetail.jsp").forward(request, response);
        }
    }

}
