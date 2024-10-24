package Models;

import java.util.Date;

public class User {

    private int id;
    private String facebookUserid;
    private String googleUserid;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String address;
    private int StatusID;
    private int roleID;
    private String avatar;
    private Date createdDate;
    private int roomHistoriesID;
    private int totalHouses;
    private int totalRooms;
    private int emptyRooms;

    public User() {
    }

    public User(int id, String facebookUserid, String googleUserid, String username, String password, String email, String phone, Date dateOfBirth, String address, int StatusID, int roleID, String avatar, Date createdDate, int roomHistoriesID) {
        this.id = id;
        this.facebookUserid = facebookUserid;
        this.googleUserid = googleUserid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.StatusID = StatusID;
        this.roleID = roleID;
        this.avatar = avatar;
        this.createdDate = createdDate;
        this.roomHistoriesID = roomHistoriesID;
    }

    public User(int id, String facebookUserid, String googleUserid, String username, String password, String email, String phone, Date dateOfBirth, String address, int StatusID, int roleID, String avatar, Date createdDate, int roomHistoriesID, int totalHouses, int totalRooms, int emptyRooms) {
        this.id = id;
        this.facebookUserid = facebookUserid;
        this.googleUserid = googleUserid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.StatusID = StatusID;
        this.roleID = roleID;
        this.avatar = avatar;
        this.createdDate = createdDate;
        this.roomHistoriesID = roomHistoriesID;
        this.totalHouses = totalHouses;
        this.totalRooms = totalRooms;
        this.emptyRooms = emptyRooms;
    }

    public int getTotalHouses() {
        return totalHouses;
    }

    public void setTotalHouses(int totalHouses) {
        this.totalHouses = totalHouses;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getEmptyRooms() {
        return emptyRooms;
    }

    public void setEmptyRooms(int emptyRooms) {
        this.emptyRooms = emptyRooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacebookUserid() {
        return facebookUserid;
    }

    public void setFacebookUserid(String facebookUserid) {
        this.facebookUserid = facebookUserid;
    }

    public String getGoogleUserid() {
        return googleUserid;
    }

    public void setGoogleUserid(String googleUserid) {
        this.googleUserid = googleUserid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoomHistoriesID() {
        return roomHistoriesID;
    }

    public void setRoomHistoriesID(int roomHistoriesID) {
        this.roomHistoriesID = roomHistoriesID;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", facebookUserid=" + facebookUserid + ", googleUserid=" + googleUserid + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", StatusID=" + StatusID + ", roleID=" + roleID + ", avatar=" + avatar + ", createdDate=" + createdDate + ", roomHistoriesID=" + roomHistoriesID + ", totalHouses=" + totalHouses + ", totalRooms=" + totalRooms + ", emptyRooms=" + emptyRooms + '}';
    }

}
