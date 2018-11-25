function confirmPasswords() {
    var message = document.getElementById('passwordMessage');
    var password = document.getElementById('password').value;
    var passwordConfirmation = document.getElementById('confirmation').value;

    if (!passwordsAreEmpty(password, passwordConfirmation)) {
        if (passwordsAreEquals(password, passwordConfirmation)) {
            message.style.color = 'green';
            message.innerHTML = "Matches";
        } else {
            message.style.color = 'red';
            message.innerHTML = "Doesn't match";
        }
    }
    else {
        message.innerText = "";
    }
}

function onDriverSubmit() {
    var login = document.getElementById('login');
    var errorMessage = document.getElementById('driverErrorMessage');
    var drivingLicenseNum = document.getElementById('dln');
    if (isLoginAvailable(login.value)) {
        if (isDrivingLicenseNumAvailable(drivingLicenseNum.value)) {
            return passwordsAreEquals(
                document.getElementById('password').value, document.getElementById('confirmation').value);
        } else {
            errorMessage.style.color = 'red';
            errorMessage.innerText = "Driving license number already exists";
            return false;
        }
    } else {
        errorMessage.style.color = 'red';
        errorMessage.innerText = "Login already exists";
        return false;
    }
}

function passwordsAreEquals(pass1, pass2) {
    return pass1 === pass2;
}

function passwordsAreEmpty(pass1, pass2) {
    return pass1 === "" || pass2 === "";
}

function isLoginAvailable(login) {
    var loginAvailable = "";
    if (login !== "") {
        $.ajax({
            type: 'GET',
            datatype: "json",
            async: false,
            url: "/api/driver/findByLogin/" + login,
            success: function (result) {
                loginAvailable = (JSON.stringify(result) === '""');
            }
        })
    }
    return loginAvailable;
}

function isDrivingLicenseNumAvailable(drivingLicenseNum) {
    var drivingLicNumAvailable = "";
    if (drivingLicenseNum !== "") {
        $.ajax({
            type: 'GET',
            datatype: "json",
            async: false,
            url: "/api/driver/findByDrivingLicenseNum/" + drivingLicenseNum,
            success: function (result) {
                drivingLicNumAvailable = (JSON.stringify(result) === '""');
            }
        })
    }
    return drivingLicNumAvailable;
}

function clearDriverErrorMessage() {
    var errorMessage = document.getElementById('driverErrorMessage');
    errorMessage.innerText = "";
}