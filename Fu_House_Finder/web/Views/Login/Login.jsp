<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FU House Finder - Login</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/login/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container-fluid d-flex align-items-center vh-100">
            <!-- Left Section -->
            <div class="left-section d-flex flex-column justify-content-center align-items-center w-50 p-5">
                <img src="${pageContext.request.contextPath}/images/logo/logo_house_finder.jpg" alt="FU House Finder" class="logo-img mb-4">
                <h1 class="text-orange">FU HOUSE FINDER</h1>
                <p class="lead">Ứng dụng tìm trọ dành cho sinh viên Đại học FPT</p>
            </div>

            <!-- Right Section - Login Form -->
            <div class="right-section d-flex flex-column justify-content-center align-items-center w-50 p-5">
                <h2 class="mb-4">Login</h2>

                <!-- Login Form -->
                <form action="${pageContext.request.contextPath}/login_user" method="POST" class="w-75">
                    <!-- Email input -->
                    <div class="form-group mb-3">
                        <input type="email" name="email" class="form-control form-control-lg" placeholder="Email" required>
                    </div>

                    <!-- Password input -->
                    <div class="form-group mb-3">
                        <input type="password" name="password" class="form-control form-control-lg" placeholder="Mật khẩu" required>
                        <span id="togglePassword" onclick="togglePasswordVisibility()">👁 Show Password️</span>
                    </div>

                    <!-- Login button -->
                    <button type="submit" name="submit" class="btn-login btn-lg mb-3 w-100 text-orange">Đăng nhập</button>
                </form>

                <!-- Social Login buttons -->
                <button class="btn-google btn-lg mb-3 w-75"><i class="fab fa-google"></i> Đăng nhập bằng Google</button>
                <button class="btn-facebook btn-lg mb-3 w-75"><i class="fab fa-facebook"></i> Đăng nhập bằng Facebook</button>

                <!-- Register link -->
                <a href="#" class="mt-3">Đăng ký tài khoản?</a>
            </div>
        </div>

    </body>
</html>
