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
                    <form action="register" method="post" id="registerForm">
                        <div class="mb-3">
                            <input type="text" name="fname" class="form-control" id="firstName" placeholder="First name">
                            <div id="firstNameError" class="error-message"></div>
                        </div>
                        <div class="mb-3">
                            <input type="text" name="lname" class="form-control" id="lastName" placeholder="Last name">
                            <div id="lastNameError" class="error-message"></div>
                        </div>
                        <div class="mb-3">
                            <input type="text" name="phone" class="form-control" id="phone" placeholder="Phone number">
                            <div id="phoneError" class="error-message"></div>
                        </div>
                        <div class="mb-3">
                            <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                            <div id="emailError" class="error-message"></div>
                        </div>
                        <!-- Password field with eye icon -->
                        <div class="mb-3 position-relative">
                            <input type="password" name="pass" class="form-control" id="password" placeholder="Password">
                            <span class="position-absolute top-50 end-0 translate-middle-y me-3" onclick="togglePassword('password', 'togglePasswordIcon')">
                                <i class="bi bi-eye-slash" id="togglePasswordIcon"></i>
                            </span>
                            <div id="passwordError" class="error-message"></div>
                        </div>
                        <!-- Confirm Password field with eye icon -->
                        <div class="mb-3 position-relative">
                            <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password">
                            <span class="position-absolute top-50 end-0 translate-middle-y me-3" onclick="togglePassword('confirmPassword', 'toggleConfirmPasswordIcon')">
                                <i class="bi bi-eye-slash" id="toggleConfirmPasswordIcon"></i>
                            </span>
                            <div id="confirmPasswordError" class="error-message"></div>
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
        <script>
                                function togglePassword(fieldId, iconId) {
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

                                function trimInput(input) {
                                    return input.replace(/\s+/g, ' ').trim();
                                }

                                function showError(field, errorId, message) {
                                    field.classList.add('error-border');
                                    document.getElementById(errorId).innerText = message;
                                }

                                function clearError(field, errorId) {
                                    field.classList.remove('error-border');
                                    document.getElementById(errorId).innerText = '';
                                }

                                function validateForm() {
                                    let isValid = true;

                                    const firstName = document.getElementById('firstName');
                                    const lastName = document.getElementById('lastName');
                                    const phone = document.getElementById('phone');
                                    const email = document.getElementById('email');
                                    const password = document.getElementById('password');
                                    const confirmPassword = document.getElementById('confirmPassword');

                                    // Clear previous errors
                                    clearError(firstName, 'firstNameError');
                                    clearError(lastName, 'lastNameError');
                                    clearError(phone, 'phoneError');
                                    clearError(email, 'emailError');
                                    clearError(password, 'passwordError');
                                    clearError(confirmPassword, 'confirmPasswordError');

                                    // Validate first name
                                    if (trimInput(firstName.value) === '') {
                                        showError(firstName, 'firstNameError', 'First name is required.');
                                        isValid = false;
                                    }

                                    // Validate last name
                                    if (trimInput(lastName.value) === '') {
                                        showError(lastName, 'lastNameError', 'Last name is required.');
                                        isValid = false;
                                    }

                                    // Validate phone
                                    if (trimInput(phone.value) === '') {
                                        showError(phone, 'phoneError', 'Phone number is required.');
                                        isValid = false;
                                    }

                                    // Validate email
                                    if (trimInput(email.value) === '') {
                                        showError(email, 'emailError', 'Email is required.');
                                        isValid = false;
                                    }

                                    // Validate password
                                    if (trimInput(password.value) === '') {
                                        showError(password, 'passwordError', 'Password is required.');
                                        isValid = false;
                                    }

                                    // Validate confirm password
                                    if (trimInput(confirmPassword.value) === '') {
                                        showError(confirmPassword, 'confirmPasswordError', 'Confirm password is required.');
                                        isValid = false;
                                    } else if (password.value !== confirmPassword.value) {
                                        showError(confirmPassword, 'confirmPasswordError', 'Passwords do not match.');
                                        isValid = false;
                                    }

                                    return isValid;
                                }

                                document.getElementById('registerForm').addEventListener('submit', function (event) {
                                    event.preventDefault(); // Ng?n ch?n hành ??ng g?i m?c ??nh c?a bi?u m?u

                                    if (validateForm()) {
                                        this.submit(); // G?i bi?u m?u n?u validateForm tr? v? true
                                    }
                                });

        </script>
    </body>
</html>