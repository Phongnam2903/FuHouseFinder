<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách nhà trọ</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>

        <style>
            .host-info, .summary-info {
                margin: 20px 0;
            }

            .table th, .table td {
                text-align: center;
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
                <div class="container mt-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Danh sách chủ trọ</a></li>
                            <li class="breadcrumb-item"><a href="#">Danh sách nhà trọ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh sách phòng trọ</li>
                        </ol>
                    </nav>

                    <div class="row host-info">
                        <div class="col-md-6">
                            <p><strong>Họ và tên:</strong> ${landlordDetail.username}</p>
                            <p><strong>Số điện thoại:</strong> ${landlordDetail.phone}</p>
                            <p><strong>Địa chỉ:</strong> ${landlordDetail.address}</p>
                        </div>
                        <div class="col-md-6 text-right summary-info">
                            <p><strong>Tổng số nhà trọ:</strong> ${landlordDetail.totalHouses} nhà</p>
                            <p><strong>Tổng số phòng:</strong> ${landlordDetail.totalRooms} phòng</p>
                            <p><strong>Tổng số phòng trống:</strong> ${landlordDetail.emptyRooms} phòng</p>
                        </div>
                    </div>

                    <h3 class="text-center">Danh sách nhà trọ</h3>

                    <table class="table table-bordered">
                        <thead class="thead-light">
                            <tr>
                                <th>STT</th>
                                <th>Tên nhà trọ</th>
                                <th>Giá điện (VND)</th>
                                <th>Giá nước (VND)</th>
                                <th><i class="fas fa-users"></i></th>
                                <th><i class="fas fa-user"></i></th>
                                <th><i class="fas fa-bed"></i></th>
                                <th>Tiện ích</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Nhà trọ Bình Yên</td>
                                <td>3,500</td>
                                <td>1,300</td>
                                <td>0</td>
                                <td>3</td>
                                <td>3</td>
                                <td><i class="fas fa-parking"></i></td>
                            </tr>         
                        </tbody>

                    </table>

                </div>
            </div>
        </div>
        <!-- Bootstrap JS và phụ thuộc -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </body>
</html>
