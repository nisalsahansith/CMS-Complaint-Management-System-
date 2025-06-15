<%@ page import="org.example.model.dao.ComplaintDAO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
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
        <li><a href="#myComplaints">Complaints</a></li>
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
      <h2>Welcome to the Admin Dashboard</h2>
      <p>This is your dashboard where you can manage complaints.</p>
    </section>


    <form action="${pageContext.request.contextPath}/admin" method="post" class="reply">
      <p id="desc"></p>
      <input type="hidden" name="complaintId" id="complaint-id" />
      <input type="hidden" name="description" id="description" />
      <input type="hidden" name="date" id="date" />
      <input type="hidden" name="uid" id="uid" />
      <label for="reply">Remark:</label><br>
      <textarea id="reply" name="reply" rows="4" cols="50" placeholder="Write your response here..."></textarea><br><br>

      <input type="hidden" name="status" id="status" />
      <button type="submit" class="btn approveBtn" onclick="setStatus('approve')">Approve</button>
      <button type="submit" class="btn decline-btn" onclick="setStatus('decline')">Decline</button>
      <button type="reset" class="btn cancel-btn" onclick="cancle()">Cancel</button>
    </form>

    <section id="myComplaints">
      <h2>Complaints</h2>
      <table id="complaintsTable">
        <thead>
        <tr>
          <th>ID</th>
          <th>Description</th>
          <th>Date Submitted</th>
          <th>Status</th>
          <th>Remark</th>
          <th>User ID</th>
          <th style="width: 260px;">Action</th>
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
          <td><%= complaint.getUId() %></td>
          <td>
            <%
            String status = complaint.getStatus();
            boolean disableButtons = "approve".equalsIgnoreCase(status) || "decline".equalsIgnoreCase(status);
            %>
            <button class="btn approve-btn"
                    data-id="<%= complaint.getId() %>"
                    data-description="<%= complaint.getDescription().replace("\"", "&quot;").replace("'", "&#39;") %>"
                    data-date="<%= complaint.getDate() %>"
                    data-uid="<%= complaint.getUId() %>"
                    onclick="handleApprove(this)"
                    <%= disableButtons ? "disabled" : "" %>>
              Approve
            </button>
            <button class="btn delete-btn"
                    data-id="<%= complaint.getId() %>"
                    data-description="<%= complaint.getDescription().replace("\"", "&quot;").replace("'", "&#39;") %>"
                    data-date="<%= complaint.getDate() %>"
                    data-uid="<%= complaint.getUId() %>"
                    onclick="handleDecline(this)"
                    <%= disableButtons ? "disabled" : "" %>>
              Decline
            </button>
            <button class="btn delete-btn" onclick="deleteComplaint('<%= complaint.getId()%>')">Delete</button>
          </td>
        </tr>
        <%   }
        } else { %>
        <tr><td colspan="6">No complaints found.</td></tr>
        <% } %>
        </tbody>
      </table>
    </section>

      <form id="deleteForm" action="${pageContext.request.contextPath}/admin" method="post" style="display:none;">
      <input type="hidden" name="complaintId" id="deleteComplaintId" />
      <input type="hidden" name="action" value="delete" />
    </form>

  </main>
  <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script src="${pageContext.request.contextPath}/js/adminPage.js"></script>
</body>
</html>
