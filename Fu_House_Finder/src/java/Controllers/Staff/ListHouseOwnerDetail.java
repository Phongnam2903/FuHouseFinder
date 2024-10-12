package Controllers.Staff;

import DAL.Staff.DAOHouseOwner;
import Models.House; // Đảm bảo bạn có import đúng lớp House
import Models.User; // Đảm bảo bạn có import đúng lớp User
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ListHouseOwnerDetail", urlPatterns = {"/listHouseOwnerDetail"})
public class ListHouseOwnerDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOHouseOwner houseOwner = new DAOHouseOwner();

        // Kiểm tra và lấy landlordId
        int landlordId;
        try {
            landlordId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid landlord ID");
            return; // Trả về lỗi nếu landlordId không hợp lệ
        }

        // Xác định trang và kích thước trang
        int page = 1;
        int pageSize = 7;

        // Kiểm tra tham số page
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1; // Đảm bảo page không âm
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number");
                return; // Trả về lỗi nếu page không hợp lệ
            }
        }

        int totalAccount = houseOwner.getHouseOwnerCount();
        int totalPages = (int) Math.ceil((double) totalAccount / pageSize);

        User landlordDetail = houseOwner.getLandlordDetailById(landlordId);
        List<House> listHouses = houseOwner.getHouseByPage(landlordId, page, pageSize); // Chú ý kiểu dữ liệu

        request.setAttribute("listHouses", listHouses); // Thay đổi tên biến để phù hợp
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPages);
        request.setAttribute("landlordDetail", landlordDetail);
        request.getRequestDispatcher("Views/Staff/ListHouseOwnerDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Không có xử lý trong phương thức POST
    }
}
