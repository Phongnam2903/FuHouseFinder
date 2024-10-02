<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/house/cssHouse.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container mt-4">
            <h2 class="text-center mb-4">House List</h2>
            <div class="mb-4">
                <a href="AddHouse" class="btn btn-secondary">+ Add New House</a>
            </div>

            <div class="d-flex justify-content-end mb-4">
                <form action="${pageContext.request.contextPath}/ListHouse" method="get" class="input-group" style="max-width: 400px;">
                    <input type="text" name="search" class="form-control" placeholder="Enter house name to search" 
                           aria-label="Enter house name to search" value="${search != null ? search : ''}" />
                    <button class="btn btn-secondary" type="submit">Search</button>
                </form>
            </div>

            <c:if test="${not empty houseList}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">House Name</th>
                            <th scope="col">Unrented Price</th>
                            <th scope="col">Utilities</th>
                            <th scope="col">Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="house" items="${houseList}" varStatus="status">
                            <tr>
                                <td>${status.index + 1 + (currentPage - 1) * itemsPerPage}</td>
                                <td>${house.houseName}</td>
                                <td>
                                    chưa thêm
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${house.fingerPrintLock || house.camera || house.parking}">
                                            <c:if test="${house.fingerPrintLock}">
                                                <i class="fas fa-fingerprint" title="Fingerprint Lock" style="font-size: 2rem;
                                                   margin-right: 20px;"></i>
                                            </c:if>
                                            <c:if test="${house.camera}">
                                                <i class="fas fa-video" title="Security camera" style="font-size: 2rem;
                                                   margin-right: 20px;"></i>
                                            </c:if>
                                            <c:if test="${house.parking}">
                                                <i class="fas fa-parking" title="Parking" style="font-size: 2rem;"  ></i>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <p>No utilities available</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="UpdateHouse?id=${house.id}" class="btn btn-warning" style="margin-right: 20px;">
                                        <i class="fas fa-tools"></i>
                                    </a>
                                    <a href="javascript:void(0);" onclick="openDeleteModal(${house.id}, '${house.houseName}');" class="btn btn-danger" style="margin-right: 20px;">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                    <a href="ListHouse?houseId=${house.id}" class="btn btn-info">
                                        <i class="fas fa-eye"></i>
                                    </a>
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
                                <a class="page-link" href="${pageContext.request.contextPath}/ListHouse?page=${currentPage - 1}" aria-label="Previous">
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
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListHouse?page=${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                <!-- Nếu tổng số trang > 5, hiển thị các trang đầu, cuối và dấu "..." -->
                                <c:if test="${currentPage > 3}">
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListHouse?page=1">1</a></li>
                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                    </c:if>

                                <c:forEach var="i" begin="${(currentPage - 2 < 1) ? 1 : currentPage - 2}" end="${(currentPage + 2 > totalPages) ? totalPages : currentPage + 2}">
                                    <c:choose>
                                        <c:when test="${i == currentPage}">
                                            <li class="page-item active"><span class="page-link">${i}</span></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/ListHouse?page=${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                <c:if test="${currentPage < totalPages - 2}">
                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.context.path}/ListHouse?page=${totalPages}">${totalPages}</a></li>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                        <!-- Nút Next -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/ListHouse?page=${currentPage + 1}" aria-label="Next">
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

            <c:if test="${empty houseList}">
                <div class="alert alert-info text-center">
                    No houses of landlord found.
                </div>
            </c:if>
        </div>

        <!-- Modal xác nhận xóa -->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Confirm House Deletion</h5>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete the house <span id="houseNameToDelete"></span>?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Delete</a>
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
