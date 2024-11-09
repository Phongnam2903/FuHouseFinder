<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách phòng trọ</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <style>
            .table {
                border-collapse: collapse; /* Để bỏ kẻ dọc */
            }
            .table th, .table td {
                border-bottom: 2px solid #dee2e6; /* Chỉ kẻ ngang */
            }
            .table tr:hover {
                background-color: #f8f9fa; /* Màu nền khi hover */
                cursor: pointer; /* Con trỏ chuột biến thành pointer khi hover */
            }
            .modal-header {
                background-color: #ff8c00; /* Màu cam */
                color: white;
            }
            .modal-footer .btn-danger {
                background-color: #ff8c00; /* Nút xóa màu cam */
                border-color: #ff8c00;
            }
        </style>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>
        <div class="container mt-4">
            <h2 class="text-center mb-4">Room list</h2>

            <!-- Thêm một alert để hiển thị thông báo xóa phòng thành công -->
            <c:if test="${param.successDeleteR eq 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Delete room successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <!-- Thêm một alert để hiển thị thông báo thêm phòng thành công -->
            <c:if test="${param.successAdd eq 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Add new room successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <!-- Thêm một alert để hiển thị thông báo Sửa phòng thành công -->
            <c:if test="${param.successUpdate eq 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Update room successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <div class="mb-4">
                <a href="ListHouse" class="btn btn-secondary">Back</a>
            </div>

            <div class="mb-4">
                <a href="AddRoom?houseId=${houseId}" class="btn btn-secondary">+ Add new room</a>
            </div>

            <c:if test="${not empty roomList}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Room Number</th>
                            <th scope="col">Floor</th>
                            <th scope="col">Description</th>
                            <th scope="col">Image</th>

                            <th scope="col">Price</th>
                            <th scope="col">Area</th>
                            <th scope="col">Service</th>
                            <th scope="col">Status</th>
                            <th scope="col">Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="room" items="${roomList}" varStatus="status">
                            <tr>
                                <td>${room.roomNumber}</td>
                                <td>${room.floorNumber}</td>
                                <td>${room.description}</td>
                                <td><img class="mb-3" src="${pageContext.request.contextPath}/images/${room.image}" alt="Image 1" width="50"></td>
                                <td>${room.price} đ</td>
                                <td>${room.area} m²</td>
                                <td>
                                    <c:if test="${room.fridge}">
                                        <i class="fas fa-snowflake" title="Tủ lạnh" style="font-size: 2rem;"></i>
                                    </c:if>
                                    <c:if test="${room.bed}">
                                        <i class="fas fa-bed" title="Giường" style="font-size: 2rem;"></i>
                                    </c:if>
                                    <c:if test="${room.desk}">
                                        <i class="fas fa-desktop" title="Bàn" style="font-size: 2rem;"></i>
                                    </c:if>
                                    <c:if test="${room.kitchen}">
                                        <i class="fas fa-utensils" title="Bếp" style="font-size: 2rem;"></i>
                                    </c:if>
                                    <c:if test="${room.closedToilet}">
                                        <i class="fas fa-toilet" title="Nhà vệ sinh khép kín" style="font-size: 2rem;"></i>
                                    </c:if>
                                    <c:if test="${room.washingMachine}">
                                        <i class="fas fa-washer" title="Máy giặt" style="font-size: 2rem;"></i>
                                    </c:if>
                                </td>
                                <!-- Room status -->
                                <td>
                                    <c:if test="${room.statusId == 1}">
                                        <i title="Available" > Avaliable</i>
                                    </c:if>
                                    <c:if test="${room.statusId == 2}">
                                        <i title="Occupied"> Occupied</i>
                                    </c:if>
                                </td>
                                <td>
                                    <a href="UpdateRoom?id=${room.id}" class="btn btn-warning" style="margin-right: 20px;">
                                        <i class="fas fa-tools"></i>
                                    </a>
                                    <c:if test="${room.statusId == 1}">
                                        <a href="javascript:void(0);" onclick="openDeleteModal(${room.id}, '${room.roomNumber}');" class="btn btn-danger">
                                            <i class="fas fa-trash-alt"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${room.statusId == 2}">
                                        <a href="javascript:void(0);" onclick="cannotopenDeleteModal(${room.id}, '${room.roomNumber}');" class="btn btn-danger">
                                            <i class="fas fa-trash-alt"></i>
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pagination-container">
                    <ul class="pagination justify-content-end">
                        <!-- Nút Previous -->
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/ListRoom?page=${currentPage - 1}&houseId=${houseId}" aria-label="Previous">
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
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListRoom?page=${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                <!-- Nếu tổng số trang > 5, hiển thị các trang đầu, cuối và dấu "..." -->
                                <c:if test="${currentPage > 3}">
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListRoom?page=1&houseId=${houseId}">1</a></li>
                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                    </c:if>

                                <c:forEach var="i" begin="${(currentPage - 2 < 1) ? 1 : currentPage - 2}" end="${(currentPage + 2 > totalPages) ? totalPages : currentPage + 2}">
                                    <c:choose>
                                        <c:when test="${i == currentPage}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListRoom?page=${i}&houseId=${houseId}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                <c:if test="${currentPage < totalPages - 2}">
                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListRoom?page=${totalPages}&houseId=${houseId}">${totalPages}</a></li>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                        <!-- Nút Next -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/ListRoom?page=${currentPage + 1}&houseId=${houseId}"  aria-label="Next">
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
            </c:if>

            <c:if test="${empty roomList}">
                <div class="alert alert-info text-center">
                    Không có phòng nào được tìm thấy.
                </div>
            </c:if>
        </div>

        <!-- Modal xác nhận xóa -->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Delete room</h5>
                    </div>
                    <div class="modal-body">
                        Do you want to delete room number <span id="roomNumberToDelete"></span>?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                        <a id="confirmDeleteBtn" href="#" onclick="openDeleteSuccessModal(${room.id}, '${room.roomNumber}')" class="btn btn-danger">Yes</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal xóa thành công -->
        <c:if test="${param.successDelete eq 'true'}">
            <div class="modal fade" id="deletesuccessModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Delete room</h5>
                        </div>
                        <div class="modal-body">
                            Delete room number <span id="roomNumberToDelete"></span> success!
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>

                        </div>
                    </div>
                </div>
            </div>
        </c:if>


        <!-- Modal thông báo không thể xóa -->
        <div class="modal fade" id="cannotdeleteModal" tabindex="-1" aria-labelledby="cannotdeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cannotdeleteModalLabel">Delete room</h5>
                    </div>
                    <div class="modal-body">
                        You can not delete room <span id="roomNumberToDelete"></span> when the room status is occupied !
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                            function openDeleteModal(roomId, roomNumber) {
                                document.getElementById('roomNumberToDelete').textContent = roomNumber;
                                document.getElementById('confirmDeleteBtn').href = 'ListRoom?id=' + roomId;  // Link for deletion
                                var deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                                deleteModal.show();
                            }

        </script>
        <script>
            function openDeleteSuccessModal(roomId, roomNumber) {
                document.getElementById('roomNumberToDelete').textContent = roomNumber;
                document.getElementById('confirmDeleteBtn').href = 'ListRoom?id=' + roomId;  // Link for deletion
                var deleteModal = new bootstrap.Modal(document.getElementById('deleteSuccess'));
                deleteSuccess.show();
            }

        </script>
        <script>
            function cannotopenDeleteModal(roomId, roomNumber) {
                document.getElementById('roomNumberToDelete').textContent = roomNumber;
                document.getElementById('confirmDeleteBtn').href = 'ListRoom?id=' + roomId;  // Link for deletion
                var cannotdeleteModal = new bootstrap.Modal(document.getElementById('cannotdeleteModal'));
                cannotdeleteModal.show();
            }

        </script>
    </body>
</html>

