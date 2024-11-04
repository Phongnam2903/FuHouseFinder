<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    </head>
    <body>
        <!-- Header -->
        <div class="header">
            <div class="header-logo">
                <img style="border-radius: 10px" src="${pageContext.request.contextPath}/images/logo/logo-fpt.jpg" alt="FU House Finder">
                <h1 style="text-align: center;">Find FPT Student Accommodation</h1>
            </div>
            <div class="dropdown">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Hello, <c:out value="${sessionScope.user.username}" />!
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li>
                                <form action="profile" method="get" id="ProfileForm">
                                    <input type="hidden" name="service" value="profile">
                                    <button type="submit" class="dropdown-item" style="border: none; padding-left: 16px;">Profile</button>
                                </form>
                            </li>
                            <li>
                                <form action="profile" method="get" id="changePasswordForm">
                                    <input type="hidden" name="service" value="changePass">
                                    <button type="submit" class="dropdown-item" style="border: none; padding-left: 16px;">Change Password</button>
                                </form>
                            </li>
                            <li><a class="dropdown-item" href="logout"><i class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-light" href="login">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
