/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DAL.House.DAOHouse;
import DAL.Room.DAORoom;
import DAL.Room.DAORoomStatus;
import DAL.Room.DAORoomType;
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

/**
 *
 * @author My Lap
 */
@WebServlet(name = "UpdateRoom", urlPatterns = {"/UpdateRoom"})
public class UpdateRoom extends HttpServlet {

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
        String id_raw = request.getParameter("id");
        int id =Integer.parseInt(id_raw);
        DAORoom r = new DAORoom();
        
        DAORoomType dbRoomType = new DAORoomType();
        DAORoomStatus dbRoomStatus = new DAORoomStatus();
        DAOHouse dbHouse = new DAOHouse();
        Room room = r.getRoomsById(id);
        request.setAttribute("room", room);

        List<RoomTypes> roomTypes = dbRoomType.getRoomTypes();
        List<RoomStatuses> roomStatuses = dbRoomStatus.getRoomStatus();

        List<House> house = dbHouse.getHouses();
        
        request.setAttribute("roomTypeList", roomTypes);
        request.setAttribute("roomStatusList", roomStatuses);
        request.setAttribute("houseList", house);
        request.getRequestDispatcher("Views/HouseOwner/UpdateRoom.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int floorNumber = Integer.parseInt(request.getParameter("floorNumber"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        float area = Float.parseFloat(request.getParameter("area"));
        int roomTypeID = Integer.parseInt(request.getParameter("roomTypeId"));
        int statusID = Integer.parseInt(request.getParameter("statusId"));
        String[] facilities = request.getParameterValues("facilities");
        List<String> faciliList = new ArrayList<>();
        for (String facility : facilities) {
            faciliList.add(facility);
        }
        boolean fridge = faciliList.contains("fridge");
        boolean kitchen = faciliList.contains("kitchen");
        boolean washingMachine = faciliList.contains("washingMachine");
        boolean bed = faciliList.contains("bed");
        boolean liveInHouseOwner = faciliList.contains("liveInHouseOwner");
        boolean closedToilet = faciliList.contains("closedToilet");
        int houseId = Integer.parseInt(request.getParameter("houseId"));
        String image = "";

        Room room = new Room();
        room.setId(id);
        room.setRoomNumber(roomNumber);
        room.setFloorNumber(floorNumber);
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

        DAL.Room.DAORoom dAORoom = new DAORoom();
        int id_raw;
        try {
            id_raw = id;
            Room r = new Room();
            r.setId(id_raw);
            r.setRoomNumber(roomNumber);
            r.setFloorNumber(floorNumber);
            r.setDescription(description);
            r.setPrice(price);
            r.setArea(area);
            r.setRoomTypeId(roomTypeID);
            r.setStatusId(statusID);
            r.setFridge(fridge);
            r.setKitchen(kitchen);
            r.setWashingMachine(washingMachine);
            r.setBed(bed);
            r.setLiveInHouseOwner(liveInHouseOwner);
            r.setClosedToilet(closedToilet);
            r.setImage(image);
            r.setHouseId(houseId);
            dAORoom.updateRoom(room);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    response.sendRedirect("ListRoom?successUpdate=true");

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
