<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Staff/Sidebar.jsp" %>
            <div id="page-content-wrapper">
                <!-- Navigation Bar in Page Content -->
                <%@include file="../Partials/Staff/Nav.jsp" %>
                <!-- Main Content -->
                <div class="container">
                    <h1 class="text-center mt-4">Thống Kê Tổng Quan</h1>

                    <!-- Số liệu thống kê -->
                    <div class="row text-center mt-4">
                        <div class="col-md-3">
                            <div class="card bg-primary text-white">
                                <div class="card-body">
                                    <h5>Tổng nhà trọ</h5>
                                    <h2>${totalHouse}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card bg-info text-white">
                                <div class="card-body">
                                    <h5>Tổng phòng trọ</h5>
                                    <h2>${totalRoom}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card bg-success text-white">
                                <div class="card-body">
                                    <h5>Sức chứa</h5>
                                    <h2>${totalCapacity}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card bg-warning text-white">
                                <div class="card-body">
                                    <h5>Tổng số chủ trọ</h5>
                                    <h2>${totalLandlord}</h2>
                                </div>
                            </div>
                        </div>
                    </div>               

                    <!-- Biểu đồ hình tròn -->
                    <div class="row mt-4">
                        <div class="col-md-3">
                            <canvas id="chartHouse"></canvas>
                            <p>Thống kê số nhà trọ</p>
                        </div>
                        <div class="col-md-3">
                            <canvas id="chartRoom"></canvas>
                            <p>Thống kê số phòng trọ</p>
                        </div>
                        <div class="col-md-3">
                            <canvas id="chartCapacity"></canvas>
                            <p>Thống kê sức chứa</p>
                        </div>
                        <div class="col-md-3">
                            <canvas id="chartHost"></canvas>
                            <p>Thống kê số chủ trọ</p>
                        </div>
                    </div>

                    <!-- Biểu đồ cột và đường -->
                    <div class="row mt-4">
                        <div class="col-md-6">
                            <canvas id="monthlyChart"></canvas>
                            <p>Thống kê số lượng nguyện vọng đăng ký nhà trọ trong năm 2022</p>
                        </div>
                        <div class="col-md-6">
                            <canvas id="lineChart"></canvas>
                            <p>Thống kê tổng số lượng nguyện vọng đăng ký nhà trọ trong năm 2022</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            // Biểu đồ hình tròn cho số nhà trọ
            var ctx1 = document.getElementById('chartHouse').getContext('2d');
            var chartHouse = new Chart(ctx1, {
                type: 'pie',
                data: {
                    labels: ['Hết chỗ', 'Còn chỗ'],
                    datasets: [{
                            data: [10, 21],
                            backgroundColor: ['#ff6384', '#36a2eb']
                        }]
                }
            });

            // Biểu đồ hình tròn cho số phòng trọ
            var ctx2 = document.getElementById('chartRoom').getContext('2d');
            var chartRoom = new Chart(ctx2, {
                type: 'pie',
                data: {
                    labels: ['Hết chỗ', 'Còn chỗ'],
                    datasets: [{
                            data: [25, 48],
                            backgroundColor: ['#ff6384', '#36a2eb']
                        }]
                }
            });

            // Biểu đồ hình tròn cho sức chứa
            var ctx3 = document.getElementById('chartCapacity').getContext('2d');
            var chartCapacity = new Chart(ctx3, {
                type: 'pie',
                data: {
                    labels: ['Đã đầy', 'Còn trống'],
                    datasets: [{
                            data: [30, 51],
                            backgroundColor: ['#ff6384', '#36a2eb']
                        }]
                }
            });

            // Biểu đồ hình tròn cho số chủ trọ
            var ctx4 = document.getElementById('chartHost').getContext('2d');
            var chartHost = new Chart(ctx4, {
                type: 'pie',
                data: {
                    labels: ['Có sẵn', 'Đang bận'],
                    datasets: [{
                            data: [10, 10],
                            backgroundColor: ['#ff6384', '#36a2eb']
                        }]
                }
            });

            // Biểu đồ dạng cột
            var ctx5 = document.getElementById('monthlyChart').getContext('2d');
            var monthlyChart = new Chart(ctx5, {
                type: 'bar',
                data: {
                    labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                    datasets: [{
                            label: 'Số nguyện vọng đăng ký',
                            data: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60],
                            backgroundColor: '#36a2eb'
                        }, {
                            label: 'Số nguyện vọng đã giải quyết',
                            data: [3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 53, 58],
                            backgroundColor: '#ff6384'
                        }]
                }
            });

            // Biểu đồ đường
            var ctx6 = document.getElementById('lineChart').getContext('2d');
            var lineChart = new Chart(ctx6, {
                type: 'line',
                data: {
                    labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                    datasets: [{
                            label: 'Tổng số nguyện vọng đăng ký',
                            data: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60],
                            borderColor: '#36a2eb',
                            fill: false
                        }, {
                            label: 'Tổng số nguyện vọng đã giải quyết',
                            data: [3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 53, 58],
                            borderColor: '#ff6384',
                            fill: false
                        }]
                }
            });
        </script>
        <!-- Bootstrap JS và phụ thuộc -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
