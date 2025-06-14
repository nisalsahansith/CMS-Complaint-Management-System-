$("#registerPage").on("click", function () {
    $('.sign-in').css('display', 'none');
    $('.register').css('display', 'block');
    $('.forgot-password').css('display', 'none');
});

$("#sign-in").on("click", function () {
    $('.sign-in').css('display', 'block');
    $('.forgot-password').css('display', 'none');
    $('.register').css('display', 'none');
});

$(document).ready(function () {
    const status = $("body").data("registration-status");
    const message = $("body").data("registration-message");

    if (status === "success") {
        $("#registerFormDiv").hide();
        $("#signInForm").show();
        Swal.fire({
            icon: 'success',
            title: 'Registration Successful!',
            text: 'Please sign in.',
            confirmButtonText: 'OK'
        });
    } else if (status === "error") {
        $("#registerFormDiv").show();
        $("#signInForm").hide();
        Swal.fire({
            icon: 'error',
            title: 'Registration Failed',
            text: message || 'Something went wrong.',
            confirmButtonText: 'OK'
        });
    } else {
        $("#registerFormDiv").hide();
        $("#signInForm").show();
    }
});


$(document).ready(function () {
    const errorMessage = $('body').data('login-error');
    if (errorMessage) {
        alert(errorMessage);
        $('#loginForm')[0].reset(); // Reset the login form
    }
});





// $('#save').on('click', function (event) { 
//     event.preventDefault();
//     const formData = new FormData();
//     formData.append('ename', $('#ename').val());
//     formData.append('enumber', $('#enumber').val());
//     formData.append('eaddress', $('#eaddress').val());
//     formData.append('edepartment', $('#edepartment').val());
//     formData.append('estatus', $('#estatus').val());
