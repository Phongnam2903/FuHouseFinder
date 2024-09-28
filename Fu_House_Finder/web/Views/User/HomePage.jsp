<%-- 
    Document   : HomePage
    Created on : Sep 18, 2024, 3:38:07 PM
    Author     : xuxum
--%>

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
    </head>
    <body>

        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>

        <!-- Search Bar -->
        <section class="container my-4">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Tìm kiếm phòng trọ" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </div>
        </section>

        <!-- Main Content -->
        <section class="container my-4">
            <div class="row">
                <!-- Filter Section -->
                <div class="col-lg-4 mb-4">
                    <div class="card p-3">
                        <h2 class="text-success">Lọc phòng trọ</h2>
                        <form>
                            <!-- Location Filter -->
                            <div class="mb-3">
                                <label for="location" class="form-label">Cơ sở</label>
                                <select class="form-select" id="location">
                                    <option value="">Chọn cơ sở</option>
                                    <option value="hanoi">Hà Nội</option>
                                    <option value="danang">Đà Nẵng</option>
                                </select>
                            </div>
                            <!-- Radius Filter -->
                            <div class="mb-3">
                                <label for="radius" class="form-label">Bán kính: 0-10km</label>
                                <input type="range" class="form-range" id="radius" min="0" max="10" step="1">
                            </div>
                            <!-- Price Filter -->
                            <div class="mb-3">
                                <label for="price" class="form-label">Khoảng giá: 0 - 20.000.000đ</label>
                                <input type="range" class="form-range" id="price" min="0" max="20000000" step="1000000">
                            </div>
                            <!-- District, Ward, Village Filter -->
                            <div class="mb-3">
                                <label for="district" class="form-label">Huyện</label>
                                <select class="form-select" id="district">
                                    <option value="">Chọn huyện</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="ward" class="form-label">Xã</label>
                                <select class="form-select" id="ward">
                                    <option value="">Chọn xã</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="village" class="form-label">Thôn</label>
                                <select class="form-select" id="village">
                                    <option value="">Chọn thôn</option>
                                </select>
                            </div>
                            <!-- Extra Filters -->
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="wifi">
                                <label class="form-check-label" for="wifi">Wifi</label>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="ac">
                                <label class="form-check-label" for="ac">Điều hòa</label>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="heater">
                                <label class="form-check-label" for="heater">Nóng lạnh</label>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="fingerprintLock">
                                <label class="form-check-label" for="fingerprintLock">Khóa vân tay</label>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="camera">
                                <label class="form-check-label" for="camera">Camera an ninh</label>
                            </div>
                            <!-- Filter Button -->
                            <button type="submit" class="btn btn-success w-100">Lọc</button>
                        </form>
                    </div>
                </div>

                <!-- Results Section -->
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-md-6 mb-4">
                            <div class="card">
                                <img src="../../images/house/hoalacApartment.jpg" class="card-img-top" alt="Hòa Lạc Apartment">
                                <div class="card-body">
                                    <h5 class="card-title">Hòa Lạc Apartment</h5>
                                    <p class="card-text"><i class="fas fa-money-bill-wave"></i> 2.000.000 - 5.000.000đ</p>
                                    <p class="card-text"><i class="fas fa-map-marker-alt"></i> Thôn 8, Thạch Hòa, Thạch Thất</p>
                                    <p class="card-text"><i class="fas fa-route"></i> 3.5km</p>
                                    <p class="card-text"><i class="fas fa-phone-alt"></i> 0896989996</p>
                                </div>
                            </div>
                        </div>
                        <!-- Bạn có thể thêm nhiều thẻ <div class="col-md-6"> cho các kết quả khác -->
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
