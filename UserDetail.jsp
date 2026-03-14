<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>EduLearn - Thông tin cá nhân</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">  

    </head>
    <body>
        <header>
            <div class="top-row">
                <a href="${pageContext.request.contextPath}/home" class="titlePage">📚FPT COURSE</a>
                
                <c:if test="${user.roleId == 1}">
                    <a href="${pageContext.request.contextPath}/UserManagement" class="nav-link push-right">Manage</a>
                </c:if>
                
                <a href="${pageContext.request.contextPath}/profile" 
                   class="nav-link <c:if test='${user.roleId != 1}'>push-right</c:if>">
                    ${user.fullName}
                </a>
            </div>
        </header>
        
        <main>  
            <h2>Thông tin cá nhân</h2>

            <form action="${pageContext.request.contextPath}/profile" method="post" class="profile-form">

                <input type="hidden" name="accountId" value="${user.accountId}" />

                <div class="field">
                    <label for="username">Tên đăng nhập:</label>
                    <input id="username" type="text" name="username" value="${user.username}">
                </div>

                <div class="field">
                    <label for="password">Mật khẩu mới:</label>
                    <input id="password" type="password" name="password" 
                           placeholder="Bỏ trống nếu không muốn thay đổi">
                </div>

                <div class="field">
                    <label for="fullName">Họ và tên:</label>
                    <input id="fullName" type="text" name="fullName" value="${user.fullName}">
                </div>

                <div class="field">
                    <label for="email">Email:</label>
                    <input id="email" type="email" name="email" value="${user.email}">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-submit">Cập nhật thông tin</button>
                </div>
            </form>
        </main>
        
        <footer>
            © 2025 FPT COURSE. Mọi quyền được bảo lưu.
        </footer>

    </body>
</html>