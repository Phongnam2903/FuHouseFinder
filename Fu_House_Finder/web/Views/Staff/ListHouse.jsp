<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>List of Houses</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/staff/litshouse.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Staff/Sidebar.jsp" %>
            <div id="page-content-wrapper">
                <!-- Navigation Bar in Page Content -->
                <%@include file="../Partials/Staff/Nav.jsp" %>

                <!-- Main Content -->
                <div class="container-fluid mt-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staffDashboard">Staff Dashboard</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List of Houses</li>
                        </ol>
                    </nav>

                    <div class="row mt-4 text-center">
                        <div class="col-md-2">
                            <div class="stats-box bg-light p-3 rounded">
                                <h4>31</h4>
                                <p>Total Houses</p>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="stats-box bg-light p-3 rounded">
                                <h4>73</h4>
                                <p>Total Rooms</p>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="stats-box bg-light p-3 rounded">
                                <h4>63</h4>
                                <p>Vacant Rooms</p>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="stats-box bg-light p-3 rounded">
                                <h4>155</h4>
                                <p>Total Slots</p>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="stats-box bg-light p-3 rounded">
                                <h4>81</h4>
                                <p>Vacant Slots</p>
                            </div>
                        </div>
                    </div>

                    <table class="table table-bordered table-hover mt-4">
                        <thead class="table-danger">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">House Name</th>
                                <th scope="col">Electricity Price (VND)</th>
                                <th scope="col">Water Price (VND)</th>
                                <th scope="col">Adults</th>
                                <th scope="col">Children</th>
                                <th scope="col">Vacant Rooms</th>
                                <th scope="col">Facilities</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Tam Le House</td>
                                <td>3,700</td>
                                <td>1,200</td>
                                <td>1</td>
                                <td>3</td>
                                <td>5</td>
                                <td>
                                    <i class="fas fa-wifi"></i>
                                    <i class="fas fa-tv"></i>
                                </td>
                            </tr>
                            <!-- Add more rows as needed -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <!-- Custom JS -->
        <script src="${pageContext.request.contextPath}/js/staff/staff.js"></script>
    </body>
</html>
