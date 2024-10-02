<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Account</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/admin/style.css" rel="stylesheet" type="text/css"/> 
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Admin/Sidebar.jsp" %>
            <!-- Right Content -->
            <div id="page-content-wrapper">
                <!-- Navigation Bar trên Page Content -->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-outline-success" id="menu-toggle"><i class="fas fa-bars"></i></button>
                        <h2 class="ms-3 text-dark">Dashboard</h2>

                        <button class="btn btn-outline-success ms-auto" id="dark-mode-toggle">
                            <i class="fas fa-moon"></i> Nguyễn Nam Phong
                        </button>

                    </div>
                </nav>
                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb" class="mt-3">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="adminDashboard">Home</a></li>
                        <li class="breadcrumb-item"><a href="viewAccountList">List Account</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Update Account</li>
                    </ol>
                </nav>
                <!-- Thông Báo Thành Công / Lỗi -->
                <div class="container">
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            ${successMessage}
                        </div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${errorMessage}
                        </div>
                    </c:if>
                </div>
                <!-- Main Content -->
                <div class="container mt-5 mb-5">
                    <h1 class="text-center mb-4">Update Account</h1>
                    <form action="updateAccount" method="post" class="row g-3">
                        <input type="hidden" name="id" value="${user.id}">

                        <!-- Username -->
                        <div class="col-md-6">
                            <label for="username" class="form-label">Full Name:</label>
                            <input type="text" name="username" id="username" class="form-control" value="${user.username}" required>
                        </div>

                        <!-- Email -->
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" name="email" id="email" class="form-control" value="${user.email}" required>
                        </div>

                        <!-- Phone Number -->
                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone Number:</label>
                            <input type="text" name="phone" id="phone" class="form-control" value="${user.phone}" required>
                        </div>

                        <!-- Status -->
                        <div class="col-md-6">
                            <label for="status" class="form-label">Status:</label>
                            <select name="status" id="status" class="form-select" required>
                                <option value="1" <c:if test="${user.statusID == 1}">selected</c:if>>Active</option>
                                <option value="2" <c:if test="${user.statusID == 2}">selected</c:if>>Inactive</option>
                                <option value="3" <c:if test="${user.statusID == 3}">selected</c:if>>Ban</option>
                                <option value="4" <c:if test="${user.statusID == 4}">selected</c:if>>Unban</option>
                            </select>
                        </div>

                        <!-- Address -->
                        <div class="col-md-6">
                            <label for="address" class="form-label">Address:</label>
                            <input type="text" name="address" id="address" class="form-control" value="${user.address}" required>
                        </div>

                        <!-- Date of Birth -->
                        <div class="col-md-6">
                            <label for="dateOfBirth" class="form-label">Date of Birth:</label>
                            <input type="date" name="dateOfBirth" id="dateOfBirth" class="form-control" value="${user.dateOfBirth}" required>
                        </div>

                        <!-- Submit Button -->
                        <div class="col-12 text-center">
                            <button type="submit" class="btn btn-primary px-4 py-2">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
