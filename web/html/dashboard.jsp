<%@ page import="org.example.model.dao.ComplaintDAO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard.css">
</head>
<body>

<%
    List<ComplaintDAO> complaints = (List<ComplaintDAO>) request.getAttribute("complaints");
%>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    String uid = (String) session.getAttribute("id");
    if (username == null || role == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<header>
    <h1>Dashboard</h1>
    <nav>
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#submitComplaints">Submit Complaints</a></li>
            <li><a href="#myComplaints">My Complaints</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </ul>
    </nav>
    <div class="user-info">
        <p>Welcome, <span id="username"><%= username %></span></p>
        <p>Role: <span id="role"><%= role %></span></p>
    </div>
</header>
<main>
    <section id="home">
        <h2>Welcome to the Dashboard</h2>
        <p>This is your dashboard where you can manage your complaints and view status.</p>
    </section>

    <section id="submitComplaints">
        <h2>Submit Complaints</h2>
        <form id="complaintForm" action="${pageContext.request.contextPath}/complaint" method="post">
            <input type="hidden" name="complaintId" id="complaintId">
            <label for="complaintDescription">Description:</label>
            <textarea id="complaintDescription" name="description" required></textarea>
            <label for="complaintDate">Date:</label>
            <input type="date" id="complaintDate" name="date" required>
            <input type="hidden" name="userId" value="<%= uid %>">
            <button type="submit" id="complaintBtn">Submit Complaint</button>
        </form>
        <% String error = (String) request.getAttribute("submissionError"); %>
        <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
        <% } %>
    </section>

    <section id="myComplaints">
        <h2>My Complaints</h2>
        <table id="complaintsTable">
            <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Date Submitted</th>
                <th>Status</th>
                <th>Reply</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% if (complaints != null && !complaints.isEmpty()) {
                for (ComplaintDAO complaint : complaints) { %>
            <tr>
                <td><%= complaint.getId() %></td>
                <td><%= complaint.getDescription() %></td>
                <td><%= complaint.getDate() %></td>
                <td><%= complaint.getStatus() %></td>
                <td><%= complaint.getReply() == null ? "Pending" : complaint.getReply() %></td>
                <td>
                    <%
                        String status = complaint.getStatus();
                        boolean disableButtons = "approve".equalsIgnoreCase(status) || "decline".equalsIgnoreCase(status);
                    %>
                    <button class="btn edit-btn"
                            data-id="<%= complaint.getId() %>"
                            data-description="<%= complaint.getDescription() %>"
                            data-date="<%= complaint.getDate() %>"
                            onclick="handleEdit(this)"
                            <%= disableButtons ? "disabled" : "" %>>
                        Edit
                    </button>

                    <button class="btn delete-btn"
                            data-id="<%= complaint.getId() %>"
                            data-description="<%= complaint.getDescription() %>"
                            onclick="handleDelete(this)"
                            <%= disableButtons ? "disabled" : "" %>>
                        Delete
                    </button>
                </td>
            </tr>
            <%   }
            } else { %>
            <tr><td colspan="6">No complaints found.</td></tr>
            <% } %>
            </tbody>
        </table>
    </section>

    <form id="deleteForm" action="${pageContext.request.contextPath}/complaint" method="post" style="display:none;">
        <input type="hidden" name="complaintId" id="deleteComplaintId" />
        <input type="hidden" name="action" value="delete" />
    </form>

</main>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
</body>
</html>
