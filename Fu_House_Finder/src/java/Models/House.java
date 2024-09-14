package Models;

import java.util.Date;

public class House {

    private int id;
    private String houseName;
    private String address;
    private String description;
    private float distanceToSchool;
    private int ownerId;
    private double powerPrice;
    private double waterPrice;
    private double otherServicePrice;
    private boolean fingerPrintLock;
    private boolean camera;
    private boolean parking;
    private Date createdDate;
    private Date lastModifiedDate;
    private String image;

    public House() {
    }

    public House(int id, String houseName, String address, String description, float distanceToSchool, int ownerId, double powerPrice, double waterPrice, double otherServicePrice, boolean fingerPrintLock, boolean camera, boolean parking, Date createdDate, Date lastModifiedDate, String image) {
        this.id = id;
        this.houseName = houseName;
        this.address = address;
        this.description = description;
        this.distanceToSchool = distanceToSchool;
        this.ownerId = ownerId;
        this.powerPrice = powerPrice;
        this.waterPrice = waterPrice;
        this.otherServicePrice = otherServicePrice;
        this.fingerPrintLock = fingerPrintLock;
        this.camera = camera;
        this.parking = parking;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.image = image;
    }

    public House(String houseName, String address, String description, float distanceToSchool, int ownerId, double powerPrice, double waterPrice, double otherServicePrice, boolean fingerPrintLock, boolean camera, boolean parking, Date createdDate, Date lastModifiedDate, String image) {
        this.houseName = houseName;
        this.address = address;
        this.description = description;
        this.distanceToSchool = distanceToSchool;
        this.ownerId = ownerId;
        this.powerPrice = powerPrice;
        this.waterPrice = waterPrice;
        this.otherServicePrice = otherServicePrice;
        this.fingerPrintLock = fingerPrintLock;
        this.camera = camera;
        this.parking = parking;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDistanceToSchool() {
        return distanceToSchool;
    }

    public void setDistanceToSchool(float distanceToSchool) {
        this.distanceToSchool = distanceToSchool;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public double getPowerPrice() {
        return powerPrice;
    }

    public void setPowerPrice(double powerPrice) {
        this.powerPrice = powerPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getOtherServicePrice() {
        return otherServicePrice;
    }

    public void setOtherServicePrice(double otherServicePrice) {
        this.otherServicePrice = otherServicePrice;
    }

    public boolean isFingerPrintLock() {
        return fingerPrintLock;
    }

    public void setFingerPrintLock(boolean fingerPrintLock) {
        this.fingerPrintLock = fingerPrintLock;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "House{" + "id=" + id + ", houseName=" + houseName + ", address=" + address + ", description=" + description + ", distanceToSchool=" + distanceToSchool + ", ownerId=" + ownerId + ", powerPrice=" + powerPrice + ", waterPrice=" + waterPrice + ", otherServicePrice=" + otherServicePrice + ", fingerPrintLock=" + fingerPrintLock + ", camera=" + camera + ", parking=" + parking + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", image=" + image + '}';
    }

}