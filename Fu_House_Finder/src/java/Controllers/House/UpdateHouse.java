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
public class UpdateHouse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int houseId = Integer.parseInt(request.getParameter("id"));

            DAOHouse daoHouse = new DAOHouse();
            House house = daoHouse.getHouseById(houseId);

            if (house == null) {
                response.sendRedirect("ListHouse");
                return;
            }

            // Lấy danh sách ảnh từ database và tách thành mảng
            String[] imageList = house.getImage().split(",");

            // Gán thông tin vào request attribute để hiển thị trong form
            request.setAttribute("house", house);
            request.setAttribute("imageList", imageList);

            // Điều hướng đến trang cập nhật
            request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListHouse");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy các thông tin chỉnh sửa từ form
            int houseId = Integer.parseInt(request.getParameter("houseId"));
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

            // Xử lý upload file nếu có
            UploadFile uploadFile = new UploadFile();
            List<String> imageFiles = uploadFile.fileUpload(request, response);

            // Tạo đối tượng House và cập nhật thông tin
            DAOHouse daoHouse = new DAOHouse();
            House house = daoHouse.getHouseById(houseId);
            if (house != null) {
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
                house.setLastModifiedDate(new Date());

                // Cập nhật hình ảnh nếu người dùng đã upload mới
                if (!imageFiles.isEmpty()) {
                    // Nếu có hình ảnh mới được tải lên, cập nhật chúng
                    String newImages = String.join(",", imageFiles);
                    house.setImage(newImages);
                } else {
                    // Không có hình ảnh mới, giữ nguyên hình ảnh cũ
                    house.setImage(house.getImage());
                }

                // Cập nhật nhà trọ trong database
                int result = daoHouse.updateHouse(house);

                if (result > 0) {
                    request.setAttribute("message", "Cập nhật nhà trọ thành công!");
                    request.setAttribute("alertClass", "alert-success");
                } else {
                    request.setAttribute("message", "Cập nhật nhà trọ thất bại!");
                    request.setAttribute("alertClass", "alert-danger");
                }

                // Quay lại form với thông báo
                request.setAttribute("house", house);
                String[] imageList = house.getImage().split(",");
                request.setAttribute("imageList", imageList);
                request.getRequestDispatcher("Views/HouseOwner/UpdateHouse.jsp").forward(request, response);
            } else {
                response.sendRedirect("ListHouse");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListHouse");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
