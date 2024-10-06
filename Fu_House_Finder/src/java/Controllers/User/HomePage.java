package Controllers.User;

import DAL.House.DAOHouse;
import Models.House;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "HomePage", urlPatterns = {"/homePage"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOHouse daoHouse = new DAOHouse();

        List<House> houseList = daoHouse.getHousesWithPrices();

        //duyệt danh sách nhà và tách ảnh cho mỗi nhà trọ
        for (House house : houseList) {
            //kiểm tra ảnh không rỗng
            if (house.getImage() != null && !house.getImage().isEmpty()) {
                //tách chuỗi và lấy ảnh đầu tiên
                String[] image = house.getImage().split(",");
                house.setImage(image[0]);
            }
        }

        request.setAttribute("houseList", houseList);
        request.getRequestDispatcher("/Views/User/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
