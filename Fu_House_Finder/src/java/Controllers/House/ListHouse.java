package Controllers.House;

import DAL.House.DAOHouse;
import Models.House;
import Models.User;
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

        String houseIdToDelete = request.getParameter("id");
        if (houseIdToDelete != null) {
            int houseId = Integer.parseInt(houseIdToDelete);
            daoHouse.deleteHouseById(houseId);

            response.sendRedirect(request.getContextPath() + "/ListHouse");
            return;
        }
        
        User owner = (User) request.getSession().getAttribute("account");

        if (owner == null) {
            response.sendRedirect("login");
            return;
        }
        
        int ownerId = owner.getId();
        
        //phân trang
        int pageSize = 4;
        String pageStr = request.getParameter("page");
        int pageNumber = 1;
        int itemsPerPage = 4;
        
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                pageNumber = 1;
            }
        }
        
        List<House> houseList = daoHouse.getHousesByOwnerId(ownerId, pageNumber, pageSize);
        
        int totalHouses = daoHouse.getTotalHousesByOwnerId(ownerId);
        int totalPages = (int) Math.ceil((double) totalHouses / pageSize);
        
        if (totalPages < 1) {
            totalPages = 1;
        }
        
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("houseList", houseList);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        
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
