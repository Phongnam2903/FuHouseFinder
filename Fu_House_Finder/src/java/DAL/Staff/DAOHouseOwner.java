package DAL.Staff;

import DAL.Admin.ManageAccount;
import DAL.DBContext;
import Models.House;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOHouseOwner extends DBContext {

    public User getLandlordDetailById(int id) {
        User landlord = null;
        String sql = """
                    SELECT  
                    u.ID,  u.FacebookUserId,  u.GoogleUserId,
                    u.FullName,  u.Password, u.Email,
                    u.PhoneNumber, u.DateOfBirth,  u.Address,  
                    u.StatusID,  u.Roleid, u.Avatar, 
                    u.CreatedDate,u.RoomHistoriesID,COUNT(DISTINCT h.ID) AS totalHouses, 
                    COUNT(r.ID) AS totalRooms,SUM(CASE WHEN r.StatusID = 2 THEN 1 END) AS emptyRooms 
                FROM  [User] u
                LEFT JOIN  House h ON u.ID = h.OwnerID   
                LEFT JOIN  Room r ON h.ID = r.HouseID   
                WHERE  u.ID = ?                  
                GROUP BY  
                    u.ID, u.FacebookUserId, u.GoogleUserId,
                    u.FullName, u.Password, u.Email,
                    u.PhoneNumber, u.DateOfBirth, u.Address,
                    u.StatusID, u.Roleid, u.Avatar, u.CreatedDate,u.RoomHistoriesID;
                """;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String facebookUserid = rs.getString(2);
                String googleUserid = rs.getString(3);
                String username = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                String phone = rs.getString(7);
                Date dateOfBirth = rs.getDate(8);
                String address = rs.getString(9);
                int statusID = rs.getInt(10);
                int roleID = rs.getInt(11);
                String avatar = rs.getString(12);
                Date createdDate = rs.getDate(13);
                int roomHistoriesID = rs.getInt(14);
                int totalHouses = rs.getInt(15);
                int totalRooms = rs.getInt(16);
                int emptyRooms = rs.getInt(17);
                landlord = new User(id, facebookUserid, googleUserid, username, password, email, phone,
                        dateOfBirth, address, statusID, roleID, avatar, createdDate, roomHistoriesID,
                        totalHouses, totalRooms, emptyRooms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return landlord;
    }

    public List<House> getHouseByPage(int ownerId, int page, int pageSize) {
        List<House> houses = new ArrayList<>();
        String sql = """
       SELECT 
            h.ID AS HouseID,
            h.HouseName,
            h.Address,
            h.Description AS HouseDescription,
            h.DistanceToSchool,
            h.PowerPrice,
            h.WaterPrice,
            r.RoomNumber,
            r.FloorNumber,
            r.Price,
            r.Area,
            r.Bed,
            r.ClosedToilet,
            r.Desk,
            r.Fridge,
            r.Kitchen,
            r.WashingMachine,
            r.LiveInHouseOwner
        FROM 
            House h
        LEFT JOIN 
            Room r ON h.ID = r.HouseID
        WHERE 
            h.OwnerID = ? 
        ORDER BY
            r.FloorNumber ASC,
            r.RoomNumber
        OFFSET ? ROWS
        FETCH NEXT ? ROWS ONLY;
        """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int offset = (page - 1) * pageSize;
            statement.setInt(1, ownerId);
            statement.setInt(2, offset);
            statement.setInt(3, pageSize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String houseName = rs.getString(2);
                String address = rs.getString(3);
                String description = rs.getString(4);
                float distanceToSchool = rs.getFloat(5);
                double powerPrice = rs.getDouble(6);
                double waterPrice = rs.getDouble(7);
                double otherServicePrice = rs.getDouble(8);
                boolean fingerPrintLock = rs.getBoolean(9);
                boolean camera = rs.getBoolean(10);
                boolean parking = rs.getBoolean(11);
                Date createdDate = rs.getDate(12);
                Date lastModifiedDate = rs.getDate(13);
                String image = rs.getString(14);
                double minPrice = rs.getDouble(15);
                double maxPrice = rs.getDouble(16);
                int totalHouse = rs.getInt(17);
                double totalPrice = rs.getDouble(18);
                int totalRooms = rs.getInt(19);
                int totalAvailableRooms = rs.getInt(20);
                String ownerName = rs.getString(21);
                String ownerPhoneNumber = rs.getString(22);
                double averageStar = rs.getDouble(23);

                House house = new House(id, houseName, address, description, distanceToSchool,
                        ownerId, powerPrice, waterPrice, otherServicePrice, fingerPrintLock,
                        camera, parking, createdDate, lastModifiedDate, image,
                        minPrice, maxPrice, totalHouse, totalPrice, totalRooms,
                        totalAvailableRooms, ownerName, ownerPhoneNumber, averageStar);
                houses.add(house);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouseOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return houses;
    }

    public static void main(String[] args) {
        DAOHouseOwner daoHouseOwner = new DAOHouseOwner();
        int ownerId = 41;
        int page = 1;
        int pageSize = 5;
        List<House> house = daoHouseOwner.getHouseByPage(ownerId, page, pageSize);
        for (House house1 : house) {
            System.out.println("House ID: " + house1.getId());
            System.out.println("House Name: " + house1.getHouseName());
            System.out.println("Address: " + house1.getAddress());
            System.out.println("Description: " + house1.getDescription());
            System.out.println("Distance to School: " + house1.getDistanceToSchool());
            System.out.println("Power Price: " + house1.getPowerPrice());
            System.out.println("Water Price: " + house1.getWaterPrice());
            System.out.println("Created Date: " + house1.getCreatedDate());
        }
    }
}
