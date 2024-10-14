package DAL.Staff;

import DAL.DBContext;
import Models.House;
import Models.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORoomForStaff extends DBContext {

    public House getHouseById(int id) {
        House house = null;
        String sql = """
             SELECT 
                 h.HouseName, 
                 h.PowerPrice, 
                 h.WaterPrice, 
                 h.Address, 
                 h.Description, 
                 h.Image,
                 COUNT(r.ID) AS TotalRooms, 
                 COUNT(CASE WHEN r.StatusID = 1 THEN 1 END) AS TotalAvailableRooms
             FROM 
                 House h
             LEFT JOIN 
                 Room r ON h.ID = r.HouseID
             WHERE 
                 h.ID = ?
             GROUP BY 
                 h.HouseName, 
                 h.PowerPrice, 
                 h.WaterPrice, 
                 h.Address, 
                 h.Description,
                 h.Image;
             """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                house = new House();
                house.setHouseName(rs.getString("HouseName"));
                house.setPowerPrice(rs.getDouble("PowerPrice"));
                house.setWaterPrice(rs.getDouble("WaterPrice"));
                house.setAddress(rs.getString("Address"));
                house.setDescription(rs.getString("Description"));
                house.setTotalRooms(rs.getInt("TotalRooms"));
                house.setTotalAvailableRooms(rs.getInt("TotalAvailableRooms"));
                house.setImage(rs.getString("Image"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAORoomForStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Sau khi lấy thông tin nhà, gọi phương thức lấy danh sách phòng
        if (house != null) {
            house.setRoom(getRoomsByHouseId(id));
        }

        return house;
    }

    public List<Room> getRoomsByHouseId(int houseId) {
        List<Room> rooms = new ArrayList<>();
        String sql = """
             SELECT 
                 r.RoomNumber,  
                 r.Price, 
                 r.Area, 
                 r.Bed,
                 r.ClosedToilet,
                 r.Desk,
                 r.Fridge,
                 r.Kitchen,
                 r.StatusID,
                 r.roomTypeId
             FROM 
                 Room r
             WHERE 
                 r.HouseID = ?;
             """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, houseId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomNumber(rs.getInt("RoomNumber"));
                room.setPrice(rs.getDouble("Price"));
                room.setArea(rs.getFloat("Area"));
                room.setBed(rs.getBoolean("Bed"));
                room.setClosedToilet(rs.getBoolean("ClosedToilet"));
                room.setDesk(rs.getBoolean("Desk"));
                room.setFridge(rs.getBoolean("Fridge"));
                room.setKitchen(rs.getBoolean("Kitchen"));
                room.setStatusId(rs.getInt("StatusID"));
                room.setRoomTypeId(rs.getInt("roomTypeId"));
                rooms.add(room);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAORoomForStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rooms;
    }

    public static void main(String[] args) {
        DAORoomForStaff roomDAO = new DAORoomForStaff();

        // ID của nhà mà bạn muốn lấy danh sách phòng
        int houseId = 1; // Giả sử nhà có ID là 1

        // Lấy thông tin house
        House houseDetail = roomDAO.getHouseById(houseId);
        if (houseDetail != null) {
            System.out.println("Thông tin nhà trọ:");
            System.out.println("Tên nhà trọ: " + houseDetail.getHouseName());
            System.out.println("Địa chỉ: " + houseDetail.getAddress());
            System.out.println("Giá điện: " + houseDetail.getPowerPrice() + " VND/kWh");
            System.out.println("Giá nước: " + houseDetail.getWaterPrice() + " VND/m³");
            System.out.println("Thông tin khác: " + houseDetail.getDescription());
        } else {
            System.out.println("Không tìm thấy nhà với ID: " + houseId);
        }

        // Lấy danh sách phòng
        List<Room> roomList = roomDAO.getRoomsByHouseId(houseId);

        // Kiểm tra và hiển thị danh sách phòng
        if (roomList != null && !roomList.isEmpty()) {
            System.out.println("\nDanh sách phòng trọ:");
            for (Room room : roomList) {
                System.out.println("Tên phòng: " + room.getRoomNumber());
                System.out.println("Giá phòng: " + room.getPrice() + " VND");
                System.out.println("Diện tích: " + room.getArea() + " m²");

                System.out.println("Trạng thái: " + (room.getStatusId() == 1 ? "Còn Trống" : "Đã Đầy"));
                System.out.println("Tiện ích: " + getUtilities(room));
                System.out.println("----------------------------");
            }
        } else {
            System.out.println("Không có phòng nào được tìm thấy.");
        }
    }

    // Hàm để lấy tiện ích của phòng
    private static String getUtilities(Room room) {
        StringBuilder utilities = new StringBuilder();
        if (room.isBed()) {
            utilities.append("Giường, ");
        }
        if (room.isClosedToilet()) {
            utilities.append("Toilet riêng, ");
        }
        if (room.isDesk()) {
            utilities.append("Bàn làm việc, ");
        }
        if (room.isFridge()) {
            utilities.append("Tủ lạnh, ");
        }
        if (room.isKitchen()) {
            utilities.append("Bếp, ");
        }
        if (utilities.length() > 0) {
            // Loại bỏ dấu phẩy cuối cùng
            utilities.setLength(utilities.length() - 2);
        } else {
            utilities.append("Không có tiện ích nào.");
        }
        return utilities.toString();
    }
}
