/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * CALLBACKFACEBOOK.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-18      1.0                PhongNN          Implement Facebook OAuth2.0 login callback
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
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles the OAuth2.0 login callback for Facebook. After Facebook redirects to
 * this servlet with an authorization code, the servlet exchanges the code for
 * an access token and then fetches the user's profile information using the
 * token. The user's Facebook ID, name, and email are retrieved and either
 * logged in or registered in the database.
 * <p>
 * Bugs: None
 *
 * @author PhongNN
 */
@WebServlet(name = "CallbackFacebook", urlPatterns = {"/callbackFacebook"})
public class CallbackFacebook extends HttpServlet {

    // Constants for the Facebook OAuth2.0 configuration
    private static final String CLIENT_ID = "488592800648512";
    private static final String CLIENT_SECRET = "77ca382ab4b2c26a844e92ce63d53cab";
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackFacebook";
    private static final String TOKEN_URL = "https://graph.facebook.com/v12.0/oauth/access_token";
    private static final String USERINFO_URL = "https://graph.facebook.com/me?fields=id,name,email&access_token=";

    /**
     * Processes the Facebook OAuth2.0 callback. Retrieves the authorization
     * code from the request, exchanges it for an access token, and uses the
     * token to fetch the user's profile information. If the user exists in the
     * database, logs them in; otherwise, registers a new user.
     *
     * @param request The HttpServletRequest object containing the request from
     * Facebook.
     * @param response The HttpServletResponse object used to send the response
     * to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when handling
     * the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Retrieve the authorization code from the request
        String code = request.getParameter(("code"));

        // Step 2: Exchange the authorization code for an access token
        String tokenResponse = getAccessToken(code);

        // Step 3: Parse the JSON response to extract the access token
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(tokenResponse).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();

        // Step 4: Use the access token to retrieve user information from Facebook
        String userInfo = getUserInfo(accessToken);
        JsonObject userJson = parser.parse(userInfo).getAsJsonObject();

        // Extract user's email, name, and Facebook ID from the response
        String email = userJson.get("email").getAsString();
        String name = userJson.get("name").getAsString();
        String facebookUserId = userJson.get("id").getAsString();

        // Step 5: Check if the user already exists in the database using their Facebook ID
        DAOLogin daoLogin = new DAOLogin();
        User student = daoLogin.getUserByFacebookId(facebookUserId);

        // If the user doesn't exist, create a new account
        if (student == null) {
            daoLogin.saveUserByFacebook(facebookUserId, name, email);
            student = daoLogin.getUserByFacebookId(facebookUserId);
        }

        // Store the user in the session and redirect them to the home page
        request.getSession().setAttribute("user", student);
        response.sendRedirect("homePage");
    }

    /**
     * Exchanges the authorization code for an access token by sending a GET
     * request to Facebook's OAuth token endpoint.
     *
     * @param code The authorization code obtained from Facebook after the user
     * grants permission.
     * @return A JSON string containing the access token and related
     * information.
     * @throws IOException If an input or output error occurs during the HTTP
     * request.
     */
    private String getAccessToken(String code) throws IOException {
        // Build the URL for the token exchange request
        URL url = new URL(TOKEN_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
                + "&client_secret=" + CLIENT_SECRET + "&code=" + code);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the input stream
        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Return the response as a string
        return response.toString();
    }

    /**
     * Retrieves the user's profile information from Facebook using the access
     * token.
     *
     * @param accessToken The access token obtained after exchanging the
     * authorization code.
     * @return A JSON string containing the user's profile information (ID,
     * name, email).
     * @throws IOException If an input or output error occurs during the HTTP
     * request.
     */
    private String getUserInfo(String accessToken) throws IOException {
        // Build the URL to fetch the user's profile data
        URL url = new URL(USERINFO_URL + accessToken);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        // Return the user's profile data as a string
        return response.toString();
    }

}
