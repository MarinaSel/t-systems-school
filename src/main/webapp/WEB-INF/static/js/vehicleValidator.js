function onVehicleSubmit() {
    var registrationNumber = document.getElementById('regNumber');
    var errorMessage = document.getElementById('vehicleErrorMessage');
    if (isRegistrationNumberAvailable(registrationNumber.value)) {
        errorMessage.innerText = "";
        return true;
    } else {
        errorMessage.style.color = 'red';
        errorMessage.innerText = "Registration number already exists";
        return false;
    }
}

function isRegistrationNumberAvailable(registrationNumber) {
    var regNumAvailable = "";
    if (registrationNumber !== "") {
        $.ajax({
            type: 'GET',
            datatype: "json",
            async: false,
            url: "/api/vehicle/findByRegistrationNumber/" + registrationNumber,
            success: function (result) {
                regNumAvailable = (JSON.stringify(result) === '""');
            }
        })
    }
    return regNumAvailable;
}

function clearVehicleErrorMessage() {
    var errorMessage = document.getElementById('vehicleErrorMessage');
    errorMessage.innerText = "";
}