<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm nhà trọ</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            body {
                padding: 20px;
            }
            .form-section {
                margin-bottom: 30px;
            }
            .form-group label {
                font-weight: bold;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center mb-4">Thêm nhà trọ mới</h2>

            <c:if test="${not empty message}">
                <div class="alert ${alertClass}">
                    ${message}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/AddHouse" method="POST">

                <div class="row">
                    <!-- Left Column -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <div class="form-group">
                                <label for="houseName">Tên nhà trọ</label>
                                <input type="text" class="form-control" id="houseName" name="houseName" placeholder="Nhập tên nhà trọ" required>
                            </div>
                            <div class="form-group">
                                <label for="address">Địa chỉ</label>
                                <input type="text" class="form-control" id="address" name="address" placeholder="Nhập địa chỉ" required>
                            </div>
                            <div class="form-group">
                                <label for="powerPrice">Giá tiền điện trên tháng (VND)</label>
                                <input type="number" class="form-control" id="powerPrice" name="powerPrice" placeholder="Nhập giá điện" required>
                            </div>
                            <div class="form-group">
                                <label for="waterPrice">Giá nước trên tháng (VND)</label>
                                <input type="number" class="form-control" id="waterPrice" name="waterPrice" placeholder="Nhập giá nước" required>
                            </div>
                            <div class="form-group">
                                <label for="servicePrice">Giá tiền dịch vụ trên tháng (VND)</label>
                                <input type="number" class="form-control" id="servicePrice" name="servicePrice" placeholder="Nhập giá tiền dịch vụ" required>
                            </div>
                        </div>
                    </div>

                    <!-- Right Column -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <div class="form-group">
                                <label for="image1">Ảnh nhà (1)</label>
                                <input type="file" class="form-control-file" id="image1" name="image1" accept="image/*">
                            </div>
                            <div class="form-group">
                                <label for="image2">Ảnh nhà (2)</label>
                                <input type="file" class="form-control-file" id="image2" name="image2" accept="image/*">
                            </div>
                            <div class="form-group">
                                <label for="image3">Ảnh nhà (3)</label>
                                <input type="file" class="form-control-file" id="image3" name="image3" accept="image/*">
                            </div>
                            <div class="form-group">
                                <label>Tiện ích</label><br>
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
                            <div class="form-group">
                                <label for="distance">Khoảng cách đến trường (km)</label>
                                <input type="text" class="form-control" id="distance" name="distance" placeholder="Nhập khoảng cách" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Thông tin mô tả</label>
                                <textarea class="form-control" id="description" name="description" rows="7" placeholder="Nhập thông tin mô tả"></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">Thêm mới</button>
                </div>

            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"></script>

    </body>
</html>
