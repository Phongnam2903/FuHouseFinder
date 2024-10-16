<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Rental Houses</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                <div class="container mt-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="listhouseowner">List of Landlords</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List of Rental Houses</li>
                        </ol>
                    </nav>

                    <div class="row host-info">
                        <div class="col-md-6">
                            <p><strong>Full Name:</strong> ${landlordDetail.username}</p>
                            <p><strong>Phone Number:</strong> ${landlordDetail.phone}</p>
                            <p><strong>Address:</strong> ${landlordDetail.address}</p>
                        </div>
                        <div class="col-md-6 text-right summary-info">
                            <p><strong>Total Houses:</strong> ${landlordDetail.totalHouses} houses</p>
                            <p><strong>Total Rooms:</strong> ${landlordDetail.totalRooms} rooms</p>
                            <p><strong>Total Empty Rooms:</strong> ${landlordDetail.emptyRooms} rooms</p>
                        </div>
                    </div>

                    <h3 class="text-center">List of Rental Houses</h3>

                    <table class="table table-bordered">
                        <thead class="thead-light">
                            <tr>
                                <th>No.</th>
                                <th>House Name</th>
                                <th>Electricity Price (VND)</th>
                                <th>Water Price (VND)</th>
                                <th>Address</th>
                                <th>Utilities</th>
                            </tr>
                        </thead>
                        <c:forEach var="houseOwner" items="${listHouses}">
                            <tbody>
                                <tr>
                                    <td>${houseOwner.id}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/listOfRoom?id=${houseOwner.id}"">${houseOwner.houseName}</a>
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${houseOwner.powerPrice}" type="number" minFractionDigits="0" maxFractionDigits="0" groupingUsed="true"/> VND
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${houseOwner.waterPrice}" type="number" minFractionDigits="0" maxFractionDigits="0" groupingUsed="true"/> VND
                                    </td>
                                    <td>${houseOwner.address}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${houseOwner.fingerPrintLock || houseOwner.camera || houseOwner.parking}">
                                                <c:if test="${houseOwner.fingerPrintLock}">
                                                    <i class="fas fa-fingerprint" title="Fingerprint Lock" style="font-size: 2rem;
                                                       margin-right: 20px;"></i>
                                                </c:if>
                                                <c:if test="${houseOwner.camera}">
                                                    <i class="fas fa-video" title="Security Camera" style="font-size: 2rem;
                                                       margin-right: 20px;"></i>
                                                </c:if>
                                                <c:if test="${houseOwner.parking}">
                                                    <i class="fas fa-parking" title="Parking" style="font-size: 2rem;"></i>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <p>No utilities available</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>         
                            </tbody>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </body>
</html>
