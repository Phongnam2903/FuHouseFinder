package Models;

import java.util.Date;

public class Feedback {

    private int id;
    private String title;
    private String description;
    private String status;
    private String reply;
    private Date senttime;
    private Date repliedtime;
    private Date createddate;
    private int renterid;

    public Feedback() {
    }

    public Feedback(int id, String title, String description, String status, String reply, Date senttime, Date repliedtime, Date createddate, int renterid) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.reply = reply;
        this.senttime = senttime;
        this.repliedtime = repliedtime;
        this.createddate = createddate;
        this.renterid = renterid;
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

    public Date getSenttime() {
        return senttime;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }

    public Date getRepliedtime() {
        return repliedtime;
    }

    public void setRepliedtime(Date repliedtime) {
        this.repliedtime = repliedtime;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public int getRenterid() {
        return renterid;
    }

    public void setRenterid(int renterid) {
        this.renterid = renterid;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + ", reply=" + reply + ", senttime=" + senttime + ", repliedtime=" + repliedtime + ", createddate=" + createddate + ", renterid=" + renterid + '}';
    }

}
