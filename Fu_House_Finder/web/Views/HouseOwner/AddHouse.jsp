<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New House</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/house/cssHouse.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container">
            <h2 class="text-center mb-4 mt-4">Add New House</h2>

            <c:if test="${not empty message}">
                <div class="alert ${alertClass}">
                    ${message}
                </div>
            </c:if>

            <div class="mb-4">
                <a href="ListHouse" class="btn btn-secondary">< Back to House List</a>
            </div>

            <form action="${pageContext.request.contextPath}/AddHouse" method="POST" enctype="multipart/form-data">

                <div class="row">
                    <!-- Left Column -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <div class="mb-3">
                                <label for="houseName" class="form-label">House Name <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="houseName" name="houseName" placeholder="Enter house name" 
                                       value="${houseName != null ? houseName : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Address <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="address" name="address" placeholder="Enter address" 
                                       value="${address != null ? address : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="powerPrice" class="form-label">Giá tiền điện trên tháng (VND) <span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="powerPrice" name="powerPrice" placeholder="Enter power price" 
                                       value="${powerPrice != null ? powerPrice : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="waterPrice" class="form-label">Monthly Power Price (VND) <span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="waterPrice" name="waterPrice" placeholder="Enter water price" 
                                       value="${waterPrice != null ? waterPrice : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="servicePrice" class="form-label">Monthly Service Price (VND)</label>
                                <input type="number" class="form-control" id="servicePrice" name="servicePrice" placeholder="Enter service price"
                                       value="${servicePrice != null ? servicePrice : ''}">
                            </div>
                        </div>
                    </div>

                    <!-- Right Column -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <div class="mb-3">
                                <label for="image1" class="form-label">House Image (1) <span class="text-danger">*</span></label>
                                <input type="file" class="form-control" id="image1" name="image1" accept="image/*" onchange="previewImage(event, 'previewImage1')" required>
                                <img id="previewImage1" src="" alt="Preview Image" style="display:none; max-width: 650px; margin-top: 10px;">
                            </div>
                            <div class="mb-3">
                                <label for="image2" class="form-label">House Image (2)</label>
                                <input type="file" class="form-control" id="image2" name="image2" accept="image/*" onchange="previewImage(event, 'previewImage2')">
                                <img id="previewImage2" src="" alt="Preview Image" style="display:none; max-width: 650px; margin-top: 10px;">
                            </div>
                            <div class="mb-3">
                                <label for="image3" class="form-label">House Image (3)</label>
                                <input type="file" class="form-control" id="image3" name="image3" accept="image/*" onchange="previewImage(event, 'previewImage3')">
                                <img id="previewImage3" src="" alt="Preview Image" style="display:none; max-width: 650px; margin-top: 10px;">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Utilities</label><br>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="fingerPrintLock" name="fingerPrintLock"
                                           ${fingerPrintLock ? 'checked' : ''}>
                                    <label class="form-check-label" for="fingerPrintLock">Fingerprint Lock</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="camera" name="camera"
                                           ${camera ? 'checked' : ''}>
                                    <label class="form-check-label" for="camera">Security Camera</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="parking" name="parking"
                                           ${parking ? 'checked' : ''}>
                                    <label class="form-check-label" for="parking">Parking Space</label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="distance" class="form-label">Distance to School (KM) <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="distance" name="distance" placeholder="Enter distance" 
                                       value="${distance != null ? distance : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description" 
                                          rows="7" placeholder="Enter description">${description != null ? description : ''}</textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success mb-4">Add New</button>
                </div>

            </form>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                    function previewImage(event, imgId) {
                                        const file = event.target.files[0];
                                        const previewImg = document.getElementById(imgId);

                                        if (file) {
                                            const reader = new FileReader();

                                            reader.onload = function (e) {
                                                previewImg.src = e.target.result;  // Thiết lập đường dẫn ảnh cho img
                                                previewImg.style.display = 'block'; // Hiển thị ảnh
                                            }

                                            reader.readAsDataURL(file); // Đọc tệp ảnh và chuyển đổi thành URL
                                        } else {
                                            previewImg.src = ""; // Xóa đường dẫn nếu không có tệp
                                            previewImg.style.display = 'none'; // Ẩn ảnh
                                        }
                                    }
        </script>

    </body>
</html>
