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
 *
 * @author xuxum
 */
@WebServlet(name = "ViewAccountList", urlPatterns = {"/viewAccountList"})
public class ViewAccountList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManageAccount account = new ManageAccount();
        int page = 1;
        int pageSize = 7;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int totalAccount = account.getAccountCount();
        int totalPages = (int) Math.ceil((double) totalAccount / pageSize);
        //get list account from current page
        List<User> listAcc = account.getAccountsByPage(page, pageSize);
        request.setAttribute("listAcc", listAcc);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPages);
        request.getRequestDispatcher("Views/Admin/AdminListAccount.jsp").forward(request, response);
    }
}
