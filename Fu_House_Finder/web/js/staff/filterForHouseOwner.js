$(document).ready(function () {
    // Filter functionality
    $('#filterForm').submit(function (e) {
        e.preventDefault();

        // Get filter values
        const filterName = $('#filterName').val().toLowerCase();
        const filterPhone = $('#filterPhone').val().toLowerCase();
        const filterStatus = $('#filterStatus').val();
        const filterTotalHouses = $('#filterTotalHouses').val();

        // Iterate through table rows and filter based on input
        $('#landlordTableBody tr').filter(function () {
            const name = $(this).find('td:nth-child(1)').text().toLowerCase();
            const phone = $(this).find('td:nth-child(2)').text().toLowerCase();
            const status = $(this).find('td:nth-child(6) button').text().toLowerCase();
            const totalHouses = $(this).find('td:nth-child(3)').text();

            $(this).toggle(
                    (filterName === '' || name.includes(filterName)) &&
                    (filterPhone === '' || phone.includes(filterPhone)) &&
                    (filterStatus === '' || status.includes(filterStatus)) &&
                    (filterTotalHouses === '' || totalHouses == filterTotalHouses)
                    );
        });
    });
});