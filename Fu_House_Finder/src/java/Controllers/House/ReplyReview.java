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
 *
 * @author ADMIN
 */
@WebServlet(name = "ReplyReview", urlPatterns = {"/replyReview"})
public class ReplyReview extends HttpServlet {

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
        DAORate daoRate = new DAORate();

        List<Rates> ratesList = daoRate.getRatesByHouse(houseId);

        //kiểm tra review có hay không
        if (ratesList != null) {
            request.setAttribute("ratesList", ratesList);
            request.setAttribute("houseId", houseId);

            request.getRequestDispatcher("/Views/HouseOwner/ReplyReview.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "ListHouse");
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
