function validateForm() {
        var email = document.getElementById("email").value;
        var phone = document.getElementById("phone").value;

        // Email format validation
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Please enter a valid email address");
            return false;
        }

        // Phone number digit check
        var phonePattern = /^\d{10}$/;
        if (!phonePattern.test(phone)) {
            alert("Phone number should contain only digits");
            return false;
        }

        return true;
    }