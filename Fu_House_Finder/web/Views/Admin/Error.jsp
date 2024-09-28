<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <link href="${pageContext.request.contextPath}/css/error/errorAdmin.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/adminAcc.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/adminCreateAcc.css" rel="stylesheet" type="text/css"/>


    </head>
    <body>
        <div class="error-container">

            <h1>Error</h1>

            <!-- Hiển thị thông báo lỗi được truyền từ servlet -->
            <p>${error}</p>

            <!-- Nút để quay lại trang trước hoặc trang danh sách tài khoản -->
            <a href="${pageContext.request.contextPath}/viewAccountList" class="back-link">Go Back to Account List</a>
        </div>
    </body>
</html>
