package Controllers.House;

import DAL.House.DAOHouse;
import Models.House;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ListHouse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAOHouse daoHouse = new DAOHouse();

        // Kiểm tra xem có tham số id không (nếu có tức là yêu cầu xóa)
        String houseIdToDelete = request.getParameter("id");
        if (houseIdToDelete != null) {
            // Gọi hàm delete trong DAO để xóa nhà trọ
            int houseId = Integer.parseInt(houseIdToDelete);
            daoHouse.deleteHouseById(houseId);

            // Sau khi xóa, chuyển hướng về danh sách nhà trọ
            response.sendRedirect(request.getContextPath() + "/ListHouse");
            return;
        }

        // Mặc định ownerId là 7
        int ownerId = 7;

        // Nếu có ownerId được truyền qua URL, dùng giá trị đó
        if (request.getParameter("ownerId") != null) {
            ownerId = Integer.parseInt(request.getParameter("ownerId"));
        }

        // Lấy danh sách nhà trọ thuộc ownerId
        List<House> houseList = daoHouse.getHousesByOwnerId(ownerId);

        // Đặt danh sách nhà trọ vào request attribute để hiển thị trên JSP
        request.setAttribute("houseList", houseList);

        // Chuyển hướng đến trang JSP để hiển thị danh sách nhà trọ
        request.getRequestDispatcher("/Views/HouseOwner/ListHouse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
