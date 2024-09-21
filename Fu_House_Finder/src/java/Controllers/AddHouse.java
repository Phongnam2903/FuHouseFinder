package Controllers;

import DAL.Process.DAOHouse;
import Models.House;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            List<String> errors = new ArrayList<>();

            String houseName = request.getParameter("houseName");
            String address = request.getParameter("address");
            String description = request.getParameter("description");
            String distanceString = request.getParameter("distance");
            String powerPriceString = request.getParameter("powerPrice");
            String waterPriceString = request.getParameter("waterPrice");
            String servicePriceString = request.getParameter("servicePrice");

            // Kiểm tra các trường không được để trống
            if (houseName == null || houseName.trim().isEmpty()) {
                errors.add("Tên nhà trọ không được để trống");
            }
            if (address == null || address.trim().isEmpty()) {
                errors.add("Địa chỉ không được để trống");
            }
            if (description == null || description.trim().isEmpty()) {
                errors.add("Thông tin mô tả không được để trống");
            }
            if (distanceString == null || distanceString.trim().isEmpty()) {
                errors.add("Khoảng cách đến trường không được để trống");
            }
            if (powerPriceString == null || powerPriceString.trim().isEmpty()) {
                errors.add("Giá điện không được để trống");
            }
            if (waterPriceString == null || waterPriceString.trim().isEmpty()) {
                errors.add("Giá nước không được để trống");
            }
            if (servicePriceString == null || servicePriceString.trim().isEmpty()) {
                errors.add("Giá dịch vụ không được để trống");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Views/HouseOwner/AddHouse.jsp").forward(request, response);
                return;
            }

            distanceString = distanceString.replace(",", ".");
            float distance = Float.parseFloat(distanceString);
            double powerPrice = Double.parseDouble(powerPriceString);
            double waterPrice = Double.parseDouble(waterPriceString);
            double servicePrice = Double.parseDouble(servicePriceString);
            boolean fingerPrintLock = request.getParameter("fingerPrintLock") != null;
            boolean camera = request.getParameter("camera") != null;
            boolean parking = request.getParameter("parking") != null;
            String image = "";

            House house = new House();
            house.setHouseName(houseName);
            house.setAddress(address);
            house.setDescription(description);
            house.setDistanceToSchool(distance);
            house.setPowerPrice(powerPrice);
            house.setWaterPrice(waterPrice);
            house.setOtherServicePrice(servicePrice);
            house.setFingerPrintLock(fingerPrintLock);
            house.setCamera(camera);
            house.setParking(parking);
            house.setOwnerId(7);
            house.setCreatedDate(new Date());
            house.setLastModifiedDate(new Date());
            house.setImage(image);

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
