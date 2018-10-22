function getDrivers() {
    if ($('#vehicles').prop('selectedIndex') > -1) {
        $('#primaryDriver').prop('disabled', false);
        $('#secondDriver').prop('disabled', false)
        $.ajax({
            type: 'GET',
            datatype: "json",
            url: "/api/driver",
            success: function (result) {
                var insert = '';
                $('#primaryDriver').empty();
                $('#secondDriver').empty();
                insert+= '<option></option>';

                $.each(result, function (index, value) {
                    insert += '<option>' + value.drivingLicenseNum + '</option>';
                });
                $('#primaryDriver').append(insert);
                $('#secondDriver').append(insert);
            }
        })

    }
    else {
        $('#primaryDriver').prop('disabled', 'disabled');
        $('#secondDriver').prop('disabled', 'disabled');
    }
};

function getVehicle() {
    var weight = $('#vehicleWeight').val();
    $.ajax({
        type: 'GET',
        datatype: "json",
        url: "/api/vehicle",
        data: {
            "weight": weight
        },
        success: function (result) {
            var insert = '';
            $('#vehicles').empty();
            insert += '<option></option>';

            $.each(result, function (index, value) {
                insert += '<option>' + value.registrationNumber + '</option>';
            });
            $('#vehicles').append(insert);
        }
    });
}