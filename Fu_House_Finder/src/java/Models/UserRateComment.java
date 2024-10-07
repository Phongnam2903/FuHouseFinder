package Models;

/**
 *
 * @author ADMIN
 */
public class UserRateComment {

    private String userName;
    private String commentDescription;
    private int starRating;

    public UserRateComment(String userName, String commentDescription, int starRating) {
        this.userName = userName;
        this.commentDescription = commentDescription;
        this.starRating = starRating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }
}
