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
    if (confirm("Are you sure you want to delete this complaint?")) {
        $('#deleteComplaintId').val(id);
        $('#deleteForm').submit();
    }
}