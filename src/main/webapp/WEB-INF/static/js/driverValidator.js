function confirmPasswords() {
    var message = document.getElementById('passwordMessage');
    var password = document.getElementById('password').value;
    var passwordConfirmation = document.getElementById('confirmation').value;

    if (!passwordsAreEmpty(password, passwordConfirmation)) {
        if (passwordsAreEquals(password, passwordConfirmation)) {
            message.style.color = 'green';
            message.innerHTML = "Password matches";
        } else {
            message.style.color = 'red';
            message.innerHTML = "Password doesn't match";
        }
    }
    else {
        message.innerText = "";
    }
}

function onSubmit() {
    var login = document.getElementById('login');
    var message = document.getElementById('loginMessage');
    if (isLoginAvailable(login.value)) {
        message.innerText = "";
        return passwordsAreEquals(
            document.getElementById('password').value, document.getElementById('confirmation').value);
    } else {
        message.style.color = 'red';
        message.innerText = "Login already exists";
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
                return (JSON.stringify(result) === '""');
            }
        })
    }
    return loginAvailable;
}