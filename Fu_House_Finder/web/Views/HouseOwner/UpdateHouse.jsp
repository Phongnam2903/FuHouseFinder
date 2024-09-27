<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa nhà trọ</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <style>
            .form-section {
                margin-bottom: 30px;
            }
            .form-label {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container">
            <h2 class="text-center mb-4 mt-4">Chỉnh sửa nhà trọ</h2>

            <c:if test="${not empty message}">
                <div class="alert ${alertClass}">
                    ${message}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/UpdateHouse" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="houseId" value="${house.id}"/>

                <div class="row">
                    <!-- Left Column -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <div class="mb-3">
                                <label for="houseName" class="form-label">Tên nhà trọ</label>
                                <input type="text" class="form-control" id="houseName" name="houseName"
                                       value="${house.houseName}">
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="address" name="address"
                                       value="${house.address}" placeholder="Nhập địa chỉ">
                            </div>
                            <div class="mb-3">
                                <label for="powerPrice" class="form-label">Giá tiền điện trên tháng (VND)</label>
                                <input type="number" class="form-control" id="powerPrice" name="powerPrice" 
                                       value="${house.powerPrice != null ? house.powerPrice : ''}" placeholder="Nhập giá tiền điện">
                            </div>
                            <div class="mb-3">
                                <label for="waterPrice" class="form-label">Giá nước trên tháng (VND)</label>
                                <input type="number" class="form-control" id="waterPrice" name="waterPrice" placeholder="Nhập giá tiền nước">
                            </div>
                            <div class="mb-3">
                                <label for="servicePrice" class="form-label">Giá tiền dịch vụ trên tháng (VND)</label>
                                <input type="number" class="form-control" id="servicePrice" name="servicePrice" placeholder="Nhập giá dịch vụ">
                            </div>
                        </div>
                    </div>

                    <!-- Right Column -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <div class="mb-3">
                                <label for="image1" class="form-label">Ảnh nhà (1)</label>
                                <input type="file" class="form-control" id="image1" name="image1" accept="image/*">
                            </div>
                            <div class="mb-3">
                                <label for="image2" class="form-label">Ảnh nhà (2)</label>
                                <input type="file" class="form-control" id="image2" name="image2" accept="image/*">
                            </div>
                            <div class="mb-3">
                                <label for="image3" class="form-label">Ảnh nhà (3)</label>
                                <input type="file" class="form-control" id="image3" name="image3" accept="image/*">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Tiện ích</label><br>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="fingerPrintLock" name="fingerPrintLock">
                                    <label class="form-check-label" for="fingerPrintLock">Khóa vân tay</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="camera" name="camera">
                                    <label class="form-check-label" for="camera">Camera giám sát</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="parking" name="parking">
                                    <label class="form-check-label" for="parking">Chỗ để xe</label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="distance" class="form-label">Khoảng cách đến trường (km)</label>
                                <input type="text" class="form-control" id="distance" name="distance" placeholder="Nhập khoảng cách">
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Thông tin mô tả</label>
                                <textarea class="form-control" id="description" name="description" rows="7" placeholder="Nhập thông tin mô tả"></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success mb-4">Chỉnh sửa</button>
                </div>

            </form>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
