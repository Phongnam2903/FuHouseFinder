package Models;

import java.util.Date;

public class Rates {

    private int rateID;
    private int Star;
    private int commentID;
    private int userID;
    private int houseID;
    private Date createdDate;
    private Date lastModifiedDate;

    public Rates() {
    }

    public Rates(int rateID, int Star, int commentID, int userID, int houseID, Date createdDate, Date lastModifiedDate) {
        this.rateID = rateID;
        this.Star = Star;
        this.commentID = commentID;
        this.userID = userID;
        this.houseID = houseID;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getRateID() {
        return rateID;
    }

    public void setRateID(int rateID) {
        this.rateID = rateID;
    }

    public int getStar() {
        return Star;
    }

    public void setStar(int Star) {
        this.Star = Star;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Rates{" + "rateID=" + rateID + ", Star=" + Star + ", commentID=" + commentID + ", userID=" + userID + ", houseID=" + houseID + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + '}';
    }

}
