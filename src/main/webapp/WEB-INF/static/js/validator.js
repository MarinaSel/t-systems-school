function compareDrivers() {
    var primaryDriverSelectText = getSelectedText('primaryDriver');
    if (primaryDriverSelectText !== "" && primaryDriverSelectText === getSelectedText('secondDriver')) {
        alert("You chose the one driver for two positions");
        getDrivers();
        return false;
    }
    return true;
}

function getSelectedText(id) {
    var select = document.getElementById(id);
    var text = "";
    if (select.selectedIndex !== -1) {
        text = select.options[select.selectedIndex].text;
    }
    return text;
}

function compare() {
    var pickUpDeliverySelectText = getSelectedText('pickUpLocation');
    if (pickUpDeliverySelectText !== "" && pickUpDeliverySelectText === getSelectedText('deliveryLocation')) {
        alert("You chose the one location for two positions");
        return false;
    }
    return compareDrivers();
}