<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Account</title>
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
            <div id="page-content-wrapper">
                <!-- Navigation Bar trên Page Content -->
                <nav class="navbar navbar-expand-lg navbar-dark bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-outline-success" id="menu-toggle"><i class="fas fa-bars"></i></button>
                        <h2 class="ms-3 text-dark">Dashboard</h2>

                        <button class="btn btn-outline-success ms-auto" id="dark-mode-toggle">
                            <i class="fas fa-moon"></i> Nguyễn Nam Phong
                        </button>

                    </div>
                </nav>
                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="adminDashboard">Home</a></li>
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

                        <!-- Phone Number -->
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="phone" class="form-control" id="phone" name="phone" placeholder="Enter Phone Number" required>
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
            </div>
        </div>
        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Chart.js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
