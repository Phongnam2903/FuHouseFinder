function togglePasswordVisibility() {
    const passwordInput = document.querySelector('input[name="password"]');
    const toggleIcon = document.getElementById('togglePassword');

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.textContent = "🙈 Hide Password️";

        // Đặt thời gian 3 giây để tự động chuyển về chế độ ẩn mật khẩu
        setTimeout(() => {
            passwordInput.type = "password";
            toggleIcon.textContent = "👁 Show Password️";
        }, 3000); // 3 giây (3000 ms)
    } else {
        passwordInput.type = "password";
        toggleIcon.textContent = "👁 Show Password️";
    }
}
