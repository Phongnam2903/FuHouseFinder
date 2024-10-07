/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * FHF.JAVA:
 *  FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26       1.0                PhongNN          View Account List 
 */
package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet responsible for handling requests to view the account list from the
 * admin side. Displays the list of accounts with pagination functionality.
 *
 * @author Nguyen Nam Phong
 */
@WebServlet(name = "ViewAccountList", urlPatterns = {"/viewAccountList"})
public class ViewAccountList extends HttpServlet {

    /**
     * Method to handle GET requests from the client to display the account
     * list. Data is fetched from the database and displayed based on the
     * current page.
     *
     * @param request The HttpServletRequest object containing the client's
     * request information.
     * @param response The HttpServletResponse object to return the response to
     * the client.
     * @throws ServletException if there is a servlet-related error.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Create ManageAccount object to interact with account data
        ManageAccount account = new ManageAccount();
        // Set the current page number and page size (number of accounts per page)
        int page = 1;
        int pageSize = 7;
        // Check if the "page" parameter is sent from the request, if so, convert it to an integer
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        // Get the total number of accounts from the database
        int totalAccount = account.getAccountCount();
        // Calculate the total number of pages based on the total accounts and page size
        int totalPages = (int) Math.ceil((double) totalAccount / pageSize);
        // Retrieve the account list for the current page from the database
        List<User> listAcc = account.getAccountsByPage(page, pageSize);

        // Set attributes to send to the JSP page
        request.setAttribute("listAcc", listAcc);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPages);
        // Forward the request to the JSP page to display the account list
        request.getRequestDispatcher("Views/Admin/AdminListAccount.jsp").forward(request, response);
    }
}
