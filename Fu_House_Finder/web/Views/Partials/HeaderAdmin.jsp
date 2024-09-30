<%-- 
    Document   : Admin Header
    Created on : Sep 14, 2024, 8:08:15 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Panel - FU House Finder</title>

        <!-- Bootstrap CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/HeaderAdmin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Admin Header -->
        <div class="header">
            <div class="header-logo">
                <img src="${pageContext.request.contextPath}/images/logo/logo_house_finder.jpg" alt="FU House Finder">
                <h1>Admin Panel</h1>
            </div>
            <div class="dropdown">
                <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <c:out value="${sessionScope.account.username}" />
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item" href="admin_profile">Profile</a></li>
                    <li><a class="dropdown-item" href="logout">Logout</a></li>
                </ul>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- Bootstrap JS -->
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
