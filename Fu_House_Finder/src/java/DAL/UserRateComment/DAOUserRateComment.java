/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.UserRateComment;

import DAL.DAO;
import Models.UserRateComment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class DAOUserRateComment extends DAO{
    public List<UserRateComment> getCommentsAndRatingsByHouseId(int houseId) {
        List<UserRateComment> list = new ArrayList<>();
        String sql = "SELECT u.FullName AS UserName, c.Description AS CommentDescription, r.Star AS StarRating " +
                     "FROM [Comment] c " +
                     "JOIN [Rates] r ON c.UserID = r.UserID AND c.HouseID = r.HouseID " +
                     "JOIN [User] u ON c.UserID = u.ID " +
                     "WHERE c.HouseID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, houseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("UserName");
                String commentDescription = rs.getString("CommentDescription");
                int starRating = rs.getInt("StarRating");
                list.add(new UserRateComment(userName, commentDescription, starRating));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
