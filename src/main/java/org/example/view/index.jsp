<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/welcomePage.css">
</head>
<body>
    <h1>Welcome to the Municiple IT Division CMS</h1>
    <nav>
        <div class="sign-in">
            <h2>Sign In</h2>
            <p>Please enter your credentials to access the system.</p>
            <label for="username">Username:</label>
            <input type="text" id="username" placeholder="Username">
            <label for="password">Password:</label>
            <input type="password" id="password" placeholder="Password">
            <button type="submit">Sign In</button>
            <p>Don't have an account? <a href="#register" id="registerPage">Register here</a></p>
        </div>
        <div class="register">
            <h2>Register</h2>
            <p>New user? Create an account to get started.</p>
            <label for="new-username">Username:</label>
            <input type="text" id="new-username" placeholder="Username">
            <label for="new-password">Password:</label>
            <input type="password" id="new-password" placeholder="Password"> 
            <label for="email">Email:</label>
            <input type="email" id="email" placeholder="Email">
            <label for="role">Role:</label>
            <select id="role">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select>
            <button type="submit">Register</button>
            <p>Already have an account? <a href="#sign-in" id="sign-in">Sign in here</a></p>
        </div>
    </nav>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/welcomePage.js"></script>
</body>
</html>