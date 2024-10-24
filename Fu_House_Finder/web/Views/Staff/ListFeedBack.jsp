<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/staff/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/staff/listofroom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="../Partials/Staff/Sidebar.jsp" %>
            <div id="page-content-wrapper">
                <!-- Navigation Bar in Page Content -->
                <%@include file="../Partials/Staff/Nav.jsp" %>
                <div class="container mt-4">


                    <!-- Table Section -->
                    <h4 class="mb-3" style="text-align: center">Feedback List</h4>
                    <!-- Thêm một alert để hiển thị thông báo xóa thành công -->
                    <c:if test="${param.successFB eq 'true'}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            Delete feedback successfully!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${param.successFB eq 'false'}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            Cannot delete this feedback it's did not slove!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <div id="searchForm">
                        <label for="title">Title:</label>
                        <input type="text" id="title" name="title"><br><br>

                        <label for="status">Status:</label>
                        <input type="text" id="status" name="status"><br><br>

                        <label for="createdDate">Created Date:</label>
                        <input type="date" id="createdDate" name="createdDate"><br><br>

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email"><br><br>

                        <label for="sentTime">Sent Time:</label>
                        <input type="time" id="sentTime" name="sentTime"><br><br>

                        <button type="button" onclick="filter()">Search</button>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Title</th>
                                <th scope="col">Description</th>
                                <th scope="col">Status</th>
                                <th scope="col">Sent Time</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Email </th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody id="feedbackTableBody">
                            <!-- Modify forEach to iterate over the feedbacks -->
                            <c:forEach var="feedbacks" items="${feedbackList}" varStatus="status" >
                                <tr>
                                    <!-- Use varStatus to get the index -->
                                    <th scope="row">${status.index + 1}</th>    
                                    <td>${feedbacks.title}</td>
                                    <td>${feedbacks.description}</td>
                                    <td>${feedbacks.status}</td>
                                    <td>${feedbacks.sentTime}</td>
                                    <td>${feedbacks.createdDate}</td>
                                    <td>${feedbacks.renterEmail}</td>
                                    <td>

                                        <a href="FeedBackdetails?id=${feedbacks.id}" class="btn btn-warning" style="margin-right: 20px;">
                                            <i class="fas fa-list"></i>
                                        </a>
                                            
                                        <a href="#" class="btn btn-facebook" style="margin-right: 20px;">
                                            <i class="fas fa-comments"></i>
                                        </a>

                                        <c:if test="${feedbacks.status.equals('Solved')}">
                                            <a href="javascript:void(0);" onclick="openDeleteModal(${feedbacks.id}, '${feedbacks.renterEmail}');" class="btn btn-danger">
                                                <i class="fas fa-trash-alt"></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${feedbacks.status.equals('Pending')}">
                                            <a href="javascript:void(0);" onclick="cannotopenDeleteModal(${feedbacks.id}, '${feedbacks.renterEmail}');" class="btn btn-danger">
                                                <i class="fas fa-trash-alt"></i>
                                            </a>
                                        </c:if>



                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- Phần này để hiển thị các nút phân trang -->
                    <div id="paginationControls" style="text-align: center"></div>

                    <script>
                        // Cấu hình số lượng phòng hiển thị trên mỗi trang
                        const itemsPerPage = 5;
                        const tableBody = document.getElementById('feedbackTableBody');
                        const rows = tableBody.getElementsByTagName('tr');
                        const totalItems = rows.length;

                        // Tính toán số trang
                        const totalPages = Math.ceil(totalItems / itemsPerPage);

                        function showPage(page) {
                            // Ẩn tất cả các hàng
                            for (let i = 0; i < totalItems; i++) {
                                rows[i].style.display = 'none';
                            }

                            // Hiển thị các hàng trong phạm vi của trang hiện tại
                            const start = (page - 1) * itemsPerPage;
                            const end = start + itemsPerPage;
                            for (let i = start; i < end && i < totalItems; i++) {
                                rows[i].style.display = '';
                            }

                            // Cập nhật nút phân trang
                            const paginationControls = document.getElementById('paginationControls');
                            paginationControls.innerHTML = '';

                            for (let i = 1; i <= totalPages; i++) {
                                const button = document.createElement('button');
                                button.innerText = i;
                                button.classList.add('btn', 'btn-primary', 'mx-1');
                                if (i === page) {
                                    button.classList.add('active');
                                }
                                button.addEventListener('click', function () {
                                    showPage(i);
                                });
                                paginationControls.appendChild(button);
                            }
                        }

                        // Hiển thị trang đầu tiên khi tải trang
                        showPage(1);

                        function filter() {
                            const title = document.getElementById('title').value.toLowerCase();
                            const status = document.getElementById('status').value.toLowerCase();
                            const createdDate = document.getElementById('createdDate').value;
                            const email = document.getElementById('email').value.toLowerCase();
                            const sentTime = document.getElementById('sentTime').value;

                            const tableBody = document.getElementById('feedbackTableBody');
                            const rows = tableBody.getElementsByTagName('tr');

                            for (let i = 0; i < rows.length; i++) {
                                const cells = rows[i].getElementsByTagName('td');
                                const rowTitle = cells[0].textContent.toLowerCase();
                                const rowStatus = cells[1].textContent.toLowerCase();
                                const rowCreatedDate = cells[2].textContent;
                                const rowEmail = cells[3].textContent.toLowerCase();
                                const rowSentTime = cells[4].textContent;

                                const matches = (!title || rowTitle.includes(title)) &&
                                        (!status || rowStatus === status) &&
                                        (!createdDate || rowCreatedDate === createdDate) &&
                                        (!email || rowEmail === email) &&
                                        (!sentTime || rowSentTime === sentTime);

                                // Show or hide row based on matches
                                rows[i].style.display = matches ? '' : 'none';
                            }
                        }


                    </script>

                    <!-- Modal xác nhận xóa -->
                    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Confirm to feedback</h5>
                                </div>
                                <div class="modal-body">
                                    Do you want to delete this feedback of <span id="feedbackToDelete"></span> or not ?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                    <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Yes</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade" id="cannotdeleteModal" tabindex="-1" aria-labelledby="cannotdeleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Confirm to feedback</h5>
                                </div>
                                <div class="modal-body">
                                    You cannot delete this feedback of <span id="feedbackToDelete"></span> when it is pending!
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">ỌK</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
        <script>
                        function openDeleteModal(feedbackid, renterid) {
                            document.getElementById('feedbackToDelete').textContent = renterid;
                            document.getElementById('confirmDeleteBtn').href = 'ListFeedback?id=' + feedbackid;  // Link for deletion
                            var deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                            deleteModal.show();
                        }
        </script>
        <script>
            function cannotopenDeleteModal(feedbackid, renterid) {
                document.getElementById('feedbackToDelete').textContent = renterid;
                document.getElementById('confirmDeleteBtn').href = 'ListFeedback?id=' + feedbackid;  // Link for deletion
                var deleteModal = new bootstrap.Modal(document.getElementById('cannotdeleteModal'));
                deleteModal.show();
            }
        </script>
    </body>
</html>
