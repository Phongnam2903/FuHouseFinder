package Models;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private String status;
    private String image;
    private double price;
    private int viewCount;
    private String Location;
    private int HouseID;

    public Post() {
    }

    public Post(int id, String title, String content, Date createdDate, Date updatedDate, String status, String image, double price, int viewCount, String Location, int HouseID) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
        this.image = image;
        this.price = price;
        this.viewCount = viewCount;
        this.Location = Location;
        this.HouseID = HouseID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getHouseID() {
        return HouseID;
    }

    public void setHouseID(int HouseID) {
        this.HouseID = HouseID;
    }
    
}
