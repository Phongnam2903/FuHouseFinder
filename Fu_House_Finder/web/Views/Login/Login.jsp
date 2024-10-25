<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>FU House Finder - Login</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/login/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Hiển thị Toast thông báo nếu có -->
        <c:if test="${not empty param.successMessage}">
            <div class="toast align-items-center text-bg-success border-0 position-fixed top-0 end-0 p-3" role="alert" aria-live="assertive" aria-atomic="true" id="successToast" style="z-index: 9999; color: #87bbf2">
                <div class="d-flex">
                    <div class="toast-body" style="color: red">
                        ${param.successMessage}
                    </div>
                    <button type="button" class="btn-close btn-close-black me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </c:if>
        <div class="container-fluid d-flex align-items-center vh-100">
            <!-- Left Section -->
            <div class="left-section d-flex flex-column justify-content-center align-items-center w-50 p-5">
                <img src="${pageContext.request.contextPath}/images/logo/logo_house_finder.jpg" alt="FU House Finder" class="logo-img mb-4">
                <h1 class="text-orange">FU HOUSE FINDER</h1>
                <p class="lead">Accommodation search application for FPT students</p>
            </div>

            <!-- Right Section - Login Form -->
            <div class="right-section d-flex flex-column justify-content-center align-items-center w-50 p-5">
                <h2 class="mb-4">Login</h2>
                <!-- Display General Error Messages -->
                <c:if test="${not empty loginError}">
                    <div class="alert alert-danger w-75 mb-3" style="border-radius: 20px">
                        ${loginError}
                    </div>
                </c:if>
                <c:if test="${not empty exceptionError}">
                    <div class="alert alert-danger w-75 mb-3" style="border-radius: 20px">
                        ${exceptionError}
                    </div>
                </c:if>
                <!-- Login Form -->
                <form action="${pageContext.request.contextPath}/login" method="POST" class="w-75">
                    <!-- Email input -->
                    <div class="form-group mb-3">
                        <input type="email" name="email" class="form-control form-control-lg" placeholder="Email or Phone Number" value="${param.email}" required>
                        <c:if test="${not empty emailError}">
                            <div class="error-message">${emailError}</div>
                        </c:if>
                        <c:if test="${not empty emailFormatError}">
                            <div class="error-message">${emailFormatError}</div>
                        </c:if>
                    </div>

                    <!-- Password input -->
                    <div class="form-group mb-3">
                        <input type="password" name="password" class="form-control form-control-lg" placeholder="Password" required>
                        <span id="togglePassword" onclick="togglePasswordVisibility()">👁 Show Password️</span>
                        <c:if test="${not empty passwordError}">
                            <div class="error-message">${passwordError}</div>
                        </c:if>
                    </div>

                    <!-- Login button -->
                    <button type="submit" name="submit" class="btn-login btn-lg mb-3 w-100 text-orange">Đăng nhập</button>
                </form>

                <!-- Social Login buttons -->
                <a href="loginGoogle" class="btn-google btn-lg mb-3 w-75"><i class="fab fa-google"></i> Login with Google</a>
                <a href="loginFacebook" class="btn-facebook btn-lg mb-3 w-75"><i class="fab fa-facebook"></i> Login with Facebook</a>

                <!-- Register link -->
                <div class="register-links d-flex justify-content-between w-75 mt-3">
                    <a href="#">Forgot Password</a>
                    <a href="./register">Sign Up</a>
                    <a href="homePage">Back</a>
                </div>

            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/login/login.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script>
                            document.addEventListener('DOMContentLoaded', function () {
                                var successToast = new bootstrap.Toast(document.getElementById('successToast'));
                                successToast.show();
                            });
        </script>
    </body>
</html>