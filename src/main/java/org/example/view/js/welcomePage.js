$("#registerPage").on("click", function () {
    $('.sign-in').css('display', 'none');
    $('.register').css('display', 'block');
    $('.forgot-password').css('display', 'none');
});

$("#sign-in").on("click", function () {
    $('.sign-in').css('display', 'block');
    $('.forgot-password').css('display', 'none');
    $('.register').css('display', 'none');
    n
});



// $('#save').on('click', function (event) { 
//     event.preventDefault();
//     const formData = new FormData();
//     formData.append('ename', $('#ename').val());
//     formData.append('enumber', $('#enumber').val());
//     formData.append('eaddress', $('#eaddress').val());
//     formData.append('edepartment', $('#edepartment').val());
//     formData.append('estatus', $('#estatus').val());
