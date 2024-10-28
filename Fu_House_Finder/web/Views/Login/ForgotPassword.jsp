<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FU House Finder - Forgot Password</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <div class="row shadow-lg" style="width: 80%; height: 80%;">
                <!-- Left panel -->
                <div class="col-md-6 d-flex flex-column justify-content-center align-items-center bg-white border-end">
                    <img src="${pageContext.request.contextPath}/images/logo/logo_house_finder.jpg" alt="FU House Finder Logo" class="img-fluid" style="width: 150px; object-fit: contain">
                    <h2 class="mt-3">FU HOUSE FINDER</h2>
                    <p class="text-muted">Accommodation search application for FPT student</p>
                </div>
                <!-- Right panel -->
                <div class="col-md-6 d-flex flex-column justify-content-center bg-light p-4">
                    <h2 class="text-center mb-4">Forgot Password</h2>
                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                    <% if (errorMessage != null) { %>
                    <div class="alert alert-danger">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                    <form action="forgotPassword" method="post" id="forgotPasswordForm">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                            <input type="email" name="email" class="form-control" id="email" placeholder="Enter your email" 
                                   value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
                            <div class="error-message">
                                <%= request.getAttribute("errorEmailMessage") != null ? request.getAttribute("errorEmailMessage") : "" %>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning w-100" style="color: white">Send Reset Link</button>
                    </form>
                    <a href="./login" class="btn btn-outline-secondary w-100 mt-3">Back to Login</a>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
