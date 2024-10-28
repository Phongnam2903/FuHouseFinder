<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FU House Finder - Reset Password</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Boxicons for Icons -->
        <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">

        <style>
            /* Custom Styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
            }
            .container-fluid {
                max-width: 900px;
            }
            .left-panel {
                background-color: #fff;
                text-align: center;
                padding: 2rem;
                border-right: 1px solid #ddd;
            }
            .left-panel img {
                width: 150px;
            }
            .right-panel {
                background-color: #f8f9fa;
                padding: 3rem;
            }
            .form-login {
                background-color: #ffffff;
                border-radius: 8px;
                padding: 2rem;
            }
            .form-login .border-bottom {
                padding-bottom: 1rem;
            }
            .btn-submit {
                background-color: #dc3545;
                color: #fff;
                border: none;
                padding: 0.75rem 1.5rem;
                font-size: 1.2rem;
                font-weight: bold;
                transition: background 0.3s ease;
            }
            .btn-submit:hover {
                background-color: #c82333;
            }
            .message {
                font-size: 15px;
                color: #dc3545;
                margin-top: 0.5rem;
            }
        </style>
    </head>

    <body>
        <div class="container-fluid vh-100 d-flex align-items-center justify-content-center">
            <div class="row shadow-lg" style="width: 90%; max-width: 800px; height: 80%;">

                <!-- Left Panel -->
                <div class="col-md-5 left-panel d-flex flex-column align-items-center justify-content-center">
                    <img src="${pageContext.request.contextPath}/images/logo/logo_house_finder.jpg" alt="FU House Finder Logo" class="img-fluid mb-3">
                    <h2 class="text-danger">FU HOUSE FINDER</h2>
                    <p class="text-muted text-center">Accommodation search application for FPT students</p>
                </div>

                <!-- Right Panel -->
                <div class="col-md-7 right-panel">
                    <h3 class="text-center mb-4 text-primary">Reset Your Password</h3>

                    <form action="${pageContext.request.contextPath}/resetPassword" method="post" class="form-login mx-auto">
                        <!-- Verification Code -->
                        <div class="mb-4">
                            <label for="code" class="fw-bold">Verification Code</label>
                            <div class="input-group mt-2">
                                <span class="input-group-text bg-light border-0"><i class='bx bx-user fs-4'></i></span>
                                <input type="text" name="code" id="code" placeholder="Enter Code" class="form-control border-0">
                            </div>
                            <c:if test="${not empty messageCode}">
                                <div class="message">${messageCode}</div>
                            </c:if>
                        </div>

                        <!-- New Password -->
                        <div class="mb-4">
                            <label for="newPassword" class="fw-bold">New Password</label>
                            <div class="input-group mt-2">
                                <span class="input-group-text bg-light border-0"><i class='bx bx-lock fs-4'></i></span>
                                <input type="password" name="newPassword" id="newPassword" placeholder="New Password" class="form-control border-0">
                            </div>
                            <c:if test="${not empty messageNewPassword}">
                                <div class="message">${messageNewPassword}</div>
                            </c:if>
                            <c:if test="${not empty messagePattern}">
                                <div class="message">${messagePattern}</div>
                            </c:if>
                        </div>

                        <!-- Confirm New Password -->
                        <div class="mb-4">
                            <label for="confirmPassword" class="fw-bold">Confirm New Password</label>
                            <div class="input-group mt-2">
                                <span class="input-group-text bg-light border-0"><i class='bx bx-lock fs-4'></i></span>
                                <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" class="form-control border-0">
                            </div>
                            <c:if test="${not empty messageConfirmPassword}">
                                <div class="message">${messageConfirmPassword}</div>
                            </c:if>
                            <c:if test="${not empty messageMatchPassword}">
                                <div class="message">${messageMatchPassword}</div>
                            </c:if>
                        </div>

                        <!-- General Message for any other errors -->
                        <c:if test="${not empty message}">
                            <div class="message text-center">${message}</div>
                        </c:if>

                        <!-- Submit Button -->
                        <div class="d-grid mt-4">
                            <button type="submit" name="btnreset" class="btn-submit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
