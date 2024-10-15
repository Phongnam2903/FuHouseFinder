<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Order</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
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
                    <!-- Breadcrumb -->
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List of Orders</li>
                        </ol>
                    </nav>
                    <!-- Info Section -->
                    <div class="row mb-3">
                        <h1 class="text-center">List Of Orders Accommodation</h1>
                        <label>Search</label>
                    </div>

                    <!-- Table Section -->
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">User Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Solve Date</th>
                                <th scope="col">Solve By</th>
                                <th scope="col">Status</th>
                                <th scope="col">Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orderList}" varStatus="status">
                                <tr>
                                    <th scope="row">${status.index + 1}</th>
                                    <td>${order.fullName}</td>
                                    <td>${order.email}</td>
                                    <td>${order.orderedDate}</td>
                                    <td>${order.solvedDate}</td>
                                    <td>${order.solvedBy}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.statusID == 1}">
                                                <span class="badge bg-success">Pending</span>
                                            </c:when>
                                            <c:when test="${order.statusID == 2}">
                                                <span class="badge bg-warning">Solved</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Cancelled</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-primary btn-sm">View</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- Pagination Section -->
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>'">
                                    <a class="page-link" href="${pageContext.request.contextPath}/listOrders?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
    </body>
</html>
