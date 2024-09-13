package Models;

import java.util.Date;

public class Comment {

    private int id;
    private String description;
    private Date createdDate;
    private int userID;
    private int houseID;
    private int roomID;

    public Comment() {
    }

    public Comment(int id, String description, Date createdDate, int userID, int houseID, int roomID) {
        this.id = id;
        this.description = description;
        this.createdDate = createdDate;
        this.userID = userID;
        this.houseID = houseID;
        this.roomID = roomID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", description=" + description + ", createdDate=" + createdDate + ", userID=" + userID + ", houseID=" + houseID + ", roomID=" + roomID + '}';
    }

}
