<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FU House Finder</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/homePage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>
        <%@include file="../Partials/Navbar.jsp" %>
        <div class="container-fluid m-3">
            <!-- Info Section -->
            <div class="row mb-3 mt-3">
                <h1 class="text-center">List Of Orders Accommodation</h1>
            </div>
            <div class="container-fluid">
                <form action="${pageContext.request.contextPath}/Order" method="GET" class="form-inline d-flex mb-3">
                    <div class="row w-100">
                        <!-- Search (căn giữa) -->
                        <div class="form-group col-md-12 d-flex justify-content-center mb-3">
                            <input class="form-control w-50" type="search" name="search" placeholder="Enter keywords to search orders" aria-label="Search" value="${param.search}">
                            <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
                        </div>

                        <!-- Filter theo ngày tạo -->
                        <div class="form-group col-md-3">
                            <label for="fromDate" class="">From Date</label>
                            <input type="date" class="form-control" id="fromDate" name="fromDate" placeholder="From Date" value="${param.fromDate}">
                        </div>

                        <div class="form-group col-md-3">
                            <label for="toDate" class="">To Date</label>
                            <input type="date" class="form-control" id="toDate" name="toDate" placeholder="To Date" value="${param.toDate}">
                        </div>

                        <!-- Filter theo status -->
                        <div class="form-group col-md-3">
                            <label for="filterStatus" class="">Status</label>
                            <select class="form-control" id="filterStatus" name="filterStatus">
                                <option value="">Select Status</option>
                                <option value="1" ${param.filterStatus == '1' ? 'selected' : ''}>Pending</option>
                                <option value="2" ${param.filterStatus == '2' ? 'selected' : ''}>Solved</option>
                            </select>
                        </div>

                        <!-- Sort (sắp xếp) -->
                        <div class="form-group col-md-3">
                            <label for="sortOrder" class="">Sort Order</label>
                            <select class="form-control" id="sortOrder" name="sortOrder">
                                <option value="">Select Sort Order</option>
                                <option value="asc" ${param.sortOrder == 'asc' ? 'selected' : ''}>Oldest to Newest</option>
                                <option value="desc" ${param.sortOrder == 'desc' ? 'selected' : ''}>Newest to Oldest</option>
                            </select>
                        </div>

                        <!-- Apply button -->
                        <div class="form-group col-md-3 mt-3">
                            <button type="submit" class="btn btn-secondary mb-2">Apply Filters</button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- Bảng List of Order -->
            <!-- OrderContent  OrderDate  Status   SolvedDate   SuggestHouse -->
            <div class="container-fluid">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Order Content</th>
                                <th scope="col">Order Date</th>
                                <th scope="col">Status</th>
                                <th scope="col">Solved Date</th>
                                <th scope="col">Suggest House</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orderL}"  varStatus="status">
                                <tr>
                                    <th scope="row">${status.index + 1 + (currentPage - 1) * itemsPerPage}</th> 
                                    <td>${order.orderContent}</td>
                                    <td>${order.orderedDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.statusID == 1}">
                                                <span class="badge bg-danger">Pending</span>
                                            </c:when>
                                            <c:when test="${order.statusID == 2}">
                                                <span class="badge bg-success">Solved</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Cancelled</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <c:if test="${order.statusID == 2}">
                                        <td>${order.solvedDate}</td>
                                        <td>
                                            <a href="####" style="text-decoration: none">${order.houseName}</a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Pagination Section -->
            <div class="pagination-container">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/Order?page=${currentPage - 1}" aria-label="Previous">
                                &laquo; Previous
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <li class="page-item active"><span class="page-link">${i}</span></li>
                                </c:when>
                                <c:otherwise>
                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Order?page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/Order?page=${currentPage + 1}" aria-label="Next">
                                Next &raquo;
                            </a>
                        </li>
                    </c:if>
                </ul>

            </div>
        </div>


        <a href="#" class="btn btn-lg btn-danger-custom" data-bs-toggle="modal" data-bs-target="#orderModal">
            <i class="fa-solid fa-pen-to-square"></i> Order Accommodation
        </a>

        <div class="modal fade" id="orderModal" tabindex="-1" aria-labelledby="orderModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="orderModalLabel">Accommodation Request</h5>
                    </div>
                    <div class="modal-body">
                        <form id="orderForm" action="${pageContext.request.contextPath}/homePage" method="POST">
                            <input type="hidden" name="userId" value="${user.id}" />
                            <input type="hidden" name="service" value="sendOrder">
                            <div class="mb-3">
                                <label for="fullName" class="form-label">Full Name <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Enter your full name"
                                       value="${sessionScope.fullName != null ? sessionScope.fullName : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="phoneNumber" class="form-label">Phone Number <span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Enter your phone number" 
                                       value="${sessionScope.phoneNumber != null ? sessionScope.phoneNumber : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" 
                                       value="${sessionScope.email != null ? sessionScope.email : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="desire" class="form-label">What is your desire to find accommodation? <span class="text-danger">*</span></label>
                                <textarea class="form-control" id="desire" rows="3" name="desire" placeholder="Describe your needs"  
                                          required>${sessionScope.desire != null ? sessionScope.desire : ''}</textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" form="orderForm" class="btn btn-custom">Submit Order</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="notificationModal">
            <span class="close-btn" onclick="closeModal()">&#10006;</span>
            <i class="fa-solid fa-circle-exclamation"></i>
            <p id="modalMessage">Your message here</p>
            <div id="progressBar">
                <div id="progress"></div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                // Hiển thị modal với thông báo
                function showModal(message, duration) {
                    // Thiết lập thông điệp cho modal
                    document.getElementById('modalMessage').innerText = message;
                    document.getElementById('notificationModal').style.display = 'block'; // Hiển thị modal

                    // Thiết lập thanh tiến trình
                    const progressBar = document.getElementById('progress');
                    const progressBarContainer = document.getElementById('progressBar');
                    let timeLeft = duration / 1000; // Chuyển đổi từ milliseconds sang seconds
                    const totalWidth = progressBarContainer.offsetWidth; // Chiều rộng tối đa của thanh tiến trình

                    // Cập nhật thanh tiến trình
                    const interval = setInterval(() => {
                        timeLeft--;
                        const newWidth = (timeLeft / (duration / 1000)) * totalWidth; // Tính toán chiều rộng mới

                        progressBar.style.width = newWidth + 'px'; // Cập nhật chiều rộng

                        if (timeLeft <= 0) {
                            clearInterval(interval); // Dừng interval khi hết thời gian
                            closeModal(); // Đóng modal khi hết thời gian
                        }
                    }, 1000); // Cập nhật mỗi giây
                }

                function closeModal() {
                    const modal = document.getElementById('notificationModal');
                    modal.style.display = 'none'; // Ẩn modal
                    modal.style.opacity = 0; // Đặt độ mờ về 0
                }

                // Lấy tham số từ URL để hiển thị thông báo (nếu có)
                window.onload = function () {
                    const urlParams = new URLSearchParams(window.location.search);
                    const status = urlParams.get('status');
                    const message = urlParams.get('message');

                    if (status && message) {
                        showModal(message, 7000);
                    }
                };
        </script>
    </body>
</html>
