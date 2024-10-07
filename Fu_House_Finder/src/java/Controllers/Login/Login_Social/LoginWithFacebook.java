package Controllers.Login.Login_Social;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginWithFacebook", urlPatterns = {"/loginFacebook"})
public class LoginWithFacebook extends HttpServlet {

    private static final String CLIENT_ID = "488592800648512";
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackFacebook";
    private static final String SCOPE = "email";
    private static final String AUTH_URL = "https://www.facebook.com/v12.0/dialog/oauth";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oauthUrl = AUTH_URL + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + SCOPE;

        response.sendRedirect(oauthUrl);
    }
}
