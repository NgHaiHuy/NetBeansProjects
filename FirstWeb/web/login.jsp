<%-- 
    Document   : loginJSP
    Created on : May 20, 2026, 8:11:12 AM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login JSP Page</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

        <style>
            /* CSS Reset */
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
                font-family: 'Inter', sans-serif;
            }

            /* Làm nền gradient xanh */
            body {
                background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 50%, #f0f9ff 100%);
                min-height: 100vh;
                display: flex;
                flex-direction: column; /* Đổi sang column để kết quả in ra nằm phía dưới hợp lý */
                align-items: center;
                justify-content: center;
            }

            /* Thẻ form chính màu trắng */
            .login-container {
                background: rgba(255, 255, 255, 0.98);
                padding: 40px;
                border-radius: 24px;
                box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
                width: 100%;
                max-width: 400px;
                text-align: center;
                margin-bottom: 20px;
            }

            h2 {
                font-size: 24px;
                font-weight: 600;
                color: #111827;
                margin-bottom: 24px; /* Tăng khoảng cách xuống dưới */
            }

            /* Nhóm ô nhập liệu (Input) */
            .input-group {
                position: relative;
                margin-bottom: 16px;
            }

            .input-icon {
                position: absolute;
                left: 16px;
                top: 50%;
                transform: translateY(-50%);
                color: #9ca3af;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 14px 16px 14px 44px;
                border: none;
                border-radius: 12px;
                background-color: #f3f4f6;
                font-size: 15px;
                color: #1f2937;
                outline: none;
                transition: box-shadow 0.2s;
            }

            input:focus {
                box-shadow: 0 0 0 2px #e5e7eb;
            }

            /* Nút Đăng nhập chính */
            .btn-primary {
                width: 100%;
                padding: 14px;
                background-color: #1f2937;
                color: white;
                border: none;
                border-radius: 12px;
                font-size: 16px;
                font-weight: 500;
                cursor: pointer;
                transition: background-color 0.2s;
                margin-top: 10px;
            }

            .btn-primary:hover {
                background-color: #111827;
            }

            /* Thêm CSS cho kết quả hiển thị */
            .result-box {
                font-weight: 600;
                font-size: 16px;
                padding: 10px 20px;
                border-radius: 8px;
            }
            .success {
                color: #16a34a;
                background-color: #dcfce7;
            }
            .danger {
                color: #dc2626;
                background-color: #fee2e2;
            }

            .btn-home {
                margin-top: 20px;
                display: block;
                text-decoration: none;
                color: #6b7280;
                font-size: 14px;
            }

            .btn-home:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Sign in</h2>

            <!-- Đã thêm action="" (gửi lại chính nó) và method="post" -->
            <form action="" method="post">

                <div class="input-group">
                    <i class="fa-solid fa-user input-icon"></i>
                    <input type="text" name="Username" placeholder="User name" required>
                </div>

                <div class="input-group">
                    <i class="fa-solid fa-lock input-icon"></i>
                    <input type="password" name="Password" placeholder="Password" required>
                </div>

                <button type="submit" class="btn-primary">Login</button>

                <a href="index.html" class="btn-home">Back to Home</a>
            </form>
        </div>

        <%
            // Kiểm tra nếu request gửi lên bằng phương thức POST (tức là khi bấm nút Login)
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                request.setCharacterEncoding("UTF-8");
                
                String user = request.getParameter("Username");
                String pass = request.getParameter("Password");
                
                // Đã sửa thành .equals() và thêm dấu chấm phẩy đầy đủ
                if ("admin".equals(user) && "123".equals(pass)) {
                    out.print("<div class='result-box success'>Hello " + user + "</div>");
                } else {
                    out.print("<div class='result-box danger'>Wrong user name or password!</div>");
                }
            }
        %>

    </body>
</html>
