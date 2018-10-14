function getVehicle() {
    var weight = $('#vehicleWeight').val();
    $.ajax({
        type: 'GET',
        datatype:"json",
        url: "/api/vehicle",
        data: {"weight": weight},
        success: function (result) {
            var insert = '';
            $('#vehicles').empty();

            $.each(result, function (index, value) {
                insert += '<option>' + value.registrationNumber + '</option>';
            });
            $('#vehicles').append(insert);
        }
    });
}