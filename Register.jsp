<%-- 
    Document   : Login
    Created on : Nov 3, 2025, 8:48:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/register.css"> 
</head>

<body>
    <div class="register">
        <h1>FPT Online Course</h1>
        <h3>Register</h3>

         <form action="${pageContext.request.contextPath}/register" method="post">
            <label>Username:</label>
            <input type="text" name="username" placeholder="Enter your Username" required>

            <label>Email:</label>
            <input type="email" name="email" placeholder="Enter your Email" required>

            <label>Password:</label>
            <input type="password" name="password" placeholder="Enter your Password" required>

            <label>Fullname:</label>
            <input type="text" name="fullname" placeholder="Enter your Fullname">

            <div class="wrap">
                <button type="submit">Register</button>
            </div>
            <a class="link-btn" href="${pageContext.request.contextPath}/Login">Back to Login</a>
        </form>
    </div>
</body>

</html>
