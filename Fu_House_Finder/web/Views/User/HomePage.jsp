<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FU House Finder</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/homePage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>

        <!-- Main Content -->
        <section class="container-fluid-custom my-4">
            <div class="row">
                <div class="col-lg-3">
                    <form id="filterForm">
                        <div class="d-flex justify-content-between align-items-center p-3" style="background-color: #f44336; padding: 10px;">
                            <h2 class="text-white mb-0">LỌC KẾT QUẢ</h2>
                            <button type="reset" class="btn btn-link text-white mb-0">Đặt lại bộ lọc</button>
                        </div>
                        <div class="card p-3">
                            <!-- Location Filters -->
                            <div class="mb-3 d-flex justify-content-between">
                                <label for="location" class="form-label">Cơ sở</label>
                                <select class="form-select" id="location">
                                    <option value="">Chọn cơ sở</option>
                                    <option value="hanoi">FU - Hòa Lạc</option>
                                </select>
                            </div>
                            <div class="mb-3 d-flex justify-content-between">
                                <label for="district" class="form-label">Huyện/Quận</label>
                                <select class="form-select" id="district">
                                    <option value="">Huyện Thạch Thất</option>
                                </select>
                            </div>
                            <div class="mb-3 d-flex justify-content-between">
                                <label for="ward" class="form-label">Phường/Xã</label>
                                <select class="form-select" id="ward">
                                    <option value="">Thị trấn Liên Quan</option>
                                </select>
                            </div>
                            <div class="mb-3 d-flex justify-content-between">
                                <label for="village" class="form-label">Thôn/Xóm</label>
                                <select class="form-select" id="village">
                                    <option value="">Đồng Cam</option>
                                </select>
                            </div>

                            <hr>
                            <!-- Distance Filter -->
                            <div class="mb-3">
                                <label for="distance" class="form-label">Khoảng cách đến trường</label>
                                <div class="d-flex align-items-center">
                                    <input type="text" class="form-control me-2" id="distance-km">
                                    <span>-</span>
                                    <input type="text" class="form-control ms-2" id="distance-miles">
                                </div>
                            </div>

                            <hr>
                            <!-- Price Filter -->
                            <div class="mb-3">
                                <label for="price" class="form-label">Giá tiền (VND/Tháng)</label>
                                <div class="d-flex align-items-center">
                                    <input type="text" class="form-control me-2" id="price-min" placeholder="Từ">
                                    <span>-</span>
                                    <input type="text" class="form-control ms-2" id="price-max" placeholder="Đến">
                                </div>
                            </div>

                            <hr>
                            <!-- Room Type Filter -->
                            <div class="mb-3">
                                <label for="room-type" class="form-label">Loại phòng</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="privateRoom">
                                    <label class="form-check-label" for="privateRoom">Khép kín</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="sharedRoom">
                                    <label class="form-check-label" for="sharedRoom">Không khép kín</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="miniApartment">
                                    <label class="form-check-label" for="miniApartment">Chung cư mini</label>
                                </div>
                            </div>

                            <hr>
                            <!-- Additional Features Filter -->
                            <div class="mb-3">
                                <label class="form-label">Tiện ích khác</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="fingerprintLock">
                                    <label class="form-check-label" for="fingerprintLock">Khóa vân tay</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="camera">
                                    <label class="form-check-label" for="camera">Camera an ninh</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="parking">
                                    <label class="form-check-label" for="parking">Chỗ để xe</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="fridge">
                                    <label class="form-check-label" for="fridge">Tủ lạnh</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="washingMachine">
                                    <label class="form-check-label" for="washingMachine">Máy giặt</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="stove">
                                    <label class="form-check-label" for="stove">Bếp</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="bed">
                                    <label class="form-check-label" for="bed">Giường</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="privateToilet">
                                    <label class="form-check-label" for="privateToilet">Vệ sinh khép kín</label>
                                </div>
                            </div>

                            <hr>
                            <!-- Rating Filter -->
                            <div class="mb-3">
                                <label for="rating" class="form-label">Đánh giá</label>
                                <div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating5" value="5">
                                        <label class="form-check-label" for="rating5">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating4" value="4">
                                        <label class="form-check-label" for="rating4">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            trở lên
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating3" value="3">
                                        <label class="form-check-label" for="rating3">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            trở lên
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating2" value="2">
                                        <label class="form-check-label" for="rating2">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            trở lên
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating1" value="1">
                                        <label class="form-check-label" for="rating1">
                                            <i class="fas fa-star text-warning"></i>
                                            trở lên
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <!-- Filter Button -->
                            <button type="submit" class="btn btn-success w-100">Lọc</button>
                        </div>
                    </form>
                </div>

                <!-- Results Section -->
                <div class="col-lg-9">
                    <div class="row mb-4 justify-content-between">
                        <div class="col-md-4">
                            <form class="d-flex" role="search">
                                <input class="form-control" type="search" placeholder="Tìm kiếm phòng trọ" aria-label="Search">
                                <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                        <div class="col-md-3 mr-5">
                            <select class="form-select" id="sortBy">
                                <option value="">Sắp xếp theo</option>
                                <option value="priceAsc">Giá tăng dần</option>
                                <option value="priceDesc">Giá giảm dần</option>
                                <option value="distanceAsc">Gần trường nhất</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach var="house" items="${houseList}">
                            <div class="col-md-4 mb-4">
                                <div class="card">
                                    <img src="${pageContext.request.contextPath}/images/${house.image}" class="card-img-top" alt="${house.houseName}">
                                    <div class="card-body">
                                        <h5 class="card-title"> ${house.houseName}</h5>
                                        <p class="card-text"><i class="fas fa-money-bill-wave"></i> chưa xử lý tiền phòng</p>
                                        <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${house.address}</p>
                                        <p class="card-text"><i class="fas fa-route"></i> ${house.distanceToSchool} km</p>
                                        <p class="card-text"><i class="fas fa-phone-alt"></i> chưa xử lý cách thức liên hệ</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>


        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
