<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>House Details</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/HouseDetail.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <!-- Header -->
        <%@include file="../Partials/Header.jsp" %>

        <!-- Main Content -->
        <div class="container mt-4">
            <!-- House Image and Owner Info -->
            <div class="row">
                <div class="d-flex">
                    <a href="homePage" style="color: #f44336;">Home Page</a>
                    >
                    <p>House Detail
                </div>
                <!-- House Image -->
                <div class="col-lg-8">
                    <div id="houseImageCarousel" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach var="image" items="${images}" varStatus="status">
                                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <img style="height: 600px; max-width: 100%;" src="${pageContext.request.contextPath}/images/${image}" class="d-block w-100 img-fluid" alt="${image}">
                                </div>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#houseImageCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#houseImageCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>

                <!-- Owner Info -->
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="card-title" style="text-align: center;">Owner Information</h3>
                            <p class="card-text">
                                <strong>Name:</strong> Tâm Lê <br>
                                <strong>Phone Number:</strong> <a href="tel:0123456789">Click để hiện số</a> <br>
                                <strong>Address:</strong> ${house.address}
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title" style="text-align: center;">User Reviews</h3>
                            <label for="comment" class="form-label">Comment</label>
                            <p class="card-text">No reviews yet</p>
                            <form>
                                <div class="mb-3">
                                    <label for="comment" class="form-label">Write a comment</label>
                                    <textarea class="form-control" id="comment" rows="2"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="rating" class="form-label">Rating</label>
                                    <div class="star-rating">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                </div>
                                <div><button type="submit" class="btn btn-secondary">Post</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- House Details and Ratings -->
            <div class="row mt-4">
                <!-- House Details -->
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title" style="text-align: center;">${house.houseName}</h2>
                            <p class="card-text">
                                <strong>Electricity Price:</strong> <fmt:formatNumber value="${house.powerPrice}" type="number" minFractionDigits="0" />VND/KWh <br>
                                <strong>Water Price:</strong> <fmt:formatNumber value="${house.waterPrice}" type="number" minFractionDigits="0" />VND/m3 <br>
                                <strong>Service Price:</strong> <fmt:formatNumber value="${house.otherServicePrice}" type="number" minFractionDigits="0" />đ/month <br>
                                <strong>Address:</strong> ${house.address} <br>
                                <strong>Other Information:</strong> ${house.description}
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Room List -->
            <div class="row mt-4">
                <div class="col-lg-12 mb-4">
                    <h2 class="mb-3" style="text-align: center;">Available Rooms List</h2>
                    <p><strong>Total Fully Available Rooms:</strong> 0 phòng</p>
                    <p><strong>Total Partially Available Rooms:</strong> 3 phòng</p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Room</th>
                                <th>Room Price</th>
                                <th>Utilities</th>
                                <th>Room Type</th>
                                <th>Area</th>
                                <th>Number of Occupants</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>101</td>
                                <td>2.900.000</td>
                                <td class="room-info-item">
                                    <i class="fas fa-wifi room-info-icon"></i>
                                    <i class="fas fa-fan room-info-icon"></i>
                                    <i class="fas fa-bath room-info-icon"></i>
                                </td>
                                <td>Không khép kín</td>
                                <td>3 m²</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>102</td>
                                <td>2.900.000</td>
                                <td class="room-info-item">
                                    <i class="fas fa-wifi room-info-icon"></i>
                                    <i class="fas fa-fan room-info-icon"></i>
                                    <i class="fas fa-bath room-info-icon"></i>
                                </td>
                                <td>Không khép kín</td>
                                <td>3 m²</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>103</td>
                                <td>2.900.000</td>
                                <td class="room-info-item">
                                    <i class="fas fa-wifi room-info-icon"></i>
                                    <i class="fas fa-fan room-info-icon"></i>
                                    <i class="fas fa-bath room-info-icon"></i>
                                </td>
                                <td>Không khép kín</td>
                                <td>3 m²</td>
                                <td>2</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Floating Button -->
        <a href="#" class="btn btn-danger btn-lg btn-danger-custom"><i class="fas fa-flag"></i></a>

        <!-- Footer -->
        <%@include file="../Partials/Footer.jsp" %>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
