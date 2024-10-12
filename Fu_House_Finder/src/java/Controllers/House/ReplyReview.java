/*
 * Copyright(C) 2024, FU House Finder.
 * FU House Finder : House Listing Application
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2024-10-12                 1.0                 DuongTD                      Initial implementation of ReplyReview servlet
 */
package Controllers.House;

import DAL.Rating.DAORate;
import Models.Rates;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This servlet handles the reply to user reviews for a specific house. It
 * retrieves the list of ratings for a house and allows the house owner to
 * respond to the reviews.
 *
 * <p>
 * Bugs: None
 *
 * @author DuongTD
 */
@WebServlet(name = "ReplyReview", urlPatterns = {"/replyReview"})
public class ReplyReview extends HttpServlet {

    /**
     * Handles GET requests to retrieve ratings for a specific house and display
     * the reply form for the house owner.
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
        DAORate daoRate = new DAORate();

        //phân trang
        int pageSize = 6;
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

        List<Rates> ratesList = daoRate.getRatesByHouse(houseId, pageNumber, pageSize);

        // Tính tổng số review và số trang
        int totalRates = daoRate.getTotalRatesByHouse(houseId);
        int totalPages = (int) Math.ceil((double) totalRates / pageSize);

        if (totalPages < 1) {
            totalPages = 1;
        }

        //kiểm tra review có hay không
        if (ratesList != null) {
            request.setAttribute("ratesList", ratesList);
            request.setAttribute("houseId", houseId);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/Views/HouseOwner/ReplyReview.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "ListHouse");
        }
    }

    /**
     * Handles POST requests to process replies from the house owner to user
     * reviews. It validates the reply input and updates the review in the
     * database.
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
        //lấy thông tin cần thiết từ jsp
        String replyText = request.getParameter("replyText").trim();
        String rateIdParam = request.getParameter("rateId");
        String houseIdParam = request.getParameter("houseId");

        int rateId = Integer.parseInt(rateIdParam);
        int houseId = Integer.parseInt(houseIdParam);

        String errorMessage = null;
        String successMessage = null;

        DAORate daoRate = new DAORate();

        if (replyText.isEmpty()) {
            errorMessage = "Cannot be blank or only white space!";
            response.sendRedirect(request.getContextPath() + "/replyReview?id=" + houseId + "&status=error&message=" + errorMessage);
            return;
        }

        //cập nhật reply của chủ trọ
        boolean isUpdated = daoRate.updateRateReply(rateId, replyText);

        //kiểm tra đã được thêm vào chưa với thông báo
        if (isUpdated) {
            successMessage = "Reply comment successfully!";
            response.sendRedirect(request.getContextPath() + "/replyReview?id=" + houseId + "&status=success&message=" + successMessage);
        } else {
            errorMessage = "Failed reply comment!";
            response.sendRedirect(request.getContextPath() + "/replyReview?id=" + houseId + "&status=error&message=" + errorMessage);
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
