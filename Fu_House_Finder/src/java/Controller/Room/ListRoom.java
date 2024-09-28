package Controller.Room;

import java.io.IOException;
import java.util.List;

import DAL.Room.DAORoom;
import Models.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListRoom", urlPatterns = {"/ListRoom"})
public class ListRoom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAORoom daoRoom = new DAORoom();
        List<Room> roomList = daoRoom.getRooms();
        // Đặt danh sách phòng vào request attribute để hiển thị trên JSP
        request.setAttribute("roomList", roomList);
        // Chuyển hướng đến trang JSP để hiển thị danh sách phòng
        request.getRequestDispatcher("/Views/HouseOwner/ListRoom.jsp").forward(request, response);
                String roomId = request.getParameter("id");
                
                if(roomId != null){
                   DAORoom roomDAO = new DAORoom();
                   roomDAO.deleteRoom(Integer.parseInt(roomId));
                   response.sendRedirect("ListRoom");
                }else{
                    List<Room> rooms = daoRoom.getRooms();
                    request.setAttribute("rooms", rooms);
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
