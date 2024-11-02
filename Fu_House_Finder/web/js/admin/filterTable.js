function filterTable() {
    const searchInput = document.getElementById("searchInput").value.toLowerCase().replace(/\s+/g, ' ').trim();
    const table = document.getElementById("accountTable");
    const rows = table.getElementsByTagName("tr");

    // Lấy trạng thái đã chọn
    const statusRadios = document.getElementsByName("status");
    let selectedStatus = "";
    for (let radio of statusRadios) {
        if (radio.checked) {
            selectedStatus = radio.value.trim().toLowerCase();
            break;
        }
    }

    for (let i = 1; i < rows.length; i++) { // Bỏ qua hàng tiêu đề
        const cells = rows[i].getElementsByTagName("td");
        const name = cells[0].textContent.toLowerCase().replace(/\s+/g, ' ').trim();
        const email = cells[1].textContent.toLowerCase().replace(/\s+/g, ' ').trim();
        const phone = cells[2].textContent.toLowerCase().replace(/\s+/g, ' ').trim();
        const status = cells[3].textContent.toLowerCase().replace(/\s+/g, ' ').trim();

        // Kiểm tra nếu tên, email hoặc số điện thoại khớp với input tìm kiếm
        const matchesSearch = name.includes(searchInput) || email.includes(searchInput) || phone.includes(searchInput);

        // Kiểm tra nếu trạng thái khớp với trạng thái đã chọn
        const matchesStatus = selectedStatus === "" || status === selectedStatus;

        // Hiển thị hoặc ẩn hàng dựa trên kết quả kiểm tra
        if (matchesSearch && matchesStatus) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}
