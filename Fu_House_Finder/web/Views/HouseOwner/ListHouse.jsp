<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách nhà trọ</title>
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
            <h2 class="text-center mb-4">Danh sách nhà trọ</h2>
            <div class="mb-4">
                <a href="AddHouse" class="btn btn-secondary">+ Thêm Trọ Mới</a>
            </div>

            <c:if test="${not empty houseList}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên nhà trọ</th>
                            <th scope="col">Tiền chưa cho thuê</th>
                            <th scope="col">Tiện ích</th>
                            <th scope="col">Tùy chọn</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="house" items="${houseList}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${house.houseName}</td>
                                <td>
                                    chưa thêm
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${house.fingerPrintLock || house.camera || house.parking}">
                                            <c:if test="${house.fingerPrintLock}">
                                                <i class="fas fa-fingerprint" title="Khóa vân tay" style="font-size: 2rem;
                                                   margin-right: 20px;"></i>
                                            </c:if>
                                            <c:if test="${house.camera}">
                                                <i class="fas fa-video" title="Camera giám sát" style="font-size: 2rem;
                                                   margin-right: 20px;"></i>
                                            </c:if>
                                            <c:if test="${house.parking}">
                                                <i class="fas fa-parking" title="Chỗ để xe" style="font-size: 2rem;"  ></i>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fas fa-ban" title="Không có tiện ích" style="font-size: 2rem;"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="EditHouse?id=${house.id}" class="btn btn-warning" style="margin-right: 20px;">
                                        <i class="fas fa-tools"></i>
                                    </a>
                                    <a href="javascript:void(0);" onclick="openDeleteModal(${house.id}, '${house.houseName}');" class="btn btn-danger">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty houseList}">
                <div class="alert alert-info text-center">
                    Không có nhà trọ nào được tìm thấy.
                </div>
            </c:if>
        </div>

        <!-- Modal xác nhận xóa -->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa nhà trọ</h5>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xóa nhà trọ <span id="houseNameToDelete"></span> không?
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
                                        // Hàm mở modal và cập nhật tên nhà trọ
                                        function openDeleteModal(houseId, houseName) {
                                            // Cập nhật nội dung modal với tên nhà trọ
                                            document.getElementById('houseNameToDelete').textContent = houseName;

                                            // Cập nhật liên kết để xóa nhà trọ
                                            document.getElementById('confirmDeleteBtn').href = 'ListHouse?id=' + houseId;

                                            // Hiển thị modal
                                            var deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                                            deleteModal.show();
                                        }
        </script>
    </body>
</html>
