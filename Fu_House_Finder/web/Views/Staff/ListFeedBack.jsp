<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room List</title>
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


                    <!-- Table Section -->
                    <h4 class="mb-3" style="text-align: center">Feedback List</h4>
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Title</th>
                                <th scope="col">Description</th>
                                <th scope="col">Status</th>
                                <th scope="col">Sent Time</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Renter ID</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody id="feedbackTableBody">
                            <!-- Modify forEach to iterate over the feedbacks -->
                            <c:forEach var="feedbacks" items="${feedbackList}" varStatus="status" >
                                <tr>
                                    <!-- Use varStatus to get the index -->
                                    <th scope="row">${status.index + 1}</th>    
                                    <td>${feedbacks.title}</td>
                                    <td>${feedbacks.description}</td>
                                    <td>${feedbacks.status}</td>
                                    <td>${feedbacks.sentTime}</td>
                                    <td>${feedbacks.createdDate}</td>
                                    <td>${feedbacks.renterId}</td>
                                    <td>
                    <a href="${pageContext.request.contextPath}/feedbackDetail?id=${feedbacks.id}">
                        Detail
                    </a>
                    <a href="${pageContext.request.contextPath}/feedbackDelete?id=${feedbacks.id}">
                        Delete
                    </a>
                                </td>
                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- Phần này để hiển thị các nút phân trang -->
                    <div id="paginationControls" style="text-align: center"></div>

                    <script>
                        // Cấu hình số lượng phòng hiển thị trên mỗi trang
                        const itemsPerPage = 5;
                        const tableBody = document.getElementById('roomTableBody');
                        const rows = tableBody.getElementsByTagName('tr');
                        const totalItems = rows.length;

                        // Tính toán số trang
                        const totalPages = Math.ceil(totalItems / itemsPerPage);

                        function showPage(page) {
                            // Ẩn tất cả các hàng
                            for (let i = 0; i < totalItems; i++) {
                                rows[i].style.display = 'none';
                            }

                            // Hiển thị các hàng trong phạm vi của trang hiện tại
                            const start = (page - 1) * itemsPerPage;
                            const end = start + itemsPerPage;
                            for (let i = start; i < end && i < totalItems; i++) {
                                rows[i].style.display = '';
                            }

                            // Cập nhật nút phân trang
                            const paginationControls = document.getElementById('paginationControls');
                            paginationControls.innerHTML = '';

                            for (let i = 1; i <= totalPages; i++) {
                                const button = document.createElement('button');
                                button.innerText = i;
                                button.classList.add('btn', 'btn-primary', 'mx-1');
                                if (i === page) {
                                    button.classList.add('active');
                                }
                                button.addEventListener('click', function () {
                                    showPage(i);
                                });
                                paginationControls.appendChild(button);
                            }
                        }

                        // Hiển thị trang đầu tiên khi tải trang
                        showPage(1);
                    </script>
                    
                            <!-- Modal xác nhận xóa -->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa phòng trọ</h5>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xóa phòng số <span id="roomNumberToDelete"></span> không?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Xóa</a>
                    </div>
                </div>
            </div>
        </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
