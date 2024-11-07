<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/userProfile.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>
        <div class="container">
            <div class="col-md-6">
                <h2 class="text-center mb-4">Change Password</h2>

                <c:if test="${not empty message}">
                    <div class="alert ${alertClass}">
                        ${message}
                    </div>
                </c:if>

                <form action="profile" method="post">
                    <c:choose>
                        <c:when test="${user.roleID == 3 && user.password != null}">
                            <input type="hidden" name="service" value="changePass">
                            <!-- Show error/success messages if any -->
                            <div class="mb-3">
                                <label for="oldPassword" class="form-label">Old Password</label>
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                            </div>

                            <div class="mb-3">
                                <label for="newPassword" class="form-label">New Password</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                            </div>

                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Confirm New Password</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="service" value="updatePassword">
                            <div class="mb-3">
                                <label for="oldPassword" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>

                            <div class="mb-3">
                                <label for="newPassword" class="form-label">Confirm Password</label>
                                <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" required>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="d-flex justify-content-between">
                        <c:choose>
                            <c:when test="${user.roleID == 3}">
                                <a href="homePage" class="btn btn-secondary">Back to Home</a>
                            </c:when>
                            <c:otherwise>
                                <a href="ListHouse" class="btn btn-secondary">Back to Home</a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${user.roleID == 3 && user.password != null}">
                                <button type="submit" class="btn btn-success">Change Password</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-success">Update Password</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
