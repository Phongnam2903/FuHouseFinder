<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/staff/feedbackdetails.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Staff/Sidebar.jsp" %>
            <div id="page-content-wrapper">
                <!-- Navigation Bar -->
                <%@include file="../Partials/Staff/Nav.jsp" %>
                <div class="container mt-4">
                    <!-- Feedback Details Section -->
                    <h4 class="mb-3 text-center">Feedback Details</h4>

                    <!-- Feedback Information -->
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Title: ${feedback.title}</h5>
                            <p class="card-text"><strong>Renter Name:</strong> ${feedback.renterName}</p>
                            <p class="card-text"><strong>Email:</strong> ${feedback.renterEmail}</p>
                            <p class="card-text"><strong>House Name:</strong> ${feedback.houseName}</p>
                            <p class="card-text"><strong>Room Number:</strong> ${feedback.roomNumber}</p>
                            <p class="card-text"><strong>Description:</strong> ${feedback.description}</p>
                            <p class="card-text"><strong>Created Date:</strong> ${feedback.createdDate}</p>
                            <p class="card-text"><strong>Sent Time:</strong> ${feedback.sentTime}</p>
                            <p class="card-text"><strong>Status:</strong> ${feedback.status}</p>
                            

                            <!-- Buttons for feedback actions -->
                            <div class="d-flex justify-content-end">
                                <a href="ListFeedback" class="btn btn-secondary">
                                    <i class="fas fa-arrow-left"></i> Back to Feedback List
                                </a>
                            </div>
                            <div class="d-flex justify-content-between">
                                <a href="#" class="btn btn-warning">
                                    <i class="fas fa-tools"></i> Edit
                                </a>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>

    </body>
</html>
