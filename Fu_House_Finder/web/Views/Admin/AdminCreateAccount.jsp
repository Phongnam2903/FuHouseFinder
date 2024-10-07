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
        <link href="${pageContext.request.contextPath}/css/admin/adminCreateAcc.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Admin/Sidebar.jsp" %>
            <!-- Right Content -->
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
                <nav aria-label="breadcrumb" class="mt-3">
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
                        <!-- Hiển thị Thông báo Thành công -->
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success" role="alert">
                                ${successMessage}
                            </div>
                        </c:if>
                        <!-- Hiển thị Thông báo Lỗi -->

                        <c:if test="${not empty errorPassword}">
                            <div class="alert alert-danger" role="alert">
                                ${errorPassword}
                            </div>
                        </c:if>

                        <!-- Full Name -->
                        <div class="mb-3">
                            <label for="username" class="form-label">Full Name</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter Full Name" 
                                   value="${param.username != null ? param.username : ''}">
                        </div>
                        <c:if test="${not empty errorUsername}">
                            <div class="alert alert-danger" role="alert">
                                ${errorUsername}
                            </div>
                        </c:if>
                        <!-- Email -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" 
                                   value="${param.email != null ? param.email : ''}">
                        </div>
                        <c:if test="${not empty errorEmail}">
                            <div class="alert alert-danger" role="alert">
                                ${errorEmail}
                            </div>
                        </c:if>
                        <!-- Phone Number -->
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter Phone Number" required pattern="[0-9]{10}"
                                   value="${param.phone != null ? param.phone : ''}">
                            <div class="form-text">Please enter a 10-digit phone number.</div>
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter Password" required minlength="8" title="Password must be at least 8 characters long and include letters and numbers.">
                        </div>

                        <!-- Confirm Password -->
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Re-enter Password</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                   placeholder="Re-enter Password" >
                        </div>

                        <!-- Address -->
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <textarea class="form-control" id="address" name="address" rows="3" placeholder="Enter Address" required>${param.address != null ? param.address : ''}</textarea>
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
