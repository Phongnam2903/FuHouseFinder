function filterTable() {
    const searchInput = document.getElementById("searchInput").value.toLowerCase();
    const table = document.getElementById("accountTable");
    const rows = table.getElementsByTagName("tr");

    // Lấy trạng thái đã chọn
    const statusRadios = document.getElementsByName("status");
    let selectedStatus = "";
    for (let radio of statusRadios) {
        if (radio.checked) {
            selectedStatus = radio.value;
            break;
        }
    }

    for (let i = 1; i < rows.length; i++) { // Bỏ qua hàng tiêu đề
        const cells = rows[i].getElementsByTagName("td");
        const name = cells[0].textContent.toLowerCase();
        const email = cells[1].textContent.toLowerCase();
        const phone = cells[2].textContent.toLowerCase();

        // Kiểm tra nếu tên, email hoặc số điện thoại khớp với input tìm kiếm
        const matchesSearch = name.includes(searchInput) || email.includes(searchInput) || phone.includes(searchInput);

        // Kiểm tra nếu trạng thái khớp với trạng thái đã chọn
        const matchesStatus = selectedStatus === "" || status === selectedStatus.toLowerCase();

        // Hiển thị hoặc ẩn hàng dựa trên kết quả kiểm tra
        if (matchesSearch && matchesStatus) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}

function filterByStatus() {
    var table, tr, td, i, txtValue;
    var selectedStatus = document.querySelector('input[name="status"]:checked').value; // Lấy trạng thái đã chọn
    table = document.getElementById("accountTable");
    tr = table.getElementsByTagName("tr"); // Lấy tất cả các hàng trong bảng

    // Lặp qua tất cả các hàng trong bảng
    for (i = 1; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3]; // Lấy ô trạng thái (cột thứ 4)
        if (td) {
            txtValue = td.textContent || td.innerText; // Lấy giá trị của ô
            // Hiển thị tất cả nếu chọn "All" hoặc khớp với trạng thái được chọn
            tr[i].style.display = (selectedStatus === "" || txtValue === selectedStatus) ? "" : "none";
        }
    }
}
