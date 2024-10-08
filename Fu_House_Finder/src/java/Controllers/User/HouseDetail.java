package Controllers.User;

import DAL.House.DAOHouse;
import DAL.Rating.DAORate;
import Models.House;
import Models.Rates;
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
 *
 * @author ADMIN
 */
@WebServlet(name = "HouseDetail", urlPatterns = {"/houseDetail"})
public class HouseDetail extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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

        List<Rates> ratesList = daoRate.getRatesByHouse(houseId);

        House house = daoHouse.getHouseById(houseId);

        //kiểm tra nhà trọ có hay không
        if (house != null) {
            request.setAttribute("house", house);
            request.setAttribute("images", house.getImage());
            request.setAttribute("ratesList", ratesList);

            request.getRequestDispatcher("/Views/User/HouseDetail.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "HomePage");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lấy thông tin từ form comment và rating
        String commentText = request.getParameter("comment"); // Lấy mô tả đánh giá
        String starRating = request.getParameter("ratingValue"); // Lấy số sao
        String houseIdParam = request.getParameter("houseId"); // ID của ngôi nhà

        User user = (User) request.getSession().getAttribute("user");  // Lấy user từ session

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
        if ((commentText == null || commentText.trim().isEmpty()) && (starRating == null || starRating.isEmpty() || Integer.parseInt(starRating) <= 0)) {
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

            //thiết lập HouseOwnerReply thành null vì người dùng chưa đánh giá
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
            // Gửi thông báo lỗi qua URL với tham số "error"
            response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId + "&status=error&message=" + errorMessage);
        } else if (successMessage != null) {
            // Gửi thông báo thành công qua URL với tham số "success"
            response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId + "&status=success&message=" + successMessage);
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
