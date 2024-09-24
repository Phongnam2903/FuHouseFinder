/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Login.Login_Social;

import DAL.DBContext;
import DAL.Login.DAOLogin;
import Models.Student;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "CallbackGoogle", urlPatterns = {"/callbackGoogle"})
public class CallbackGoogle extends HttpServlet {
    
    private static final String CLIENT_ID = "676915622854-csj092neit40ppk7bidp1b1afp2maoqo.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackGoogle";
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String USERINFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        // Bước 3: Đổi mã lấy token
        String tokenResponse = getAccessToken(code);

        // Parse JSON để lấy access token
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(tokenResponse).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();

        // Bước 4: Lấy thông tin người dùng
        String userInfo = getUserInfo(accessToken);
        JsonObject userJson = parser.parse(userInfo).getAsJsonObject();
        
        String googleUserId = userJson.get("id").getAsString();
        String email = userJson.get("email").getAsString();
        String name = userJson.get("name").getAsString();

        //lưu thông tin vào database
        //Check user is exist
        DAOLogin daoLogin = new DAOLogin();
        Student student = daoLogin.getUserByGoogleId(googleUserId);
        
        if (student == null) {
            //Nếu người dùng chưa tồn tại, lưu vào database với các thuộc tính cần thiết
            daoLogin.saveUser(googleUserId, name, email);
            //Lấy thông tin sau khi lưu
            student = daoLogin.getUserByGoogleId(googleUserId);
        }

        //Lưu thông tin người dùng vào session
        request.getSession().setAttribute("user", student);
        response.sendRedirect("Views/User/HomePage.jsp");
//        response.getWriter().println("Login successful! Hello, " + googleUserId + name + " (" + email + ")");

    }
    
    private String getAccessToken(String code) throws IOException {
        URL url = new URL(TOKEN_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        
        String params = "code=" + code
                + "&client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET
                + "&redirect_uri=" + REDIRECT_URI
                + "&grant_type=authorization_code";
        
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(params);
        writer.flush();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        
        writer.close();
        reader.close();
        
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
