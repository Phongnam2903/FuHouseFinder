document.getElementById("menu-toggle").addEventListener("click", function (e) {
    e.preventDefault();
    document.getElementById("wrapper").classList.toggle("toggled");
});

// Đợi cho trang load xong rồi ẩn thông báo sau 5 giây
document.addEventListener("DOMContentLoaded", function () {
    setTimeout(function () {
        let alerts = document.querySelectorAll('.alert');
        alerts.forEach(function (alert) {
            alert.style.opacity = 0;
            setTimeout(function () {
                alert.style.display = 'none';
            }, 500); // Ẩn hoàn toàn sau khi hiệu ứng kết thúc
        });
    }, 5000); // 5 giây
});

// Hàm chuyển hướng sau khi hiển thị thông báo
function redirectAfterDelay(url, delay) {
    setTimeout(function () {
        window.location.href = url;
    }, delay);
}

