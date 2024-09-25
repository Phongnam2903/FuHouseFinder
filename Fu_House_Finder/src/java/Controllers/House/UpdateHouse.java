package Controllers.House;

import DAL.House.DAOHouse;
import Models.House;
import Validations.UploadFile;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class UpdateHouse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int houseId = Integer.parseInt(request.getParameter("id"));

            DAOHouse daoHouse = new DAOHouse();
            House house = daoHouse.getHouseById(houseId);

            if (house != null) {
                request.setAttribute("house", house);
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

            // Xử lý chuỗi hình ảnh (nếu có thay đổi)
            String images = String.join(",", imageFiles);

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
                if (!images.isEmpty()) {
                    house.setImage(images);
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
                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
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
