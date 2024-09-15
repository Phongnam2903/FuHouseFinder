<%-- 
    Document   : AdminCreateAccount
    Created on : Sep 14, 2024, 10:01:17 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/adminProfile.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>
        <!-- Navigation -->
        <%@include file="../Partials/Navbar.jsp" %>
        <!-- Profile Section -->
        <div class="profile-container">
            <div class="profile-card">
                <div class="profile-image">
                    <img src="https://via.placeholder.com/150" alt="Avatar">
                </div>
                <div class="profile-info">
                    <h2>Nguyễn Văn A</h2>
                    <p>Email: nguyenvana@gmail.com</p>
                    <p>Số điện thoại: 0909 123 456</p>
                    <p>Địa chỉ: 123 Đường ABC, Quận 1, TP. HCM</p>
                </div>
            </div>

            <div class="profile-details">
                <h3>Chi tiết tài khoản</h3>
                <ul>
                    <li>Ngày tạo tài khoản: 01/01/2023</li>
                    <li>Vai trò: Quản trị viên</li>
                    <li>Tình trạng tài khoản: Hoạt động</li>
                </ul>
            </div>

            <!-- Edit Profile Button -->
            <div class="edit-profile">
                <button class="btn-edit">Chỉnh sửa thông tin</button>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

