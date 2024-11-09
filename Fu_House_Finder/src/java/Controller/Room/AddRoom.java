package Controller.Room;

import java.io.IOException;
import DAL.House.DAOHouse;
import DAL.Room.DAORoom;
import DAL.Room.DAORoomType;
import DAL.Room.DAORoomStatus;
import Models.House;
import Models.Room;
import Models.RoomStatuses;
import Models.RoomTypes;
import Validations.UploadFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My Lap
 */
@WebServlet(name = "AddRoom", urlPatterns = {"/AddRoom"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddRoom extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAORoomType dbRoomType = new DAORoomType();
        DAORoomStatus dbRoomStatus = new DAORoomStatus();
        DAOHouse dbHouse = new DAOHouse();
        DAORoom gHouse = new DAORoom();
        List<RoomTypes> roomTypes = dbRoomType.getRoomTypes();
        List<RoomStatuses> roomStatuses = dbRoomStatus.getRoomStatus();
        List<House> house = dbHouse.getHouses();

        //Get houseId from request parameter and set it as an attribute
        String houseIdParam = request.getParameter("houseId");
        int houseId = houseIdParam != null ? Integer.parseInt(houseIdParam) : 0;
        //Get house name based on houseId and set it as an attribute
        String houseName = gHouse.getHouseNameByHouseId(houseId);
        request.setAttribute("houseName", houseName);
        request.setAttribute("roomTypeList", roomTypes);
        request.setAttribute("roomStatusList", roomStatuses);
        request.setAttribute("houseList", house);
        request.setAttribute("houseId", houseId);
        request.getRequestDispatcher("Views/HouseOwner/AddRoom.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorMessage = "";
        boolean isValid = true;

        // Extract parameters from the request
        String roomNumberStr = request.getParameter("roomNumber");
        String floorNumberStr = request.getParameter("floorNumber");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String priceStr = request.getParameter("price");
        String areaStr = request.getParameter("area");
        String roomTypeIDStr = request.getParameter("roomTypeId");
        String statusIDStr = request.getParameter("statusId");
        String[] facilities = request.getParameterValues("facilities");
        int houseId = Integer.parseInt(request.getParameter("houseId"));

        // Validate parameters
        if (roomNumberStr == null || roomNumberStr.isEmpty()) {
            errorMessage += "Room number is required.<br>";
            isValid = false;
        }

        if (floorNumberStr == null || floorNumberStr.isEmpty() || Integer.parseInt(floorNumberStr) <= 0) {
            errorMessage += "Floor number must be greater than 0.<br>";
            isValid = false;
        }

        if (description == null || description.isEmpty()) {
            errorMessage += "Description is required.<br>";
            isValid = false;
        }

        double price = 0;
        if (priceStr == null || priceStr.isEmpty()) {
            errorMessage += "Price is required.<br>";
            isValid = false;
        } else {
            try {
                price = Double.parseDouble(priceStr);
                if (price < 0) {
                    errorMessage += "Price cannot be negative.<br>";
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                errorMessage += "Invalid price format.<br>";
                isValid = false;
            }
        }

        float area = 0;
        if (areaStr == null || areaStr.isEmpty()) {
            errorMessage += "Area is required.<br>";
            isValid = false;
        } else {
            try {
                area = Float.parseFloat(areaStr);
                if (area < 0) {
                    errorMessage += "Area cannot be negative.<br>";
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                errorMessage += "Invalid area format.<br>";
                isValid = false;
            }
        }

        int roomTypeID = 0;
        if (roomTypeIDStr == null || roomTypeIDStr.isEmpty()) {
            errorMessage += "Room type is required.<br>";
            isValid = false;
        } else {
            try {
                roomTypeID = Integer.parseInt(roomTypeIDStr);
            } catch (NumberFormatException e) {
                errorMessage += "Invalid room type format.<br>";
                isValid = false;
            }
        }

        int statusID = 0;
        if (statusIDStr == null || statusIDStr.isEmpty()) {
            errorMessage += "Status is required.<br>";
            isValid = false;
        } else {
            try {
                statusID = Integer.parseInt(statusIDStr);
            } catch (NumberFormatException e) {
                errorMessage += "Invalid status format.<br>";
                isValid = false;
            }
        }

        // If validation fails, set the error message and forward to the JSP
        if (!isValid) {
            request.setAttribute("message", errorMessage);
            request.setAttribute("alertClass", "alert-danger");
            request.getRequestDispatcher("Views/HouseOwner/AddRoom.jsp").forward(request, response);
            return;
        }

        // Proceed with adding the room if validation is successful
        List<String> faciliList = new ArrayList<>();
        if (facilities != null) {
            for (String facility : facilities) {
                faciliList.add(facility);
            }
        }

        //Process Image
        UploadFile uploadFile = new UploadFile();
        List<String> imageFiles = uploadFile.fileUpload(request, response);
        String images = String.join(",", imageFiles);

        boolean fridge = faciliList.contains("fridge");
        boolean kitchen = faciliList.contains("kitchen");
        boolean washingMachine = faciliList.contains("washingMachine");
        boolean bed = faciliList.contains("bed");
        boolean liveInHouseOwner = faciliList.contains("liveInHouseOwner");
        boolean closedToilet = faciliList.contains("closedToilet");

        Room room = new Room();
        room.setRoomNumber(Integer.parseInt(roomNumberStr));
        room.setFloorNumber(Integer.parseInt(floorNumberStr));
        room.setDescription(description);
        room.setPrice(price);
        room.setArea(area);
        room.setRoomTypeId(roomTypeID);
        room.setStatusId(statusID);
        room.setFridge(fridge);
        room.setKitchen(kitchen);
        room.setWashingMachine(washingMachine);
        room.setBed(bed);
        room.setLiveInHouseOwner(liveInHouseOwner);
        room.setClosedToilet(closedToilet);
        room.setImage(images);
        room.setHouseId(houseId);

        DAORoom dAORoom = new DAORoom();
        int result = dAORoom.addRoom(room);
        if (result > 0) {
            request.setAttribute("message", "Thêm phòng trọ thành công!");
            request.setAttribute("alertClass", "alert-success");
            response.sendRedirect("ListRoom?houseId=" + houseId);
        } else {
            request.setAttribute("message", "Thêm phòng trọ thất bại!");
            request.setAttribute("alertClass", "alert-danger");
            response.sendRedirect("AddRoom?houseId=" + houseId);
        }
    }
}
