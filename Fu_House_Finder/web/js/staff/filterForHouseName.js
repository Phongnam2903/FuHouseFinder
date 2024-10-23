$(document).ready(function () {
    //Filter functionality
    $('#filterFormRentalHouse').submit(function (e) {
        e.preventDefault();

        //Get filter values
        const filterName = ('#filterHouseName').val.toLowerCase();
        const filterAddress = ('#filterAddress').val.toLowerCase();

        //Iterate through table rows and filter based on input
        $('#landlordDetailTableBody tr').filter(function () {
            const name = $(this).find('td:nth-child(2)').text.toLowerCase();
            const address = $(this).find('td:nth-child(5)').text.toLowerCase();

            $(this).toggle(
                    (filterName === '' || name.includes(filterName)) &&
                    (filterAddress === '' || address.includes(filterAddress))
                    );
        });
    });
});


