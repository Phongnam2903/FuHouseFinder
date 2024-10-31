<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/css/staff/sidebar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="bg-orange border-right" id="sidebar-wrapper">
            <!-- Updated icon to a user-cog for Staff -->
            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
                <i class="fas fa-user-cog me-2"></i>Staff
            </div>
            <div class="list-group list-group-flush">
                <!-- Changed icon to a user group to represent landlords -->
                <a href="listhouseowner" class="list-group-item list-group-item-action bg-orange text-white">
                    <i class="fas fa-users me-2"></i>List of Landlords
                </a>
                <!-- Changed icon to home to represent houses -->
                <a href="houseList" class="list-group-item list-group-item-action bg-orange text-white">
                    <i class="fas fa-home me-2"></i>List of Houses
                </a>
                <!-- Changed icon to chart-bar to represent reports -->
                <a href="#" class="list-group-item list-group-item-action bg-orange text-white">
                    <i class="fas fa-chart-bar me-2"></i>Reports House
                </a>
                <!-- Changed icon to clipboard-list to represent requests -->
                <a href="listOrder" class="list-group-item list-group-item-action bg-orange text-white">
                    <i class="fas fa-clipboard-list me-2"></i>Requests Orders Accommodation
                </a>
                <!-- Changed icon to clipboard-list to represent Feedbacks -->
                <a href="ListFeedback" class="list-group-item list-group-item-action bg-orange text-white">
                    <i class="fas fa-clipboard-list me-2"></i>List of Feedbacks
                </a>
                <!-- Log out icon remains the same -->
                <a href="logout" class="list-group-item list-group-item-action bg-orange text-white">
                    <i class="fas fa-sign-out-alt me-2"></i>Log Out
                </a>
            </div>
        </div>
    </body>
</html>
