/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.Staff;

import DAL.User.DAOFeedBack;
import Models.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author My Lap
 */
@WebServlet(name="UpdateFeedbackStatus", urlPatterns={"/UpdateFeedbackStatus"})
public class UpdateFeedbackStatus extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateFeedbackStatus</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFeedbackStatus at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

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
        DAOFeedBack daoFeedback = new DAOFeedBack();

        String feedbackId = request.getParameter("id");
        String title = request.getParameter("title");
        String renterEmail = request.getParameter("renterEmail");
        String status = request.getParameter("status");
        String sentTimestartStr = request.getParameter("sentTimestart");
        String sentTimeendStr = request.getParameter("sentTimeend");
        
        
        // Initialize date formatter
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Feedback> feedbackList = null;
        Feedback feedback = null;

        try {
            if (title != null && !title.isEmpty()) {
                feedback = daoFeedback.searchFeedbackByTitle(title);
                request.setAttribute("feedbackList", feedbackList);
            } else if (renterEmail != null && !renterEmail.isEmpty()) {
                feedbackList = daoFeedback.searchFeedbackByRenterEmail(renterEmail);
                request.setAttribute("feedbackList", feedbackList);
            } else if (status != null && !status.isEmpty()) {
                feedbackList = daoFeedback.getAllFeedbackByStatus(status);
                request.setAttribute("feedbackList", feedbackList);
            } else if (sentTimestartStr != null && sentTimeendStr != null) {
                // Parse dates and check range validity
                Date sentTimestart = dateFormat.parse(sentTimestartStr);
                Date sentTimeend = dateFormat.parse(sentTimeendStr);
                
                if (sentTimestart.compareTo(sentTimeend) <= 0) {
                    feedbackList = daoFeedback.getAllFeedbackBySentTime(sentTimestart, sentTimeend);
                    request.setAttribute("feedbackList", feedbackList);
                } else {
                    request.setAttribute("errorMessage", "Start date must be before or equal to end date.");
                }
            } else {
                feedbackList = daoFeedback.getAllFeedback();
                request.setAttribute("feedbackList", feedbackList);
            }
            
            // If a feedback ID is provided, attempt deletion
            if (feedbackId != null) {
                daoFeedback.updateFeedbackStatus(Integer.parseInt(feedbackId));
                response.sendRedirect("ListFeedback?successFBU=true");
                return;
            }
            
            
            

        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format. Please use 'yyyy-MM-dd'.");
        }

        // Forward to the JSP page with the list of feedback or errors if any
        request.getRequestDispatcher("/Views/Staff/ListFeedBack.jsp").forward(request, response);
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
        processRequest(request, response);
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
