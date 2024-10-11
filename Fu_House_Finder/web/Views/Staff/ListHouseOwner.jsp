<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ListHouseOwner</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Staff/Sidebar.jsp" %>
            <div id="page-content-wrapper">
                <!-- Navigation Bar in Page Content -->
                <%@include file="../Partials/Staff/Nav.jsp" %>
                <!-- Main Content -->
                <div class="container">
                    <section class="container my-5">
                        <!-- Statistics Section -->
                        <div class="row text-center mb-4">
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Total Houses</h4>
                                    <h3 class="stat-number">${totalHouse}</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Total Rooms</h4>
                                    <h3 class="stat-number">${totalRoom}</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Empty Rooms</h4>
                                    <h3 class="stat-number">${totalRoomEmpty}</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Total Landlord</h4>
                                    <h3 class="stat-number">${totalLandlord}</h3>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="stat-card p-3 border rounded shadow-sm">
                                    <h4 class="stat-title">Empty Spaces</h4>
                                    <h3 class="stat-number">81</h3>
                                </div>
                            </div>
                        </div>
                        <!-- Table Header -->
                        <div class="row mb-3">
                            <div class="col">
                                <p class="small text-muted">You are viewing ${totalLandlord} landlords with ${totalHouse} rental houses.</p>
                                <h2 class="text-center">Landlord List</h2>
                            </div>
                        </div>
                        <!-- Landlord List Table -->
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered text-center">
                                <thead class="table-light">
                                    <tr>
                                        <th>Name</th>
                                        <th>Phone Number</th>
                                        <th>Total Houses</th>
                                        <th>Total Rooms</th>
                                        <th>Empty Rooms</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="acc" items="${listAccLandlord}" varStatus="status">
                                        <tr>
                                            <td>${acc.username}</td>
                                            <td>${acc.phone}</td>
                                            <td>${acc.totalHouses}</td>
                                            <td>${acc.totalRooms}</td>
                                            <td>${acc.emptyRooms}</td>
                                            <td>
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input toggle-switch" type="checkbox" id="toggleActive${acc.id}" ${acc.statusID == 1 ? 'checked' : ''}>
                                                    <label class="form-check-label" for="toggleActive${acc.id}">
                                                        ${acc.statusID == 1 ? 'Active' : 'Inactive'}
                                                    </label>
                                                </div>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- Pagination Start -->
                            <nav aria-label="Phân trang">
                                <ul class="pagination justify-content-center">
                                    <!-- Nút Previous -->
                                    <c:if test="${currentPage > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="${pageContext.request.contextPath}/listhouseowner?page=${currentPage - 1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo; Previous</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${currentPage == 1}">
                                        <li class="page-item disabled">
                                            <span class="page-link" aria-label="Previous">
                                                <span aria-hidden="true">&laquo; Previous</span>
                                            </span>
                                        </li>
                                    </c:if>

                                    <!-- Các số trang -->
                                    <c:forEach var="i" begin="1" end="${totalPage}">
                                        <c:choose>
                                            <c:when test="${i == currentPage}">
                                                <li class="page-item active"><span class="page-link">${i}</span></li>
                                                </c:when>
                                                <c:otherwise>
                                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/listhouseowner?page=${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                    <!-- Nút Next -->
                                    <c:if test="${currentPage < totalPage}">
                                        <li class="page-item">
                                            <a class="page-link" href="${pageContext.request.contextPath}/listhouseowner?page=${currentPage + 1}" aria-label="Next">
                                                <span aria-hidden="true">Next &raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${currentPage == totalPage}">
                                        <li class="page-item disabled">
                                            <span class="page-link" aria-label="Next">
                                                <span aria-hidden="true">Next &raquo;</span>
                                            </span>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                            <!-- Pagination End -->
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    </body>
</html>
