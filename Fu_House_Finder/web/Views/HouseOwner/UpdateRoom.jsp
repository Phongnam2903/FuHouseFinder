<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa phòng trọ</title>
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
        <!--Màn hình thêm phòng-->
        <div class="container mt-4">    
            <h2 class="text-center mb-4">Room Update</h2>
            <div class="mb-4">
                <a href="ListRoom" class="btn btn-secondary">Cancel</a>
            </div>
            <form action="UpdateRoom" method="post">
                <c:set var="dbRoom" value="${requestScope.room}"/>
                <!--Text điền tòa nhà-->
                <div class="mb-3">
                    <label for="id" class="form-label"> </label>
                    <input type="hidden" class="form-control" id="id" name="id" readonly value="${room.id}" required>
                </div>
                <div class="mb-3">
                    <label for="roomNumber" class="form-label">Room number</label>
                    <input type="text" class="form-control" id="roomNumber" name="roomNumber" value="${room.roomNumber}" required>
                </div>
                <!--Text tầng-->
                <div class="mb-3">
                    <label for="floorNumber" class="form-label">Floor</label>
                    <input type="text" class="form-control" id="floorNumber" name="floorNumber" value="${room.floorNumber}" required>
                </div>
                <!--Text tên phòng-->
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description" value="${room.description}" required>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Image</label>
                    <input type="file" class="form-control" id="image" name="image" accept="image/*" onchange="previewImage(event, 'previewImage')" value="${room.image}" required>
                </div>
                <!--Text giá-->
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="text" class="form-control" id="price" name="price" value="${room.price}" required>
                </div>
                <!--Text diện tích-->
                <div class="mb-3">
                    <label for="area" class="form-label">Area</label>
                    <input type="text" class="form-control" id="area" name="area" value="${room.area}" required>
                </div>
                <div class="mb-3" >
                    <label for="houseId" class="form-label" hidden>House name</label>
                    <select class="form-select" id="houseId" name="houseId" hidden>
                        <c:forEach items="${houseList}" var="house"  >
                            <c:if test="${room.houseId eq house.id}">
                                <option selected value="${house.id}" hidden>${house.houseName}</option>
                            </c:if>
                            <c:if test="${room.houseId ne house.id}">
                                <option  value="${house.id}" hidden>${house.houseName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <!--Chọn loại phòng-->
                    <div class="mb-3">
                        <label for="roomTypeId" class="form-label">Room Type</label>
                        <select class="form-select" id="roomTypeId" name="roomTypeId">
                            <c:forEach items="${roomTypeList}" var="roomType">
                                <c:if test="${room.roomTypeId eq roomType.roomTypeID}">
                                    <option selected value="${roomType.roomTypeID}">${roomType.roomTypeName}</option>

                                </c:if>
                                <c:if test="${room.roomTypeId ne roomType.roomTypeID}">
                                    <option value="${roomType.roomTypeID}">${roomType.roomTypeName}</option>

                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="statusId" class="form-label">Status</label>
                        <select class="form-select" id="statusId" name="statusId">
                            <c:forEach items="${roomStatusList}" var="roomStatus">
                                <c:if test="${room.statusId eq roomStatus.statusID}">
                                    <option selected value="${roomStatus.statusID}">${roomStatus.statusName}</option>

                                </c:if>
                                <c:if test="${room.statusId ne roomStatus.statusID}">
                                    <option value="${roomStatus.statusID}">${roomStatus.statusName}</option>

                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <!--Checkbox tiện ích: Tủ lanh, Bếp, Máy giặt, Bàn, Giường, Không chung chủ, Vệ sinh khép kín -->
                    <div class="mb-3">
                        <label for="facilities" class="form-label">Services</label>
                        <div class="form-check">
                                
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="fridge"  <c:if test="${room.fridge}">checked="true"</c:if>>
                            <label class="form-check-label" for="fridge">
                                Fridge
                            </label>
                        </div>
                        <div>
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="kitchen" <c:if test="${room.kitchen}">checked="true"</c:if>>
                            <label class="form-check-label" for="kitchen">
                                Kitchen
                            </label>
                        </div>
                        <div>
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="washingMachine" <c:if test="${room.washingMachine}">checked="true"</c:if>>
                            <label class="form-check-label" for="washingMachine">
                                Washing machine
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="bed" <c:if test="${room.bed}">checked="true"</c:if>>
                            <label class="form-check-label" for="bed">
                                Bed
                            </label>
                        </div>
                        <div class="form-check"></div>
                        <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="liveInHouseOwner" <c:if test="${room.liveInHouseOwner}">checked="true"</c:if>>
                        <label class="form-check-label" for="liveInHouseOwner">
                            Live in house owner
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="closedToilet" <c:if test="${room.closedToilet}">checked="true"</c:if>>
                        <label class="form-check-label" for="closedToilet">
                            Closed toilet

                        </label>
                    </div>

                    <div class="mb-3">
                        <button type="submit" class="btn btn-secondary">Update</button>
                    </div>

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
