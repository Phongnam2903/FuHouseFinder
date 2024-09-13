package Models;

import java.util.Date;

public class Room {

    private int id;
    private int roomNumber;
    private int floorNumber;
    private int houseId;
    private String description;
    private String image;
    private int capacity;
    private double price;
    private float area;
    private boolean liveInHouseOwner;
    private boolean fridge;
    private boolean bed;
    private boolean desk;
    private boolean kitchen;
    private boolean closedToilet;
    private boolean washingMachine;
    private Date createdDate;
    private Date lastModifiedDate;
    private int statusId;
    private int roomTypeId;
    private boolean deleted;

    // Constructor
    public Room(int id, int roomNumber, int floorNumber, int houseId, String description, String image, int capacity,
            double price, float area, boolean liveInHouseOwner, boolean fridge, boolean bed, boolean desk,
            boolean kitchen, boolean closedToilet, boolean washingMachine, Date createdDate, Date lastModifiedDate,
            int statusId, int roomTypeId, boolean deleted) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.houseId = houseId;
        this.description = description;
        this.image = image;
        this.capacity = capacity;
        this.price = price;
        this.area = area;
        this.liveInHouseOwner = liveInHouseOwner;
        this.fridge = fridge;
        this.bed = bed;
        this.desk = desk;
        this.kitchen = kitchen;
        this.closedToilet = closedToilet;
        this.washingMachine = washingMachine;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.statusId = statusId;
        this.roomTypeId = roomTypeId;
        this.deleted = deleted;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public boolean isLiveInHouseOwner() {
        return liveInHouseOwner;
    }

    public void setLiveInHouseOwner(boolean liveInHouseOwner) {
        this.liveInHouseOwner = liveInHouseOwner;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public boolean isDesk() {
        return desk;
    }

    public void setDesk(boolean desk) {
        this.desk = desk;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isClosedToilet() {
        return closedToilet;
    }

    public void setClosedToilet(boolean closedToilet) {
        this.closedToilet = closedToilet;
    }

    public boolean isWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(boolean washingMachine) {
        this.washingMachine = washingMachine;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", roomNumber=" + roomNumber + ", floorNumber=" + floorNumber + ", houseId=" + houseId
                + ", description=" + description + ", image=" + image + ", capacity=" + capacity + ", price=" + price
                + ", area=" + area + ", liveInHouseOwner=" + liveInHouseOwner + ", fridge=" + fridge + ", bed=" + bed
                + ", desk=" + desk + ", kitchen=" + kitchen + ", closedToilet=" + closedToilet + ", washingMachine="
                + washingMachine + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
                + ", statusId=" + statusId + ", roomTypeId=" + roomTypeId + ", deleted=" + deleted + "]";
    }
}
