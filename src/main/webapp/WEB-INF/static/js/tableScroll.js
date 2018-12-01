$(document).ready(function () {
    $('#tableScroll').DataTable({
        "scrollY": "50%",
        "scrollX": true,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]
    });
});