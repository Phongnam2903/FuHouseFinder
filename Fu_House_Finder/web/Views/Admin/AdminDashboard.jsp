<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
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
            <!-- Page Content -->
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

                <div class="container-fluid mt-4">
                    <h1 class="text-dark">Welcome to the admin page!</h1>

                    <!-- Các thẻ thống kê -->
                    <div class="row mt-4">
                        <div class="col-md-4">
                            <div class="card text-white bg-warning mb-3">
                                <div class="card-header"><i class="fas fa-users me-2"></i> Người Dùng</div>
                                <div class="card-body">
                                    <h5 class="card-title">150</h5>
                                    <p class="card-text">Tổng số người dùng.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card text-white bg-warning mb-3">
                                <div class="card-header"><i class="fas fa-box me-2"></i> Sản Phẩm</div>
                                <div class="card-body">
                                    <h5 class="card-title">75</h5>
                                    <p class="card-text">Tổng số sản phẩm.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card text-white bg-warning mb-3">
                                <div class="card-header"><i class="fas fa-chart-line me-2"></i> Doanh Thu</div>
                                <div class="card-body">
                                    <h5 class="card-title">$12,345</h5>
                                    <p class="card-text">Tổng doanh thu tháng này.</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Thêm các thành phần quản trị khác như biểu đồ, bảng, v.v. -->

                    <!-- Ví dụ: Biểu đồ Doanh Thu -->
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header bg-warning text-dark">
                                    <i class="fas fa-chart-pie me-2"></i>Thống Kê Doanh Thu
                                </div>
                                <div class="card-body">
                                    <canvas id="revenueChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- Bootstrap JS và phụ thuộc -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Chart.js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
