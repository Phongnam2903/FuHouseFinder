<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update House</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/house/cssHouse.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container">
            <h2 class="text-center mb-4 mt-4">Update House</h2>
            <c:if test="${not empty message}">
                <div class="alert ${alertClass}">
                    ${message}
                </div>
            </c:if>

            <div class="mb-4">
                <a href="ListHouse" class="btn btn-secondary">< Back to House List</a>
            </div>

            <!-- Form chỉnh sửa nhà trọ -->
            <form action="${pageContext.request.contextPath}/UpdateHouse" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="houseId" value="${house.id}"/>
                <input type="hidden" name="existingImages" value="${house.image}" />

                <div class="row">
                    <!-- Cột trái -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <!-- Tên nhà trọ -->
                            <div class="mb-3">
                                <label for="houseName" class="form-label">House Name <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="houseName" name="houseName"
                                       value="${houseName != null ? houseName : house.houseName}" placeholder="Enter house name" required>
                            </div>

                            <!-- Địa chỉ -->
                            <div class="mb-3">
                                <label for="address" class="form-label">Address <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="address" name="address"
                                       value="${address != null ? address : house.address}" placeholder="Enter address" required>
                            </div>

                            <!-- Giá tiền điện -->
                            <div class="mb-3">
                                <label for="powerPrice" class="form-label">Monthly Power Price (VND) <span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="powerPrice" name="powerPrice" 
                                       value="${powerPrice != null ? powerPrice.intValue() : house.powerPrice.intValue()}" 
                                       placeholder="Enter electricity price" required>
                            </div>

                            <!-- Giá tiền nước -->
                            <div class="mb-3">
                                <label for="waterPrice" class="form-label">Monthly Water Price (VND) <span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="waterPrice" name="waterPrice"
                                       value="${waterPrice != null ? waterPrice.intValue() : house.waterPrice.intValue()}" 
                                       placeholder="Enter water price" required>
                            </div>

                            <!-- Giá dịch vụ khác -->
                            <div class="mb-3">
                                <label for="servicePrice" class="form-label">Monthly Service Price (VND)</label>
                                <input type="number" class="form-control" id="servicePrice" name="servicePrice" 
                                       value="${servicePrice != null ? servicePrice.intValue() : house.otherServicePrice.intValue()}" 
                                       placeholder="Enter service price" required>
                            </div>

                            <!-- Tiện ích -->
                            <div class="mb-3">
                                <label class="form-label">Utilities</label><br>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="fingerPrintLock" name="fingerPrintLock" 
                                           ${fingerPrintLock ? 'checked' : house.fingerPrintLock ? 'checked' : ''}>
                                    <label class="form-check-label" for="fingerPrintLock">Fingerprint Lock</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="camera" name="camera" 
                                           ${camera ? 'checked' : house.camera ? 'checked' : ''}>
                                    <label class="form-check-label" for="camera">Camera</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" class="form-check-input" id="parking" name="parking" 
                                           ${parking ? 'checked' : house.parking ? 'checked' : ''}>
                                    <label class="form-check-label" for="parking">Parking</label>
                                </div>
                            </div>

                            <!-- Khoảng cách đến trường -->
                            <div class="mb-3">
                                <label for="distance" class="form-label">Distance to School (km) <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="distance" name="distance" 
                                       value="${distance != null ? distance : house.distanceToSchool}" placeholder="Enter distance" required>
                            </div>

                            <!-- Mô tả -->
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="7" 
                                          placeholder="Enter description" required>${description != null ? description : house.description}</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Cột phải -->
                    <div class="col-md-6">
                        <div class="form-section">
                            <!-- Ảnh nhà -->
                            <div class="mb-3">
                                <label for="image1" class="form-label">House Image (1) <span class="text-danger">*</span></label>
                                <c:if test="${not empty imageList[0]}">
                                    <br/>
                                    <img class="mb-3" id="previewImage1" src="${pageContext.request.contextPath}/images/${imageList[0]}" alt="Image 1" width="650">
                                    <input type="hidden" name="imageFileIndexes" value="0">
                                    <p class="mb-4">Change Image</p>
                                </c:if>
                                <input type="file" class="form-control" id="image1" name="image1" accept="image/*" onchange="previewImage(event, 'previewImage1')">
                            </div>

                            <div class="mb-3">
                                <label for="image2" class="form-label">House Image (2)</label>
                                <c:if test="${not empty imageList[1]}">
                                    <br/>
                                    <img class="mb-3" id="previewImage2" src="${pageContext.request.contextPath}/images/${imageList[1]}" alt="Image 2" width="650">
                                    <input type="hidden" name="imageFileIndexes" value="1">
                                    <p class="mb-4">Change Image</p>
                                </c:if>
                                <input type="file" class="form-control" id="image2" name="image2" accept="image/*" onchange="previewImage(event, 'previewImage2')">
                            </div>

                            <div class="mb-3">
                                <label for="image3" class="form-label">House Image (3)</label>
                                <c:if test="${not empty imageList[2]}">
                                    <br/>
                                    <img class="mb-3" id="previewImage3" src="${pageContext.request.contextPath}/images/${imageList[2]}" alt="Image 3" width="650">
                                    <input type="hidden" name="imageFileIndexes" value="2">
                                    <p class="mb-4">Change Image</p>
                                </c:if>
                                <input type="file" class="form-control" id="image3" name="image3" accept="image/*" onchange="previewImage(event, 'previewImage3')">
                            </div>
                        </div>
                        <input type="hidden" name="imageFileIndexes" value="0,1,2">
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success mb-4">Update</button>
                </div>

            </form>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                    function previewImage(event, previewElementId) {
                                        const file = event.target.files[0];
                                        const previewElement = document.getElementById(previewElementId);

                                        if (file) {
                                            const reader = new FileReader();
                                            reader.onload = function (e) {
                                                previewElement.src = e.target.result;
                                            }
                                            reader.readAsDataURL(file);
                                        } else {
                                        }
                                    }
        </script>

    </body>
</html>
