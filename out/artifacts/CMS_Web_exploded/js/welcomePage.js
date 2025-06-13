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
        alert("Registration successful! Please sign in.");
    } else if (status === "error") {
        $("#registerFormDiv").show();
        $("#signInForm").hide();
        alert(message);
    } else {
        $("#registerFormDiv").hide();
        $("#signInForm").show();
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
