$(function () {
    $("#issueDate").datepicker({
        dateFormat: "yy-mm-dd",
        maxDate: -7
    });

    $("#date").datepicker({
        dateFormat: "yy-mm-dd",
        minDate: +1
    });
});