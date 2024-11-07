<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/userProfile.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container">
            <div class="col-md-6">
                <h2 class="text-center mb-4">User Profile</h2>

                <c:if test="${not empty message}">
                    <div class="alert ${alertClass}">
                        ${message}
                    </div>
                </c:if>

                <form action="profile" method="post">
                    <input type="hidden" name="service" value="updateProfile">
                    <div class="mb-3">
                        <label for="username" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="username" name="username" value="${user.username}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" value="${user.address}">
                    </div>
                    <!-- Thêm các trường khác nếu cần -->
                    <div class="d-flex justify-content-between">
                        <c:choose>
                            <c:when test="${user.roleID == 3}">
                                <a href="homePage" class="btn btn-secondary">Back to Home</a>
                            </c:when>
                            <c:otherwise>
                                <a href="ListHouse" class="btn btn-secondary">Back to Home</a>
                            </c:otherwise>
                        </c:choose>
                        <button type="submit" class="btn btn-success">Update Profile</button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
