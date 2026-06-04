<%-- 
    Document   : addStudent
    Created on : Jun 2, 2026, 10:27:59 PM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm sinh viên mới</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
                font-family: 'Inter', sans-serif;
            }

            body {
                background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 50%, #f0f9ff 100%);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 20px;
            }

            .card {
                background: rgba(255, 255, 255, 0.98);
                padding: 40px;
                border-radius: 24px;
                box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
                width: 100%;
                max-width: 450px;
            }

            h2 {
                font-size: 24px;
                font-weight: 700;
                color: #111827;
                margin-bottom: 24px;
                text-align: center;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                display: block;
                font-size: 14px;
                font-weight: 600;
                color: #374151;
                margin-bottom: 8px;
            }

            input {
                width: 100%;
                padding: 12px 16px;
                border: 1px solid #e5e7eb;
                border-radius: 12px;
                font-size: 15px;
                transition: all 0.2s;
                background-color: #f9fafb;
                color: #111827;
            }

            input:focus {
                outline: none;
                border-color: #3b82f6;
                background-color: white;
                box-shadow: 0 0 0 4px rgba(59, 130, 246, 0.1);
            }

            .button-group {
                display: flex;
                gap: 16px;
                margin-top: 32px;
            }

            .btn {
                flex: 1;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                padding: 14px;
                border-radius: 12px;
                font-size: 15px;
                font-weight: 600;
                text-decoration: none;
                transition: all 0.2s;
                cursor: pointer;
            }

            .btn-black {
                background-color: #1f2937;
                color: white;
                border: none;
            }

            .btn-black:hover {
                background-color: #111827;
            }

            .btn-white {
                background-color: white;
                color: #1f2937;
                border: 1px solid #e5e7eb;
            }

            .btn-white:hover {
                background-color: #f9fafb;
                border-color: #d1d5db;
            }

            /* Alert Styles */
            .alert {
                padding: 14px 20px;
                border-radius: 12px;
                font-size: 14px;
                font-weight: 500;
                margin-bottom: 24px;
                line-height: 1.5;
                text-align: left;
            }

            .alert-error {
                background-color: #fee2e2;
                color: #991b1b;
                border: 1px solid #fca5a5;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <h2>Thêm sinh viên</h2>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="alert alert-error">
                    <%= error %>
                </div>
            <%
                }
            %>
            
            <form action="listStudent" method="GET">
                <div class="form-group">
                    <label for="id">Mã sinh viên (ID)</label>
                    <input type="number" name="id" id="id" min="0" required placeholder="Nhập mã số sinh viên"
                           value="<%= request.getParameter("id") != null ? request.getParameter("id").replace("\"", "&quot;") : "" %>">
                </div>

                <div class="form-group">
                    <label for="name">Họ và tên</label>
                    <input type="text" name="name" id="name" required placeholder="Nhập họ và tên đầy đủ"
                           value="<%= request.getParameter("name") != null ? request.getParameter("name").replace("\"", "&quot;") : "" %>">
                </div>

                <div class="form-group">
                    <label for="gpa">Điểm trung bình (GPA)</label>
                    <input type="number" name="gpa" id="gpa" min="0" max="4" step="0.01" required placeholder="Nhập GPA (ví dụ: 3.5)"
                           value="<%= request.getParameter("gpa") != null ? request.getParameter("gpa").replace("\"", "&quot;") : "" %>">
                </div>
                
                <div class="button-group">
                    <a href="listStudent" class="btn btn-white">Hủy bỏ</a>
                    <button type="submit" value="add" name="action" class="btn btn-black">Xác nhận</button>
                </div>
            </form>
        </div>
    </body>
</html>
