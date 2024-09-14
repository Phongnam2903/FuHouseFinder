<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tìm Trọ Sinh Viên FPT</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../css/adminAcc.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <%@include file="../Partials/Header.jsp" %>
        <!-- Navigation -->
        <%@include file="../Partials/Navbar.jsp" %>
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Account</li>
            </ol>
        </nav>

        <!-- Main Content -->
        <div class="container my-5">
            <h2 class="text-center">List Staff Account</h2>
            <div class="d-flex justify-content-between mb-3">
                <button class="btn btn-primary">+ Add new Account</button>
            </div>
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                    <tr>
                        <th>No.</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Position</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Lê Thành</td>
                        <td>thanhle@gmail.com</td>
                        <td>Trưởng phòng tuyển sinh</td>
                        <td class="table-actions">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="green"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                            <path
                                d="M12.854.146a.5.5 0 0 1 .638.057l2.5 2.5a.5.5 0 0 1-.057.638l-10 10a.5.5 0 0 1-.233.13l-5 1.5a.5.5 0 0 1-.632-.632l1.5-5a.5.5 0 0 1 .13-.233l10-10zm1.415 3.207L11.207 1.793 2 11v2h2l8.854-8.854zM2.5 12.5v1h1l.5-.5H2.5z" />
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-trash"
                                 viewBox="0 0 16 16">
                            <path
                                d="M5.5 5.5v6h-1v-6h1zm3 0v6h-1v-6h1zm3 0v6h-1v-6h1zM14.5 4a.5.5 0 0 1 .5.5v.5H1v-.5a.5.5 0 0 1 .5-.5h12.5zM4.118 4l.405-1.607A.5.5 0 0 1 5 2h6a.5.5 0 0 1 .477.393L11.882 4H14v1H2V4h2.118zm1.415 10A1.5 1.5 0 0 0 7 14h2a1.5 1.5 0 0 0 1.467-1.607L9.882 6H6.118l-.415 6H4.883l-.415 6z" />
                            </svg>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <%@include file="../Partials/Footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
