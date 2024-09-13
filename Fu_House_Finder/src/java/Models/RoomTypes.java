
package Models;

import java.util.Date;


public class RoomTypes {
    private int roomTypeID;
    private String roomTypeName;
    private Date createdDate;

    public RoomTypes() {
    }

    public RoomTypes(int roomTypeID, String roomTypeName, Date createdDate) {
        this.roomTypeID = roomTypeID;
        this.roomTypeName = roomTypeName;
        this.createdDate = createdDate;
    }

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "RoomTypes{" + "roomTypeID=" + roomTypeID + ", roomTypeName=" + roomTypeName + ", createdDate=" + createdDate + '}';
    }
    
}
