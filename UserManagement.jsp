<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>EduLearn - Trang chủ</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">  
        <style>
            /* Tổng thể trang */
            body {
                background-color: #f4f7f6; /* Màu nền xám rất nhạt */
            }

            /* Bố cục 3 cột */
            .canvas {
                max-width: 1400px; /* Rộng hơn cho dễ nhìn */
                margin: 28px auto;
                display: flex;
                gap: 30px; /* Khoảng cách giữa các cột */
                align-items: flex-start;
                padding: 0 20px; /* Thêm đệm 2 bên */
            }

            /* Định dạng chung cho các khung "List" và "Information" */
            .panel {
                background: #ffffff;
                box-shadow: 0 4px 12px rgba(0,0,0,0.05);
            }

            .panel .head {
                padding: 14px 20px;
                border-bottom: 1px solid #e0e0e0;
                font-weight: 600;
                font-size: 16px;
                text-align: left; /* Căn trái tiêu đề */
                background-color: #fcfcfc; /* Nền tiêu đề hơi xám */
            }

            /* Cột 1: Danh sách */
            .list {
                flex: 3; /* Chiếm nhiều không gian nhất */
            }

            .list table {
                border-collapse: collapse;
                width: 100%;
            }

            .list th, .list td {
                border-top: 1px solid #eee; /* Viền mỏng hơn */
                padding: 12px 20px; /* Thêm đệm */
                text-align: left;
            }

            .list th {
                background-color: #fcfcfc; /* Nền header bảng */
                font-weight: 700;
            }
            .list .body {
                overflow-x: auto; /* Cho phép cuộn ngang khi cần */
            }
            /* Thêm hiệu ứng khi di chuột vào hàng */
            .list tbody tr:hover {
                background-color: #f5f5f5;
            }

            /* Style cho link "Select" */
            .list td a[href*='UserManagement?id='] {
                text-decoration: none;
                color: #0b76d1; /* Màu xanh chủ đạo */
                font-weight: 600;
            }
            .list td a[href*='UserManagement?id=']:hover {
                text-decoration: underline;
            }


            /* Cột 2: Các nút hành động */
            .actions {
                flex: 1; /* Cột này nhỏ nhất */
                display: flex;
                flex-direction: column;
                align-items: stretch; /* Kéo dãn các nút cho bằng nhau */
                gap: 15px;
                /* Xóa bỏ CSS cũ, các nút sẽ được style ở dưới */
            }

            /* Cột 3: Form thông tin */
            .form {
                flex: 2; /* Cột này lớn vừa */
            }

            .form .body {
                padding: 20px;
            }

            .field {
                margin-bottom: 15px; /* Tăng khoảng cách */
            }

            label {
                display: block;
                font-weight: 600;
                margin-bottom: 6px;
            }

            /* Style cho các ô input */
            input[type="text"],
            input[type="password"],
            input[type="email"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 6px;
                box-sizing: border-box; /* Quan trọng */
            }

            /* --- PHẦN QUAN TRỌNG: STYLE CÁC NÚT --- */

            /* Nút chung */
            .btn {
                display: inline-block;
                padding: 10px 20px;
                border: none;
                border-radius: 6px;
                color: #ffffff; /* Chữ trắng */
                font-weight: 600;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.2s ease;
                text-decoration: none; /* Cho thẻ <a> */
                text-align: center;
            }

            /* Nút màu xanh lá (Tạo mới) */
            .btn-success {
                background-color: #28a745;
            }
            .btn-success:hover {
                background-color: #218838;
            }

            /* Nút màu xanh dương (Cập nhật/Lưu) */
            .btn-primary {
                background-color: #0b76d1;
            }
            .btn-primary:hover {
                background-color: #085a9f;
            }

            /* Nút màu đỏ (Xóa) */
            .btn-danger {
                background-color: #dc3545;
            }
            .btn-danger:hover {
                background-color: #c82333;
            }

            /* Nút màu xám (Clear) */
            .btn-secondary {
                background-color: #6c757d;
            }
            .btn-secondary:hover {
                background-color: #5a6268;
            }

            /* Nút khi bị vô hiệu hóa (disabled) */
            .btn:disabled {
                background-color: #e9ecef;
                color: #6c757d;
                cursor: not-allowed;
                opacity: 1; /* Ghi đè opacity cũ */
            }
            /* Thêm đoạn này vào khối <style> */

            /* Style cho hàng đang được chọn (active) */
            .list tbody tr.active {
                background-color: #e6f7ff;
                font-weight: 600;
            }
            .list tbody tr.active:hover {
                background-color: #d1f0ff;
            }
            /* CSS cho nút Select nhỏ trong bảng */
            .btn-small {
                padding: 4px 10px;
                font-size: 12px;
                text-align: center;
                text-decoration: none;
                color: white;
                background-color: #0b76d1; /* Màu xanh dương */
                border-radius: 4px;
                display: inline-block;
                transition: background-color 0.2s;
            }
            .btn-small:hover {
                background-color: #085a9f; /* Màu xanh đậm hơn khi hover */
                color: white;
                text-decoration: none;
            }

        </style>
    </head>
    <body>
        <header>
            <div class="top-row">
                <a href="${pageContext.request.contextPath}/home" class="titlePage">📚FPT COURSE</a>
            </div>
        </header>
        <main>
            <div class="canvas">

                <!-- LEFT: LIST -->
                <div class="panel list">
                    <div class="head">List</div>
                    <div class="body">
                        <div style="font-size:12px;margin-bottom:8px">Users loaded: ${fn:length(users)}</div>

                        <table>
                            <thead>
                                <tr>
                                    <th>accountId</th>
                                    <th>username</th>
                                    <th>fullname</th>
                                    <th>email</th>
                                    <th>Role</th>
                                    <th>Action</th> 
                                </tr>

                            </thead>
                            <tbody>
                                <c:forEach var="u" items="${users}">
                                    <tr <c:if test="${selected != null && u.accountId == selected.accountId}">class="active"</c:if>>

                                            <td>${u.accountId}</td>
                                        <td>${u.username}</td>
                                        <td>${u.fullName}</td>
                                        <td>${u.email}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${u.roleId == 1}">Admin</c:when>
                                                <c:when test="${u.roleId == 2}">Staff</c:when>
                                                <c:when test="${u.roleId == 3}">User</c:when>
                                            </c:choose>
                                        </td>

                                        <td>
                                            <a class="btn-small" href="${pageContext.request.contextPath}/UserManagement?id=${u.accountId}">Select</a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty users}">
                                    <tr><td colspan="6">No data</td></tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- CENTER: BUTTONS -->
                <div class="actions">
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/UserManagement">Create New User</a>

                    <form method="post" action="${pageContext.request.contextPath}/UserManagement" style="width:100%;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${selected != null ? selected.accountId : ''}">
                        <button class="btn btn-danger" type="submit" <c:if test="${selected == null}">disabled</c:if> style="width:100%;">Delete Selected</button>
                        </form>

                        <button class="btn btn-primary" type="submit" form="infoForm" <c:if test="${mode ne 'update'}">disabled</c:if>>Update Selected</button>

                    </div>

                    <!-- RIGHT: INFORMATION (Create/Update form) -->
                    <div class="panel form">
                        <div class="head">Information</div>
                        <div class="body">
                            <form id="infoForm" method="post" action="${pageContext.request.contextPath}/UserManagement">
                            <input type="hidden" name="action" value="${mode eq 'update' ? 'update' : 'create'}"/>
                            <c:if test="${mode eq 'update'}">
                                <input type="hidden" name="account_id" value="${selected.accountId}"/>
                            </c:if>

                            <div class="field">
                                <label>Username</label>
                                <input name="username" required value="${mode eq 'update' ? selected.username : ''}">
                            </div>

                            <div class="field">
                                <label>Password</label>
                                <input type="password" name="password" 
                                       placeholder="${mode eq 'update' ? 'Bỏ trống nếu không đổi' : 'Nhập mật khẩu'}" 
                                       <c:if test="${mode ne 'update'}">required</c:if>>
                                </div>

                                <div class="field">
                                    <label>Fullname</label>
                                    <input name="fullname" value="${mode eq 'update' ? selected.fullName : ''}">
                            </div>

                            <div class="field">
                                <label>Email</label>
                                <input type="email" name="email" value="${mode eq 'update' ? selected.email : ''}">
                            </div>

                            <div class="row-end" style="margin-top: 20px; text-align: right;">
                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/UserManagement">Clear</a>

                                <button class="btn btn-primary" type="submit">
                                    ${mode eq 'update' ? 'Save Changes' : 'Create User'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </main>
        <footer>
            © 2025 FPT COURSE. Mọi quyền được bảo lưu.
        </footer>

    </body>
</html>
