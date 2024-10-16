<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>House Details</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/detailHouse.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>

        <!-- Main Content -->
        <div class="container mt-4">
            <!-- House Image and Owner Info -->
            <div class="row">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="homePage" style="color: #f44336;">Home Page</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">House Detail</li>
                </ol>
                <!-- House Image -->
                <div class="col-lg-8">
                    <div id="houseImageCarousel" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach var="image" items="${images}" varStatus="status">
                                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <img style="height: 800px; max-width: 100%;" src="${pageContext.request.contextPath}/images/${image}" class="d-block w-100 img-fluid" alt="${image}">
                                </div>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#houseImageCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden"></span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#houseImageCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden"></span>
                        </button>
                    </div>
                </div>

                <!-- Owner Info -->
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="card-title" style="text-align: center;">Owner Information</h3>
                            <p class="card-text">
                                <strong>Owner Name:</strong> ${house.ownerName} <br>
                                <strong>Phone Number:</strong> ${house.ownerPhoneNumber} <br>
                                <strong>House Address:</strong> ${house.address}
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title" style="text-align: center;">User Reviews</h3>
                            <!-- Hiển thị các bình luận trước đó -->
                            <label for="comment" class="form-label">Comments:</label>
                            <c:if test="${not empty ratesList}">
                                <c:forEach var="rate" items="${ratesList}">
                                    <div class="card mb-1 review-container">
                                        <span class="created-date">${rate.createdDate}</span>
                                        <p class="m-1">
                                            <span class="username">${rate.userName}:</span>
                                            <span class="description-and-star">
                                                <span class="description-text">${rate.decription}</span>
                                                <c:if test="${not empty rate.star}">
                                                    <span class="star-rating">
                                                        <c:forEach var="i" begin="1" end="${rate.star}">
                                                            <i class="fas fa-star" style="color: gold;"></i>
                                                        </c:forEach>
                                                    </span>
                                                </c:if>
                                            </span>
                                        </p>
                                        <c:if test="${not empty rate.houseOwnerReply}">
                                            <p><i class="fas fa-chevron-right" style="margin-right: 5px;"></i>
                                                Owner: ${rate.houseOwnerReply}
                                            </p>
                                        </c:if>
                                    </div>
                                </c:forEach>
                                <!-- Phân trang -->
                                <div class="pagination-container">
                                    <ul class="pagination">
                                        <!-- Nút Previous -->
                                        <c:if test="${pageNumber > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="?id=${house.id}&page=${pageNumber - 1}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <!-- Hiển thị các số trang -->
                                        <c:choose>
                                            <c:when test="${totalPages <= 4}">
                                                <!-- Nếu tổng số trang <= 4, hiển thị tất cả các trang -->
                                                <c:forEach var="i" begin="1" end="${totalPages}">
                                                    <li class="page-item ${i == pageNumber ? 'active' : ''}">
                                                        <a class="page-link" href="?id=${house.id}&page=${i}">${i}</a>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- Hiển thị trang đầu tiên -->
                                                <li class="page-item ${pageNumber == 1 ? 'active' : ''}">
                                                    <a class="page-link" href="?id=${house.id}&page=1">1</a>
                                                </li>

                                                <!-- Hiển thị dấu "..." nếu cần -->
                                                <c:if test="${pageNumber > 3}">
                                                    <li class="page-item disabled">
                                                        <span class="page-link">...</span>
                                                    </li>
                                                </c:if>

                                                <!-- Hiển thị các trang xung quanh trang hiện tại -->
                                                <c:forEach var="i" begin="${(pageNumber - 1 < 2) ? 2 : pageNumber - 1}" end="${(pageNumber + 1 > totalPages - 1) ? totalPages - 1 : pageNumber + 1}">
                                                    <li class="page-item ${i == pageNumber ? 'active' : ''}">
                                                        <a class="page-link" href="?id=${house.id}&page=${i}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <!-- Hiển thị dấu "..." trước trang cuối cùng nếu cần -->
                                                <c:if test="${pageNumber < totalPages - 2}">
                                                    <li class="page-item disabled">
                                                        <span class="page-link">...</span>
                                                    </li>
                                                </c:if>

                                                <!-- Hiển thị trang cuối cùng -->
                                                <li class="page-item ${pageNumber == totalPages ? 'active' : ''}">
                                                    <a class="page-link" href="?id=${house.id}&page=${totalPages}">${totalPages}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>

                                        <!-- Nút Next -->
                                        <c:if test="${pageNumber < totalPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="?id=${house.id}&page=${pageNumber + 1}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </c:if>
                            <c:if test="${empty ratesList}">
                                <div class="card mb-1">
                                    <p class="m-1">No reviews yet.</p>
                                </div>
                            </c:if>

                            <!-- Form nhập đánh giá và bình luận mới -->
                            <form action="${pageContext.request.contextPath}/houseDetail" method="POST">
                                <input type="hidden" name="service" value="sendReview">
                                <input type="hidden" name="houseId" value="${house.id}" />

                                <!-- Nhập bình luận -->
                                <div class="mb-3">
                                    <label for="comment" class="form-label">Write a comment</label>
                                    <textarea class="form-control" id="comment" name="comment" rows="2"></textarea>
                                </div>

                                <!-- Chọn sao để đánh giá -->
                                <div class="mb-3 d-flex">
                                    <label for="rating" class="form-label">Rating</label>
                                    <div class="star-rating">
                                        <i class="fa fa-star" data-value="1"></i>
                                        <i class="fa fa-star" data-value="2"></i>
                                        <i class="fa fa-star" data-value="3"></i>
                                        <i class="fa fa-star" data-value="4"></i>
                                        <i class="fa fa-star" data-value="5"></i>
                                    </div>
                                    <!-- Input ẩn để lưu giá trị rating đã chọn -->
                                    <input type="hidden" id="ratingValue" name="ratingValue" value="0">
                                </div>

                                <!-- Nút gửi -->
                                <div><button type="submit" class="btn btn-secondary">Post</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- House Details and Ratings -->
            <div class="row mt-4">
                <!-- House Details -->
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title" style="text-align: center;">${house.houseName}</h2>
                            <p class="card-text">
                                <strong>Electricity Price:</strong> <fmt:formatNumber value="${house.powerPrice}" type="number" minFractionDigits="0" /> VND/KWh <br>
                                <strong>Water Price:</strong> <fmt:formatNumber value="${house.waterPrice}" type="number" minFractionDigits="0" /> VND/m3 <br>
                                <strong>Service Price:</strong> <fmt:formatNumber value="${house.otherServicePrice}" type="number" minFractionDigits="0" /> VND/month <br>
                                <strong>House Address:</strong> ${house.address} <br>
                                <strong>Other Information:</strong> ${house.description}
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Room List -->
            <div class="row mt-4">
                <div class="col-lg-12 mb-4">
                    <h2 class="mb-3" style="text-align: center;">Available Rooms List</h2>
                    <p><strong>Total Fully Available Rooms:</strong> 0 phòng</p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Room Name</th>
                                <th>Room Price</th>
                                <th>Utilities</th>
                                <th>Room Type</th>
                                <th>Area</th>
                                <th>Number of Occupants</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="room" items="${roomList}">
                                <tr>
                                    <td>
                                        ${room.roomNumber}
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${room.price}" type="number" minFractionDigits="0" /> VND
                                    </td>
                                    <td>
                                        <c:if test="${room.fridge}">
                                            <i class="fas fa-snowflake" title="Fridge" style="margin-right: 5px;"></i>
                                        </c:if>
                                        <c:if test="${room.bed}">
                                            <i class="fas fa-bed" title="Bed" style="margin-right: 5px;"></i>
                                        </c:if>
                                        <c:if test="${room.desk}">
                                            <i class="fas fa-table" title="Desk" style="margin-right: 5px;"></i>
                                        </c:if>
                                        <c:if test="${room.kitchen}">
                                            <i class="fas fa-utensils" title="Kitchen" style="margin-right: 5px;"></i>
                                        </c:if>
                                        <c:if test="${room.closedToilet}">
                                            <i class="fas fa-toilet" title="Closed Toilet" style="margin-right: 5px;"></i>
                                        </c:if>
                                        <c:if test="${room.washingMachine}">
                                            <i class="fas fa-water" title="Washing Machine" style="margin-right: 5px;"></i>
                                        </c:if>
                                    </td>
                                    <td>
                                        ${room.roomTypeId == 1 ? "Single Room" : "Double Room"}
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${room.area}" type="number" minFractionDigits="0" /> m²
                                    </td>
                                    <td>
                                        ${room.statusId == 1 ? "Fully Available" : "Partially Available"}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Floating Button -->
        <a href="#" class="btn btn-danger btn-lg btn-danger-custom" data-bs-toggle="modal" data-bs-target="#orderModal">
            <i class="fas fa-flag"></i>
        </a>

        <div class="modal fade" id="orderModal" tabindex="-1" aria-labelledby="orderModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="orderModalLabel">Report House</h5>
                    </div>
                    <div class="modal-body">
                        <form id="reportForm" action="${pageContext.request.contextPath}/houseDetail" method="POST">
                            <input type="hidden" name="userId" value="${user.id}" />
                            <input type="hidden" name="houseId" value="${house.id}" />
                            <input type="hidden" name="service" value="sendReport">
                            <div class="mb-3">
                                <label for="fullName" class="form-label">Title</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="Enter title"
                                       value="${sessionScope.fullName != null ? sessionScope.fullName : ''}">
                            </div>
                            <div class="mb-3">
                                <label for="desire" class="form-label">Reason you report this house? <span class="text-danger">*</span></label>
                                <textarea class="form-control" id="reason" rows="3" name="reason" placeholder="Describe your opinion"  
                                          required>${sessionScope.desire != null ? sessionScope.desire : ''}</textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" form="reportForm" class="btn btn-custom">Submit Report</button>
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
                // JavaScript để xử lý rating bằng ngôi sao
                const stars = document.querySelectorAll('.star-rating i');
                const ratingValue = document.getElementById('ratingValue');

                stars.forEach(star => {
                    star.addEventListener('click', () => {
                        const selectedRating = star.getAttribute('data-value'); // Lấy giá trị sao đã chọn
                        ratingValue.value = selectedRating; // Ghi giá trị sao đã chọn vào input hidden
                        resetStars(); // Xóa màu của tất cả các ngôi sao
                        highlightStars(selectedRating); // Tô màu cho các ngôi sao đã chọn
                        console.log(`Rated: ${selectedRating} stars`); // In ra console (để kiểm tra nếu cần)
                    });
                });

                function highlightStars(rating) {
                    stars.forEach(star => {
                        if (star.getAttribute('data-value') <= rating) {
                            star.classList.add('selected'); // Tô màu ngôi sao đã chọn
                        }
                    });
                }

                function resetStars() {
                    stars.forEach(star => {
                        star.classList.remove('selected'); // Xóa màu của tất cả các ngôi sao
                    });
                }

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