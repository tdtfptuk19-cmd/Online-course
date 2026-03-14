<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>EduLearn - Trang chủ</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">  

    </head>
    <body>

        <header>
            <div class="top-row">
                <a href="${pageContext.request.contextPath}/home" class="titlePage">📚FPT COURSE</a>
                <a href="${pageContext.request.contextPath}/profile" class="User">${user.fullName}</a>
            </div>
            <div class="category-item">
                <ul>
                    <c:forEach var="category" items="${categoryList}">
                        <li>${category.name}</li>
                        </c:forEach>
                </ul>

            </div>
        </header>
        <main>
            <div class="course-detail">
                <table border="1" style="margin: auto;;">
                    <tr><th>ID</th><td>${course.courseId}</td></tr>
                    <tr><th>Tên</th><td>${course.title}</td></tr>
                    <tr><th>Mô tả</th><td>${course.description}</td></tr>
                    <tr><th>Giá</th><td>${course.price}VND</td></tr>
                </table>
            </div>
            <!-- Nút đăng ký hoặc đã đăng ký -->
            <div style="margin-top:20px; text-align:center;">
                <c:choose>
                    <c:when test="${enrolled}">
                        <button disabled style="background:gray; color:white; padding:8px 16px; border:none; border-radius:6px;">
                            ĐÃ ĐĂNG KÝ KHÓA HỌC
                        </button>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/enroll" method="post" style="display:inline-block;">
                            <input type="hidden" name="id" value="${course.courseId}">
                            <button type="submit" style="background:green; color:white; padding:8px 16px; border:none; border-radius:6px;">
                                Đăng ký khóa học
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
        <footer>
            © 2025 FPT COURSE. Mọi quyền được bảo lưu.
        </footer>

    </body>
</html>
