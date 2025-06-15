function handleEdit(button) {
    const id = button.dataset.id;
    const description = button.dataset.description;
    const date = button.dataset.date;
    $('#complaintId').val(id);
    $('#complaintDescription').val(description);

    const formattedDate = date.split(' ')[0];
    $('#complaintDate').val(formattedDate);

    const btn = document.getElementById('complaintBtn');
    btn.textContent = "Update Complaint";
    btn.setAttribute("data-mode", "update");
}

function handleDelete(button) {
    const id = $(button).data('id');
    const description = $(button).data('description');

    Swal.fire({
        title: 'Are you sure?',
        text: `Do you really want to delete this complaint: "${description}"?`,
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

function editComplaint(id, description, date) {

}

$(document).ready(function () {
    $('#complaintForm')[0].reset();

    $('#complaintId').val('');

    const $btn = $('#complaintBtn');
    $btn.text('Submit Complaint');
    $btn.attr('data-mode', 'submit');
});

function deleteComplaint(id){

}

$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');

    if (status === 'success') {
        Swal.fire({
            icon: 'success',
            title: 'Complaint Submitted',
            text: 'Your complaint has been submitted successfully.',
            confirmButtonColor: '#3085d6'
        });
    } else if (status === 'error') {
        Swal.fire({
            icon: 'error',
            title: 'Submission Failed',
            text: 'Complaint submission failed. Please try again.',
            confirmButtonColor: '#d33'
        });
    } else if (status === 'deleted') {
        Swal.fire({
            icon: 'success',
            title: 'Complaint Deleted',
            text: 'The complaint has been deleted successfully.',
            confirmButtonColor: '#3085d6'
        });
    } else if (status === 'deleteError') {
        Swal.fire({
            icon: 'error',
            title: 'Delete Failed',
            text: 'Failed to delete the complaint. Please try again.',
            confirmButtonColor: '#d33'
        });
    }

    if (status) {
        const cleanUrl = window.location.origin + window.location.pathname + window.location.hash;
        window.history.replaceState({}, document.title, cleanUrl);
    }
});
