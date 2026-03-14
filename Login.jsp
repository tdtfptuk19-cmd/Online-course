<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/login.css"> 
</head>
<body>
    <div class="login">
        <h1>FPT Online Course</h1>
        <h3>Login</h3>

       <c:if test="${not empty errorMessage}">
            <p style="color: red; font-weight: bold;">${errorLogin}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p style="color: green; font-weight: bold;">${ErrorRegister}</p>
        </c:if>
       
        <form action="${pageContext.request.contextPath}/Login" method="POST">
            <label for="first">
                Username:
            </label>
            <input type="text" id="first" name="username" 
                placeholder="Enter your Username" required>

            <label for="password">
                Password:
            </label>
            <input type="password" id="password" name="password" 
                placeholder="Enter your Password" required>

            <div class="wrap">
                <button type="submit">
                    Submit
                </button>
            </div>
        </form>
        
        <p>Not registered?
            <a href="${pageContext.request.contextPath}/register" style="text-decoration: none;">
                Create an account
            </a>
        </p>
    </div>
</body>
</html>