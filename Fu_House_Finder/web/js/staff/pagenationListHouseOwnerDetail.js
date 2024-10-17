
const itemsPerPage = 4;
const table = document.querySelector('table'); // Lấy toàn bộ bảng
const tbodyList = table.getElementsByTagName('tbody'); // Lấy tất cả các phần tử tbody
let rows = [];

// Tạo một danh sách tất cả các hàng từ các tbody
for (let i = 0; i < tbodyList.length; i++) {
    const tbody = tbodyList[i];
    const trList = tbody.getElementsByTagName('tr');
    for (let j = 0; j < trList.length; j++) {
        rows.push(trList[j]);
    }
}

const totalItems = rows.length;
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
