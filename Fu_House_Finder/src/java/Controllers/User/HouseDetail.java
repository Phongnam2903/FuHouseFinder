/*
 * Copyright(C) 2024, FU House Finder.
 * FU House Finder : House Listing Application
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2024-10-12                 1.0                 DuongTD                      Initial implementation of HouseDetail servlet
 */
package Controllers.User;

import DAL.House.DAOHouse;
import DAL.Rating.DAORate;
import DAL.Room.DAORoom;
import DAL.User.DAOFeedBack;
import Models.Feedback;
import Models.House;
import Models.Rates;
import Models.Room;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * This servlet handles requests related to the details of a specific house. It
 * retrieves house information, ratings, and rooms associated with the house. It
 * also processes comments and ratings submitted by users.
 *
 * <p>
 * Bugs: None
 *
 * @author DuongTD
 */
@WebServlet(name = "HouseDetail", urlPatterns = {"/houseDetail"})
public class HouseDetail extends HttpServlet {

    /**
     * Handles GET requests to retrieve and display the details of a specific
     * house. It fetches the house details, associated rooms, and ratings from
     * the database.
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
        //lấy id của ngôi nhà
        String houseIdParam = request.getParameter("id");
        //chuyển String sang int
        int houseId = Integer.parseInt(houseIdParam);

        DAOHouse daoHouse = new DAOHouse();
        DAORate daoRate = new DAORate();
        DAORoom daoRoom = new DAORoom();

        //phân trang cho rate
        int ratepageSize = 3;
        String ratepageStr = request.getParameter("ratePage");
        int ratepageNumber = 1;

        //phân tích số trang
        if (ratepageStr != null && !ratepageStr.isEmpty()) {
            try {
                ratepageNumber = Integer.parseInt(ratepageStr);
            } catch (NumberFormatException e) {
                ratepageNumber = 1;
            }
        }

        List<Rates> ratesList = daoRate.getRatesByHouse(houseId, ratepageNumber, ratepageSize);

        //tính tổng số trang cho phân trang
        int totalRates = daoRate.getTotalRatesByHouse(houseId);
        int totalRatePages = (int) Math.ceil((double) totalRates / ratepageSize);

        //đảm bảo có ít nhất 1 trang
        if (totalRatePages < 1) {
            totalRatePages = 1;
        }

        //phân trang cho room
        int roomPageSize = 4;
        String roomPageStr = request.getParameter("roomPage");
        int roomPageNumber = 1;
        if (roomPageStr != null && !roomPageStr.isEmpty()) {
            try {
                roomPageNumber = Integer.parseInt(roomPageStr);
            } catch (NumberFormatException e) {
                roomPageNumber = 1;
            }
        }

        List<Room> roomList = daoRoom.getRoomsByHouseId(houseId, roomPageNumber, roomPageSize);
        int totalRooms = daoRoom.getTotalRoomsByHouseId(houseId);
        int totalRoomPages = (int) Math.ceil((double) totalRooms / roomPageSize);

        if (totalRoomPages < 1) {
            totalRoomPages = 1;
        }

        House house = daoHouse.getHouseById(houseId);

        //kiểm tra nhà trọ có hay không
        if (house != null) {
            request.setAttribute("house", house);
            request.setAttribute("roomList", roomList);
            request.setAttribute("images", house.getImage());
            request.setAttribute("ratesList", ratesList);
            request.setAttribute("ratepageNumber", ratepageNumber);//trang hiện tại
            request.setAttribute("totalRatePages", totalRatePages);//tổng trang
            request.setAttribute("roomPageNumber", roomPageNumber);
            request.setAttribute("totalRoomPages", totalRoomPages);

            request.getRequestDispatcher("/Views/User/HouseDetail.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "HomePage");
        }
    }

    /**
     * Handles POST requests to process comments and ratings submitted and
     * REPORT House by users. Validates the input data and adds the
     * comment/rating and report to the database.
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
        if (service.equals("sendReview")) {
            //lấy thông tin từ form comment và rating
            String commentText = request.getParameter("comment").trim(); //lấy mô tả đánh giá
            String starRating = request.getParameter("ratingValue"); //lấy số sao
            String houseIdParam = request.getParameter("houseId"); //ID của ngôi nhà

            User user = (User) request.getSession().getAttribute("user");  //lấy user từ session

            //nếu không tìm thấy chủ trọ thì quay về trang đăng nhập
            if (user == null) {
                response.sendRedirect("login");
                return;
            }

            int userId = user.getId();
            int houseId = Integer.parseInt(houseIdParam);

            DAOHouse daoHouse = new DAOHouse();
            House house = daoHouse.getHouseById(houseId);

            //khởi tạo biến để lưu thông báo và hiển thị lên jsp
            String errorMessage = null;
            String successMessage = null;

            //kiểm tra nếu comment không rỗng và user đã nhập đánh giá
            if ((commentText == null || commentText.isEmpty()) && (starRating == null || starRating.isEmpty() || Integer.parseInt(starRating) <= 0)) {
                errorMessage = "You need to provide at least one: commnet or rating!";
            } else {
                //tạo đối tượng DAO để lưu đánh giá
                DAORate daoRate = new DAORate();
                Rates rate = new Rates();
                rate.setStar(Integer.parseInt(starRating)); //thiết lập số sao
                rate.setHouseID(houseId);
                rate.setUserID(userId);
                rate.setCreatedDate(new Date(System.currentTimeMillis()));
                rate.setDecription(commentText);

                //thiết lập HouseOwnerReply thành null vì người dùng mới comment
                rate.setHouseOwnerReply(null);

                //thiết lập LastModifiedBy thành ownerId từ đối tượng House
                rate.setLastModifiedBy(house.getOwnerId());
                rate.setLastModifiedDate(new Date(System.currentTimeMillis()));

                //lưu đánh giá vào cơ sở dữ liệu
                int result = daoRate.addRate(rate);

                if (result > 0) {
                    successMessage = "Posted review successfully!";
                } else {
                    errorMessage = "Failed to add posted review!";
                }
            }

            //thiết lập thông báo vào yêu cầu trước khi chuyển hướng
            if (errorMessage != null) {
                //gửi thông báo lỗi qua URL với tham số "error"
                response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId + "&status=error&message=" + errorMessage);
            } else if (successMessage != null) {
                //gửi thông báo thành công qua URL với tham số "success"
                response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId + "&status=success&message=" + successMessage);
            }
        }

        if (service.equals("sendReport")) {
            //lấy thông tin từ form report
            String title = request.getParameter("title").trim();
            String reason = request.getParameter("reason").trim();
            int userId = Integer.parseInt(request.getParameter("userId"));
            int houseId = Integer.parseInt(request.getParameter("houseId"));

            User user = (User) request.getSession().getAttribute("user");  //lấy user từ session

            //nếu không tìm thấy chủ trọ thì quay về trang đăng nhập
            if (user == null) {
                response.sendRedirect("login");
                return;
            }

            //khởi tạo biến để lưu thông báo và hiển thị lên jsp
            String errorMessage = null;
            String successMessage = null;

            if (reason.isEmpty()) {
                errorMessage = "Reason you report this house can't be blank or white spaces!";
            } else {
                //tạo đối tượng DAO để lưu feedback
                DAOFeedBack daoFeedBack = new DAOFeedBack();
                Feedback feedBack = new Feedback();
                feedBack.setTitle(title);
                feedBack.setDescription(reason);
                feedBack.setStatus("Pending");//mặc định là đang chờ phản hồi
                feedBack.setSentTime(new Date(System.currentTimeMillis()));
                feedBack.setCreatedDate(new Date(System.currentTimeMillis()));
                feedBack.setRenterId(userId);

                //lưu đánh giá vào cơ sở dữ liệu
                int result = daoFeedBack.addFeedBack(feedBack);

                if (result > 0) {
                    successMessage = "Report successfully!";
                } else {
                    errorMessage = "Failed to report!";
                }
            }

            //thiết lập thông báo vào yêu cầu trước khi chuyển hướng
            if (errorMessage != null) {
                // Gửi thông báo lỗi qua URL với tham số "error"
                response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId + "&status=error&message=" + errorMessage);
            } else if (successMessage != null) {
                // Gửi thông báo thành công qua URL với tham số "success"
                response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId + "&status=success&message=" + successMessage);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
