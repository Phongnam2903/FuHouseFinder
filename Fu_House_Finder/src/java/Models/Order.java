package Models;

import java.util.Date;

public class Order {

    private int id;
    private int userID;
    private String userName;
    private String phoneNumber;
    private String email;
    private String orderContent;
    private int statusID;
    private Date orderedDate;
    private Date solvedDate;
    private int solvedBy;

    public Order() {
    }

    public Order(int id, int userID, String userName, String phoneNumber, String email, String orderContent, int statusID, Date orderedDate, Date solvedDate, int solvedBy) {
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orderContent = orderContent;
        this.statusID = statusID;
        this.orderedDate = orderedDate;
        this.solvedDate = solvedDate;
        this.solvedBy = solvedBy;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userID=" + userID + ", userName=" + userName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", orderContent=" + orderContent + ", statusID=" + statusID + ", orderedDate=" + orderedDate + ", solvedDate=" + solvedDate + ", solvedBy=" + solvedBy + '}';
    }

}
