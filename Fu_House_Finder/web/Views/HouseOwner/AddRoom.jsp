<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm phòng trọ</title>
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
            .error-message {
                color: red; /* Màu chữ đỏ cho thông báo lỗi */
                font-size: 0.875em; /* Kích thước chữ nhỏ hơn */
            }
        </style>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>
        <!--Màn hình thêm phòng-->
        <div class="container mt-4">    
            <h2 class="text-center mb-4">Add new room</h2>
            <div class="mb-4">
                <a href="ListRoom" class="btn btn-secondary">Cancel</a>
            </div>
            <form action="AddRoom" method="post" onsubmit="return validateForm()">
                <!--Text điền tòa nhà-->
                <div class="mb-3">
                    <label for="roomNumber" class="form-label">Room number</label>
                    <input type="text" class="form-control" id="roomNumber" name="roomNumber" required>
                    <div id="roomNumberError" class="error-message"></div>
                </div>
                <!--Text tầng-->
                <div class="mb-3">
                    <label for="floorNumber" class="form-label">Floor</label>
                    <input type="number" class="form-control" id="floorNumber" name="floorNumber" required min="1">
                    <div id="floorNumberError" class="error-message"></div>
                </div>
                <!--Text tên phòng-->
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description" required>
                    <div id="descriptionError" class="error-message"></div>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Image <span class="text-danger">*</span></label>
                    <input type="file" class="form-control" id="image" name="image" accept="image/*" onchange="previewImage(event, 'previewImage')" required>
                    <img id="previewImage" src="" alt="Preview Image" style="display:none; max-width: 650px; margin-top: 10px;">
                </div>
                <!--Text giá-->
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" class="form-control" id="price" name="price" required min="0" step="0.01">
                    <div id="priceError" class="error-message"></div>
                </div>
                <!--Text diện tích-->
                <div class="mb-3">
                    <label for="area" class="form-label">Area</label>
                    <input type="number" class="form-control" id="area" name="area" required min="0" step="0.01">
                    <div id="areaError" class="error-message"></div>
                </div>
                <div class="mb-3">
                    <label for="houseId" class="form-label" >House name</label>
                    <select class="form-select" id="houseId" name="houseId" required>
                        <c:forEach items="${houseList}" var="house">
                            <option value="${house.id}">${house.houseName}</option>
                        </c:forEach>
                    </select>
                    <div id="houseIdError" class="error-message"></div>
                </div>

                

                <!--Chọn loại phòng-->
                <div class="mb-3">
                    <label for="roomTypeId" class="form-label">Room Type</label>
                    <select class="form-select" id="roomTypeId" name="roomTypeId" required>
                        <c:forEach items="${roomTypeList}" var="roomType">
                            <option value="${roomType.roomTypeID}">${roomType.roomTypeName}</option>
                        </c:forEach>
                    </select>
                    <div id="roomTypeIdError" class="error-message"></div>
                </div>
                <div class="mb-3">
                    <label for="statusId" class="form-label">Status</label>
                    <select class="form-select" id="statusId" name="statusId" required>
                        <c:forEach items="${roomStatusList}" var="roomStatus">
                            <option value="${roomStatus.statusID}">${roomStatus.statusName}</option>
                        </c:forEach>
                    </select>
                    <div id="statusIdError" class="error-message"></div>
                </div>

                <!--Checkbox tiện ích: Tủ lanh, Bếp, Máy giặt, Bàn, Giường, Không chung chủ, Vệ sinh khép kín -->
                <div class="mb-3">
                    <label class="form-label">Services</label>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="fridge" name="facilities" value="fridge">
                        <label class="form-check-label" for="fridge">Fridge</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="kitchen" name="facilities" value="kitchen">
                        <label class="form-check-label" for="kitchen">Kitchen</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="washingMachine" name="facilities" value="washingMachine">
                        <label class="form-check-label" for="washingMachine">Washing machine</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="bed" name="facilities" value="bed">
                        <label class="form-check-label" for="bed">Bed</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="liveInHouseOwner" name="facilities" value="liveInHouseOwner">
                        <label class="form-check-label" for="liveInHouseOwner">Live in house owner</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="closedToilet" name="facilities" value="closedToilet">
                        <label class="form-check-label" for="closedToilet">Closed toilet</label>
                    </div>
                </div>

                <div class="mb-3">
                    <button type="submit" class="btn btn-secondary">Add new</button>
                </div>
            </form>

            <%@include file="../Partials/Footer.jsp" %>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                        function validateForm() {
                            // Clear previous error messages
                            document.getElementById('roomNumberError').innerText = '';
                            document.getElementById('floorNumberError').innerText = '';
                            document.getElementById('descriptionError').innerText = '';
                            document.getElementById('priceError').innerText = '';
                            document.getElementById('areaError').innerText = '';

                            const roomNumber = document.getElementById('roomNumber').value;
                            const floorNumber = document.getElementById('floorNumber').value;
                            const description = document.getElementById('description').value;
                            const price = document.getElementById('price').value;
                            const area = document.getElementById('area').value;

                            let isValid = true; // Track overall validity

                            if (!roomNumber) {
                                document.getElementById('roomNumberError').innerText = "Số phòng không được để trống.";
                                isValid = false;
                            }

                            if (floorNumber <= 0) {
                                document.getElementById('floorNumberError').innerText = "Tầng phải lớn hơn 0.";
                                isValid = false;
                            }

                            if (!description) {
                                document.getElementById('descriptionError').innerText = "Mô tả không được để trống.";
                                isValid = false;
                            }

                            if (price < 0) {
                                document.getElementById('priceError').innerText = "Giá không được âm.";
                                isValid = false;
                            }

                            if (area < 0) {
                                document.getElementById('areaError').innerText = "Diện tích không được âm.";
                                isValid = false;
                            }

                            return isValid; // Prevent form submission if invalid

                        }

                        // Check if the message attribute exists
                        if (document.getElementById('message')) {
                            const message = document.getElementById('message').getAttribute('message');
                            if (message) {
                                alert(message);
                            }
                        }
            </script>
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
