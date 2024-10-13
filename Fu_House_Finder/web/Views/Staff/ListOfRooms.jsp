<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách phòng trọ</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <style>
            /* CSS styling (sử dụng lại từ ví dụ trước) */
            .breadcrumb {
                background-color: transparent;
                font-size: 14px;
            }
            .breadcrumb-item a {
                color: #007bff;
                text-decoration: none;
            }
            .breadcrumb-item.active {
                color: #6c757d;
            }
            .container .row .col-md-3 img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
            }
            .container h5, .container p {
                margin: 5px 0;
            }
            .table {
                margin-top: 20px;
            }
            .table thead {
                background-color: #343a40;
                color: white;
            }
            .table td, .table th {
                vertical-align: middle;
                text-align: center;
            }
            .table i {
                margin-right: 5px;
                font-size: 1.2rem;
                color: #6c757d;
            }
            .table td:last-child {
                font-weight: bold;
                color: #28a745;
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
                    <!-- Breadcrumb -->
                    <c:url var="rentalHousesURL" value="/listHouseOwnerDetail">
                        <c:param name="id" value="${acc.id}"/>
                    </c:url>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="listhouseowner">List of Landlords</a></li>
                            <li class="breadcrumb-item"><a href="${rentalHousesUrl}">List of Rental Houses</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List of Rooms</li>
                        </ol>
                    </nav>

                    <!-- Info Section -->
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <img src="your_image.jpg" class="img-fluid rounded" alt="Nhà trọ Bình Yên">
                        </div>
                        <div class="col-md-9">
                            <h5>Tên nhà trọ: <strong>Nhà trọ Bình Yên</strong></h5>
                            <p><strong>Giá điện:</strong> 3500VND/kWh</p>
                            <p><strong>Giá nước:</strong> 1300VND/m³</p>
                            <p><strong>Địa chỉ:</strong> 7M34G-CR3, Kiên Sơn, Bình Xuyên, Vĩnh Phúc, Việt Nam</p>
                            <p><strong>Thông tin khác:</strong> Không chung chủ, giờ giấc thoải mái</p>
                        </div>
                        <div class="col-md-3">
                            <p><strong>Tổng số phòng trống:</strong> 9 phòng</p>
                            <p><strong>Tổng số chỗ trống:</strong> 0 chỗ</p>
                        </div>
                    </div>

                    <!-- Table Section -->
                    <h4 class="mb-3">Danh sách phòng trọ</h4>
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Tên phòng</th>
                                <th scope="col">Giá phòng (VND)</th>
                                <th scope="col">Tiện ích</th>
                                <th scope="col">Diện tích</th>
                                <th scope="col">Số người ở</th>
                                <th scope="col">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>101</td>
                                <td>3,100,000</td>
                                <td><i class="fas fa-bed"></i> <i class="fas fa-bath"></i> <i class="fas fa-users"></i></td>
                                <td>4 m²</td>
                                <td>2</td>
                                <td>Còn Trống</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>102</td>
                                <td>2,400,000</td>
                                <td><i class="fas fa-bed"></i> <i class="fas fa-bath"></i> <i class="fas fa-users"></i></td>
                                <td>4 m²</td>
                                <td>2</td>
                                <td>Còn Trống</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>103</td>
                                <td>2,200,000</td>
                                <td><i class="fas fa-bed"></i> <i class="fas fa-bath"></i> <i class="fas fa-users"></i></td>
                                <td>4 m²</td>
                                <td>2</td>
                                <td>Còn Trống</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <!-- Bootstrap JS và phụ thuộc -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
