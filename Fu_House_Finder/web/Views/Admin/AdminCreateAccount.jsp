<%-- 
    Document   : AdminCreateAccount
    Created on : Sep 14, 2024, 10:01:17 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/adminCreateAcc.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Header -->
        <%@include file="../Partials/HeaderAdmin.jsp" %>
        <!-- Navigation -->
        <%@include file="../Partials/Navbar.jsp" %>
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item"><a href="viewAccountList">List Account</a></li>
                <li class="breadcrumb-item active" aria-current="page">Create New Account</li>
            </ol>
        </nav>
        <!-- Main Content -->
        <div class="create-account">
            <h2>Create Account For Staff</h2>
            <form action="${pageContext.request.contextPath}/createAccount" method="post">
                <% 
                String message = (String) request.getAttribute("message");
                if (message != null) {
                %>
                <div class="alert" style="color: red">
                    <%= message %>
                </div>
                <script>
                    alert('<%= message %>');
                </script>
                <% 
                    }
                %>  
                <!-- Full Name -->
                <div class="mb-3">
                    <label for="username" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter FullName" required>
                </div>

                <!-- Email -->
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
                </div>

                <!-- Password -->
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Enter Password" required>
                </div>

                <!-- Confirm Password -->
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Re-enter Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                           placeholder="Re-enter Password" required>
                </div>

                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary w-100">Create Account</button>
            </form>
        </div>

        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
