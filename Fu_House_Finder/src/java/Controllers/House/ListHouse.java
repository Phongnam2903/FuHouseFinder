/*
 * Copyright(C) 2024,  Group2-SE1866-KS.
 * FU House Finder :
 *  FHF.JAVA
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2024-10-06                1.0                 DuongTD                     Initial creation of ListHouse servlet.
 */
package Controllers.House;

import DAL.House.DAOHouse;
import Models.House;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class handles house management actions such as displaying, deleting, and
 * viewing detailed house information for a house owner. It also supports
 * pagination and search functionality.
 *
 * <p>
 * Bugs: No known bugs
 *
 * @author DuongTD
 */
public class ListHouse extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. This method retrieves house
     * information based on the owner ID from the session, handles house
     * deletion, displays detailed house information, and supports pagination
     * and search features for house listings.
     *
     * @param request HttpServletRequest object that contains the request the
     * client made to the servlet
     * @param response HttpServletResponse object that contains the response the
     * servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //khởi tạo DAOHouse để tương tác với cơ sở dữ liệu
        DAOHouse daoHouse = new DAOHouse();

        //kiểm tra xem có houseId nào cần xóa không
        String houseIdToDelete = request.getParameter("id");
        if (houseIdToDelete != null) {
            //phân tích houseId và xóa nhà tương ứng
            int houseId = Integer.parseInt(houseIdToDelete);
            daoHouse.deleteHouseById(houseId);

            //chuyển hướng về trang ListHouse sau khi xóa
            response.sendRedirect(request.getContextPath() + "/ListHouse");
            return;
        }

        //chi tiết nhà
        String houseIdStr = request.getParameter("houseId");

        if (houseIdStr != null) {
            //phân tích houseId và lấy thông tin chi tiết của nhà tương ứng
            int houseId = Integer.parseInt(houseIdStr);
            House houseDetail = daoHouse.getHouseById(houseId);

            if (houseDetail != null) {
                //Tách danh sách hình ảnh của nhà và chuyển tiếp tới trang ViewHouseDetail.jsp
                String[] imageList = houseDetail.getImage().split(",");
                request.setAttribute("house", houseDetail);
                request.setAttribute("imageList", imageList);
                request.getRequestDispatcher("/Views/HouseOwner/ViewHouseDetail.jsp").forward(request, response);
                return;
            }
        }

        //Get information of house owner from session
        User owner = (User) request.getSession().getAttribute("user");

        //chuyển hướng tới trang đăng nhập nếu chủ nhà chưa đăng nhập
        if (owner == null) {
            response.sendRedirect("login");
            return;
        }

        //lấy ownerId từ session
        int ownerId = owner.getId();

        //thiết lập phân trang
        int pageSize = 4;
        String pageStr = request.getParameter("page");
        int pageNumber = 1;
        int itemsPerPage = 4;

        //phân tích số trang
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                pageNumber = 1;
            }
        }

        House houseSummary = daoHouse.getHouseSummary(ownerId);

        //tìm kiếm
        String search = request.getParameter("search");
        List<House> houseList;
        int totalHouses;

        //lấy danh sách nhà dựa trên tên nhà trọ hoặc toàn bộ nhà của chủ sở hữu
        if (search != null && !search.isEmpty()) {
            houseList = daoHouse.searchHouses(ownerId, search, pageNumber, pageSize);
            totalHouses = daoHouse.getTotalHouseBySearch(ownerId, search);
        } else {
            houseList = daoHouse.getHousesByOwnerId(ownerId, pageNumber, pageSize);
            totalHouses = daoHouse.getTotalHousesByOwnerId(ownerId);
        }

        //tính tổng số trang cho phân trang
        int totalPages = (int) Math.ceil((double) totalHouses / pageSize);

        //đảm bảo có ít nhất 1 trang
        if (totalPages < 1) {
            totalPages = 1;
        }

        //đặt các thuộc tính cho phân trang và danh sách nhà
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("houseList", houseList);
        request.setAttribute("totalHouses", houseSummary.getTotalHouse());
        request.setAttribute("totalRooms", houseSummary.getTotalRooms());
        request.setAttribute("totalAvailableRooms", houseSummary.getTotalAvailableRooms());
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);

        //chuyển tiếp tới trang ListHouse.jsp
        request.getRequestDispatcher("/Views/HouseOwner/ListHouse.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Currently, this method is not
     * implemented.
     *
     * @param request HttpServletRequest object that contains the request the
     * client made to the servlet
     * @param response HttpServletResponse object that contains the response the
     * servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Not implemented
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
