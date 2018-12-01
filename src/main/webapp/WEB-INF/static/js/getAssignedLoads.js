function getLoadsAssignedToAuthenticationDriver() {
    var locationJson;
    $.ajax({
        type: 'GET',
        datatype: "json",
        async: false,
        url: "/api/vehicle/findVehicleWithLoads",
        success: function (result) {
            locationJson = result;
        }
    });
    return locationJson;
}