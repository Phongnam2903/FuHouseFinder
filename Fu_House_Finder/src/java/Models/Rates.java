package Models;

import java.util.Date;

public class Rates {

    private int id;
    private int star;
    private int userID;
    private int houseID;
    private Date createdDate;
    private String decription;
    private String houseOwnerReply;
    private Date lastModifiedDate;
    private int lastModifiedBy;
    private String userName;

    public Rates() {
    }

    public Rates(int id, int star, int userID, int houseID, Date createdDate, String decription, String houseOwnerReply, Date lastModifiedDate, int lastModifiedBy, String userName) {
        this.id = id;
        this.star = star;
        this.userID = userID;
        this.houseID = houseID;
        this.createdDate = createdDate;
        this.decription = decription;
        this.houseOwnerReply = houseOwnerReply;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getHouseOwnerReply() {
        return houseOwnerReply;
    }

    public void setHouseOwnerReply(String houseOwnerReply) {
        this.houseOwnerReply = houseOwnerReply;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
