function confirmPasswords() {
    var message = document.getElementById('message');
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

function compareOnSubmit() {
    return passwordsAreEquals(
        document.getElementById('password').value, document.getElementById('confirmation').value);
}

function passwordsAreEquals(pass1, pass2) {
    return pass1 === pass2;
}

function passwordsAreEmpty(pass1, pass2) {
    return pass1 === "" || pass2 === "";
}