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

                <a href="${pageContext.request.contextPath}/UserManagement" class="nav-link push-right">Manage</a>

                <a href="${pageContext.request.contextPath}/profile" class="nav-link">${user.fullName}</a>
            </div>
        </div>
        <div class="category-item">
            <ul>
                <li><a href="${pageContext.request.contextPath}/home" class="btn">Tất cả</a></li>

                <c:forEach var="category" items="${categoryList}">
                    <li><a href="${pageContext.request.contextPath}/Category?id=${category.id}" class="btn">${category.name}</a></li>
                    </c:forEach>
            </ul>

        </div>
    </header>

    <h2>Khóa học nổi bật</h2>

    <div class="grid">
        <c:forEach var="c" items="${courseList}">
            <div class="card">
                <img src="${c.image}" alt="${c.title}" />
                <div class="title">${c.title}</div>
                <div class="desc">${c.description}</div>
                <div class="price">${c.price}₫</div>
                <a href="${pageContext.request.contextPath}/description?id=${c.courseId}" class="btn">Xem chi tiết</a>
            </div>
        </c:forEach>
    </div>
    <footer>
        © 2025 FPT COURSE. Mọi quyền được bảo lưu.
    </footer>

</body>
</html>
