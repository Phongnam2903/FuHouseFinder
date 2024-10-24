<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FU House Finder - Register</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            .error-border {
                border-color: red;
            }
            .error-message {
                color: red;
                font-size: 0.875em;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid vh-100 d-flex justify-content-center align-items-center">
            <div class="row shadow-lg" style="width: 900px; height: 500px;">
                <!-- Left panel -->
                <div class="col-md-6 d-flex flex-column justify-content-center align-items-center bg-white border-end">
                    <img src="./images/logo/logo_house_finder.jpg" alt="FU House Finder Logo" class="img-fluid" style="width: 150px; object-fit: contain">
                    <h2 class="mt-3">FU HOUSE FINDER</h2>
                    <p class="text-muted">Accommodation search application for FPT student</p>
                </div>
                <!-- Right panel -->
                <div class="col-md-6 d-flex flex-column justify-content-center bg-light p-4">
                    <h2 class="text-center mb-4">Register</h2>
                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                    <% if (errorMessage != null) { %>
                    <div class="alert alert-danger">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                    <form action="register" method="post" id="registerForm">
                        <div class="mb-3">
                            <input type="text" name="fname" class="form-control" id="firstName" placeholder="First name">
                            <div class="error-message">
                                <%= request.getAttribute("errorfNameMessage") != null ? request.getAttribute("errorfNameMessage") : "" %>
                            </div>
                        </div>
                        <div class="mb-3">
                            <input type="text" name="lname" class="form-control" id="lastName" placeholder="Last name">
                            <div class="error-message">
                                <%= request.getAttribute("errorlNameMessage") != null ? request.getAttribute("errorlNameMessage") : "" %>
                            </div>
                        </div>
                        <div class="mb-3">
                            <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                            <div class="error-message">
                                <%= request.getAttribute("errorEmailMessage") != null ? request.getAttribute("errorEmailMessage") : "" %>
                            </div>
                        </div>
                        <div class="mb-3">
                            <input type="text" name="phone" class="form-control" id="phone" placeholder="Phone number">
                            <div class="error-message">
                                <%= request.getAttribute("errorPhoneMessage") != null ? request.getAttribute("errorPhoneMessage") : "" %>
                            </div>
                        </div>
                        <div class="mb-3 position-relative">
                            <input type="password" name="pass" class="form-control" id="password" placeholder="Password">
                            <div class="error-message">
                                <%= request.getAttribute("errorPasswordMessage") != null ? request.getAttribute("errorPasswordMessage") : "" %>
                            </div>
                        </div>
                        <div class="mb-3 position-relative">
                            <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password">
                            <div class="error-message">
                                <%= request.getAttribute("errorConfirmMessage") != null ? request.getAttribute("errorConfirmMessage") : "" %>
                            </div>
                        </div>
                        <button type="submit" name="btnRegister" class="btn btn-warning w-100" style="color: white">Register</button>
                    </form>
                    <a href="./login" class="btn btn-outline-secondary w-100 mt-3">Already have an account</a>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and Bootstrap Icons -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
        <script>function togglePassword(fieldId, iconId) {
                const passwordField = document.getElementById(fieldId);
                const toggleIcon = document.getElementById(iconId);


                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    toggleIcon.classList.remove("bi-eye-slash");
                    toggleIcon.classList.add("bi-eye");
                } else {

                    passwordField.type = "password";
                    toggleIcon.classList.remove("bi-eye");
                    toggleIcon.classList.add("bi-eye-slash");
                }
            }
        </script>
    </body>
</html>
