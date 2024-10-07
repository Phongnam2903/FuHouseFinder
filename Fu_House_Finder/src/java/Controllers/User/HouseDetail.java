package Controllers.User;

import DAL.Comment.DAOComment;
import DAL.House.DAOHouse;
import DAL.Rating.DAORate;
import DAL.UserRateComment.DAOUserRateComment;
import Models.Comment;
import Models.House;
import Models.Rates;
import Models.User;
import Models.UserRateComment;
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

        House house = daoHouse.getHouseById(houseId);

        //kiểm tra nhà trọ có hay không
        if (house != null) {
            request.setAttribute("house", house);
            request.setAttribute("images", house.getImage());
            //lấy comment và rate
            DAOUserRateComment daoCommentRateUser = new DAOUserRateComment();
            List<UserRateComment> commentsAndRatings = daoCommentRateUser.getCommentsAndRatingsByHouseId(houseId);
            request.setAttribute("commentsAndRatings", commentsAndRatings);
            
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
        // Lấy thông tin từ form comment và rating
        String commentText = request.getParameter("comment");
        String starRating = request.getParameter("ratingValue");
        String houseIdParam = request.getParameter("houseId");

        User user = (User) request.getSession().getAttribute("user");  // Lấy user từ session

        //nếu không tìm thấy chủ trọ thì quay về trang đăng nhập
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getId();

        int houseId = Integer.parseInt(houseIdParam);

        if (commentText != null && !commentText.trim().isEmpty()) {
            // Xử lý lưu comment
            DAOComment daoComment = new DAOComment();
            Comment comment = new Comment();
            comment.setDescription(commentText);
            comment.setCreatedDate(new Date(System.currentTimeMillis()));
            comment.setUserID(userId);  // Giả sử lấy từ session đăng nhập
            comment.setHouseID(houseId);
            comment.setRoomID(4);  // Nếu cần gán phòng, gán RoomID thích hợp, ở đây để 0
            daoComment.addComment(comment);
        }

        if (starRating != null && !starRating.isEmpty()) {
            // Xử lý lưu rate
            DAORate daoRate = new DAORate();
            Rates rate = new Rates();
            rate.setStar(Integer.parseInt(starRating));
            rate.setUserID(userId);  // Giả sử lấy từ session đăng nhập
            rate.setHouseID(houseId);
            rate.setCreatedDate(new Date(System.currentTimeMillis()));
            daoRate.addRate(rate);
        }

        // Sau khi thêm comment và rating, tải lại trang chi tiết nhà
        response.sendRedirect(request.getContextPath() + "/houseDetail?id=" + houseId);
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
