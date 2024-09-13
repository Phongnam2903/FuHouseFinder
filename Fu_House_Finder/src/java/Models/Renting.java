package Models;

import java.util.Date;

public class Renting {

    private int id;
    private int renterID;
    private int houseID;
    private int roomID;
    private Date startDate;
    private Date endDate;

    public Renting() {
    }

    public Renting(int id, int renterID, int houseID, int roomID, Date startDate, Date endDate) {
        this.id = id;
        this.renterID = renterID;
        this.houseID = houseID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRenterID() {
        return renterID;
    }

    public void setRenterID(int renterID) {
        this.renterID = renterID;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Renting{" + "id=" + id + ", renterID=" + renterID + ", houseID=" + houseID + ", roomID=" + roomID + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

}
