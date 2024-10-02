<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/house/DetailHouse.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../Partials/Header.jsp" %>

        <div class="container">
            <h2 class="text-center mb-4 mt-4">House Details</h2>

            <div class="d-flex justify-content-between mb-4">
                <a href="ListHouse" class="btn btn-secondary">< Back to House List</a>
                <a href="UpdateHouse?id=${house.id}" class="btn btn-secondary">Update House ></a>
            </div>

            <div class="row">
                <!-- Left Column -->
                <div class="col-md-6">
                    <div class="form-section">
                        <div class="mb-3">
                            <label class="form-label">House Name</label>
                            <p>${house.houseName}</p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Address</label>
                            <p>${house.address}</p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Monthly Power Price (VND)</label>
                            <p><fmt:formatNumber value="${house.powerPrice}" type="number" minFractionDigits="0" /></p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Monthly Water Price (VND)</label>
                            <p><fmt:formatNumber value="${house.waterPrice}" type="number" minFractionDigits="0" /></p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Monthly Service Price (VND)</label>
                            <p><fmt:formatNumber value="${house.otherServicePrice}" type="number" minFractionDigits="0" /></p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Utilities</label><br>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="fingerPrintLock" name="fingerPrintLock"
                                       ${house.fingerPrintLock ? 'checked' : ''}>
                                <label class="form-check-label" for="fingerPrintLock">Fingerprint Lock</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="camera" name="camera"
                                       ${house.camera ? 'checked' : ''}>
                                <label class="form-check-label" for="camera">Security Camera</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" class="form-check-input" id="parking" name="parking"
                                       ${house.parking ? 'checked' : ''}>
                                <label class="form-check-label" for="parking">Parking Space</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Distance to School (KM)</label>
                            <p>${house.distanceToSchool}</p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label>
                            <p>${house.description}</p>
                        </div>
                    </div>
                </div>

                <!-- Right Column -->
                <div class="col-md-6">
                    <div class="form-section">
                        <div class="mb-3">
                            <label class="form-label">House Image (1)</label>
                            <c:if test="${not empty imageList[0]}">
                                <br/>
                                <img class="mb-3" src="${pageContext.request.contextPath}/images/${imageList[0]}" alt="Image 1" width="650">
                            </c:if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">House Image (2)</label>
                            <c:if test="${not empty imageList[1]}">
                                <br/>
                                <img class="mb-3" src="${pageContext.request.contextPath}/images/${imageList[1]}" alt="Image 2" width="650">
                            </c:if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">House Image (3)</label>
                            <c:if test="${not empty imageList[2]}">
                                <br/>
                                <img class="mb-3" src="${pageContext.request.contextPath}/images/${imageList[2]}" alt="Image 3" width="650">
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../Partials/Footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>

