/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * CALLBACKGOOGLE.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26      1.0                PhongNN          Implement Google OAuth2.0 login callback
 */
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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This servlet handles the callback from Google OAuth2.0 login. It exchanges
 * the authorization code for an access token and retrieves the user's
 * information from Google.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Nam Phong
 */
@WebServlet(name = "CallbackGoogle", urlPatterns = {"/callbackGoogle"})
public class CallbackGoogle extends HttpServlet {

    // OAuth 2.0 credentials for Google API
    private static final String CLIENT_ID = "676915622854-csj092neit40ppk7bidp1b1afp2maoqo.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackGoogle";
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String USERINFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    /**
     * Handles the HTTP <code>GET</code> method for the Google OAuth2.0 login
     * callback. This method receives the authorization code, exchanges it for
     * an access token, retrieves the user's information from Google, and
     * processes the user's login.
     *
     * @param request The HttpServletRequest object containing the client's
     * request.
     * @param response The HttpServletResponse object containing the servlet's
     * response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error is detected.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Step 3: Retrieve authorization code from the request
        String code = request.getParameter("code");

        // Exchange authorization code for access token
        String tokenResponse = getAccessToken(code);

        // Parse the JSON response to extract the access token
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(tokenResponse).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();

        // Step 4: Use the access token to get user information from Google
        String userInfo = getUserInfo(accessToken);
        JsonObject userJson = parser.parse(userInfo).getAsJsonObject();

        // Extract user information from the JSON response
        String googleUserId = userJson.get("id").getAsString();
        String email = userJson.get("email").getAsString();
        String name = userJson.get("name").getAsString();

        // Check if the user exists in the database
        DAOLogin daoLogin = new DAOLogin();
        User student = daoLogin.getUserByGoogleId(googleUserId);

        // If the user doesn't exist, save the user in the database
        if (student == null) {
            daoLogin.saveUser(googleUserId, name, email);
            student = daoLogin.getUserByGoogleId(googleUserId); // Retrieve the saved user
        }

        // Save user information in the session
        request.getSession().setAttribute("user", student);
        response.sendRedirect("Views/User/HomePage.jsp"); // Redirect to home page
    }

    /**
     * Exchanges the authorization code for an access token from Google's
     * OAuth2.0 server.
     *
     * @param code The authorization code received from Google after the user
     * consents.
     * @return A JSON string containing the access token and other token
     * details.
     * @throws IOException if an I/O error occurs while sending the request.
     */
    private String getAccessToken(String code) throws IOException {
        // Prepare the connection to Google's token endpoint
        URL url = new URL(TOKEN_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Parameters for the POST request to exchange the code for a token
        String params = "code=" + code
                + "&client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET
                + "&redirect_uri=" + REDIRECT_URI
                + "&grant_type=authorization_code";

        // Send the request
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(params);
        writer.flush();

        // Read the response from Google
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        // Append each line of the response to the StringBuilder
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        // Close the streams
        writer.close();
        reader.close();

        // Return the response as a string
        return response.toString();
    }

    /**
     * Retrieves the user's profile information from Google using the access
     * token.
     *
     * @param accessToken The access token to authenticate the request.
     * @return A JSON string containing the user's profile information.
     * @throws IOException if an I/O error occurs while sending the request.
     */
    private String getUserInfo(String accessToken) throws IOException {
        // Prepare the connection to Google's user info endpoint
        URL url = new URL(USERINFO_URL + accessToken);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from Google
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        // Append each line of the response to the StringBuilder
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        // Close the stream
        reader.close();

        // Return the response as a string
        return response.toString();
    }

}
