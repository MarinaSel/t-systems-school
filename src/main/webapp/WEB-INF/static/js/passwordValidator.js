function comparePassword() {
    var password = document.getElementById('password').value;
    var passwordConfirmation = document.getElementById('confirmation').value;
    var message = document.getElementById('message');

    if (password === passwordConfirmation) {
        message.style.color = 'green';
        message.innerHTML = "Password matcher";
        return true;
    } else {
        message.style.color = 'red';
        message.innerHTML = "Passwords doesn't matcher";
        return false;
    }
}