<%-- 
    Document   : Sidebar
    Created on : Oct 1, 2024, 4:18:48 PM
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
        <div class="bg-dark border-right" id="sidebar-wrapper">
            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
                <i class="fas fa-user-shield me-2"></i>Admin
            </div>
            <div class="list-group list-group-flush">
                <a href="adminDashboard" class="list-group-item list-group-item-action bg-dark text-white active">
                    <i class="fas fa-home me-2"></i>Home
                </a>
                <a href="viewAccountList" class="list-group-item list-group-item-action bg-dark text-white">
                    <i class="fas fa-envelope me-2"></i>List Account
                </a>
                <a href="introduce.jsp" class="list-group-item list-group-item-action bg-dark text-white">
                    <i class="fas fa-info-circle me-2"></i>Giới Thiệu
                </a>
                <a href="settings.jsp" class="list-group-item list-group-item-action bg-dark text-white">
                    <i class="fas fa-cog me-2"></i>Cài Đặt
                </a>
                <a href="logout" class="list-group-item list-group-item-action bg-dark text-white">
                    <i class="fas fa-sign-out-alt me-2"></i>Log Out
                </a>
            </div>
        </div>
    </body>
</html>
