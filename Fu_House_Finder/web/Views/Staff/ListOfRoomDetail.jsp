<%-- 
    Document   : ListOfRoomDetail
    Created on : Oct 10, 2024, 9:22:18 AM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết phòng trọ</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .breadcrumb {
                margin: 20px 0;
            }

            .room-carousel img {
                width: 100%;
                height: 450px;
                object-fit: cover;
            }

            .room-description {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 20px;
                margin-top: 20px;
                background-color: #f9f9f9;
            }

            .room-description h4 {
                text-align: center;
                margin-bottom: 20px;
            }

            .room-description .row div {
                margin-bottom: 10px;
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
                    <!-- Breadcrumb Navigation -->
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Danh sách chủ trọ</a></li>
                            <li class="breadcrumb-item"><a href="#">Danh sách nhà trọ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh sách phòng trọ</li>
                        </ol>
                    </nav>

                    <!-- Image Carousel -->
                    <div id="roomCarousel" class="carousel slide room-carousel" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="../../images/nha-tro3_9c3bd593-cf87-41e2-99d7-799c1fa4743d.jpg" alt="Room Image 1">
                            </div>
                            <div class="carousel-item">
                                <img src="../../images/nha-tro4_689fe492-c9ec-469f-be48-875c3cb5c446.jpg" alt="Room Image 2">
                            </div>
                            <div class="carousel-item">
                                <img src="../../images/nha-tro_4f58c2dd-71ab-43a1-b64f-3f0f7f79000b.jpg" alt="Room Image 3">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#roomCarousel" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#roomCarousel" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <!-- Room Description Box -->
                    <div class="room-description">
                        <h4>Thông tin mô tả phòng</h4>
                        <div class="row">
                            <div class="col-md-6">
                                <p><i class="fas fa-snowflake"></i> Điều hòa: Có</p>
                                <p><i class="fas fa-wifi"></i> Wi-Fi: Có</p>
                                <p><i class="fas fa-bath"></i> Phòng tắm riêng: Có</p>
                            </div>
                            <div class="col-md-6">
                                <p><i class="fas fa-tv"></i> TV: Không</p>
                                <p><i class="fas fa-utensils"></i> Bếp: Có</p>
                                <p><i class="fas fa-bed"></i> Giường: Có</p>
                            </div>
                        </div>
                        <p class="text-center">Khu vực an ninh tốt, gần trung tâm</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- JS Scripts -->
        <!-- Bootstrap JS và phụ thuộc -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </body>
</html>
