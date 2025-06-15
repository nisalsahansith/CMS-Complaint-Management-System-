function handleApprove(button) {
    const id = button.dataset.id;
    const description = button.dataset.description;
    const date = button.dataset.date;
    const uid = button.dataset.uid;
    approve(id, description, date, uid);
}

function handleDecline(button) {
    const id = button.dataset.id;
    const description = button.dataset.description;
    const date = button.dataset.date;
    const uid = button.dataset.uid;
    decline(id, description, date, uid);
}

function approve(id, description, date, uid) {
    $(".reply").css("display", "block");
    $(".approveBtn").css("display", "inline-block");
    $(".decline-btn").css("display", "none");

    $("#desc").text(description);
    $("#complaint-id").val(id);
    $("#description").val(description);
    $("#date").val(date);
    $("#uid").val(uid);
}

function decline(id, description, date, uid) {
    $(".reply").css("display", "block");
    $(".approveBtn").css("display", "none");
    $(".decline-btn").css("display", "inline-block");

    $("#desc").text(description);
    $("#complaint-id").val(id);
    $("#description").val(description);
    $("#date").val(date);
    $("#uid").val(uid);
}


function cancle(){
    $(".reply").css("display","none")
    $("#status").val("");
}

function setStatus(value) {
    document.getElementById("status").value = value;
}

function deleteComplaint(id){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.isConfirmed) {
            $('#deleteComplaintId').val(id);
            $('#deleteForm').submit();
        }
    });
}

$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const updateStatus = urlParams.get('update');
    const deleteStatus = urlParams.get('delete');

    if (updateStatus === 'success') {
        Swal.fire({
            icon: 'success',
            title: 'Success',
            text: 'Complaint updated successfully!',
            timer: 2000,
            showConfirmButton: false
        });
        // Remove the query param from URL after showing alert
        history.replaceState(null, '', window.location.pathname + window.location.hash);
    } else if (updateStatus === 'failure') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Failed to update complaint. Please try again.'
        });
        history.replaceState(null, '', window.location.pathname + window.location.hash);
    }

    if (deleteStatus === 'success') {
        Swal.fire({
            icon: 'success',
            title: 'Deleted',
            text: 'Complaint deleted successfully!',
            timer: 2000,
            showConfirmButton: false
        });
        history.replaceState(null, '', window.location.pathname + window.location.hash);
    } else if (deleteStatus === 'failure') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Failed to delete complaint. Please try again.'
        });
        history.replaceState(null, '', window.location.pathname + window.location.hash);
    }
});
