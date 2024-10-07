package Controllers.Login.Login_Social;

import DAL.Login.DAOLogin;
import Models.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "CallbackFacebook", urlPatterns = {"/callbackFacebook"})
public class CallbackFacebook extends HttpServlet {

    private static final String CLIENT_ID = "488592800648512";
    private static final String CLIENT_SECRET = "77ca382ab4b2c26a844e92ce63d53cab";
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackFacebook";
    private static final String TOKEN_URL = "https://graph.facebook.com/v12.0/oauth/access_token";
    private static final String USERINFO_URL = "https://graph.facebook.com/me?fields=id,name,email&access_token=";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter(("code"));
        // Bước 3: Đổi mã lấy token
        String tokenResponse = getAccessToken(code);

        // Parse JSON để lấy access token
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(tokenResponse).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();

        // Bước 4: Lấy thông tin người dùng
        String userInfo = getUserInfo(accessToken);
        JsonObject userJson = parser.parse(userInfo).getAsJsonObject();

        String email = userJson.get("email").getAsString();
        String name = userJson.get("name").getAsString();
        String facebookUserId = userJson.get("id").getAsString();

        DAOLogin daoLogin = new DAOLogin();
        User student = daoLogin.getUserByFacebookId(facebookUserId);
        // Bạn có thể lưu thông tin người dùng vào session hoặc database tại đây
        if (student == null) {
            daoLogin.saveUserByFacebook(facebookUserId, name, email);
            student = daoLogin.getUserByFacebookId(facebookUserId);
        }
        request.getSession().setAttribute("user", student);
        response.sendRedirect("homePage");
    }

    private String getAccessToken(String code) throws IOException {
        URL url = new URL(TOKEN_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&client_secret=" + CLIENT_SECRET + "&code=" + code);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    private String getUserInfo(String accessToken) throws IOException {
        URL url = new URL(USERINFO_URL + accessToken);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return response.toString();
    }

}
