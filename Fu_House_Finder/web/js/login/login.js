
function togglePasswordVisibility() {
    var passwordInput = document.getElementById('pass');
    var togglePassword = document.getElementById('togglePassword');
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        togglePassword.textContent = '🙈 Hide Password️';
    } else {
        passwordInput.type = 'password';
        togglePassword.textContent = '👁 Show Password️️';
    }
}

// Hide alert after 3 seconds
setTimeout(function () {
    var alert = document.getElementById('alert');
    if (alert) {
        alert.classList.add('hidden');
    }
}, 3000);
