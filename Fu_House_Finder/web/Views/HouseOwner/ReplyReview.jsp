<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/house/cssHouse.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/house/cssReply.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container mt-4">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title" style="text-align: center;">Users Review</h3>
                    <div class="mb-4">
                        <a href="ListHouse" class="btn btn-secondary">< Back to House List</a>
                    </div>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${errorMessage}
                        </div>
                    </c:if>

                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                            ${successMessage}
                        </div>
                    </c:if>

                    Comment:
                    <div class="card">
                        <c:if test="${not empty ratesList}">
                            <c:forEach var="rate" items="${ratesList}">
                                <div class="mb-2">
                                    <p class="m-1">
                                        ${rate.userName}:
                                        ${rate.decription}
                                        <c:if test="${not empty rate.star}">
                                            <span>
                                                <c:forEach var="i" begin="1" end="${rate.star}">
                                                    <i class="fas fa-star" style="color: gold;"></i>
                                                </c:forEach>
                                            </span>
                                        </c:if>
                                        <span>
                                            <!-- If owner has replied, display reply, otherwise show Reply link -->
                                            <c:if test="${empty rate.houseOwnerReply}">
                                                <a href="javascript:void(0);" onclick="toggleReplyForm(${rate.id});">Reply</a>
                                            </c:if>
                                        </span>
                                    </p>
                                    <c:if test="${not empty rate.houseOwnerReply}">
                                        <i class="fas fa-chevron-right" style="margin-right: 5px;"></i>
                                        Owner: ${rate.houseOwnerReply}
                                    </c:if>
                                    <div id="replyForm-${rate.id}" class="reply-form" style="display: none; margin-left: 20px;">
                                        <form action="replyReview" method="post">
                                            <input type="hidden" name="rateId" value="${rate.id}">
                                            <input type="hidden" name="houseId" value="${houseId}">
                                            <div class="d-flex" style="display: flex; align-items: center; margin-bottom: 1rem;">
                                                <div style="display: flex; align-items: center; margin-right: 1rem;">
                                                    <label for="reply-${rate.id}" class="form-label" style="margin-bottom: 0; margin-right: 0.5rem;">Your Reply:</label>
                                                    <textarea name="replyText" id="reply-${rate.id}" class="form-control" style="height: 30px; width: 300px; border-radius: 5px; padding: 5px;"></textarea>
                                                </div>
                                                <div>
                                                    <button type="submit" class="btn btn-primary">Reply</button>
                                                    <button type="button" class="btn btn-secondary" onclick="toggleReplyForm(${rate.id});">Cancel</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty ratesList}">
                            <p class="m-1">No reviews yet.</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div id="notificationModal">
            <span class="close-btn" onclick="closeModal()">&#10006;</span>
            <p id="modalMessage">Your message here</p>
            <div id="progressBar">
                <div id="progress"></div>
            </div>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                // Function to toggle the display of the reply form
                function toggleReplyForm(rateId) {
                    // Get the reply form for the specified rateId
                    var selectedForm = document.getElementById('replyForm-' + rateId);

                    // Toggle visibility
                    if (selectedForm.style.display === 'none' || selectedForm.style.display === '') {
                        // Hide all other reply forms
                        var replyForms = document.querySelectorAll('.reply-form');
                        replyForms.forEach(function (form) {
                            form.style.display = 'none';  // Hide all forms
                        });
                        selectedForm.style.display = 'block';  // Show the selected form
                    } else {
                        selectedForm.style.display = 'none';  // Hide the form if it is already visible
                    }
                }
        </script>
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
