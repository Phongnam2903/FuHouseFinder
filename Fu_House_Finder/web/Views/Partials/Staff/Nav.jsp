<%-- 
    Document   : Nav
    Created on : Oct 9, 2024, 9:05:05 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-light border-bottom">
            <div class="container-fluid">
                <button class="btn btn-outline-warning" id="menu-toggle"><i class="fas fa-bars"></i></button>
                <h2 class="ms-3 text-dark">Dashboard</h2>

                <button class="btn btn-outline-success ms-auto" id="dark-mode-toggle">
                    <a href="admin_profile" style="text-decoration: none; color: #87bbf2">
                        Hello, <c:out value="${sessionScope.user.username}" />!
                    </a>  
                </button>
            </div>
        </nav>
    </body>
</html>
