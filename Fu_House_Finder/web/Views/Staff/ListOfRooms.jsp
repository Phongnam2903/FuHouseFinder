<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách phòng trọ</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/staff/listofroom.css" rel="stylesheet" type="text/css"/>
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
                    <c:set var="houseOwnerId" value="${landlordDetail.id}"/>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="listhouseowner">List of Landlords</a></li>
                            <li class="breadcrumb-item">
                                <a href="${pageContext.request.contextPath}/listHouseOwnerDetail?id=${houseOwnerId}">List of Rental Houses</a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">List of Rooms</li>
                        </ol>
                    </nav>
                    <!-- Info Section -->
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <!-- Hiển thị hình ảnh -->
                            <img class="mb-3" src="${pageContext.request.contextPath}/images/${imageList[0]}" alt="${roomDetail.houseName}" width="650">
                        </div>
                        <div class="col-md-9">
                            <!-- Hiển thị thông tin nhà trọ động -->
                            <h5>Tên nhà trọ: <strong>${roomDetail.houseName}</strong></h5>
                            <p><strong>Giá điện:</strong> ${roomDetail.powerPrice} VND/kWh</p>
                            <p><strong>Giá nước:</strong> ${roomDetail.waterPrice} VND/m³</p>
                            <p><strong>Địa chỉ:</strong> ${roomDetail.address}</p>
                            <p><strong>Thông tin khác:</strong> ${roomDetail.description}</p>
                        </div>
                        <div class="col-md-3">
                            <!-- Hiển thị số phòng trống và số chỗ trống -->
                            <p><strong>Tổng số phòng trống:</strong> ${roomDetail.totalAvailableRooms} phòng</p>
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
                                <th scope="col">Loại Phòng</th>
                                <th scope="col">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Sửa forEach để duyệt qua các phòng -->
                            <c:forEach var="rooms" items="${roomList}" varStatus="status" >
                                <tr>
                                    <!-- Sử dụng varStatus để lấy chỉ số -->
                                    <th scope="row">${status.index + 1}</th>
                                    <td>${rooms.roomNumber}</td>
                                    <td>${rooms.price}</td>
                                    <td>
                                        <!-- Hiển thị các tiện ích động -->
                                        <c:if test="${rooms.bed}">
                                            <i class="fas fa-bed"></i> 
                                        </c:if>
                                        <c:if test="${rooms.closedToilet}">
                                            <i class="fas fa-bath"></i> 
                                        </c:if>
                                        <c:if test="${rooms.desk}">
                                            <i class="fas fa-desktop"></i>
                                        </c:if>
                                        <c:if test="${rooms.fridge}">
                                            <i class="fas fa-ice-cream"></i>
                                        </c:if>
                                        <c:if test="${rooms.kitchen}">
                                            <i class="fas fa-utensils"></i>
                                        </c:if>
                                    </td>
                                    <td>${rooms.area} m²</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${rooms.roomTypeId == 1}">
                                                single
                                            </c:when>
                                            <c:when test="${rooms.roomTypeId == 2}">
                                                double
                                            </c:when>
                                            <c:when test="${rooms.roomTypeId == 3}">
                                                Triple
                                            </c:when>
                                            <c:when test="${rooms.roomTypeId == 4}">
                                                Quad
                                            </c:when>
                                            <c:when test="${rooms.roomTypeId == 5}">
                                                Mini Apartment
                                            </c:when>
                                            <c:otherwise>
                                                Full House
                                            </c:otherwise>
                                        </c:choose></td>
                                    <td>
                                        <!-- Kiểm tra trạng thái phòng -->
                                        <c:choose>
                                            <c:when test="${rooms.statusId == 1}">
                                                Còn Trống
                                            </c:when>
                                            <c:otherwise>
                                                Đã Đầy
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
