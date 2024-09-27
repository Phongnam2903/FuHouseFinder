/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Login.Login_Social;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author xuxum
 */
@WebServlet(name = "LoginWithGoogle", urlPatterns = {"/loginGoogle"})
public class LoginWithGoogle extends HttpServlet {

    private static final String CLIENT_ID = "676915622854-csj092neit40ppk7bidp1b1afp2maoqo.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:8080/Fu_House_Finder/callbackGoogle";
    private static final String SCOPE = "email profile";
    private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oauthUrl = AUTH_URL + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + SCOPE
                + "&access_type=offline";

        response.sendRedirect(oauthUrl);
    }
}
