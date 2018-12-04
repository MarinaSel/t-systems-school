var previousVehicle;
var emptyOption = "<option></option>";

function getDrivers() {
    if ($('#vehicles').prop('selectedIndex') > -1) {
        $.ajax({
            type: 'GET',
            datatype: "json",
            url: "/api/driver",
            success: function (result) {
                var insert = emptyOption;
                $.each(result, function (index, value) {
                    insert += wrapToOption(value.drivingLicenseNum);
                });
                $('#primaryDriver').empty().append(insert);
                $('#secondDriver').empty().append(insert);
            }
        })
    }
}

function getVehicles() {
    var weight = $('#vehicleWeight').val();
    $.ajax({
        type: 'GET',
        datatype: "json",
        url: "/api/vehicle",
        data: {
            "weight": weight
        },
        success: function (result) {
            var insertContainsPrevious = false;
            var select = document.getElementById('vehicles');
            var index = select.selectedIndex;
            if (index !== -1) {
                var selectedText = select.options[index].text;
                if (selectedText !== '') {
                    previousVehicle = selectedText;
                }
            }
            var insert = '';
            $.each(result, function (index, value) {
                if (value.registrationNumber !== previousVehicle) {
                    insert += wrapToOption(value.registrationNumber);
                } else {
                    insertContainsPrevious = true;
                }
            });
            $('#vehicles').empty().append(addPreviousVehicleToInsert(insert, insertContainsPrevious));
        }
    });
}

function wrapToOption(value) {
    return '<option>' + value + '</option>';
}

function addPreviousVehicleToInsert(insert, flag) {
    var previous = wrapToOption(previousVehicle);
    if (previous === emptyOption) {
        insert = previous + insert;
    } else {
        if (flag) {
            insert = previous + insert;
        } else {
            insert = emptyOption + insert;
        }
    }
    return insert;
}