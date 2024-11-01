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
        <!-- Main Content -->
        <section class="container-fluid-custom my-4">
            <div class="row">
                <div class="col-lg-3">
                    <form id="filterForm" method="get" action="homePage">
                        <div class="d-flex justify-content-between align-items-center p-3" style="background-color: #f44336; padding: 10px;">
                            <h2 class="text-white mb-0">FILTER RESULTS</h2>
                            <button type="reset" class="btn btn-link text-white mb-0" onclick="window.location.href = 'homePage';">Reset Filters</button>
                        </div>
                        <div class="card p-3">
                            <!-- Distance Filter -->
                            <div class="mb-3">
                                <label for="distance" class="form-label">Distance to School</label>
                                <div class="d-flex align-items-center">
                                    <input type="text" class="form-control me-2" id="distance-km" name="distanceFrom" placeholder="From (km)" value="${param.distanceFrom}">
                                    <span>-</span>
                                    <input type="text" class="form-control ms-2" id="distance-miles" name="distanceTo" placeholder="To (km)" value="${param.distanceTo}">
                                </div>
                            </div>

                            <hr>
                            <!-- Price Filter -->
                            <div class="mb-3">
                                <label for="price" class="form-label">Price (VND/Month)</label>
                                <div class="d-flex align-items-center">
                                    <input type="text" class="form-control me-2" id="price-min" name="priceMin" placeholder="From" value="${param.priceMin}">
                                    <span>-</span>
                                    <input type="text" class="form-control ms-2" id="price-max" name="priceMax" placeholder="To" value="${param.priceMax}">
                                </div>
                            </div>

                            <hr>
                            <!-- Room Type Filter -->
                            <div class="mb-3">
                                <label for="room-type" class="form-label">Room Type</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="singleRoom" id="singleRoom" ${param.singleRoom != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="singleRoom">Single</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="doubleRoom" id="doubleRoom" ${param.doubleRoom != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="doubleRoom">Double</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="tripleRoom" id="tripleRoom" ${param.tripleRoom != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="tripleRoom">Triple</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="quadRoom" id="quadRoom" ${param.quadRoom != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="quadRoom">Quad</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="miniApartment" id="miniApartment" ${param.miniApartment != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="miniApartment">Mini Apartment</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="fullHouse" id="fullHouse" ${param.fullHouse != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="fullHouse">Full House</label>
                                </div>
                            </div>

                            <hr>
                            <!-- Additional Features Filter -->
                            <div class="mb-3">
                                <label class="form-label">Additional Features</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="fingerprintLock" id="fingerprintLock" ${param.fingerprintLock != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="fingerprintLock">Fingerprint Lock</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="camera" id="camera" ${param.camera != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="camera">Security Camera</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="parking" id="parking" ${param.parking != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="parking">Parking Space</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="fridge" id="fridge" ${param.fridge != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="fridge">Fridge</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="washingMachine" id="washingMachine" ${param.washingMachine != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="washingMachine">Washing Machine</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="desk" id="desk" ${param.desk != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="desk">Desk</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="kitchen" id="kitchen" ${param.kitchen != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="kitchen">Kitchen</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="bed" id="bed" ${param.bed != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="bed">Bed</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="privateToilet" id="privateToilet" ${param.privateToilet != null ? 'checked' : ''}>
                                    <label class="form-check-label" for="privateToilet">Private Toilet</label>
                                </div>
                            </div>

                            <hr>
                            <!-- Rating Filter -->
                            <div class="mb-3">
                                <label for="rating" class="form-label">Rating</label>
                                <div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating5" value="5" ${param.rating == '5' ? 'checked' : ''}>
                                        <label class="form-check-label" for="rating5">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating4" value="4" ${param.rating == '4' ? 'checked' : ''}>
                                        <label class="form-check-label" for="rating4">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating3" value="3" ${param.rating == '3' ? 'checked' : ''}>
                                        <label class="form-check-label" for="rating3">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating2" value="2" ${param.rating == '2' ? 'checked' : ''}>
                                        <label class="form-check-label" for="rating2">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating1" value="1" ${param.rating == '1' ? 'checked' : ''}>
                                        <label class="form-check-label" for="rating1">
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <!-- Filter Button -->
                            <button type="submit" class="btn btn-success w-100">Filter</button>
                        </div>
                    </form>
                </div>

                <!-- Results Section -->
                <div class="col-lg-9">
                    <div class="row mb-4 justify-content-between">
                        <div class="col-md-4">
                            <form class="d-flex" role="search">
                                <input class="form-control" type="search" placeholder="Search for rooms" aria-label="Search">
                                <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                        <div class="col-md-4">
                            <select class="form-select" id="sortBy">
                                <option value="">Sort By</option>
                                <option value="priceAsc">Price: Low to High</option>
                                <option value="priceDesc">Price: High to Low</option>
                                <option value="distanceAsc">Closest to School</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach var="house" items="${houseList}">
                            <div class="col-md-4 mb-4">
                                <a href="${pageContext.request.contextPath}/houseDetail?id=${house.id}" style="text-decoration: none; color: inherit;">
                                    <div class="card" style="cursor: pointer;">
                                        <img style="height: 300px; max-width: 100%" src="${pageContext.request.contextPath}/images/${house.image}" class="card-img-top" alt="${house.houseName}">

                                        <div class="card-body">
                                            <h5 class="card-title"> ${house.houseName}</h5>
                                            <p class="card-text"><i class="fas fa-money-bill-wave"></i>
                                                <fmt:formatNumber value="${house.minPrice}" type="number" minFractionDigits="0" /> VND - 
                                                <fmt:formatNumber value="${house.maxPrice}" type="number" minFractionDigits="0" /> VND
                                            </p>
                                            <p class="card-text address"><i class="fas fa-map-marker-alt"></i> ${house.address}</p>
                                            <p class="card-text"><i class="fas fa-route"></i> ${house.distanceToSchool} km</p>

                                            <div class="star-rating" style="position: absolute; bottom: 10px; right: 10px; display: flex; align-items: center;">
                                                <!-- Lấy giá trị trung bình sao và tính số sao đầy đủ -->
                                                <c:set var="fullStars" value="${Math.floor(house.averageStar)}" />

                                                <!-- Hiển thị các ngôi sao đầy đủ -->
                                                <c:forEach var="i" begin="1" end="${fullStars}">
                                                    <i class="fas fa-star" style="color: gold;"></i>
                                                </c:forEach>

                                                <!-- Kiểm tra nếu có sao nửa -->
                                                <c:if test="${house.averageStar - fullStars >= 0.5}">
                                                    <i class="fas fa-star-half-alt" style="color: gold;"></i>
                                                </c:if>

                                                <!-- Hiển thị ngôi sao rỗng cho các sao chưa được tính -->
                                                <c:set var="emptyStars" value="${5 - fullStars - (house.averageStar - fullStars >= 0.5 ? 1 : 0)}" />
                                                <c:forEach var="i" begin="1" end="${emptyStars}">
                                                    <i class="fas fa-star" style="color: lightgray;"></i>
                                                </c:forEach>

                                                <!-- Hiển thị số trung bình sao -->
                                                <span style="margin-left: 5px;">(<fmt:formatNumber value="${house.averageStar}" type="number" minFractionDigits="1" maxFractionDigits="1"/>)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>

                        <!--Phân Trang -->
                        <div class="pagination-container">
                            <ul class="pagination justify-content-end">
                                <!-- Nút Previous -->
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="${pageContext.request.contextPath}/homePage?page=${currentPage - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo; Previous</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == 1}">
                                    <li class="page-item disabled">
                                        <span class="page-link" aria-label="Previous">
                                            <span aria-hidden="true">&laquo; Previous</span>
                                        </span>
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
                                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/homePage?page=${i}">${i}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                        <!-- Nếu tổng số trang > 5, hiển thị các trang đầu, cuối và dấu "..." -->
                                        <c:if test="${currentPage > 3}">
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/homePage?page=1">1</a></li>
                                            <li class="page-item disabled"><span class="page-link">...</span></li>
                                            </c:if>

                                        <c:forEach var="i" begin="${(currentPage - 2 < 2) ? 2 : currentPage - 2}" end="${(currentPage + 2 > totalPages - 1) ? totalPages - 1 : currentPage + 2}">
                                            <c:choose>
                                                <c:when test="${i == currentPage}">
                                                    <li class="page-item active"><span class="page-link">${i}</span></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/homePage?page=${i}">${i}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                        <c:if test="${currentPage < totalPages - 2}">
                                            <li class="page-item disabled"><span class="page-link">...</span></li>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/homePage?page=${totalPages}">${totalPages}</a></li>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>

                                <!-- Nút Next -->
                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="${pageContext.request.contextPath}/homePage?page=${currentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">Next &raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == totalPages}">
                                    <li class="page-item disabled">
                                        <span class="page-link" aria-label="Next">
                                            <span aria-hidden="true">Next &raquo;</span>
                                        </span>
                                    </li>
                                </c:if>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </section>

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
