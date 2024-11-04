/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Room;

import java.io.IOException;
import java.io.PrintWriter;
import DAL.House.DAOHouse;
import DAL.Room.DAORoom;
import DAL.Room.DAORoomType;
import DAL.Room.DAORoomStatus;
import Models.House;
import Models.Room;
import Models.RoomStatuses;
import Models.RoomTypes;
import jakarta.servlet.ServletException;
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
public class AddRoom extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRoom</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRoom at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        List<RoomTypes> roomTypes = dbRoomType.getRoomTypes();
        List<RoomStatuses> roomStatuses = dbRoomStatus.getRoomStatus();

        List<House> house = dbHouse.getHouses();

        request.setAttribute("roomTypeList", roomTypes);
        request.setAttribute("roomStatusList", roomStatuses);
        request.setAttribute("houseList", house);
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
    room.setImage(image);
    room.setHouseId(houseId);

    DAORoom dAORoom = new DAORoom();
    int result = dAORoom.addRoom(room);
    if (result > 0) {
        request.setAttribute("message", "Thêm phòng trọ thành công!");
        request.setAttribute("alertClass", "alert-success");
    } else {
        request.setAttribute("message", "Thêm phòng trọ thất bại!");
        request.setAttribute("alertClass", "alert-danger");
    }
    response.sendRedirect("ListRoom?successAdd=true&houseId="+houseId);

}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
