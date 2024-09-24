<%-- 
    Document   : AdminCreateAccount
    Created on : Sep 14, 2024, 10:01:17 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/adminProfile.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!-- Header -->
        <%@include file="../Partials/HeaderAdmin.jsp" %>
        <!-- Navigation -->
        <%@include file="../Partials/Navbar.jsp" %>
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="viewAccountList">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Profile</li>
            </ol>
        </nav>

        <!-- Profile Section -->
        <div class="profile-container">
            <div class="profile-card">
                <div class="profile-image">
                    <img src="${pageContext.request.contextPath}/images/avatar/avatar2.png" alt="Avatar">
                </div>
                <div class="profile-info">
                    <h2>Name: ${admin.getUsername()}</h2>
                    <p>Email: ${admin.email}</p>
                    <p>Phone Number: ${admin.phone}</p>
                    <p>Address: ${admin.address}</p>
                </div>
            </div>

            <div class="profile-details">
                <h3>Detail Account</h3>
                <ul>
                    <li>Date Of Birth: ${admin.dateOfBirth}</li>
                    <li>Role: ${admin.roleID == 1 ? 'admin' : 'Unknow'}</li>
                    <li>Account Status: ${admin.getStatusID() == 1 ? 'Active' : 'UnActive'}</li>
                </ul>
            </div>

            <!-- Edit Profile Button -->
            <div class="edit-profile">
                <button class="btn-edit btn btn-primary" data-bs-toggle="modal" data-bs-target="#editProfileModal">Update Profile</button>
            </div>
        </div>

        <!-- Bootstrap Modal for Editing Profile -->
        <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editProfileModalLabel">Edit Profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="admin_profile" method="POST" enctype="multipart/form-data">
                            <!-- Username -->
                            <div class="mb-3">
                                <label for="username" class="form-label">Full Name</label>
                                <input type="text" class="form-control" id="username" name="username" value="${admin.username}">
                            </div>
                            <!-- Email -->
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${admin.email}">
                            </div>
                            <!-- Phone -->
                            <div class="mb-3">
                                <label for="phone" class="form-label">Phone Number</label>
                                <input type="text" class="form-control" id="phone" name="phone" value="${admin.phone}">
                            </div>
                            <!-- Address -->
                            <div class="mb-3">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" name="address" value="${admin.address}">
                            </div>
                            <!-- Date of Birth -->
                            <div class="mb-3">
                                <label for="dateOfBirth" class="form-label">Date of Birth</label>
                                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${admin.dateOfBirth}">
                            </div>
                            <!-- Upload Avatar -->
                            <div class="mb-3">
                                <label for="avatar" class="form-label">Upload Avatar</label>
                                <input type="file" class="form-control" id="avatar" name="avatar">
                            </div>
                            <!-- Submit Button -->
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
