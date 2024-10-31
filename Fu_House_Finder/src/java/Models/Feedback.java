package Models;

import java.util.Date;

public class Feedback {

    private int id;
    private String title;
    private String description;
    private String status;
    private String reply;
    private Date sentTime;
    private Date repliedTime;
    private Date createdDate;
    private int renterId;
    private String renterEmail;
    private String renterName;
    private String houseName;
    private int roomNumber;

    public Feedback() {
    }

    public Feedback(int id, String title, String description, String status, String reply, Date sentTime, Date repliedTime, Date createdDate, int renterId, String renterEmail, String renterName, String houseName, int roomNumber) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.reply = reply;
        this.sentTime = sentTime;
        this.repliedTime = repliedTime;
        this.createdDate = createdDate;
        this.renterId = renterId;
        this.renterEmail = renterEmail;
        this.renterName = renterName;
        this.houseName = houseName;
        this.roomNumber = roomNumber;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    

    public String getRenterEmail() {
        return renterEmail;
    }

    public void setRenterEmail(String renterEmail) {
        this.renterEmail = renterEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Date getRepliedTime() {
        return repliedTime;
    }

    public void setRepliedTime(Date repliedTime) {
        this.repliedTime = repliedTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + ", reply=" + reply + ", sentTime=" + sentTime + ", repliedTime=" + repliedTime + ", createdDate=" + createdDate + ", renterId=" + renterId + '}';
    }

}
