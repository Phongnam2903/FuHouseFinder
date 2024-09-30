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
