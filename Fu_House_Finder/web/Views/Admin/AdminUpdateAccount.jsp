<%-- 
    Document   : AdminCreateAccount
    Created on : Sep 14, 2024, 10:01:17 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Update Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/adminCreateAcc.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>
        <!-- Navigation -->
        <%@include file="../Partials/Navbar.jsp" %>

        <!-- Main Content -->
        <div class="container mt-5 mb-5">
            <h1 class="text-center mb-4">Update Account</h1>
            <form action="updateAccount" method="post" class="row g-3">
                <input type="hidden" name="id" value="${student.id}">

                <!-- Username -->
                <div class="col-md-6">
                    <label for="username" class="form-label">Full Name:</label>
                    <input type="text" name="username" id="username" class="form-control" value="${student.username}" required>
                </div>

                <!-- Email -->
                <div class="col-md-6">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" name="email" id="email" class="form-control" value="${student.email}" required>
                </div>

                <!-- Address -->
                <div class="col-md-6">
                    <label for="address" class="form-label">Address:</label>
                    <input type="text" name="address" id="address" class="form-control" value="${student.address}" required>
                </div>

                <!-- Status -->
                <div class="col-md-6">
                    <label for="status" class="form-label">Status:</label>
                    <select name="status" id="status" class="form-select" required>
                        <option value="1" ${student.getStatusID() == 1 ? 'selected' : ''}>Active</option>
                        <option value="2" ${student.getStatusID() == 2 ? 'selected' : ''}>InActive</option>
                        <option value="3" ${student.getStatusID() == 3 ? 'selected' : ''}>Ban</option>
                        <option value="4" ${student.getStatusID() == 4 ? 'selected' : ''}>UnBan</option>
                    </select>
                </div>

                <!-- Submit Button -->
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-primary px-4 py-2">Update</button>
                </div>
            </form>
        </div>

        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
