/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAL.House.DAOHouse;
import Models.House;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "HouseList", urlPatterns = {"/houseList"})
public class HouseList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
        } else {
            DAOHouse house = new DAOHouse();
            int page = 1;
            int pageSize = 5;
            String searchName = "";

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            //Check if the search parameter is sent from the request
            if (request.getParameter("search") != null) {
                searchName = request.getParameter("search").trim();
            }
            int totalHouse = house.getHousesCount(searchName);
            int totalPages = (int) Math.ceil((double) totalHouse / pageSize);
            List<House> listHouse = house.getHousesByPage(page, pageSize, searchName);

            request.setAttribute("listHouse", listHouse);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPage", totalPages);
            request.getRequestDispatcher("Views/Staff/ListHouse.jsp").forward(request, response);
        }
    }

}
