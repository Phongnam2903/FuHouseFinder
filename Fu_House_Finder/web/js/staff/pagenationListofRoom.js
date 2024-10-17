
// Cấu hình số lượng phòng hiển thị trên mỗi trang
const itemsPerPage = 5;
const tableBody = document.getElementById('roomTableBody');
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
               