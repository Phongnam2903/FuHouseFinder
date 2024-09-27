package Controllers.Admin;

import DAL.Admin.ManageAccount;
import Models.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "AdminProfile", urlPatterns = {"/admin_profile"})
public class AdminProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student admin = (Student) request.getSession().getAttribute("account");
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get information from form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dateOfBirthStr = request.getParameter("dateOfBirth");

        // Parse the Date of Birth (String -> Date)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(dateOfBirthStr);
        } catch (ParseException ex) {
            Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Handle file upload
        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            // Get Name of File
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "avatar"; // Directory to save file

            // Create directory if it doesn't exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Save the file in the uploads folder
            filePart.write(uploadPath + File.separator + fileName);

            // Save the file path to display the image later
            String avatarPath = "uploads" + File.separator + fileName;
            request.setAttribute("avatarPath", avatarPath);
        }

        // Save other information to the database, including Date of Birth
        Student admin = (Student) request.getSession().getAttribute("account");
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setAddress(address);
        admin.setDateOfBirth(dateOfBirth); // Setting Date object

        // Update the account in the database
        ManageAccount update = new ManageAccount();
        update.updateAccount(admin);

        // Set attributes and forward the request
        request.setAttribute("admin", admin);
        request.setAttribute("message", "Profile updated successfully!");
        request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
    }

}
