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
                <nav class="navbar navbar-expand-lg navbar-dark bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-outline-success" id="menu-toggle"><i class="fas fa-bars"></i></button>
                        <h2 class="ms-3 text-dark">Dashboard</h2>

                        <button class="btn btn-outline-success ms-auto" id="dark-mode-toggle">
                            <a href="admin_profile" style="text-decoration: none; color: #87bbf2">
                                Hello, <c:out value="${sessionScope.user.username}" />!
                            </a>  
                        </button>
                    </div>
                </nav>
                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb" class="mt-3">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="viewAccountList">List Account</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Update Account</li>
                    </ol>
                </nav>
                <!-- Thông Báo Thành Công / Lỗi -->
                <div class="container">
                    <!-- Hiển thị Thông báo Thành công -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            ${successMessage}
                        </div>
                    </c:if>
                    <!-- Hiển thị Thông báo Lỗi -->

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                </div>
                <!-- Main Content -->
                <div class="container mt-5 mb-5">
                    <h1 class="text-center mb-4">Update Account</h1>
                    <form action="updateAccount" method="post" class="row g-3">
                        <!-- Truyền lại ID -->
                        <input type="hidden" name="id" value="${param.id != null ? param.id : user.id}" />

                        <div class="col-md-6">
                            <label for="username">Full Name:<span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="username" name="username" value="${user != null ? user.username : param.username}" >
                        </div>

                        <div class="col-md-6">
                            <label for="email">Email:<span class="text-danger">*</span></label>
                            <input type="email" class="form-control" id="email" name="email" value="${user != null ? user.email : param.email}" >
                        </div>

                        <div class="col-md-6">
                            <label for="phone">Phone:<span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${user != null ? user.phone : param.phone}" >
                        </div>

                        <div class="col-md-6">
                            <label for="address">Address:<span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="address" name="address" value="${user != null ? user.address : param.address}">
                        </div>

                        <div class="col-md-6">
                            <label for="status">Status:<span class="text-danger">*</span></label>
                            <select class="form-control" id="status" name="status">
                                <option value="1" ${user.statusID == 1 ? 'selected' : ''}>Active</option>
                                <option value="2" ${user.statusID == 2 ? 'selected' : ''}>Inactive</option>
                            </select>
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
