<%-- 
    Document   : AdminAccount
    Created on : Sep 14, 2024, 12:50:19 PM
    Author     : xuxum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tìm Trọ Sinh Viên FPT</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .header {
                background-color: #f44336;
                color: white;
                padding: 20px;
            }

            .header h1 {
                font-size: 32px;
                margin: 0;
                font-weight: bold;
            }

            .header img {
                height: 50px;
            }

            .table-actions svg {
                cursor: pointer;
                margin-right: 10px;
            }

            .footer {
                background-color: #f44336;
                color: white;
                padding: 20px 0;
                text-align: center;
            }

            .footer .container {
                display: flex;
                justify-content: space-between;
            }

            .footer p {
                margin: 0;
            }

            .footer a {
                color: white;
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        <!-- Header -->
        <div class="header d-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center">
                <img src="logo.png" alt="FU House Finder">
                <h1 class="ms-3">Find FPT Student Accommodation</h1>
            </div>
            <div class="dropdown">
                <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    Admin
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><a class="dropdown-item" href="#">Logout</a></li>
                </ul>
            </div>
        </div>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-sm navbar-light bg-light">
            <div class="container">
                <ul class="navbar-nav">
                    <li class="nav-item"><a href="#" class="nav-link">Trang chủ</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Tuyển sinh</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Liên hệ</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Giới thiệu</a></li>
                </ul>
            </div>
        </nav>

        <!-- Main Content -->
        <div class="container my-5">
            <h2 class="text-center">Danh sách tuyển sinh</h2>
            <div class="d-flex justify-content-between mb-3">
                <button class="btn btn-primary">+ Thêm mới</button>
            </div>
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                    <tr>
                        <th>STT</th>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Vị trí</th>
                        <th>Hành động</th>
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
                    <!-- Additional rows can be added similarly -->
                </tbody>
            </table>
        </div>

        <!-- Footer -->
        <div class="footer">
            <div class="container">
                <div>
                    <h5>FPT Education</h5>
                    <p>Hoa Lac High Tech Park, Hanoi, Vietnam</p>
                    <p>Email: fpteducation@fe.edu.vn</p>
                </div>
                <div>
                    <h5>Tuyển sinh</h5>
                    <ul>
                        <li><a href="#">Đại học FPT</a></li>
                        <li><a href="#">Greenwich Việt Nam</a></li>
                        <li><a href="#">Swinburne Việt Nam</a></li>
                    </ul>
                </div>
                <div>
                    <h5>Sinh viên quốc tế</h5>
                    <ul>
                        <li><a href="#">Ngắn hạn</a></li>
                        <li><a href="#">Dài hạn</a></li>
                    </ul>
                </div>
            </div>
            <div class="mt-3">
                <p>Sitemap website | Copyright &copy; 2017 FPT Education</p>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>

