<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/welcomePage.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
</head>
<body
        data-registration-status="<%= request.getAttribute("registrationSuccess") != null ? "success" : request.getAttribute("registrationError") != null ? "error" : "" %>"
        data-registration-message="<%= request.getAttribute("registrationError") != null ? request.getAttribute("registrationError") : "" %>">
    <h1>Welcome to the Municiple IT Division CMS</h1>
    <nav>
        <form id="loginForm"  action="${pageContext.request.contextPath}/signIn" method="post">
            <div class="sign-in" id="signInForm" style="display: block;">
                <h2>Sign In</h2>
                <p>Please enter your credentials to access the system.</p>
                <label for="email">Email:</label>
                <input type="email" id="emailAddress" name="email" placeholder="Email" required>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Password" required>
                <button type="submit">Sign In</button>
                <p>Don't have an account? <a href="#register" id="registerPage">Register here</a></p>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <div class="register" id="registerFormDiv" style="display: none;">
                <h2>Register</h2>
                <p>New user? Create an account to get started.</p>

                <label for="new-username">Username:</label>
                <input type="text" id="new-username" name="username" placeholder="Username" required>

                <label for="new-password">Password:</label>
                <input type="password" id="new-password" name="password" placeholder="Password" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Email" required>

                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="admin">Admin</option>
                    <option value="user">User</option>
                </select>

                <button type="submit">Register</button>
                <p>Already have an account? <a href="#sign-in" id="sign-in">Sign in here</a></p>
            </div>
        </form>
    </nav>

    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <% if (request.getAttribute("loginError") != null) { %>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            Swal.fire({
                icon: 'error',
                title: 'Login Failed',
                text: '<%= request.getAttribute("loginError") %>',
                confirmButtonColor: '#d33'
            });
            $('#loginForm')[0].reset();
        });
    </script>
    <% } %>

    <script src="${pageContext.request.contextPath}/js/welcomePage.js"></script>
</body>
</html>