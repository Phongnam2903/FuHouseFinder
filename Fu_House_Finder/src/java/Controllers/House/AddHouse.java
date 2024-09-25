package Controllers.House;

import DAL.House.DAOHouse;
import Models.House;
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
            String houseName = request.getParameter("houseName");
            String address = request.getParameter("address");
            String description = request.getParameter("description");
            float distanceToSchool = Float.parseFloat(request.getParameter("distance"));
            double powerPrice = Double.parseDouble(request.getParameter("powerPrice"));
            double waterPrice = Double.parseDouble(request.getParameter("waterPrice"));
            double servicePrice = Double.parseDouble(request.getParameter("servicePrice"));
            boolean fingerPrintLock = request.getParameter("fingerPrintLock") != null;
            boolean camera = request.getParameter("camera") != null;
            boolean parking = request.getParameter("parking") != null;
            
            UploadFile uploadFile = new UploadFile();
            List<String> imageFiles = uploadFile.fileUpload(request, response);

            // Xử lý ảnh và nối tất cả đường dẫn ảnh thành một chuỗi, phân cách bằng dấu phẩy
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
            house.setOwnerId(7);
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
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
