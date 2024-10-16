<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Room Details</title>
        <!-- Bootstrap CSS (using version 5.1.3) -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .breadcrumb {
                margin: 20px 0;
            }

            .room-carousel img {
                width: 100%;
                height: 450px;
                object-fit: cover;
            }

            .room-description {
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 20px;
                margin-top: 20px;
                background-color: #f9f9f9;
            }

            .room-description h4 {
                text-align: center;
                margin-bottom: 20px;
            }

            .room-description .row div {
                margin-bottom: 10px;
            }

            .error-message {
                color: red;
                text-align: center;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Staff/Sidebar.jsp" %>
            <div id="page-content-wrapper">
                <!-- Navigation Bar in Page Content -->
                <%@include file="../Partials/Staff/Nav.jsp" %>
                <div class="container mt-4">
                    <!-- Display error message if any -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger error-message" role="alert">
                            ${error}
                        </div>
                    </c:if>

                    <!-- Breadcrumb Navigation -->
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="listhouseowner">List of Landlords</a></li>
                            <li class="breadcrumb-item"><a href="#">List of Rental House</a></li>
                            <li class="breadcrumb-item"><a href="#">List of Rooms</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Room</li>
                        </ol>
                    </nav>

                    <!-- Image Carousel -->
                    <div id="roomCarousel" class="carousel slide room-carousel" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="${pageContext.request.contextPath}/images/nha-tro3_9c3bd593-cf87-41e2-99d7-799c1fa4743d.jpg" alt="Room Image 1">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/images/nha-tro4_689fe492-c9ec-469f-be48-875c3cb5c446.jpg" alt="Room Image 2">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/images/nha-tro_4f58c2dd-71ab-43a1-b64f-3f0f7f79000b.jpg" alt="Room Image 3">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#roomCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#roomCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>

                    <!-- Room Description Box -->

                    <div class="room-description">
                        <h4 style="size: 28px">Room Description</h4>
                        <div class="row">
                            <div class="col-md-6">
                                <!-- Fridge Icon -->
                                <p><i class="fas fa-snowflake"></i> Fridge: 
                                    <c:choose>
                                        <c:when test="${roomDetails.fridge}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <!-- Landlord Living In House Icon -->
                                <p><i class="fas fa-user-tie"></i> Landlord Living in House: 
                                    <c:choose>
                                        <c:when test="${roomDetails.liveInHouseOwner}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <!-- Private Bathroom Icon -->
                                <p><i class="fas fa-bath"></i> Private Bathroom: 
                                    <c:choose>
                                        <c:when test="${roomDetails.closedToilet}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <!-- Washing Machine Icon -->
                                <p><i class="fas fa-washer"></i> Washing Machine: 
                                    <c:choose>
                                        <c:when test="${roomDetails.washingMachine}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            <div class="col-md-6">
                                <!-- Desk Icon -->
                                <p><i class="fas fa-desktop"></i> Desk: 
                                    <c:choose>
                                        <c:when test="${roomDetails.desk}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <!-- Kitchen Icon -->
                                <p><i class="fas fa-utensils"></i> Kitchen: 
                                    <c:choose>
                                        <c:when test="${roomDetails.kitchen}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <!-- Bed Icon -->
                                <p><i class="fas fa-bed"></i> Bed: 
                                    <c:choose>
                                        <c:when test="${roomDetails.bed}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>

                            <p class="text-center">Description: ${roomDetails.description}</p>
                        </div>

                    </div>
                </div>
            </div>
            <!-- JS Scripts -->
            <!-- Bootstrap JS (using version 5.1.3) -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
            <!-- Font Awesome JS -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
            <!-- Custom JS -->
            <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
