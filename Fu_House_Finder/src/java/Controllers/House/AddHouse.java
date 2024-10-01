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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddHouse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User owner = (User) request.getSession().getAttribute("account");

            if (owner == null) {
                response.sendRedirect("login");
                return;
            }

            int ownerId = owner.getId();

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

            if (houseName.isEmpty() || address.isEmpty() || distanceStr.isEmpty()
                    || powerPriceStr.isEmpty() || waterPriceStr.isEmpty() || servicePriceStr.isEmpty()) {

                request.setAttribute("message", "Các trường có dấu * không được để trống hoặc chỉ chứa khoảng trắng!");
                request.setAttribute("alertClass", "alert-danger");

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

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            float distanceToSchool;
            double powerPrice, waterPrice, servicePrice;
            try {
                distanceToSchool = Float.parseFloat(distanceStr);
                powerPrice = Double.parseDouble(powerPriceStr);
                waterPrice = Double.parseDouble(waterPriceStr);
                servicePrice = Double.parseDouble(servicePriceStr);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Khoảng cách, giá điện, nước, dịch vụ phải là số hợp lệ!");
                request.setAttribute("alertClass", "alert-danger");

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

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            if (distanceToSchool < 0 || powerPrice < 0 || waterPrice < 0 || servicePrice < 0) {
                request.setAttribute("message", "Khoảng cách, giá tiền, điện, nước không được là số âm!");
                request.setAttribute("alertClass", "alert-danger");

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

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            UploadFile uploadFile = new UploadFile();
            List<String> imageFiles = uploadFile.fileUpload(request, response);

            if (imageFiles.isEmpty()) {
                request.setAttribute("message", "Phải có ít nhất một ảnh nhà!");
                request.setAttribute("alertClass", "alert-danger");

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

                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            String images = String.join(",", imageFiles);

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

            DAOHouse daoHouse = new DAOHouse();
            int result = daoHouse.addHouse(house);

            if (result > 0) {
                request.setAttribute("message", "Thêm nhà trọ thành công!");
                request.setAttribute("alertClass", "alert-success");
            } else {
                request.setAttribute("message", "Thêm nhà trọ thất bại!");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
