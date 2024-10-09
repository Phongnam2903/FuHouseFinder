package Controllers.User;

import DAL.House.DAOHouse;
import DAL.User.DAOOrder;
import Models.House;
import Models.Order;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@WebServlet(name = "HomePage", urlPatterns = {"/homePage"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOHouse daoHouse = new DAOHouse();

        User user = (User) request.getSession().getAttribute("user");

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
        request.setAttribute("user", user);
        request.getRequestDispatcher("/Views/User/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        if (service.equals("sendOrder")) {
            //lấy dữ liệu cần thiết để xử lý đơn order
            String userIdParam = request.getParameter("userId");
            String fullName = request.getParameter("fullName").trim();
            String phoneNumber = request.getParameter("phoneNumber").trim();
            String email = request.getParameter("email").trim();
            String desire = request.getParameter("desire").trim();
            User user = (User) request.getSession().getAttribute("user");

            //thông báo
            String errorMessage = null;
            String successMessage = null;
            boolean hasError = false;

            if (user == null) {
                response.sendRedirect("login");
                return;
            }

            int userId = Integer.parseInt(userIdParam);

            //kiểm tra lỗi
            if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || desire.isEmpty()) {
                errorMessage = "Fields marked with * cannot be blank or contain only spaces!";
                hasError = true;
            }

            if (!phoneNumber.matches("\\d{11}")) {
                errorMessage = "Phone number must contain 11 digits!";
                hasError = true;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {  // Kiểm tra định dạng email
                errorMessage = "Invalid email format!";
                hasError = true;
            }

            if (hasError) {
                //gửi thông báo lỗi qua URL
                response.sendRedirect(request.getContextPath() + "/homePage?status=error&message=" + errorMessage);
            } else {
                //thêm order vào csdl
                Order order = new Order();
                order.setUserID(userId);
                order.setFullName(fullName);
                order.setPhoneNumber(phoneNumber);
                order.setEmail(email);
                order.setOrderContent(desire);
                order.setStatusID(1);
                order.setOrderedDate(new Date());

                DAOOrder daoOrder = new DAOOrder();
                int result = daoOrder.addOrder(order);

                if (result > 0) {
                    // Thêm thành công
                    successMessage = "Order placed successfully!";
                    response.sendRedirect(request.getContextPath() + "/homePage?status=success&message=" + successMessage);
                } else {
                    // Thêm thất bại
                    errorMessage = "Failed to order accommodation!";
                    response.sendRedirect(request.getContextPath() + "/homePage?status=error&message=" + errorMessage);
                }
            }
        }
    }

}
