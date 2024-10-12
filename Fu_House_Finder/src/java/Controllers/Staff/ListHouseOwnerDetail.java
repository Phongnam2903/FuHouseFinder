package Controllers.Staff;

import DAL.Staff.DAOHouseOwner;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListHouseOwnerDetail", urlPatterns = {"/listHouseOwnerDetail"})
public class ListHouseOwnerDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int landlordId = Integer.parseInt(request.getParameter("id"));

        DAOHouseOwner houseOwner = new DAOHouseOwner();

        User landlordDetail = houseOwner.getLandlordDetailById(landlordId);

        request.setAttribute("landlordDetail", landlordDetail);
        request.getRequestDispatcher("Views/Staff/ListHouseOwnerDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
