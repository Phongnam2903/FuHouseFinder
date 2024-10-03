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
            <h2 class="text-center mb-4">Chỉnh sửa phòng trọ</h2>
            <div class="mb-4">
                <a href="ListRoom" class="btn btn-secondary">Hủy</a>
            </div>
            <form action="UpdateRoom" method="post">
                <c:set var="dbRoom" value="${requestScope.room}"/>
                <!--Text điền tòa nhà-->
                <div class="mb-3">
                    <label for="id" class="form-label"> </label>
                    <input type="hidden" class="form-control" id="id" name="id" readonly value="${room.id}" required>
                </div>
                <div class="mb-3">
                    <label for="roomNumber" class="form-label">Số phòng</label>
                    <input type="text" class="form-control" id="roomNumber" name="roomNumber" value="${room.roomNumber}" required>
                </div>
                <!--Text tầng-->
                <div class="mb-3">
                    <label for="floorNumber" class="form-label">Tầng</label>
                    <input type="text" class="form-control" id="floorNumber" name="floorNumber" value="${room.floorNumber}" required>
                </div>
                <!--Text tên phòng-->
                <div class="mb-3">
                    <label for="description" class="form-label">Mô tả</label>
                    <input type="text" class="form-control" id="description" name="description" value="${room.description}" required>
                </div>
                <!--Text giá-->
                <div class="mb-3">
                    <label for="price" class="form-label">Giá</label>
                    <input type="text" class="form-control" id="price" name="price" value="${room.price}" required>
                </div>
                <!--Text diện tích-->
                <div class="mb-3">
                    <label for="area" class="form-label">Diện tích</label>
                    <input type="text" class="form-control" id="area" name="area" value="${room.area}" required>
                </div>
                <div class="mb-3">
                    <label for="houseId" class="form-label">Tên tòa nhà</label>
                    <select class="form-select" id="houseId" name="houseId">
                        <c:forEach items="${houseList}" var="house">
                            <c:if test="${room.houseId eq house.id}">
                                <option selected value="${house.id}">${house.houseName}</option>
                            </c:if>
                            <c:if test="${room.houseId ne house.id}">
                                <option  value="${house.id}">${house.houseName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <!--Chọn loại phòng-->
                    <div class="mb-3">
                        <label for="roomTypeId" class="form-label">Loại phòng</label>
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
                        <label for="statusId" class="form-label">Tình trạng</label>
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
                        <label for="facilities" class="form-label">Tìm kiếm</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="fridge">
                            <label class="form-check-label" for="fridge">
                                Tủ lạnh
                            </label>
                        </div>
                        <div>
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="kitchen">
                            <label class="form-check-label" for="kitchen">
                                Bếp
                            </label>
                        </div>
                        <div>
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="washingMachine">
                            <label class="form-check-label" for="washingMachine">
                                Máy giặt
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="bed">
                            <label class="form-check-label" for="bed">
                                Giường
                            </label>
                        </div>
                        <div class="form-check"></div>
                        <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="liveInHouseOwner">
                        <label class="form-check-label" for="liveInHouseOwner">
                            Không chung chủ
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="facilities" name="facilities" value="closedToilet">
                        <label class="form-check-label" for="closedToilet">
                            Vé sinh khép kín
                        </label>
                    </div>

                    <div class="mb-3">
                        <button type="submit" class="btn btn-secondary">Chỉnh sửa</button>
                    </div>

                </div>


            </form>
        </div>





        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
