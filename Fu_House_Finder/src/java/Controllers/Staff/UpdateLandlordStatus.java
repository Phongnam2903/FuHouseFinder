/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAL.Staff.DAOStaff;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "UpdateLandlordStatus", urlPatterns = {"/updateLandlordStatus"})
public class UpdateLandlordStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int newStatus = Integer.parseInt(request.getParameter("status"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        DAOStaff daoStaff = new DAOStaff();
        boolean results = daoStaff.updateLandlordStatus(id, newStatus);

        if (results) {
            request.getSession().setAttribute("message", "Status updated successfully!");
        } else {
            request.getSession().setAttribute("message", "Failed to update status.");
        }
        response.sendRedirect(request.getContextPath() + "/listhouseowner?page=" + currentPage);
    }

}
