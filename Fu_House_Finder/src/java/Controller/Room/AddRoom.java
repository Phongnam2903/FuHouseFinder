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
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int floorNumber = Integer.parseInt(request.getParameter("floorNumber"));
        String description = request.getParameter(request.getParameter("description"));
        double price = Double.parseDouble(request.getParameter("price"));
        float area = Float.parseFloat(request.getParameter("area"));
        int roomTypeID = Integer.parseInt(request.getParameter("roomTypeId"));
        int statusID = Integer.parseInt(request.getParameter("statusId"));
        boolean fridge = request.getParameter(request.getParameter("fridge")) != null;
        boolean kitchen = request.getParameter(request.getParameter("kitchen")) != null;
        boolean washingMachine = request.getParameter(request.getParameter("washingMachine")) != null;
        boolean bed = request.getParameter(request.getParameter("bed")) != null;
        boolean liveInHouseOwner = request.getParameter(request.getParameter("liveInHouseOwner")) != null;
        boolean closedToilet = request.getParameter(request.getParameter("closedToilet")) != null;
        int houseId = Integer.parseInt(request.getParameter("houseId"));
        String image = "";

        Room room = new Room();
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
        int result = dAORoom.addRoom(room);
        if (result > 0) {
            request.setAttribute("message", "Thêm phòng trọ thành công!");
            request.setAttribute("alertClass", "alert-success");
        } else {
            request.setAttribute("message", "Thêm phòng trọ thất bại!");
            request.setAttribute("alertClass", "alert-danger");

        }
        request.getRequestDispatcher("Views/HouseOwner/AddRoom.jsp").forward(request, response);
        response.sendRedirect("ListRoom");

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
