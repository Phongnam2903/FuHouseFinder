/*
 * Copyright(C) 2024, FU House Finder.
 * FU House Finder : House Listing Application
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2024-10-03                 1.0                 DuongTD                      Initial implementation of AddHouse servlet
 */
package Controllers.House;

import DAL.House.DAOHouse;
import Models.House;
import Models.User;
import Validations.UploadFile;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for handling requests related to adding a new house
 * to the system. It provides functionality for displaying the add house form
 * and processing house information from the form submission.
 *
 * <p>
 * Bugs: None
 *
 * @author DuongTD
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddHouse extends HttpServlet {

    /**
     * This method handles GET requests to display the AddHouse page. It
     * forwards the request to the AddHouse.jsp view where house owners can
     * input information about their new house.
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
        //điều hướng đến trang AddHouse
        request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
    }

    /**
     * This method handles POST requests to process the form submission when a
     * house owner adds a new house. It validates the input, processes image
     * uploads, and stores the house information in the database.
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
        try {
            //lấy thông tin chủ trọ từ session
            User owner = (User) request.getSession().getAttribute("user");

            //nếu không tìm thấy chủ trọ thì quay về trang đăng nhập
            if (owner == null) {
                response.sendRedirect("login");
                return;
            }

            //lấy id của chủ nhà
            int ownerId = owner.getId();

            //lấy dữ liệu từ jsp và kiểm tra
            String houseName = request.getParameter("houseName").trim();
            String address = request.getParameter("address").trim();
            String description = request.getParameter("description").trim();
            String distanceStr = request.getParameter("distance").trim();
            String powerPriceStr = request.getParameter("powerPrice").trim();
            String waterPriceStr = request.getParameter("waterPrice").trim();
            String servicePriceStr = request.getParameter("servicePrice").trim();
            boolean fingerPrintLock = request.getParameter("fingerPrintLock") != null;
            boolean camera = request.getParameter("camera") != null;
            boolean parking = request.getParameter("parking") != null;

            //kiểm tra các trường bắt buộc không được trống
            if (houseName.isEmpty() || address.isEmpty() || distanceStr.isEmpty()
                    || powerPriceStr.isEmpty() || waterPriceStr.isEmpty()) {

                //Nếu có thì thông báo lỗi và hiển thị ở trang jsp
                request.setAttribute("message", "Fields marked with * cannot be blank or contain only spaces!");
                request.setAttribute("alertClass", "alert-danger");

                //giữ lại các giá trị được nhập khi hiển thị lại trang jsp
                setRequestAttributes(request, houseName, address, description, distanceStr, powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            //chuyển đổi từ chuỗi sang số và kiểm tra 
            float distanceToSchool;
            double powerPrice, waterPrice, servicePrice;
            try {
                distanceToSchool = Float.parseFloat(distanceStr);
                powerPrice = Double.parseDouble(powerPriceStr);
                waterPrice = Double.parseDouble(waterPriceStr);
                servicePrice = Double.parseDouble(servicePriceStr);
            } catch (NumberFormatException e) {
                //Nếu có thì thông báo lỗi và hiển thị ở trang jsp
                request.setAttribute("message", "Distance, power, water, and service prices must be valid numbers!");
                request.setAttribute("alertClass", "alert-danger");

                setRequestAttributes(request, houseName, address, description, distanceStr, powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            //kiểm tra các giá trị số không âm
            if (distanceToSchool < 0 || powerPrice < 0 || waterPrice < 0 || servicePrice < 0) {
                request.setAttribute("message", "Distance, power, water, and service prices cannot be negative numbers!");
                request.setAttribute("alertClass", "alert-danger");

                setRequestAttributes(request, houseName, address, description, distanceStr, powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            //xử lý tải ảnh
            UploadFile uploadFile = new UploadFile();
            List<String> imageFiles = uploadFile.fileUpload(request, response);

            //kiểm tra nếu chủ trọ chưa tải ảnh ít nhất 1 ảnh lên
            if (imageFiles.isEmpty()) {
                //Nếu có thì thông báo lỗi và hiển thị ở trang jsp
                request.setAttribute("message", "Must have at least one house photo!");
                request.setAttribute("alertClass", "alert-danger");

                setRequestAttributes(request, houseName, address, description, distanceStr, powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            //kết hợp các ảnh thành chuỗi ngăn cách nhau bằng dấu ,
            String images = String.join(",", imageFiles);

            // Tạo đối tượng House và lưu dữ liệu từ form jsp
            House house = new House();
            house.setHouseName(houseName);
            house.setAddress(address);
            house.setDescription(description);
            house.setDistanceToSchool(distanceToSchool);
            house.setPowerPrice(powerPrice);
            house.setWaterPrice(waterPrice);
            house.setOtherServicePrice(servicePrice);
            house.setFingerPrintLock(fingerPrintLock);
            house.setCamera(camera);
            house.setParking(parking);
            house.setOwnerId(ownerId);
            house.setCreatedDate(new Date());
            house.setLastModifiedDate(new Date());
            house.setImage(images);

            // Thực hiện thêm nhà mới vào cơ sở dữ liệu
            DAOHouse daoHouse = new DAOHouse();
            int result = daoHouse.addHouse(house);

            // Kiểm tra kết quả và thông báo cho chủ trọ
            if (result > 0) {
                request.setAttribute("message", "Add House Successfully!");
                request.setAttribute("alertClass", "alert-success");
            } else {
                request.setAttribute("message", "Fail To Add House!");
                request.setAttribute("alertClass", "alert-danger");
            }

            request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi, vui lòng thử lại!");
            request.setAttribute("alertClass", "alert-danger");
            request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
        }
    }

    /**
     * Set the request attributes to preserve form input values after a failed
     * submission.
     *
     * @param request the HttpServletRequest object
     * @param houseName the name of the house
     * @param address the house address
     * @param description the house description
     * @param distanceStr the distance to the school as a string
     * @param powerPriceStr the power price as a string
     * @param waterPriceStr the water price as a string
     * @param servicePriceStr the service price as a string
     * @param fingerPrintLock whether the house has a fingerprint lock
     * @param camera whether the house has a camera
     * @param parking whether the house has parking
     */
    private void setRequestAttributes(HttpServletRequest request, String houseName, String address,
            String description, String distanceStr, String powerPriceStr,
            String waterPriceStr, String servicePriceStr, boolean fingerPrintLock,
            boolean camera, boolean parking) {
        request.setAttribute("houseName", houseName);
        request.setAttribute("address", address);
        request.setAttribute("description", description);
        request.setAttribute("distance", distanceStr);
        request.setAttribute("powerPrice", powerPriceStr);
        request.setAttribute("waterPrice", waterPriceStr);
        request.setAttribute("servicePrice", servicePriceStr);
        request.setAttribute("fingerPrintLock", fingerPrintLock);
        request.setAttribute("camera", camera);
        request.setAttribute("parking", parking);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
