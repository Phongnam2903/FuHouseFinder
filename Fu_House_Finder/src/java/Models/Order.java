package Models;

import java.util.Date;

public class Order {

    private int id;
    private int userID;
    private String FullName;
    private String phoneNumber;
    private String email;
    private String orderContent;
    private int statusID;
    private Date orderedDate;
    private Date solvedDate;
    private int solvedBy;
    private int houseID;
    private String solvedByName;
    private String houseName;
    private String houseDescription;
    private String suggestHouseName;

    public Order() {
    }

    public Order(int id, int userID, String FullName, String phoneNumber, String email, String orderContent, int statusID, Date orderedDate, Date solvedDate, int solvedBy, int houseID, String solvedByName, String houseName, String houseDescription, String suggestHouseName) {
        this.id = id;
        this.userID = userID;
        this.FullName = FullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orderContent = orderContent;
        this.statusID = statusID;
        this.orderedDate = orderedDate;
        this.solvedDate = solvedDate;
        this.solvedBy = solvedBy;
        this.houseID = houseID;
        this.solvedByName = solvedByName;
        this.houseName = houseName;
        this.houseDescription = houseDescription;
        this.suggestHouseName = suggestHouseName;
    }

    public String getSuggestHouseName() {
        return suggestHouseName;
    }

    public void setSuggestHouseName(String suggestHouseName) {
        this.suggestHouseName = suggestHouseName;
    }
    
    

    public String getSolvedByName() {
        return solvedByName;
    }

    public void setSolvedByName(String solvedByName) {
        this.solvedByName = solvedByName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Date solvedDate) {
        this.solvedDate = solvedDate;
    }

    public int getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(int solvedBy) {
        this.solvedBy = solvedBy;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userID=" + userID + ", FullName=" + FullName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", orderContent=" + orderContent + ", statusID=" + statusID + ", orderedDate=" + orderedDate + ", solvedDate=" + solvedDate + ", solvedBy=" + solvedBy + ", houseID=" + houseID + '}';
    }

}
