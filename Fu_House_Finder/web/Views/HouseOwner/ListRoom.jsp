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
    <style>
        .table {
            border-collapse: collapse;
        }
        .table th, .table td {
            border-bottom: 2px solid #dee2e6;
        }
        .table tr:hover {
            background-color: #f8f9fa;
            cursor: pointer;
        }
        .modal-header {
            background-color: #ff8c00;
            color: white;
        }
        .modal-footer .btn-danger {
            background-color: #ff8c00;
            border-color: #ff8c00;
        }
    </style>
</head>
<body>
    <%@include file="../Partials/Header.jsp" %>

    <div class="container mt-4">
        <h2 class="text-center mb-4">Danh sách phòng trọ</h2>
        <div class="mb-4">
            <a href="AddRoom" class="btn btn-secondary">+ Thêm phòng mới</a>
        </div>

        <c:if test="${not empty roomList}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Số phòng</th>
                        <th scope="col">Tầng</th>
                        <th scope="col">Mô tả</th>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Số người ở</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Diện tích</th>
                        <th scope="col">Tiện ích</th>
                        <th scope="col">Tùy chọn</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="room" items="${roomList}" varStatus="status">
                        <tr>
                            <td>${room.roomNumber}</td>
                            <td>${room.floorNumber}</td>
                            <td>${room.description}</td>
                            <td><img src="${room.image}" alt="Ảnh phòng" style="width: 100px; height: auto;"></td>
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
                            <td>
                                <a href="EditRoom?id=${room.id}" class="btn btn-warning" style="margin-right: 20px;">
                                    <i class="fas fa-tools"></i>
                                </a>
                                <a href="javascript:void(0);" onclick="openDeleteModal(${room.id}, '${room.roomNumber}');" class="btn btn-danger">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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
</body>
</html>

