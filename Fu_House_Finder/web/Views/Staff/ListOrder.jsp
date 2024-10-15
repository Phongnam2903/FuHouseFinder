<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Order</title>
        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/staff/listOrder.css" rel="stylesheet" type="text/css"/>
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
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List of Orders</li>
                        </ol>
                    </nav>
                    <!-- Info Section -->
                    <div class="row mb-3">
                        <h1 class="text-center">List Of Orders Accommodation</h1>
                        <label>Search</label>
                    </div>

                    <!-- Table Section -->
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">User Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Solve Date</th>
                                <th scope="col">Solve By</th>
                                <th scope="col">Status</th>
                                <th scope="col">Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orderList}" varStatus="status">
                                <tr>
                                    <th scope="row">${status.index + 1 + (currentPage - 1) * itemsPerPage}</th>
                                    <td>${order.fullName}</td>
                                    <td>${order.email}</td>
                                    <td>${order.orderedDate}</td>
                                    <td>${order.solvedDate}</td>
                                    <td>${order.solvedByName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.statusID == 1}">
                                                <span class="badge bg-success">Pending</span>
                                            </c:when>
                                            <c:when test="${order.statusID == 2}">
                                                <span class="badge bg-warning">Solved</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Cancelled</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/listOrder?orderId=${order.id}&page=${currentPage}" class="btn btn-primary">
                                            <i class="fa-regular fa-circle-exclamation"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- Pagination Section -->
                    <div class="pagination-container">
                        <ul class="pagination justify-content-center">
                            <!-- Nút Previous -->
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/listOrder?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo; Previous</span>
                                    </a>
                                </li>
                            </c:if>

                            <!-- Hiển thị các số trang -->
                            <c:choose>
                                <c:when test="${totalPages <= 5}">
                                    <!-- Nếu tổng số trang <= 5, hiển thị tất cả -->
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <c:choose>
                                            <c:when test="${i == currentPage}">
                                                <li class="page-item active"><span class="page-link">${i}</span></li>
                                                </c:when>
                                                <c:otherwise>
                                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/listOrder?page=${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                    <!-- Nếu tổng số trang > 5, hiển thị các trang đầu, cuối và dấu "..." -->
                                    <c:if test="${currentPage > 3}">
                                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/listOrder?page=1">1</a></li>
                                        <li class="page-item disabled"><span class="page-link">...</span></li>
                                        </c:if>

                                    <c:forEach var="i" begin="${(currentPage - 2 < 1) ? 1 : currentPage - 2}" end="${(currentPage + 2 > totalPages) ? totalPages : currentPage + 2}">
                                        <c:choose>
                                            <c:when test="${i == currentPage}">
                                                <li class="page-item active"><span class="page-link">${i}</span></li>
                                                </c:when>
                                                <c:otherwise>
                                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/listOrder?page=${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                    <c:if test="${currentPage < totalPages - 2}">
                                        <li class="page-item disabled"><span class="page-link">...</span></li>
                                        <li class="page-item"><a class="page-link" href="${pageContext.request.context.path}/listOrder?page=${totalPages}">${totalPages}</a></li>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>

                            <!-- Nút Next -->
                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/listOrder?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">Next &raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>

                <div class="modal fade" id="orderModal" tabindex="-1" aria-labelledby="orderModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="orderModalLabel">Order Detail</h5>
                            </div>
                            <form action="${pageContext.request.contextPath}/updateOrder" method="post">
                                <div class="modal-body">
                                    <!-- Kiểm tra nếu có selectedOrder -->
                                    <c:if test="${not empty selectedOrder}">
                                        <p><strong>Full Name:</strong> ${selectedOrder.fullName}</p>
                                        <p><strong>Phone Number:</strong> ${selectedOrder.phoneNumber}</p>
                                        <p><strong>Email:</strong> ${selectedOrder.email}</p>
                                        <p><strong>Order Date:</strong> ${selectedOrder.orderedDate}</p>
                                        <p><strong>Order Content:</strong> ${selectedOrder.orderContent}</p>
                                        <hr>

                                        <!-- Dropdown chọn trạng thái -->
                                        <div class="form-group">
                                            <label for="orderStatus">Status</label>
                                            <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle form-control" type="button" id="orderStatusDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Select Status
                                                </button>
                                                <ul class="dropdown-menu" aria-labelledby="orderStatusDropdown">
                                                    <li><a class="dropdown-item" data-value="1">Pending</a></li>
                                                    <li><a class="dropdown-item" data-value="2">Solved</a></li>
                                                </ul>
                                                <input type="hidden" name="orderStatus" id="orderStatus" value="">
                                            </div>
                                        </div>

                                        <!-- Dropdown chọn nhà trọ và phòng trọ trống -->
                                        <div class="form-group">
                                            <label for="houseSelect">Choose a House</label>
                                            <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle" type="button" id="houseDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Select House
                                                </button>
                                                <div class="dropdown-menu" aria-labelledby="houseDropdown">
                                                    <c:forEach var="house" items="${houseList}">
                                                        <a class="dropdown-item" href="#" data-id="${house.id}">
                                                            <div style="white-space: normal;">
                                                                <strong>${house.houseName}</strong><br>
                                                                Min Price: <fmt:formatNumber value="${house.minPrice}" type="number" minFractionDigits="0" /> VND 
                                                                - Max Price: <fmt:formatNumber value="${house.maxPrice}" type="number" minFractionDigits="0" /> VND<br>
                                                                Distance To School: ${house.distanceToSchool} km<br>
                                                                Description: ${house.description}
                                                            </div>
                                                        </a>
                                                        <div class="dropdown-divider"></div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <input type="hidden" id="houseId" name="houseId" value="">
                                        </div>

                                        <!-- Hidden field để gửi Order ID (cần thiết để cập nhật đơn hàng) -->
                                        <input type="hidden" name="orderId" value="${selectedOrder.id}">
                                    </c:if>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <script>
            <c:if test="${selectedOrder != null}">
            // Khi selectedOrder tồn tại, tự động hiển thị modal
            var myModal = new bootstrap.Modal(document.getElementById('orderModal'));
            myModal.show();
            </c:if>
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var dropdownItems = document.querySelectorAll('.dropdown-item');

                dropdownItems.forEach(function (item) {
                    item.addEventListener('click', function (event) {
                        event.preventDefault();

                        // Lấy thông tin house đã chọn
                        var houseName = item.querySelector('strong').innerText;

                        // Cập nhật nút dropdown
                        var dropdownButton = document.getElementById('houseDropdown');
                        dropdownButton.innerHTML = houseName;

                        // Cập nhật giá trị cho input ẩn
                        var houseId = item.getAttribute('data-id'); // Thêm thuộc tính data-id vào mỗi item
                        document.getElementById('houseId').value = houseId;

                        // Đóng dropdown
                        var dropdown = new bootstrap.Dropdown(dropdownButton);
                        dropdown.hide();
                    });
                });
            });
            document.addEventListener('DOMContentLoaded', function () {
                var dropdownStatusItems = document.querySelectorAll('.dropdown-item[data-value]');

                dropdownStatusItems.forEach(function (item) {
                    item.addEventListener('click', function (event) {
                        event.preventDefault();

                        // Cập nhật nút dropdown
                        var dropdownButton = document.getElementById('orderStatusDropdown');
                        dropdownButton.innerHTML = item.innerText;

                        // Lưu giá trị vào input ẩn
                        var value = item.getAttribute('data-value');
                        document.getElementById('orderStatus').value = value;

                        // Đóng dropdown
                        var dropdown = new bootstrap.Dropdown(dropdownButton);
                        dropdown.hide();
                    });
                });
            });
        </script>
    </body>
</html>
