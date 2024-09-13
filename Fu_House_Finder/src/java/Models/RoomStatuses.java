package Models;

import java.util.Date;

public class RoomStatuses {

    private int statusID;
    private String statusName;
    private Date createdDate;

    public RoomStatuses() {
    }

    public RoomStatuses(int statusID, String statusName, Date createdDate) {
        this.statusID = statusID;
        this.statusName = statusName;
        this.createdDate = createdDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "RoomStatuses{" + "statusID=" + statusID + ", statusName=" + statusName + ", createdDate=" + createdDate + '}';
    }

}
