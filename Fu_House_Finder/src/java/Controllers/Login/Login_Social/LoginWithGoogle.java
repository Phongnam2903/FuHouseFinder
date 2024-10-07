/*
 * Copyright(C) 2024, Group2-SE1866-KS.
 * LOGINWITHGOOGLE.JAVA:
 * FU House Finder
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2024-09-26      1.0                 PhongNN          Implement Google OAuth2.0 login initiation
 */
package Controllers.Login.Login_Social;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This servlet initiates the Google OAuth2.0 login process. It constructs the
 * authorization URL with necessary parameters and redirects the user to
 * Google's OAuth2.0 consent screen.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Nam Phong
 */
@WebServlet(name = "LoginWithGoogle", urlPatterns = {"/loginGoogle"})
public class LoginWithGoogle extends HttpServlet {

    // OAuth 2.0 Client ID provided by Google API Console
    private static final String CLIENT_ID = "676915622854-csj092neit40ppk7bidp1b1afp2maoqo.apps.googleusercontent.com";
    // Redirect URI configured in Google API Console to handle the callback
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackGoogle";
    // Scopes specifying the level of access requested (email and profile information)
    private static final String SCOPE = "email profile";
    // Google's OAuth2.0 authorization endpoint
    private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    /**
     * Handles the HTTP <code>GET</code> method to initiate Google OAuth2.0
     * login. Constructs the authorization URL with required parameters and
     * redirects the user to Google's consent screen.
     *
     * @param request The HttpServletRequest object containing the client's
     * request.
     * @param response The HttpServletResponse object containing the servlet's
     * response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an input or output error is detected when handling
     * the GET request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Construct the OAuth2.0 authorization URL with necessary parameters
        String oauthUrl = AUTH_URL + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + SCOPE
                + "&access_type=offline";

        // Redirect the user to Google's OAuth2.0 consent screen
        response.sendRedirect(oauthUrl);
    }

}
