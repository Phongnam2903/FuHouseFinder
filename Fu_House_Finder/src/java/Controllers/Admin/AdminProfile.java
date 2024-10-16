package Controllers.Admin;

/*
 * Copyright(C) 2005, Morses Club of London Scottish Bank.
 * AdminProfile.JAVA
 *  Fu House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26       1.0                PhongNN          View Admin Profile
 */
import DAL.Admin.ManageAccount;
import Models.User;
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
 * This servlet handles the profile management for the admin, allowing the admin
 * to view and update their profile information including avatar upload. The
 * servlet handles both GET and POST requests.
 *
 * @author xuxum
 */
@WebServlet(name = "AdminProfile", urlPatterns = {"/admin_profile"})
public class AdminProfile extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method to display the admin profile
     * page. The admin information is retrieved from the session and forwarded
     * to the JSP.
     *
     * @param request The HttpServletRequest object that contains the client's
     * request.
     * @param response The HttpServletResponse object that contains the
     * servlet's response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error is detected.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the current admin's information from session
        User admin = (User) request.getSession().getAttribute("user");
        // Set admin object as a request attribute to be forwarded to the JSP
        request.setAttribute("admin", admin);
        // Forward the request to the Admin Profile JSP
        request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method for updating the admin's
     * profile. This includes updating personal information and handling avatar
     * file upload.
     *
     * @param request The HttpServletRequest object that contains the client's
     * request.
     * @param response The HttpServletResponse object that contains the
     * servlet's response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error is detected.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data from the profile update form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dateOfBirthStr = request.getParameter("dateOfBirth");

        // Parse the Date of Birth from String to Date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(dateOfBirthStr);
        } catch (ParseException ex) {
            // Log the exception in case of an error during date parsing
            Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Handle file upload for avatar image
        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            // Get the file name from the uploaded file
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            // Define the upload directory path
            String uploadPath = getServletContext().getRealPath("") + File.separator + "avatar";

            // Create the upload directory if it doesn't exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Create directory if needed
            }

            // Save the uploaded file in the designated directory
            filePart.write(uploadPath + File.separator + fileName);

            // Store the file path for later display (e.g., for the avatar image)
            String avatarPath = "uploads" + File.separator + fileName;
            request.setAttribute("avatarPath", avatarPath);
        }

        // Retrieve the admin object from the session and update its information
        User admin = (User) request.getSession().getAttribute("account");
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setAddress(address);
        admin.setDateOfBirth(dateOfBirth); // Set the parsed Date object

        // Update the admin account in the database
        ManageAccount update = new ManageAccount();
        update.updateAccount(admin);

        // Set updated attributes and forward the request back to the profile page
        request.setAttribute("admin", admin);
        request.setAttribute("message", "Profile updated successfully!");
        request.getRequestDispatcher("Views/Admin/AdminProfile.jsp").forward(request, response);
    }
}
