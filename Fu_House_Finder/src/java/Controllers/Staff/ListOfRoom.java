package Controllers.Staff;

import DAL.Staff.DAORoomForStaff;
import Models.House;
import Models.Room;
import java.util.List;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListOfRoom", urlPatterns = {"/listOfRoom"})
public class ListOfRoom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAORoomForStaff roomDAO = new DAORoomForStaff();

        try {
            // Lấy giá trị id từ request và kiểm tra null
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("error", "House ID is required.");
                request.getRequestDispatcher("Views/Staff/ListOfRooms.jsp").forward(request, response);
                return;
            }

            int houseId = Integer.parseInt(idParam);

            // Lấy thông tin House dựa trên id
            House houseDetail = roomDAO.getHouseById(houseId);
            // Lấy danh sách phòng từ House
            List<Room> roomList = roomDAO.getRoomsByHouseId(houseId);

            // Kiểm tra nếu houseDetail là null
            if (houseDetail == null) {
                request.setAttribute("error", "House not found.");
            } else {
                String[] imageList = houseDetail.getImage().split(",");
                // Gửi dữ liệu houseDetail sang trang JSP để hiển thị
                request.setAttribute("imageList", imageList);
                request.setAttribute("roomDetail", houseDetail);
                request.setAttribute("roomList", roomList); // Cập nhật danh sách phòng
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid House ID.");
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while retrieving house details.");
        }

        // Chuyển tiếp yêu cầu tới trang JSP để hiển thị hoặc hiển thị lỗi
        request.getRequestDispatcher("Views/Staff/ListOfRooms.jsp").forward(request, response);
    }
}
