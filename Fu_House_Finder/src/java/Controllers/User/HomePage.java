/*
 * Copyright(C) 2024, FU House Finder.
 * FHF : House Finder Application
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2024-10-12                 1.0                 DuongTD                      Initial implementation of HomePage servlet
 */
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
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * This servlet handles requests related to the home page of the house listing
 * application. It retrieves available houses and displays them to the user. It
 * also processes order submissions from the user.
 *
 * <p>
 * Bugs: None
 *
 * @author DuongTD
 */
@WebServlet(name = "HomePage", urlPatterns = {"/homePage"})
public class HomePage extends HttpServlet {

    /**
     * Handles GET requests to retrieve and display a list of houses. It fetches
     * houses from the database and prepares the data for the view.
     *
     * @param request the HttpServletRequest object that contains the request
     * made by the client
     * @param response the HttpServletResponse object that contains the response
     * from the servlet
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs while handling the
     * request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOHouse daoHouse = new DAOHouse();

        //lấy thông tin người dùng từ session
        User user = (User) request.getSession().getAttribute("user");

        String search = request.getParameter("search");
        String minDistanceParam = request.getParameter("distanceFrom");
        String maxDistanceParam = request.getParameter("distanceTo");
        String minPriceParam = request.getParameter("priceMin");
        String maxPriceParam = request.getParameter("priceMax");
        String minRatingParam = request.getParameter("rating");

        // Convert to appropriate types (null if not provided)
        Float minDistance = (minDistanceParam != null && !minDistanceParam.isEmpty()) ? Float.valueOf(minDistanceParam) : null;
        Float maxDistance = (maxDistanceParam != null && !maxDistanceParam.isEmpty()) ? Float.valueOf(maxDistanceParam) : null;
        Double minPrice = (minPriceParam != null && !minPriceParam.isEmpty()) ? Double.valueOf(minPriceParam) : null;
        Double maxPrice = (maxPriceParam != null && !maxPriceParam.isEmpty()) ? Double.valueOf(maxPriceParam) : null;
        Integer minRating = (minRatingParam != null && !minRatingParam.isEmpty()) ? Integer.valueOf(minRatingParam) : null;

        // Room type filter (convert to Boolean values)
        Boolean singleRoom = request.getParameter("singleRoom") != null;
        Boolean doubleRoom = request.getParameter("doubleRoom") != null;
        Boolean tripleRoom = request.getParameter("tripleRoom") != null;
        Boolean quadRoom = request.getParameter("quadRoom") != null;
        Boolean miniApartment = request.getParameter("miniApartment") != null;
        Boolean fullHouse = request.getParameter("fullHouse") != null;

        // Features filter (convert to Boolean values)
        Boolean fingerprintLock = request.getParameter("fingerprintLock") != null;
        Boolean camera = request.getParameter("camera") != null;
        Boolean parking = request.getParameter("parking") != null;
        Boolean fridge = request.getParameter("fridge") != null;
        Boolean washingMachine = request.getParameter("washingMachine") != null;
        Boolean desk = request.getParameter("desk") != null;
        Boolean kitchen = request.getParameter("kitchen") != null;
        Boolean bed = request.getParameter("bed") != null;
        Boolean privateToilet = request.getParameter("privateToilet") != null;

        //thiết lập phân trang
        int pageSize = 9;
        String pageStr = request.getParameter("page");
        int pageNumber = 1;

        //phân tích số trang
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                pageNumber = 1;
            }
        }

        List<House> houseList = daoHouse.getHousesWithPricesAndStar(minDistance, maxDistance, minPrice, maxPrice, singleRoom, doubleRoom,
                tripleRoom, quadRoom, miniApartment, fullHouse, fingerprintLock,
                camera, parking, fridge, washingMachine, desk, kitchen, bed, privateToilet, minRating, search, pageNumber, pageSize);

        int totalHouses = daoHouse.getCountHousesWithPricesAndStar(minDistance, maxDistance, minPrice, maxPrice, singleRoom,
                doubleRoom, tripleRoom, quadRoom, miniApartment, fullHouse, fingerprintLock,
                camera, parking, fridge, washingMachine, desk, kitchen, bed, privateToilet, minRating, search);

        int totalPages = (int) Math.ceil((double) totalHouses / pageSize);

        //đảm bảo có ít nhất 1 trang
        if (totalPages < 1) {
            totalPages = 1;
        }

        //duyệt danh sách nhà và tách ảnh cho mỗi nhà trọ
        for (House house : houseList) {
            //kiểm tra ảnh không rỗng
            if (house.getImage() != null && !house.getImage().isEmpty()) {
                //tách chuỗi và lấy ảnh đầu tiên
                String[] image = house.getImage().split(",");
                house.setImage(image[0]);
            }
        }

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("houseList", houseList);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/Views/User/HomePage.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to process order submissions from the user.
     * Validates input data and creates a new order if the data is valid.
     *
     * @param request the HttpServletRequest object that contains the request
     * made by the client
     * @param response the HttpServletResponse object that contains the response
     * from the servlet
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs while handling the
     * request
     */
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

            //kiểm tra lỗi null
            if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || desire.isEmpty()) {
                errorMessage = "Fields marked with * cannot be blank or contain only spaces!";
                hasError = true;
            }

            //kiểm tra định dạng số điện thoại
            if (!phoneNumber.matches("\\d{10}")) {
                errorMessage = "Phone number must contain between 10 adn 11 digits!";
                hasError = true;
            }

            //kiểm tra định dạng email
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                errorMessage = "Invalid email format!";
                hasError = true;
            }

            if (hasError) {
                //lưu dữ liệu vào session để có thể lấy lại sau khi redirect
                HttpSession session = request.getSession();
                session.setAttribute("fullName", fullName);
                session.setAttribute("phoneNumber", phoneNumber);
                session.setAttribute("email", email);
                session.setAttribute("desire", desire);

                //gửi thông báo lỗi qua URL
                response.sendRedirect(request.getContextPath() + "/homePage?status=error&message=" + errorMessage);
            } else {
                //xóa các dữ liệu sau khi xử lý thành công
                HttpSession session = request.getSession();
                session.removeAttribute("fullName");
                session.removeAttribute("phoneNumber");
                session.removeAttribute("email");
                session.removeAttribute("desire");

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing the servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
