/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * LOGINWITHFACEBOOK.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-10-18      1.0                PhongNN          Implement Facebook OAuth2.0 login request
 */
package Controllers.Login.Login_Social;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles the login process by redirecting the user to Facebook's OAuth2.0
 * authorization endpoint to initiate the OAuth2.0 login flow.
 * <p>
 * When the user accesses the "/loginFacebook" URL, they are redirected to
 * Facebook's OAuth dialog where they can authorize the application and allow
 * access to their basic profile information and email.
 * </p>
 * <p>
 * Bugs: None
 * </p>
 *
 * @author PhongNN
 */
@WebServlet(name = "LoginWithFacebook", urlPatterns = {"/loginFacebook"})
public class LoginWithFacebook extends HttpServlet {

    // Constants for the Facebook OAuth2.0 configuration
    private static final String CLIENT_ID = "488592800648512"; // The application's Facebook Client ID
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackFacebook"; // The redirect URI registered with Facebook
    private static final String SCOPE = "email"; // The scope of permissions requested from Facebook
    private static final String AUTH_URL = "https://www.facebook.com/v12.0/dialog/oauth"; // Facebook's OAuth2.0 authorization URL

    /**
     * Handles the GET request by redirecting the user to Facebook's OAuth2.0
     * authorization URL. The URL includes the client ID, redirect URI, response
     * type, and requested scope.
     *
     * @param request The HttpServletRequest object that contains the request
     * from the client.
     * @param response The HttpServletResponse object used to send the response
     * to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an input or output error is detected when handling
     * the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Build the Facebook OAuth2.0 authorization URL with required parameters
        String oauthUrl = AUTH_URL + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code" // Request an authorization code
                + "&scope=" + SCOPE; // Request access to the user's email

        // Redirect the user to Facebook's OAuth2.0 login page
        response.sendRedirect(oauthUrl);
    }
}
