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
    const id = button.dataset.id;
    const description = button.dataset.description;
    if (confirm("Are you sure you want to delete this complaint?")) {
        $('#deleteComplaintId').val(id);
        $('#deleteForm').submit();
    }}

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