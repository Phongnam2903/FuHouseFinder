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

    public int getHouseOwnerCount() {
        String sql = "SELECT COUNT(*) FROM [User] Where Roleid = 5";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

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
        h.OwnerID,                           
        h.PowerPrice,                        
        h.WaterPrice,                        
        h.OtherServicePrice,                 
        h.FingerPrintLock,                   
        h.Camera,                            
        h.Parking,                           
        MIN(r.Price) AS MinPrice,            
        MAX(r.Price) AS MaxPrice,            
        h.CreatedDate,                       
        h.LastModifiedDate,                  
        h.Image,                             
        (SELECT COUNT(*) FROM House) AS TotalHouse,     
        (SELECT COUNT(*) FROM Room WHERE HouseID = h.ID) AS TotalRooms, 
        (SELECT COUNT(*) FROM Room WHERE HouseID = h.ID AND StatusID = 1) AS TotalAvailableRooms, 
        u.FullName AS OwnerName,                 
        u.PhoneNumber AS OwnerPhoneNumber,   
        STRING_AGG(r.FloorNumber, ', ') AS Floors, 
        STRING_AGG(r.RoomNumber, ', ') AS Rooms    
    FROM 
        House h
    LEFT JOIN 
        Room r ON h.ID = r.HouseID
    LEFT JOIN 
        [User] u ON h.OwnerID = u.ID
    WHERE 
        h.OwnerID = ? 
    GROUP BY
        h.ID, 
        h.HouseName, 
        h.Address, 
        h.Description, 
        h.DistanceToSchool, 
        h.OwnerID, 
        h.PowerPrice, 
        h.WaterPrice, 
        h.OtherServicePrice, 
        h.FingerPrintLock, 
        h.Camera, 
        h.Parking, 
        h.CreatedDate, 
        h.LastModifiedDate, 
        h.Image, 
        u.FullName, 
        u.PhoneNumber
    ORDER BY
        h.ID
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
                House house = new House();

                // Thiết lập các thuộc tính của House từ kết quả truy vấn
                house.setId(rs.getInt("HouseID"));
                house.setHouseName(rs.getString("HouseName"));
                house.setAddress(rs.getString("Address"));
                house.setDescription(rs.getString("HouseDescription"));
                house.setDistanceToSchool(rs.getFloat("DistanceToSchool"));
                house.setOwnerId(rs.getInt("OwnerID"));
                house.setPowerPrice(rs.getDouble("PowerPrice"));
                house.setWaterPrice(rs.getDouble("WaterPrice"));
                house.setOtherServicePrice(rs.getDouble("OtherServicePrice"));
                house.setFingerPrintLock(rs.getBoolean("FingerPrintLock"));
                house.setCamera(rs.getBoolean("Camera"));
                house.setParking(rs.getBoolean("Parking"));
                house.setCreatedDate(rs.getDate("CreatedDate"));
                house.setLastModifiedDate(rs.getDate("LastModifiedDate"));
                house.setImage(rs.getString("Image"));

                // Thiết lập giá trị minPrice và maxPrice
                house.setMinPrice(rs.getDouble("MinPrice"));
                house.setMaxPrice(rs.getDouble("MaxPrice"));

                // Thiết lập tổng số nhà, phòng và phòng trống
                house.setTotalHouse(rs.getInt("TotalHouse"));
                house.setTotalRooms(rs.getInt("TotalRooms"));
                house.setTotalAvailableRooms(rs.getInt("TotalAvailableRooms"));

                // Thiết lập thông tin chủ nhà
                house.setOwnerName(rs.getString("OwnerName"));
                house.setOwnerPhoneNumber(rs.getString("OwnerPhoneNumber"));

                // Thêm đối tượng House vào danh sách
                houses.add(house);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHouseOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return houses;
    }

    public static void main(String[] args) {
        DAOHouseOwner daoHouseOwner = new DAOHouseOwner();
        int houseOwnerCount = daoHouseOwner.getHouseOwnerCount();
        System.out.println("số lượng tài khoản là : " + houseOwnerCount);
//        int ownerId = 41;
//        int page = 1;
//        int pageSize = 5;
//        List<House> house = daoHouseOwner.getHouseByPage(ownerId, page, pageSize);
//        for (House house1 : house) {
//            System.out.println("House ID: " + house1.getId());
//            System.out.println("House Name: " + house1.getHouseName());
//            System.out.println("Address: " + house1.getAddress());
//            System.out.println("Description: " + house1.getDescription());
//            System.out.println("Distance to School: " + house1.getDistanceToSchool());
//            System.out.println("Power Price: " + house1.getPowerPrice());
//            System.out.println("Water Price: " + house1.getWaterPrice());
//            System.out.println("Created Date: " + house1.getCreatedDate());
//        }
    }
}
