<%-- 
    Document   : ListHouseOwner
    Created on : Oct 9, 2024, 9:09:24 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ListHouseOwner</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <style>
            /* Statistics Section */
            .stat-title {
                font-size: 1.1rem;
                font-weight: 500;
                color: #555;
            }

            .stat-number {
                font-size: 1.8rem;
                font-weight: bold;
                color: #d9534f;
            }

            /* Table Styling */
            .table th, .table td {
                vertical-align: middle;
            }

            .table td button {
                min-width: 100px;
            }

            /* Optional: Additional styling for responsiveness */
            @media (max-width: 768px) {
                .stat-number {
                    font-size: 1.5rem;
                }
            }

        </style>
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
                    <section class="container my-5">
                        <!-- Statistics Section -->
                        <div class="row text-center mb-4">
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Tổng nhà</h4>
                                    <h3 class="stat-number">31</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Nhà trống</h4>
                                    <h3 class="stat-number">29</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Tổng phòng</h4>
                                    <h3 class="stat-number">73</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Phòng trống</h4>
                                    <h3 class="stat-number">63</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Tổng chỗ</h4>
                                    <h3 class="stat-number">155</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Chỗ trống</h4>
                                    <h3 class="stat-number">81</h3>
                                </div>
                            </div>
                        </div>
                        <!-- Table Header -->
                        <div class="row mb-3">
                            <div class="col">
                                <p class="small text-muted">Bạn đang thấy 20 chủ trọ có 31 nhà trọ ở 5 thôn, 4 xí, 4 huyện</p>
                                <h2 class="text-center">Danh sách chủ trọ</h2>
                            </div>
                        </div>

                        <!-- Landlord List Table -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered text-center">
                                <thead class="table-light">
                                    <tr>
                                        <th>STT</th>
                                        <th>Họ Tên</th>
                                        <th>Số Điện Thoại</th>
                                        <th>Tổng nhà</th>
                                        <th>Tổng phòng</th>
                                        <th>Phòng trống</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Example rows -->
                                    <tr>
                                        <td>1</td>
                                        <td>Bình Yên</td>
                                        <td>0978366690</td>
                                        <td>4</td>
                                        <td>6</td>
                                        <td>6</td>
                                        <td>
                                            <button class="btn btn-primary">Hoạt động</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Dũng Nhung</td>
                                        <td>0982299681</td>
                                        <td>4</td>
                                        <td>10</td>
                                        <td>9</td>
                                        <td>
                                            <button class="btn btn-primary">Hoạt động</button>
                                        </td>
                                    </tr>
                                    <!-- More rows here -->
                                </tbody>
                            </table>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>
