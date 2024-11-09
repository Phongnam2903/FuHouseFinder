package Controller.Room;

import DAL.Room.DAORoom;
import Models.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListRoom", urlPatterns = {"/ListRoom"})
public class ListRoom extends HttpServlet {

    public final int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageIndex = 1;
        int pageHouse = 1;
        String raw_houseId = request.getParameter("houseId");
        if (raw_houseId != null && !raw_houseId.isBlank()) {
            pageHouse = Integer.parseInt(raw_houseId);
        }

        String raw_pageIndex = request.getParameter("page");
        if (raw_pageIndex != null && !raw_pageIndex.isBlank()) {
            pageIndex = Integer.parseInt(raw_pageIndex);
        }

        DAORoom daoRoom = new DAORoom();
        List<Room> roomList = daoRoom.getRoomsByHouseIdPaging(pageHouse, pageIndex, PAGE_SIZE);
        int totalItems = daoRoom.countRoomsByHouseId(pageHouse);
        int totalPages = totalItems % PAGE_SIZE == 0 ? totalItems / PAGE_SIZE : totalItems / PAGE_SIZE + 1;
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("houseId", pageHouse);
        // Đặt danh sách phòng vào request attribute để hiển thị trên JSP
        request.setAttribute("roomList", roomList);
        System.out.println(roomList.size());

        String roomId = request.getParameter("id");

        if (roomId != null) {
            DAORoom roomDAO = new DAORoom();
            roomDAO.deleteRoom(Integer.parseInt(roomId));
            response.sendRedirect("ListRoom?successDeleteR=true&houseId=" + pageHouse);
        } else {
            request.getRequestDispatcher("/Views/HouseOwner/ListRoom.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý POST nếu cần thiết
    }

    @Override
    public String getServletInfo() {
        return "Servlet hiển thị danh sách phòng";
    }
}
