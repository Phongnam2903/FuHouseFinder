<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
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
                            <h2 class="text-white mb-0">FILTER RESULTS</h2>
                            <button type="reset" class="btn btn-link text-white mb-0">Reset Filters</button>
                        </div>
                        <div class="card p-3">
                            <!-- Distance Filter -->
                            <div class="mb-3">
                                <label for="distance" class="form-label">Distance to School</label>
                                <div class="d-flex align-items-center">
                                    <input type="text" class="form-control me-2" id="distance-km" placeholder="From (km)">
                                    <span>-</span>
                                    <input type="text" class="form-control ms-2" id="distance-miles" placeholder="To (km)">
                                </div>
                            </div>

                            <hr>
                            <!-- Price Filter -->
                            <div class="mb-3">
                                <label for="price" class="form-label">Price (VND/Month)</label>
                                <div class="d-flex align-items-center">
                                    <input type="text" class="form-control me-2" id="price-min" placeholder="From">
                                    <span>-</span>
                                    <input type="text" class="form-control ms-2" id="price-max" placeholder="To">
                                </div>
                            </div>

                            <hr>
                            <!-- Room Type Filter -->
                            <div class="mb-3">
                                <label for="room-type" class="form-label">Room Type</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="privateRoom">
                                    <label class="form-check-label" for="privateRoom">Private</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="sharedRoom">
                                    <label class="form-check-label" for="sharedRoom">Shared</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="miniApartment">
                                    <label class="form-check-label" for="miniApartment">Mini Apartment</label>
                                </div>
                            </div>

                            <hr>
                            <!-- Additional Features Filter -->
                            <div class="mb-3">
                                <label class="form-label">Additional Features</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="fingerprintLock">
                                    <label class="form-check-label" for="fingerprintLock">Fingerprint Lock</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="camera">
                                    <label class="form-check-label" for="camera">Security Camera</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="parking">
                                    <label class="form-check-label" for="parking">Parking Space</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="fridge">
                                    <label class="form-check-label" for="fridge">Refrigerator</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="washingMachine">
                                    <label class="form-check-label" for="washingMachine">Washing Machine</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="stove">
                                    <label class="form-check-label" for="stove">Stove</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="bed">
                                    <label class="form-check-label" for="bed">Bed</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="privateToilet">
                                    <label class="form-check-label" for="privateToilet">Private Toilet</label>
                                </div>
                            </div>

                            <hr>
                            <!-- Rating Filter -->
                            <div class="mb-3">
                                <label for="rating" class="form-label">Rating</label>
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
                                            and above
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating3" value="3">
                                        <label class="form-check-label" for="rating3">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating2" value="2">
                                        <label class="form-check-label" for="rating2">
                                            <i class="fas fa-star text-warning"></i>
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="rating" id="rating1" value="1">
                                        <label class="form-check-label" for="rating1">
                                            <i class="fas fa-star text-warning"></i>
                                            and above
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <!-- Filter Button -->
                            <button type="submit" class="btn btn-success w-100">Filter</button>
                        </div>
                    </form>
                </div>

                <!-- Results Section -->
                <div class="col-lg-9">
                    <div class="row mb-4 justify-content-between">
                        <div class="col-md-4">
                            <form class="d-flex" role="search">
                                <input class="form-control" type="search" placeholder="Search for rooms" aria-label="Search">
                                <button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                        <div class="col-md-4">
                            <select class="form-select" id="sortBy">
                                <option value="">Sort By</option>
                                <option value="priceAsc">Price: Low to High</option>
                                <option value="priceDesc">Price: High to Low</option>
                                <option value="distanceAsc">Closest to School</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach var="house" items="${houseList}">
                            <div class="col-md-4 mb-4">
                                <a href="${pageContext.request.contextPath}/houseDetail?id=${house.id}" style="text-decoration: none; color: inherit;">
                                    <div class="card" style="cursor: pointer;">
                                        <img style="height: 300px; max-width: 100%" src="${pageContext.request.contextPath}/images/${house.image}" class="card-img-top" alt="${house.houseName}">

                                        <div class="card-body">
                                            <h5 class="card-title"> ${house.houseName}</h5>
                                            <p class="card-text"><i class="fas fa-money-bill-wave"></i>
                                                <fmt:formatNumber value="${house.minPrice}" type="number" minFractionDigits="0" /> VND - 
                                                <fmt:formatNumber value="${house.maxPrice}" type="number" minFractionDigits="0" /> VND
                                            </p>
                                            <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${house.address}</p>
                                            <p class="card-text"><i class="fas fa-route"></i> ${house.distanceToSchool} km</p>
                                        </div>
                                    </div>
                                </a>
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
