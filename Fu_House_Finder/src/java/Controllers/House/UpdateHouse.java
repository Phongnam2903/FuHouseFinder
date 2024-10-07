/*
 * Copyright(C) 2024,  Group2-SE1866-KS.
 * FU House Finder :
 *  FHF.JAVA
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2024-10-06                1.0                 DuongTD                     Initial creation of UpdateHouse servlet.
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class handles house update actions, including changing the name, address,
 * description, electricity, water, service prices, and house images. It validates
 * the input data and updates the house information in the database.
 *
 * <p>Bugs: Can't update right image
 *
 * @author DuongTD
 */

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateHouse extends HttpServlet {

    
    /**
     * Handles the HTTP <code>GET</code> method.
     * This method fetches the house details based on the house ID from the parameter,
     * then displays the house update page.
     *
     * @param request  The HttpServletRequest object that contains the request the client has made of the servlet
     * @param response The HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //lấy id từ trang jsp của list house
            int houseId = Integer.parseInt(request.getParameter("id"));

            //khởi tạo DAOHouse để có thể lấy được nhà trọ từ id 
            DAOHouse daoHouse = new DAOHouse();
            House house = daoHouse.getHouseById(houseId);

            //nếu không tìm thấy thì chuyển hướng về List House
            if (house == null) {
                response.sendRedirect("ListHouse");
                return;
            }

            //khởi tạo biến imageList để có thể lấy được hình ảnh và tách chuỗi
            String[] imageList = house.getImage().split(",");

            request.setAttribute("house", house);
            request.setAttribute("imageList", imageList);

            request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListHouse");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * This method performs house updates in the database.
     * It validates input values, handles image updates, and saves new data.
     *
     * @param request  The HttpServletRequest object that contains the request the client has made of the servlet
     * @param response The HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //lấy thông tin người dùng từ session
            User owner = (User) request.getSession().getAttribute("account");

            //chuyển hướng tới trang đăng nhập nếu chưa đăng nhập
            if (owner == null) {
                response.sendRedirect("login");
                return;
            }

            //lấy id của chủ nhà
            int ownerId = owner.getId();

            // Lấy và kiểm tra các tham số từ request
            int houseId = Integer.parseInt(request.getParameter("houseId").trim());
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

            //kiểm tra các trường bắt buộc không được để trống
            if (houseName.isEmpty() || address.isEmpty() || distanceStr.isEmpty()
                    || powerPriceStr.isEmpty() || waterPriceStr.isEmpty()) {

                //đặt thông báo để hiển thị lỗi
                request.setAttribute("message", "Fields marked with * cannot be blank or contain only spaces!");
                request.setAttribute("alertClass", "alert-danger");

                //giữ lại những trường đã nhập nếu có lỗi
                setRequestAttributes(request, houseName, address, description, distanceStr,
                        powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
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
                request.setAttribute("message", "Distance, power, water, and service prices must be valid numbers!");
                request.setAttribute("alertClass", "alert-danger");

                setRequestAttributes(request, houseName, address, description, distanceStr,
                        powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
                return;
            }

             //kiểm tra các giá trị số không âm
            if (distanceToSchool < 0 || powerPrice < 0 || waterPrice < 0 || servicePrice < 0) {
                request.setAttribute("message", "Distance, power, water, and service prices cannot be negative numbers!");
                request.setAttribute("alertClass", "alert-danger");

                setRequestAttributes(request, houseName, address, description, distanceStr,
                        powerPriceStr, waterPriceStr, servicePriceStr, fingerPrintLock, camera, parking);

                request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
                return;
            }

            //xử lý tải lên hình ảnh
            UploadFile uploadFile = new UploadFile();
            String existingImages = request.getParameter("existingImages");
            String[] imageFileIndexes = request.getParameterValues("imageFileIndexes");
            List<String> imageFiles = uploadFile.fileUpload(request, response);

            String images;
            if (!imageFiles.isEmpty()) {
                //chuyển đổi danh sách ảnh hiện có từ chuỗi thành danh sách các phần tử
                List<String> allImages = new ArrayList<>(Arrays.asList(existingImages.split(",")));
                for (int i = 0; i < imageFiles.size(); i++) {
                    //lấy vị trí của ảnh cần cập nhật
                    int indexToUpdate = Integer.parseInt(imageFileIndexes[i]);

                    if (indexToUpdate < allImages.size()) {
                        allImages.set(indexToUpdate, imageFiles.get(i));
                    } else {
                        allImages.add(imageFiles.get(i));
                    }
                }

                //kết hợp các ảnh thành chuỗi ngăn cách nhau bằng dấu ,
                images = String.join(",", allImages);
            } else {
                //nếu không có ảnh mới được tải lên, giữ nguyên danh sách ảnh cũ
                images = existingImages;
            }

            // Cập nhật nhà trọ
            House house = new House();
            house.setId(houseId);
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
            house.setLastModifiedDate(new Date());
            house.setImage(images);

            DAOHouse daoHouse = new DAOHouse();
            int result = daoHouse.updateHouse(house);

            //kiểm tra và hiển thị thông báo
            if (result > 0) {
                request.setAttribute("message", "Update House Successfully!");
                request.setAttribute("alertClass", "alert-success");
            } else {
                request.setAttribute("message", "Fail To Update House!");
                request.setAttribute("alertClass", "alert-danger");
            }

            request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi, vui lòng thử lại!");
            request.setAttribute("alertClass", "alert-danger");
            request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
        }
    }

    /**
     * Set request attributes to retain form data when an error occurs.
     *
     * @param request HttpServletRequest containing client data
     * @param houseName The house name
     * @param address The house address
     * @param description The house description
     * @param distanceStr The distance to the school
     * @param powerPriceStr The electricity price
     * @param waterPriceStr The water price
     * @param servicePriceStr The other service price
     * @param fingerPrintLock Whether the house has fingerprint lock
     * @param camera Whether the house has a camera
     * @param parking Whether the house has parking
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
