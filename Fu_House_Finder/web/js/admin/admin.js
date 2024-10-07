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

// Biểu đồ Doanh Thu
document.addEventListener("DOMContentLoaded", function () {
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var revenueChart = new Chart(ctx, {
        type: 'bar', // Có thể thay đổi thành 'line', 'pie', v.v.
        data: {
            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6'],
            datasets: [{
                    label: 'Doanh Thu ($)',
                    data: [12000, 15000, 18000, 14000, 16000, 20000],
                    backgroundColor: '#FFA500',
                    borderColor: '#343a40',
                    borderWidth: 1
                }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        color: '#343a40'
                    }
                },
                x: {
                    ticks: {
                        color: '#343a40'
                    }
                }
            },
            plugins: {
                legend: {
                    labels: {
                        color: '#343a40'
                    }
                }
            }
        }
    });
});

// Hàm chuyển hướng sau khi hiển thị thông báo
function redirectAfterDelay(url, delay) {
    setTimeout(function () {
        window.location.href = url;
    }, delay);
}
