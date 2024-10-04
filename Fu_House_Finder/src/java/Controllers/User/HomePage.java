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

/**
 *
 * @author ADMIN
 */
@WebServlet(name="HomePage", urlPatterns={"/HomePage"})
public class HomePage extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAOHouse daoHouse = new DAOHouse();
        
        List<House> houseList = daoHouse.getHouses();
        
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

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
