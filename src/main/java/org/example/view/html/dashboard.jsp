<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/dashboard.css">
</head>
<body>
    <header>
        <h1>Dashboard</h1>
        <nav>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#submitComplaints">Submit Complaints</a></li>
                <li><a href="#myComplaints">My Complaints</a></li>
                <li><a href="/index.jsp">Logout</a></li>
            </ul>
        </nav>
        <div class="user-info">
            <p>Welcome, <span id="username">User</span></p>
            <p>Role: <span id="role">User Role</span></p>
        </div>
    </header>
    <main>
        <section id="home">
            <h2>Welcome to the Dashboard</h2>
            <p>This is your dashboard where you can manage your complaints and view status.</p>
        </section>

        <section id="submitComplaints">
            <h2>Submit Complaints</h2>
            <form id="complaintForm">
                <h3>Submit your complaint here!</h3>

                <label for="complaintDescription">Description:</label>
                <textarea id="complaintDescription" required></textarea>
                <label for="Date">Date:</label>
                <input type="date" id="complaintDate" required>


                <button type="submit">Submit Complaint</button>
            </form>
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
                    <!-- Complaints will be dynamically populated here -->
                </tbody>
            </table>
        </section>
    
</body>
</html>